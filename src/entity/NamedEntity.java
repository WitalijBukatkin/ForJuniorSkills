package entity;

public abstract class NamedEntity extends BaseEntity{
    public String name;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}