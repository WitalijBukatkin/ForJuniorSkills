package util;

import entity.BaseEntity;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
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
                        field.set(row, ImageArray.ByteToImage(rs.getBinaryStream(i)));
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

    public int insert(BaseEntity entity) throws Exception {
        try(ResultSet resultSet=Connector.getStatement().executeQuery(query)) {
            resultSet.moveToInsertRow();
            for (Field field : entity.getClass().getFields()) {
                if (field.get(entity) instanceof Image) {
                    resultSet.updateObject(field.getName(), ImageArray.ImageToByte(field.get(entity)));
                }
                else {
                    resultSet.updateObject(field.getName(), field.get(entity));
                }
            }
            resultSet.insertRow();
            //getting generated id
            resultSet.last();
            return resultSet.getInt("id");
        }
    }

    public void update(BaseEntity entity) throws Exception{
        try(ResultSet resultSet=Connector.getStatement().executeQuery(query+" WHERE id="+entity.id)) {
            if(resultSet.first()) {
                for (Field field : entity.getClass().getFields()) {
                    if (field.get(entity) instanceof Image) {
                        resultSet.updateObject(field.getName(), ImageArray.ImageToByte(field.get(entity)));
                    } else {
                        resultSet.updateObject(field.getName(), field.get(entity));
                    }
                }
                resultSet.updateRow();
            }
        }
    }

    public void delete(BaseEntity entity) throws Exception{
        try(ResultSet resultSet=Connector.getStatement().executeQuery(query+" WHERE id="+entity.id)) {
            if(resultSet.first()) {
                resultSet.deleteRow();
            }
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