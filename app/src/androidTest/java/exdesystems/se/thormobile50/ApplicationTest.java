package exdesystems.se.thormobile50;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.CountDownLatch;

import exdesystems.se.thormobile50.Activities.MapOverLayActivity;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ActivityInstrumentationTestCase2<MapOverLayActivity>{
    private Activity mFirstTestActivity;
    private ImageView imageKorTur;
    private ImageView imageKorOrder;
    private ImageView imageInstallningar;
    private TextView mFirstTestText;

    public ApplicationTest() {
        super(MapOverLayActivity.class);
    }



    @Override
    protected void setUp() throws Exception {
        super.setUp();

    }
    @MediumTest
    public void testPreconditions() {
        assertNotNull("mFirstTestActivity is null", mFirstTestActivity);
        assertNotNull("mFirstTestText is null", mFirstTestText);
        assertNotNull("mimageKortur is null", imageKorTur);
        assertNotNull("imageKororder is null", imageKorOrder);
        assertNotNull("imageInstallningar is null", imageInstallningar);
    }
    @MediumTest
    public void testMyFirstTestTextView_labelText() {
        final String expected =
                mFirstTestActivity.getString(R.string.dash_kororder);
        final String actual = mFirstTestText.getText().toString();
        assertEquals(expected, actual);
    }

    @MediumTest
    public void testUpdateLinkAsync() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        // Execute the async task on the UI thread! THIS IS KEY!
        final String[] text = new String[1];
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {

                } catch (Exception e) {
                    Log.e("ERROR", e.getMessage());
                    fail();
                }
            }
        });
        signal.await();// wait for callback
        assertEquals("http://int.exdesystems.se/thorrestserver/401/thor_rest_service.svc",text[0]);
    }

    @MediumTest
    public void testUpdateStatusAsync() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        // Execute the async task on the UI thread! THIS IS KEY!
        final String[] text = new String[1];
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {

                } catch (Exception e) {
                    Log.e("ERROR", e.getMessage());
                    fail();
                }
            }
        });
        signal.await();// wait for callback
        assertEquals("4.15",text[0]);
    }

    @MediumTest
    public void testHomeScreenButtons()
    {
        //String expectedInfoText = mFirstTestActivity.getString(R.string.info_text);
        //TouchUtils.clickView(this, imageKorTur);
       // assertEquals(expectedInfoText, mInfoTextView.getText());
    }



    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }


}