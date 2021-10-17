package by.training.finaltask.bean.entities;

import by.training.finaltask.bean.Entity;

import java.io.Serializable;
import java.util.Objects;

public class Student extends Entity{
    private String name;
    private String lastname;
    private String patronymic;
    private String date;
    private String mail;
    private Integer deanId;

    public Student(){}

    public Student(Integer id, String name, String lastname, String patronymic, String date, String mail, Integer deanId){
        super(id);
        this.name = name;
        this.lastname = lastname;
        this.patronymic = patronymic;
        this.date = date;
        this.mail = mail;
        this.deanId = deanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getDeanId() {
        return deanId;
    }

    public void setDeanId(Integer deanId) {
        this.deanId = deanId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return name.equals(student.name) && lastname.equals(student.lastname) && patronymic.equals(student.patronymic) && date.equals(student.date) && mail.equals(student.mail) && deanId.equals(student.deanId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastname, patronymic, date, mail, deanId);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", date='" + date + '\'' +
                ", mail='" + mail + '\'' +
                ", deanId=" + deanId +
                '}';
    }
}
