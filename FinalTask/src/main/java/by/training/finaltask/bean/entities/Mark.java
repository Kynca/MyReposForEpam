package by.training.finaltask.bean.entities;


import by.training.finaltask.bean.Entity;

import java.util.Objects;

public class Mark extends Entity {
    private String subjectName;
    private Double rate;
    private String date;
    private Integer studentId;

    public Mark(){}

    public Mark(String subjectName, Double rate, String date, Integer studentId){
        this.subjectName = subjectName;
        this.rate = rate;
        this.date = date;
        this.studentId = studentId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mark mark = (Mark) o;
        return subjectName.equals(mark.subjectName) && Objects.equals(rate, mark.rate) && Objects.equals(date, mark.date) && studentId.equals(mark.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjectName, rate, date, studentId);
    }

    @Override
    public String toString() {
        return "Mark{" +
                "subjectName='" + subjectName + '\'' +
                ", rate=" + rate +
                ", date='" + date + '\'' +
                ", studentId=" + studentId +
                '}';
    }
}
