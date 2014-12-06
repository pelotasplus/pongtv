/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package pl.pelotasplus.pongtv;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

/*
 * MainActivity class that loads MainFragment
 */
public class TvMainActivity extends Activity {
    private static final String TAG = TvMainActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tv_main);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (listener != null) {
            listener.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    GamePadMovement listener = null;

    public void addListener(GamePadMovement listener) {
        this.listener = listener;
    }

    public interface GamePadMovement {
        public void onKeyDown(int keyCode, KeyEvent event);
    }
}
