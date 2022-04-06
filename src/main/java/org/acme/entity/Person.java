package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "person")
public class Person extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    public String name;
    public String lastName;
    public char genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id")
    public Apartment apartment;


    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", genre=" + genre +
                ", Apartment= {apartmentId=" + (apartment != null ? apartment.id : -1) +
                ", homeId=" + (apartment != null && apartment.home != null ? apartment.home.id : -1) + "}" +
                '}';
    }
}
