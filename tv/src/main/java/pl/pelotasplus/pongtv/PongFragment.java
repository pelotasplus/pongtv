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

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v17.leanback.app.BackgroundManager;
import android.support.v17.leanback.app.BrowseFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.OnItemViewSelectedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.support.v4.app.ActivityOptionsCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class PongFragment extends android.app.Fragment {
    private static final String TAG = PongFragment.class.getSimpleName();
    private static final long DELAY = 100;

    PongView pongView;
    Handler handler;
    RandomMovementRunnable randomMovementRunnable;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler = new Handler();
        randomMovementRunnable = new RandomMovementRunnable();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.pong_fragment, container, false);

        pongView = (PongView) v.findViewById(R.id.pongView);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        handler.postDelayed(randomMovementRunnable, DELAY);
    }

    @Override
    public void onPause() {
        super.onPause();

        handler.removeCallbacks(randomMovementRunnable);
    }

    class RandomMovementRunnable implements Runnable {
        public int nextInt(int min, int max) {
            if (min == max) {
                return max;
            }

            return new Random().nextInt(max - min + 1) + min;
        }

        @Override
        public void run() {
            // random position
            float position;

            position = pongView.getPlayerLeftPosition();
            position += nextInt(-10, 10);

            pongView.setPlayerLeftPosition(position);

            position = pongView.getPlayerRightPosition();
            position += nextInt(-10, 10);

            pongView.setPlayerRightPosition(position);

            pongView.invalidate();

            handler.postDelayed(randomMovementRunnable, DELAY);
        }
    }
}
