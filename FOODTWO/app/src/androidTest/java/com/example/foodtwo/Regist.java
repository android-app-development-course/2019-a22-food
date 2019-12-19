package com.example.foodtwo;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.example.foodtwo.yuzhu.Register;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class Regist {
    private String name;
    private String pwds;
    @Rule
    public ActivityTestRule<Register> activityRule =
            new ActivityTestRule<>(Register.class);
    @Before
    public void stringInit(){
        name = "a";
        pwds = "1234";
    }
    @Test
    public void logging(){
        onView(withId(R.id.editText)).perform(typeText(name),closeSoftKeyboard());
        onView(withId(R.id.editText2)).perform(typeText(pwds),closeSoftKeyboard());
        onView(withId(R.id.button4)).perform(click());
    }
}
