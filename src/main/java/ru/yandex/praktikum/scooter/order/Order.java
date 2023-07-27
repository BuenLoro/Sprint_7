package ru.yandex.praktikum.scooter.order;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String number;
    private String rentTime;
    private String deliveryDate;
    private String comment;
    private List<String> color = new ArrayList<String>();
    public Order(List<String> colour) {
        this.firstName = "Monica";
        this.lastName = "Belucci";
        this.address = "Aviation street, 17";
        this.metroStation = "Fili";
        this.number = "+79556754535";
        this.rentTime = "3";
        this.deliveryDate = "2023-07-26";
        this.comment = "Hasta la vista";
        this.color = color;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMetroStation() {
        return metroStation;
    }

    public void setMetroStation(String metroStation) {
        this.metroStation = metroStation;
    }

    public String getNumber() {
        return number;
    }

    public void setPhone(String number) {
        this.number = number;
    }

    public String getRentTime() {
        return rentTime;
    }

    public void setRentTime(String rentTime) {
        this.rentTime = rentTime;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }
}
