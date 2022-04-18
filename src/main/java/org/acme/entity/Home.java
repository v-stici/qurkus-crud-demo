package org.acme.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name = "home", indexes = @Index(columnList = "address"))
@NoArgsConstructor
public class Home extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column(columnDefinition = "int default 2000")
    public int year;

    @Column(unique = true)
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

    //TODO: Refactor this shit
    public static List<HashMap<String, Object>> getHouses() {
        List<HashMap<String, Object>> houses = new ArrayList<>();
        for (PanacheEntityBase home : Home.listAll()) {
            houses.add(((Home) home).getHashHome());
        }
        return houses;
    }

    public HashMap<String, Object> getHashHome() {
        return new HashMap<>() {{
            put("id", id);
            put("year", year);
            put("address", address);
        }};
    }
}
