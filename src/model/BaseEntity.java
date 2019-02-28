package model;

import javax.persistence.*;

@MappedSuperclass
@Access(AccessType.PROPERTY)
public abstract class BaseEntity {

    private Integer id;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Transient
    public boolean isNew(){
        return id == null;
    }
}