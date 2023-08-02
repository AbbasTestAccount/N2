package com.example.n2

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest{

    private lateinit var activityScenario : ActivityScenario<MainActivity>

    @Before
    fun addActivity(){
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun isActivityDisplay() {
        onView(withId(R.id.mainActivity)).check(matches(isDisplayed()))

    }
}