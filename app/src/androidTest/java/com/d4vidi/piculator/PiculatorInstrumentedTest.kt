//package com.d4vidi.piculator
//
//import android.support.test.InstrumentationRegistry
//import android.support.test.InstrumentationRegistry.getInstrumentation
//import android.support.test.espresso.Espresso.onView
//import android.support.test.espresso.IdlingRegistry
//import android.support.test.espresso.IdlingResource
//import android.support.test.espresso.action.ViewActions
//import android.support.test.espresso.assertion.ViewAssertions.matches
//import android.support.test.espresso.matcher.ViewMatchers.*
//import android.support.test.rule.ActivityTestRule
//import android.support.test.runner.AndroidJUnit4
//import android.view.KeyEvent
//import org.junit.After
//import org.junit.Assert.assertEquals
//import org.junit.Ignore
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//
///**
// * Instrumented test, which will execute on an Android device.
// *
// * See [testing documentation](http://d.android.com/tools/testing).
// */
//@RunWith(AndroidJUnit4::class)
//class PiculatorInstrumentedTest {
//    @Rule @JvmField
//    val activityRule = ActivityTestRule(MainActivity::class.java)
//
//    private var activityIdlingResource: IdlingResource? = null
//
//    @Test
//    fun useAppContext() {
//        // Context of the app under test.
//        val appContext = InstrumentationRegistry.getTargetContext()
//        assertEquals("com.d4vidi.piculator", appContext.packageName)
//    }
//
//    @Test @Ignore
//    fun awaitSinglePiculator() {
//        activityIdlingResource = activityRule.activity.getIdlingResource()
//        IdlingRegistry.getInstance().register(activityIdlingResource)
//
//        onView(withId(R.id.btnTriggerPi)).perform(ViewActions.click())
//
//        // Insert here: Await idle resource
//        onView(withId(R.id.txtPiResult)).check(matches(isDisplayed()))
//        onView(withId(R.id.txtPiResult)).check(matches(withText("3.141592653589787"))) // This is wrong :-/
//    }
//
//    @Test
//    fun espressoTroll() {
//        getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_HOME)
//
//        activityRule.launchActivity(null)
//    }
//
//    @After
//    fun wrapUp() {
//        activityIdlingResource?.let {
//            IdlingRegistry.getInstance().unregister(activityIdlingResource)
//        }
//    }
//}
