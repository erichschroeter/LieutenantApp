package com.lieutenant.erichschroeter.lieutenantapp;

import android.support.design.widget.FloatingActionButton;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;

/**
 * Created by erich on 5/24/16.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @SmallTest
    public void testAddEntryButtonExists() {
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        assertNotNull(fab);
    }
}
