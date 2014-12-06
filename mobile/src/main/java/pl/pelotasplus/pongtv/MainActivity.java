package pl.pelotasplus.pongtv;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;

import io.relayr.LoginEventListener;
import io.relayr.RelayrSdk;
import io.relayr.model.Reading;
import io.relayr.model.TransmitterDevice;
import rx.Subscriber;


public class MainActivity extends Activity implements LoginEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!RelayrSdk.isUserLoggedIn()) {
            RelayrSdk.logIn(this, this);
        } else {
            onSuccessUserLogIn();
        }
    }

    @Override
    public void onSuccessUserLogIn() {
        RelayrSdk.getWebSocketClient().subscribe(new TransmitterDevice("28e3d9e0-0973-4439-b9cb-f153a55f8915", "mpHPRAuOVJRQ7PbWq5CL6Hsal3Gq1jCv", "", "", ""), new Subscriber<Object>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                Reading reading = new Gson().fromJson(o.toString(), Reading.class);
                Log.e("tag", reading.prox + " prox");
            }
        });

    }

    @Override
    public void onErrorLogin(Throwable throwable) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
