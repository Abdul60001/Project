package com.example.project;



import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


public class Deliverable3Test {


    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void enrollCourseStudentFail() {
        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("student1@gmail.com"));
        onView(withId(R.id.editTextTextPassword)).perform(typeText("Student1!!"));
        closeSoftKeyboard();
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.button3)).perform(click());
        onView(withId(R.id.button39)).perform(click());
        onView(withId(R.id.editTextTextPersonName19)).perform(typeText("CSI21101"));
        onView(withText("SEARCH")).perform(click());
    }


    @Test
    public void enrollCourseStudent() {
        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("student1@gmail.com"));
        onView(withId(R.id.editTextTextPassword)).perform(typeText("Student1!!"));
        closeSoftKeyboard();
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.button3)).perform(click());
        onView(withId(R.id.button39)).perform(click());
        onView(withId(R.id.editTextTextPersonName19)).perform(typeText("CSI2110"));
        onView(withText("SEARCH")).perform(click());
        onView(withText("CSI2110")).perform(click());
        onView(withText("ENROL")).perform(click());
    }

    @Test
    public void searchCourseByName() {
        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("student1@gmail.com"));
        onView(withId(R.id.editTextTextPassword)).perform(typeText("Student1!!"));
        closeSoftKeyboard();
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.button3)).perform(click());
        onView(withId(R.id.button39)).perform(click());
        onView(withId(R.id.editTextTextPersonName20)).perform(typeText("Data Structures"));
        onView(withText("SEARCH")).perform(click());
    }

    @Test
    public void searchCourseByDay() {
        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("student1@gmail.com"));
        onView(withId(R.id.editTextTextPassword)).perform(typeText("Student1!!"));
        closeSoftKeyboard();
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.button3)).perform(click());
        onView(withId(R.id.button39)).perform(click());
        onView(withId(R.id.editTextTextPersonName21)).perform(typeText("monday"));
        onView(withText("SEARCH")).perform(click());
    }

    @Test
    public void searchCourseByCourseCode() {
        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("student1@gmail.com"));
        onView(withId(R.id.editTextTextPassword)).perform(typeText("Student1!!"));
        closeSoftKeyboard();
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.button3)).perform(click());
        onView(withId(R.id.button39)).perform(click());
        onView(withId(R.id.editTextTextPersonName19)).perform(typeText("SEG2105"));
        onView(withText("SEARCH")).perform(click());
    }

    @Test
    public void courseConflictError() {
        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("student1@gmail.com"));
        onView(withId(R.id.editTextTextPassword)).perform(typeText("Student1!!"));
        closeSoftKeyboard();
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.button3)).perform(click());
        onView(withId(R.id.button39)).perform(click());
        onView(withId(R.id.editTextTextPersonName19)).perform(typeText("MAT2377"));
        onView(withText("SEARCH")).perform(click());
        onView(withText("MAT2377")).perform(click());
        onView(withText("ENROL")).perform(click());
    }





}

