package pl.pelotasplus.pongtv;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;

import io.relayr.RelayrSdk;
import io.relayr.model.Reading;
import io.relayr.model.TransmitterDevice;
import rx.Subscriber;

public final class PongApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RelayrSdk.init(this);
    }
}
