package com.powerfitness.gymregrestapis.model;


import jakarta.persistence.*;

@Entity
@Table(name = "members")
public class GymMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile")
    private long mobile;

    @Column(name = "weight")
    private double weight;

    @Column(name = "height")
    private double height;

    @Column(name = "bmi")
    private double bmi;

    @Column(name = "bmiResult")
    private String bmiResult;

    @Column(name = "gender")
    private String gender;

    @Column(name = "requireTrainer")
    private String requireTrainer;

    @Column(name = "Package")
    private String Package;

    @Column(name = "important")
    private String[] important;

    @Column(name = "haveGymBefore")
    private String haveGymBefore;

    @Column(name = "enquiryDate")
    private String enquiryDate;


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }
    public String getBmiResult() {
        return bmiResult;
    }

    public void setBmiResult(String bmiResult) {
        this.bmiResult = bmiResult;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRequireTrainer() {
        return requireTrainer;
    }

    public void setRequireTrainer(String requireTrainer) {
        this.requireTrainer = requireTrainer;
    }

    public String getPackage() {
        return Package;
    }

    public void setPackage(String aPackage) {
        Package = aPackage;
    }

    public String[] getImportant() {
        return important;
    }

    public void setImportant(String[] important) {
        this.important = important;
    }

    public String getHaveGymBefore() {
        return haveGymBefore;
    }

    public void setHaveGymBefore(String haveGymBefore) {
        this.haveGymBefore = haveGymBefore;
    }

    public String getEnquiryDate() {
        return enquiryDate;
    }

    public void setEnquiryDate(String enquiryDate) {
        this.enquiryDate = enquiryDate;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public GymMember() {
    }

    public GymMember(String firstName, String lastName, String email, long mobile, double weight, double height, double bmi, String bmiResult, String gender, String requireTrainer, String aPackage, String[] important, String haveGymBefore, String enquiryDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
        this.weight = weight;
        this.height = height;
        this.bmi = bmi;
        this.bmiResult = bmiResult;
        this.gender = gender;
        this.requireTrainer = requireTrainer;
        Package = aPackage;
        this.important = important;
        this.haveGymBefore = haveGymBefore;
        this.enquiryDate = enquiryDate;
    }
}
