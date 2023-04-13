//package com.example.hive.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import jakarta.persistence.*;
//import lombok.*;
//import org.hibernate.Hibernate;
//
//import java.util.Objects;
//import java.util.UUID;
//
//@Getter
//@Setter
//@ToString
////@RequiredArgsConstructor
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Builder
//@JsonIgnoreProperties({"number", "country"})
//public class Address {
//   @Id
//   @GeneratedValue(strategy = GenerationType.AUTO)
//    private UUID address_id;
//    private Integer number;
//    private String street;
//    private String city;
//    private String state;
//    private String country;
//    @OneToOne
//    private User user;
//
//    public Address(String addressString) {
//        String[] parts = addressString.split(",\\s*");
//        this.street = parts[0];
//        this.city = parts[1];
//        this.state = parts[2];
////        this.zip = parts[3];
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        Address address = (Address) o;
//        return getAddress_id() != null && Objects.equals(getAddress_id(), address.getAddress_id());
//    }
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }
//}
