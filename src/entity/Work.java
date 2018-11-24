package entity;

import java.io.ByteArrayInputStream;

public class Work extends NamedEntity{
    public Integer junior;
    public ByteArrayInputStream data;
    public Integer Raiting;

    public Integer getJunior() {
        return junior;
    }

    public ByteArrayInputStream getData() {
        return data;
    }

    public Integer getRaiting() {
        return Raiting;
    }
}