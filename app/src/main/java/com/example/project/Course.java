package com.example.project;

import java.io.Serializable;

public class Course implements Serializable {
    private int id;
    private String code;
    private String name;
    private int instructorId;
    private String description;
    private String courseDay;
    private String courseHours;
    private int capacity;

    //Used to create a course class based on date received from the database
    public Course(int id, String code, String name, int instructorId, String description, String courseDay, String courseHours, int capacity) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.instructorId = instructorId;
        this.description = description;
        this.courseDay = courseDay;
        this.courseHours = courseHours;
        this.capacity = capacity;
    }

    //Used to initialize a course to be added or updated in the database (no id is specified)
    public Course(String code, String name, int instructorId, String description, String courseDay, String courseHours, int capacity) {
        this.code = code;
        this.name = name;
        this.instructorId = instructorId;
        this.description = description;
        this.courseDay = courseDay;
        this.courseHours = courseHours;
        this.capacity = capacity;
    }

    //Used to initialize a course to be added or updated in the database that includes only course code and name information
    public Course(String code, String name) {
        this.code = code;
        this.name = name;
        this.instructorId = -1;
        this.description = "";
        this.courseDay = "";
        this.courseHours = "";
        this.capacity = 0;
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

    public String getCourseDay() {
        return courseDay;
    }

    public void setCourseDay(String courseDay) {
        this.courseDay = courseDay;
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
                "\nDay: " + courseDay +
                "\nHours: " + courseHours +
                "\nCapacity: " + capacity + "\n";
    }

}
