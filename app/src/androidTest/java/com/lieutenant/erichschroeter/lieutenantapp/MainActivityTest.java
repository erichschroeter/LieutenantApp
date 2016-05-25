package com.lieutenant.erichschroeter.lieutenantapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.design.widget.FloatingActionButton;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.SmallTest;

/**
 * Created by erich on 5/24/16.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Activity activity;
    private FloatingActionButton fab;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
        fab = (FloatingActionButton) activity.findViewById(R.id.fab);
    }

    @SmallTest
    public void testAddEntryButtonExists() {
        assertNotNull(fab);
    }

    public void testCreateNewEntry() {
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(EntryActivity.class.getName(), null, false);
        TouchUtils.tapView(this, fab);
        // assertThat EntryActivity is started
        EntryActivity entryActivity = (EntryActivity)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(entryActivity);
        entryActivity.finish();
    }
}
