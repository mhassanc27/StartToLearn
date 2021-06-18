package com.ets.thcs.easythcsearch;

import android.app.Application;
import android.content.Context;
//import android.test.ApplicationTestCase;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;


/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest{
    public ApplicationTest() {
        //super(Application.class);
        Context context = ApplicationProvider.getApplicationContext();
    }
}