package com.app.jobApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AddressDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country",nullable = false)
    private String country;

    @Column(name = "city",nullable = false)
    private String city;

    @Column(name = "state",nullable = false)
    private String state;

    @Column(name = "other_details")
    private String otherDetails;

    @Column(name = "zipcode",nullable = false)
    private String zipCode;

    @Column(name = "type",nullable = false)
    private String type;

    @Column(name = "isDeleted")
    private Boolean isDeleted;

}
