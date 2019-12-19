package com.example.foodtwo;

//import androidx.test.ext.junit.runners.AndroidJUnit4;
//import androidx.test.filters.LargeTest;
//import androidx.test.rule.ActivityTestRule;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import androidx.test.rule.ActivityTestRule;

import com.example.foodtwo.Activity.SettingActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class Setting {
    private String oldpwds;
    private String newpwds;
    @Rule
    public ActivityTestRule<SettingActivity> activityRule =
            new ActivityTestRule<>(SettingActivity.class);
    @Before
    public void stringInit(){
        oldpwds = "123";
        newpwds = "1234";
    }
    @Test
    public void logging(){
        onView(withId(R.id.oldpwd)).perform(typeText(oldpwds),closeSoftKeyboard());
        onView(withId(R.id.newpwd)).perform(typeText(newpwds),closeSoftKeyboard());
        onView(withId(R.id.button7)).perform(click());
        onView(withId(R.id.button6)).perform(click());
    }
}
