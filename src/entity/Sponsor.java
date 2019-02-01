package entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Sponsor extends NamedEntity{
    private byte[] logo;
    private String description;

    @ManyToMany(mappedBy = "sponsors")
    private Set<Junior> juniors=new HashSet<>();

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Junior> getJuniors() {
        return juniors;
    }

    public void setJuniors(Set<Junior> juniors) {
        this.juniors = juniors;
    }
}