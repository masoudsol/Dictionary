package com.dictionary;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.dictionary.modules.views.DictionaryActivity;
import com.services.APIServices;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private CountDownLatch lock = new CountDownLatch(1);


    @Rule
    public ActivityTestRule<DictionaryActivity> mainActivityActivityTestRule =
            new ActivityTestRule<>(DictionaryActivity.class);


    @Test
    public void requestEXRates() throws Exception{
        APIServices services =  new APIServices(mainActivityActivityTestRule.getActivity());

        services.getDefinition(new APIServices.CompletionListener() {
            @Override
            public void onCompletion(Boolean success, Exception error) {
                assertTrue(success);
                lock.countDown();
            }
        });

        lock.await(2000, TimeUnit.MILLISECONDS);
    }

}
