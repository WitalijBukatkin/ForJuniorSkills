package dao;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import util.Connector;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

public class Query<T> {
    private Class type;
    private String query;
    private ResultSetMetaData metaData;

    public Query(Class type) {
        this(type, "SELECT * FROM " + type.getSimpleName());
    }

    public Query(Class type, String query) {
        this.type = type;
        this.query = query;
    }

    public List<T> getAll() throws Exception{
        List<T> table=new ArrayList<>();
        try (ResultSet rs = Connector.getConnection().createStatement().executeQuery(query)) {
            metaData = rs.getMetaData();
            while (rs.next()) {
                Object row = type.newInstance();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    Field field=row.getClass().getField(metaData.getColumnName(i));
                    if(field.getType().getSimpleName().equals("Image"))
                        field.set(row, SwingFXUtils.toFXImage(ImageIO.read(rs.getBinaryStream(i)), null));
                    else
                        field.set(row, rs.getObject(i));
                }
                table.add((T) row);
            }
        }
        return table;
    }

    public T get(int index) throws Exception{
        return getAll().get(index);
    }

    public Stream<T> getStream() throws Exception{
        return getAll().stream();
    }

    public int insert(T table) throws Exception {
        Statement statement=Connector.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        try(ResultSet resultSet=statement.executeQuery(query)) {
            resultSet.moveToInsertRow();
            for (Field field : table.getClass().getFields()) {
                if (field.get(table) instanceof Image) {
                    BufferedImage bufferedImage = SwingFXUtils.fromFXImage((Image) field.get(table), null);
                    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                        ImageIO.write(bufferedImage, "jpg", outputStream);
                        resultSet.updateObject(field.getName(), new ByteArrayInputStream(outputStream.toByteArray()));
                    }
                } else {
                    resultSet.updateObject(field.getName(), field.get(table));
                }
            }
            resultSet.insertRow();
            //getting generated id
            resultSet.last();
            return resultSet.getInt("id");
        }
    }

    public List<TableColumn<T, Object>> getColumns() throws Exception {
        List<TableColumn<T, Object>> list = new ArrayList<>();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            TableColumn<T, Object> column = new TableColumn<>(metaData.getColumnName(i));
            column.setCellValueFactory(new PropertyValueFactory<>(metaData.getColumnName(i)));
            list.add(column);
        }
        return list;
    }
}