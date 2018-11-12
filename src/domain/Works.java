package domain;

import java.io.ByteArrayInputStream;

public class Works extends NamedEntity{
    public int junior;
    public ByteArrayInputStream data;
    public int Raiting;

    public int getJunior() {
        return junior;
    }

    public ByteArrayInputStream getData() {
        return data;
    }

    public int getRaiting() {
        return Raiting;
    }
}