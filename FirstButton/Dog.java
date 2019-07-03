package com.elanco.elancoapps.FirstButton;

public class Dog {
    String dog_name;
    String dog_birthdate;
    String dog_imagepath;

    String one_medicine_name;
    String two_medicine_name;
    String one_medicine_date;
    String two_medicine_date;

    public String getOne_medicine_name() {
        return one_medicine_name;
    }

    public void setOne_medicine_name(String one_medicine_name) {
        this.one_medicine_name = one_medicine_name;
    }

    public String getTwo_medicine_name() {
        return two_medicine_name;
    }

    public void setTwo_medicine_name(String two_medicine_name) {
        this.two_medicine_name = two_medicine_name;
    }

    public String getOne_medicine_date() {
        return one_medicine_date;
    }

    public void setOne_medicine_date(String one_medicine_date) {
        this.one_medicine_date = one_medicine_date;
    }

    public String getTwo_medicine_date() {
        return two_medicine_date;
    }

    public void setTwo_medicine_date(String two_medicine_date) {
        this.two_medicine_date = two_medicine_date;
    }

    public Dog(String dog_name, String dog_birthdate, String dog_imagepath) {
        this.dog_name = dog_name;
        this.dog_birthdate = dog_birthdate;
        this.dog_imagepath = dog_imagepath;
    }

    public String getDog_name() {
        return dog_name;
    }

    public void setDog_name(String dog_name) {
        this.dog_name = dog_name;
    }

    public String getDog_birthdate() {
        return dog_birthdate;
    }

    public void setDog_birthdate(String dog_birthdate) {
        this.dog_birthdate = dog_birthdate;
    }

    public String getDog_imagepath() {
        return dog_imagepath;
    }

    public Dog() {
    }

    public void setDog_imagepath(String dog_imagepath) {
        this.dog_imagepath = dog_imagepath;
    }
}
