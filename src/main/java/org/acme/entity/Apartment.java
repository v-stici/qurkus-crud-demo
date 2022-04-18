package org.acme.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "apartment")
@NoArgsConstructor
public class Apartment extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    public int size; //square meters
    public int rooms;
    public String phone;
    public boolean haveInternet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_id")
    public Home home;

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    public List<Person> locatari = new ArrayList<>();

    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + id +
                ", size=" + size +
                ", rooms=" + rooms +
                ", phone='" + phone + '\'' +
                ", haveInternet=" + haveInternet +
                ", Home= {homeId = " + home.id + "}" +
                ", locatari=" + locatari.size() +
                '}';
    }
}
