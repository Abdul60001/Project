package com.example.project;

import android.text.Editable;

import java.io.Serializable;

public class Course implements Serializable {
    private int id;
    private String code;
    private String name;
    private int instructorId;
    private String description;
    private String courseDays;
    private String courseHours;
    private int capacity;




    public Course(String courseDayInput, String courseHourInput, String courseDescriptionInput, Integer courseStudentCapacityInput) {
    }

    public Course(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Course(int id, String code, String name, int instructorId, String description, int capacity) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.instructorId = instructorId;
        this.description = description;
        this.capacity = capacity;
    }


    public Course(String code, String name,String courseDays, String courseHours, int capacity,String description) {
        this.code=code;
        this.code=name;
        this.courseDays = courseDays;
        this.courseHours = courseHours;
        this.capacity = capacity;
        this.description=description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCourseDays() {
        return courseDays;
    }

    public void setCourseDays(String courseDays) {
        this.courseDays = courseDays;
    }

    public String getCourseHours() {
        return courseHours;
    }

    public void setCourseHours(String courseHours) {
        this.courseHours = courseHours;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String toString() {
        return "Id: " + id +
                "\nCode: " + code +
                "\nName: " + name +
                "\nInstructorId: " + instructorId +
                "\nDescription: " + description +
                "\nCapacity: " + capacity + "\n";
    }
    public String toStringS(){
        return "Course Days:  "+courseDays+
                "\nCourse Hours: "+courseHours+
                "\nCourse Description: "+description+
                "\nCourse Capacity: " + capacity+ "\n";
    }
}
