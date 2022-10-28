package com.example.project;

public class Course {
    private int id;
    private String code;
    private String name;
    private int instructorId;
    private String description;
    private int capacity;

    public Course() {
    }

    public Course(int id, String code, String name) {
        this.id = id;
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
}
