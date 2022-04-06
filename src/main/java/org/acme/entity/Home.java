package org.acme.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;
import org.jboss.logging.Logger;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "home")
@NoArgsConstructor
public class Home extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    public int year;
    public String address;

    @OneToMany(mappedBy = "home", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    public List<Apartment> apartments = new ArrayList<>();


    public Home(int year, String address) {
        this.year = year;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Home{" +
                "id=" + id +
                ", year=" + year +
                ", address='" + address + '\'' +
                ", Apartment {" + "size='" + apartments.size() +
                "'}}";
    }
}
