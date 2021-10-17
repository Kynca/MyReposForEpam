package by.training.finaltask.bean.entities;

import by.training.finaltask.bean.Entity;

public class Dean extends Entity {
    private String address;
    private String faculty;
    private Long phoneNumber;
    private Integer universityId;
    private String universityName;

    public Dean(String address, String faculty, Long phoneNumber, Integer universityId) {
        this.address = address;
        this.faculty = faculty;
        this.phoneNumber = phoneNumber;
        this.universityId = universityId;
    }

    public Dean() {
    }

    public Dean(Integer id, String faculty, String address, Long phoneNumber, Integer universityId) {
        super(id);
        this.address = address;
        this.faculty = faculty;
        this.phoneNumber = phoneNumber;
        this.universityId = universityId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Integer universityId) {
        this.universityId = universityId;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    @Override
    public String toString() {
        return "Dean{" +
                "address='" + address + '\'' +
                ", faculty='" + faculty + '\'' +
                ", phoneNum=" + phoneNumber +
                ", university_id=" + universityId +
                ", universityName='" + universityName + '\'' +
                '}';
    }
}
