package com.example.project;



import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


public class MainActivityTest {


    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void loginAdmin() {
        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("admin"));
        onView(withId(R.id.editTextTextPassword)).perform(typeText("admin123"));
        closeSoftKeyboard();
        onView(withId(R.id.button2)).perform(click());
    }

    @Test
    public void createInstructor() {
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.editTextTextEmailAddress2)).perform(typeText("instructor1@gmail.com"));
        closeSoftKeyboard();
        onView(withId(R.id.editTextTextPassword2)).perform(typeText("Instructor1!!"));
        closeSoftKeyboard();
        onView(withId(R.id.editTextTextPassword3)).perform(typeText("Instructor1!!"));
        closeSoftKeyboard();
        onView(withId(R.id.editTextTextRole)).perform(typeText("instructor"));
        closeSoftKeyboard();
        onView(withId(R.id.button25)).perform(click());
    }

    @Test
    public void loginInstructor() {
        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("instructor1@gmail.com"));
        onView(withId(R.id.editTextTextPassword)).perform(typeText("Instructor1!!"));
        closeSoftKeyboard();
        onView(withId(R.id.button2)).perform(click());
    }

    @Test
    public void createStudent() {
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.editTextTextEmailAddress2)).perform(typeText("student1@gmail.com"));
        closeSoftKeyboard();
        onView(withId(R.id.editTextTextPassword2)).perform(typeText("Student1!!"));
        closeSoftKeyboard();
        onView(withId(R.id.editTextTextPassword3)).perform(typeText("Student1!!"));
        closeSoftKeyboard();
        onView(withId(R.id.editTextTextRole)).perform(typeText("student"));
        closeSoftKeyboard();
        onView(withId(R.id.button25)).perform(click());
    }

    @Test
    public void loginStudent() {
        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("student1@gmail.com"));
        onView(withId(R.id.editTextTextPassword)).perform(typeText("Student1!!"));
        closeSoftKeyboard();
        onView(withId(R.id.button2)).perform(click());
    }

    @Test
    public void viewUsers() {
        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("student1@gmail.com"));
        onView(withId(R.id.editTextTextPassword)).perform(typeText("Student1!!"));
        closeSoftKeyboard();
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.button3)).perform(click());
        onView(withId(R.id.button39)).perform(click());
        onView(withId(R.id.editTextTextPersonName19)).perform(typeText("CSI2110"));
        onView(withId(R.id.button21)).perform(click());

    }



}

