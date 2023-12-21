package com.ibrawin.spring5web.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String addressLine;
    private String city;
    private String county;
    private String postCode;

    @OneToMany(mappedBy = "publisher")
    private Set<Book> books = new HashSet<>();

    public Publisher() {
    }

    public Publisher(String firstName, String lastName, String address, String city, String county, String postCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressLine = address;
        this.city = city;
        this.county = county;
        this.postCode = postCode;
    }

}
