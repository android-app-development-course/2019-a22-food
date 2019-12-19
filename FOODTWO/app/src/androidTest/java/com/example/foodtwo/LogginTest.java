package com.example.foodtwo;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.example.foodtwo.yuzhu.Loggin;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LogginTest {
    private String account;
    private String pwd;
    @Rule
    public ActivityTestRule<Loggin> activityRule = new ActivityTestRule<>(Loggin.class);

    @Before
    public void stringInit(){
        account = "?";
        pwd = "123";
    }
    @Test
    public void logging(){
        onView(withId(R.id.account)).perform(typeText(account),closeSoftKeyboard());
        onView(withId(R.id.pwd)).perform(typeText(pwd),closeSoftKeyboard());
//        onView(withId(R.id.account)).check(matches(withText(account)));
//        onView(withId(R.id.account)).check(matches(withText(pwd)));
        onView(withId(R.id.login)).perform(click());
    }
}
