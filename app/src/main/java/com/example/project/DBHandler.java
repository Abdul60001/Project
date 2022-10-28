package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "AppDatabase";
    private static final int DATABASE_VERSION = 1;

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_users_table = "CREATE TABLE Users (" +
                "id INTEGER PRIMARY KEY, " +
                "username TEXT, " +
                "password TEXT, " +
                "userType TEXT" +
                ");";

        String create_courses_table = "CREATE TABLE Courses (" +
                "id INTEGER PRIMARY KEY, " +
                "code TEXT, " +
                "name TEXT, " +
                "instructorId INTEGER, " +
                "description TEXT, " +
                "capactiy INTEGER, " +
                "FOREIGN KEY (instructorId) REFERENCES Users(userId)" +
                ");";

        String create_times_table = "CREATE TABLE Times (" +
                "id INTEGER PRIMARY KEY, " +
                "dateTime TEXT" +
                ");";

        String create_courses_times_table = "CREATE TABLE Courses_Times (" +
                "courseId INTEGER, " +
                "timeId INTEGER, " +
                "FOREIGN KEY (courseId) REFERENCES Courses(id), " +
                "FOREIGN KEY (timeId) REFERENCES Times(id)" +
                ");";

        db.execSQL(create_users_table);
        db.execSQL(create_courses_table);
        db.execSQL(create_times_table);
        db.execSQL(create_courses_times_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + "Users");
        db.execSQL("DROP TABLE IF EXISTS " + "Courses");
        db.execSQL("DROP TABLE IF EXISTS " + "Courses_Times");
        db.execSQL("DROP TABLE IF EXISTS " + "Times");
        onCreate(db);
    }

    public Cursor getUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + "Users";
        return db.rawQuery(query, null);
    }

    public Cursor getCourses() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + "Courses";
        return db.rawQuery(query, null);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username", user.getUsername());
        values.put("password", user.getPassword());
        values.put("userType", user.getUserType());

        db.insert("Users", null, values);
        db.close();
    }
}
