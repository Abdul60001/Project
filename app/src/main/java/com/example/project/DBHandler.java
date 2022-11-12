package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import org.jetbrains.annotations.Nullable;

public class DBHandler extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "AppDatabase";
    private static final int DATABASE_VERSION = 1;

    //users table variables
    private static final String USERS_TABLE_NAME = "Users";
    private static final String USERS_ID = "id";
    private static final String USERS_USERNAME = "username";
    private static final String USERS_PASSWORD = "password";
    private static final String USERS_USERTYPE = "userType";

    //courses table variables
    private static final String COURSES_TABLE_NAME = "Courses";
    private static final String COURSES_ID = "id";
    private static final String COURSES_CODE = "code";
    private static final String COURSES_NAME = "name";
    private static final String COURSES_INSTRUCTOR_ID = "instructorId";
    private static final String COURSES_DESCRIPTION = "description";
    private static final String COURSES_DAY = "day";
    private static final String COURSES_HOURS = "hours";
    private static final String COURSES_CAPACITY = "capacity";

    //Course Information for instructor
    private static final String TABLE_NAME="course_Information";
    private static final String COLUMN_ID="ID";
    private static final String COLUMN_courseDay="Cours Days";
    private static final String COLUMN_courseHour="Cours Hours";
    private static final String COLUMN_courseCapacity="Course Capacity";
    private static final String COLUMN_courseDescription="Course Description";



    public DBHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_users_table = "CREATE TABLE " + USERS_TABLE_NAME + " (" +
                USERS_ID + " INTEGER PRIMARY KEY, " +
                USERS_USERNAME + " TEXT UNIQUE, " +
                USERS_PASSWORD + " TEXT, " +
                USERS_USERTYPE + " TEXT" +
                ");";

        String create_courses_table = "CREATE TABLE " + COURSES_TABLE_NAME + " (" +
                COURSES_ID + " INTEGER PRIMARY KEY, " +
                COURSES_CODE + " TEXT, " +
                COURSES_NAME + " TEXT, " +
                COURSES_INSTRUCTOR_ID + " INTEGER, " +
                COURSES_DESCRIPTION + " TEXT, " +
                COURSES_DAY + " TEXT, " +
                COURSES_HOURS + " TEXT, " +
                COURSES_CAPACITY + " INTEGER, " +
                "FOREIGN KEY (" + COURSES_INSTRUCTOR_ID + ") REFERENCES " + USERS_TABLE_NAME + "(" + USERS_ID + ")" +
                ");";

        String courseInfo="CREATE TABLE "+TABLE_NAME+  // Store course day, hour , capacity, description by instructor.
                      "("+COLUMN_ID+"INTEGER PRIMARY KEY AUTOINCREMENT,"+
                       COLUMN_courseDay+"TEXT, "+
                       COLUMN_courseHour+"TEXT,"+
                       COLUMN_courseCapacity+"INTEGER,"+
                       COLUMN_courseDescription+"TEXT);";




        db.execSQL(create_users_table);
        db.execSQL(create_courses_table);
        db.execSQL(courseInfo);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + COURSES_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Cursor getUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + USERS_TABLE_NAME;
        return db.rawQuery(query, null);
    }

    public Cursor getUsersById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + USERS_TABLE_NAME + " WHERE " + USERS_ID + "=" + id;
        return db.rawQuery(query, null);
    }

    public Cursor getCourses() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + COURSES_TABLE_NAME;
        return db.rawQuery(query, null);
    }

    public Cursor getCoursesByInstructorId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + COURSES_TABLE_NAME + " WHERE " + COURSES_INSTRUCTOR_ID + "=" + id;
        return db.rawQuery(query, null);
    }

    public Cursor getCoursesByCodeAndName(String code, String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + COURSES_TABLE_NAME + " WHERE " + COURSES_CODE + " LIKE " + code + " AND " + COURSES_NAME + " LIKE " + name;

        if(code.equals("") && !name.equals("")) {
            query = "SELECT * FROM " + COURSES_TABLE_NAME + " WHERE " + COURSES_NAME + " LIKE " + name;
        }
        else if(!code.equals("") && name.equals("")) {
            query = "SELECT * FROM " + COURSES_TABLE_NAME + " WHERE " + COURSES_CODE + " LIKE " + code;
        }
        
        return db.rawQuery(query, null);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(USERS_USERNAME, user.getUsername());
        values.put(USERS_PASSWORD, user.getPassword());
        values.put(USERS_USERTYPE, user.getUserType());

        db.insert(USERS_TABLE_NAME, null, values);
        db.close();
    }

    public void addCourse(Course course) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COURSES_CODE, course.getCode());
        values.put(COURSES_NAME, course.getName());
        values.put(COURSES_INSTRUCTOR_ID, course.getInstructorId());
        values.put(COURSES_DESCRIPTION, course.getDescription());
        values.put(COURSES_DAY, course.getCourseDay());
        values.put(COURSES_HOURS, course.getCourseHours());
        values.put(COURSES_CAPACITY, course.getCapacity());

        db.insert(COURSES_TABLE_NAME, null, values);
        db.close();
    }

//    public void assignInstructorToCourse(User instructor, Course course) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//
//        values.put(COURSES_ID, course.getId());
//        values.put(COURSES_CODE, course.getCode());
//        values.put(COURSES_NAME, course.getName());
//        values.put(COURSES_INSTRUCTOR_ID, instructor.getId());
//        values.put(COURSES_DESCRIPTION, course.getDescription());
//        values.put(COURSES_DAY, course.getCourseDay());
//        values.put(COURSES_HOURS, course.getCourseHours());
//        values.put(COURSES_CAPACITY, course.getCapacity());
//
//        db.update(COURSES_TABLE_NAME, values, COURSES_ID + "=" + course.getId(), null);
//    }


    public void addCourseINFO(String courseDay,String courseHour,int courseCapacity,String courseDescription){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_courseDay,courseDay);
        values.put(COLUMN_courseHour,courseHour);
        values.put(COLUMN_courseCapacity,courseCapacity);
        values.put(COLUMN_courseDescription,courseDescription);
        long result=db.insert(TABLE_NAME,null,values);
        if (result==0){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Added Successfully! ", Toast.LENGTH_SHORT).show();
        }

    }

    public void editCourseINFO(String courseDay,String courseHour,int courseCapacity,String courseDescription){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_courseDay,courseDay);
        values.put(COLUMN_courseHour,courseHour);
        values.put(COLUMN_courseCapacity,courseCapacity);
        values.put(COLUMN_courseDescription,courseDescription);
        long result=db.insert(TABLE_NAME,null,values);
        if (result==0){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Changed Successfully! ", Toast.LENGTH_SHORT).show();
        }

    }




    public void deleteUserById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(USERS_TABLE_NAME, USERS_ID + "=" + id, null);
    }


    public void deleteCourseById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(COURSES_TABLE_NAME, COURSES_ID + "=" + id, null);
    }

    public void updateCourseById(int id, Course newCourse) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COURSES_ID, id);
        values.put(COURSES_CODE, newCourse.getCode());
        values.put(COURSES_NAME, newCourse.getName());
        values.put(COURSES_INSTRUCTOR_ID, newCourse.getInstructorId());
        values.put(COURSES_DESCRIPTION, newCourse.getDescription());
        values.put(COURSES_DAY, newCourse.getCourseDay());
        values.put(COURSES_HOURS, newCourse.getCourseHours());
        values.put(COURSES_CAPACITY, newCourse.getCapacity());

        db.update(COURSES_TABLE_NAME, values, COURSES_ID + "=" + id, null);
    }
}
