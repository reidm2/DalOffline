package com.example.achristians.gpproject;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UserCourseViewTest {
        @Rule
        public ActivityTestRule<MyCourses> testRule = new ActivityTestRule<>(MyCourses.class);

        private MyCourses myCourseActivity = testRule.getActivity();
        private final ArrayList<Course> utilityCourseList = new ArrayList<>();

        @Before
        public void setupForTests() throws Throwable {
            //Thread.sleep(2000);
            myCourseActivity = testRule.getActivity();

            utilityCourseList.clear();
            utilityCourseList.add(Course.exampleCourse);

            User.MockUser();

            Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
            firebaseDB.dbInterface = new firebaseDB(context);

        }

        //Test 1: check if the listview exists
        @Test
        public void listViewExists() {
            onView(withId(R.id.myCourseListView)).check(matches(isDisplayed()));
        }


        //Test 2: Listen to make sure a cell in the listview is clickable
        @Test
        public void cellClicks() {
            onData(anything()).inAdapterView(withId(R.id.courseListView)).atPosition(0).perform(click());
            Espresso.pressBack();
        }

        @Test
        public void cellClickOpensDescription(){
            onData(anything()).inAdapterView(withId(R.id.courseListView)).atPosition(0).perform(click());
            onView(withId(R.id.courseNameView)).check(matches(withText("Calculus 1")));
            Espresso.pressBack();
        }

        @Test
        public void checkListMatchesData(){}
}