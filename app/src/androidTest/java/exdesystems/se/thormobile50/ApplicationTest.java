package exdesystems.se.thormobile50;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.CountDownLatch;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ActivityInstrumentationTestCase2<HomeScreenActivity>{
    private Activity mFirstTestActivity;
    private ImageView imageKorTur;
    private ImageView imageKorOrder;
    private ImageView imageInstallningar;
    private TextView mFirstTestText;

    public ApplicationTest() {
        super(HomeScreenActivity.class);
    }



    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mFirstTestActivity = getActivity();
        mFirstTestText =
                (TextView) mFirstTestActivity
                        .findViewById(R.id.korordertxt);
        imageKorTur =
                (ImageView) mFirstTestActivity
                        .findViewById(R.id.dashKorturStart);
        imageKorOrder =
                (ImageView) mFirstTestActivity
                        .findViewById(R.id.dashKororderStart);
        imageInstallningar =
                (ImageView) mFirstTestActivity
                        .findViewById(R.id.dashInstallningar);
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
                    HomeScreenActivity.GetUpdateLink task = new HomeScreenActivity.GetUpdateLink() {
                        @Override
                        protected void onPostExecute(String newLink) {
                            text[0] = newLink;
                            signal.countDown();
                            super.onPostExecute(newLink);
                        }
                    };
                    task.execute("http://int.exdesystems.se/thorrestserver/400/thor_rest_service.svc");
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
                    HomeScreenActivity.GetUpdateAvailableStatus task = new HomeScreenActivity.GetUpdateAvailableStatus() {
                        @Override
                        protected void onPostExecute(String version) {
                            text[0] = version;
                            signal.countDown();
                            super.onPostExecute(version);

                        }
                    };
                    task.execute("http://int.exdesystems.se/thorrestserver/400/thor_rest_service.svc");
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