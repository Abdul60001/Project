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

        db.execSQL(create_users_table);
        db.execSQL(create_courses_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + COURSES_TABLE_NAME);
        onCreate(db);
    }

    public Cursor getUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + USERS_TABLE_NAME;
        return db.rawQuery(query, null);
    }

    public Cursor getUsersById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + USERS_TABLE_NAME + " WHERE " + USERS_ID + "=?", new String[]{String.valueOf(id)});
    }

    public Cursor getCourses() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + COURSES_TABLE_NAME;
        return db.rawQuery(query, null);
    }

    public Cursor getCoursesByInstructorId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + COURSES_TABLE_NAME + " WHERE " + COURSES_INSTRUCTOR_ID + "=?", new String[]{String.valueOf(id)});
    }

    public Cursor getCoursesByCodeAndName(String code, String name) {
        SQLiteDatabase db = this.getReadableDatabase();

        if(code.equals("") && !name.equals("")) {
            return db.rawQuery("SELECT * FROM " + COURSES_TABLE_NAME + " WHERE " + COURSES_NAME + " LIKE?", new String[]{name});
        }
        else if(!code.equals("") && name.equals("")) {
            return db.rawQuery("SELECT * FROM " + COURSES_TABLE_NAME + " WHERE " + COURSES_CODE + " LIKE?", new String[]{code});
        }
        else {
            return db.rawQuery("SELECT * FROM " + COURSES_TABLE_NAME + " WHERE " + COURSES_CODE + " LIKE? AND " + COURSES_NAME + " LIKE?", new String[]{code, name});
        }
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
