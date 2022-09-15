package com.example.Restaurant.Entitys;

import javax.persistence.*;
import java.util.Objects;
@Embeddable
public class Address {
    @Column(name = "address_zip")
    private String zip ;
    @Column(name = "address_public_place")
    private String publicPlace;
    @Column(name = "address_number")
    private String number;
    @Column(name = "address_complement")
    private String complement;
    @Column(name = "address_district")
    private String district;
    @ManyToOne
    @JoinColumn(name = "address_city_id ")
    private City city;


    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPublicPlace() {
        return publicPlace;
    }

    public void setPublicPlace(String publicPlace) {
        this.publicPlace = publicPlace;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address adress = (Address) o;
        return zip.equals(adress.zip) && publicPlace.equals(adress.publicPlace) && number.equals(adress.number) && complement.equals(adress.complement) && district.equals(adress.district) && city.equals(adress.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zip, publicPlace, number, complement, district, city);
    }

}
