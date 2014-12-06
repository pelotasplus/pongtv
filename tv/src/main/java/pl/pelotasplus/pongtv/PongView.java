package pl.pelotasplus.pongtv;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by alek on 06/12/14.
 */
public class PongView extends View {
    private static final String TAG = PongView.class.getSimpleName();

    public PongView(Context context) {
        super(context);
        init();
    }

    public PongView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PongView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    int width, height, halfWidth;
    int middleLineWidth;

    int padWidth, padHeight;
    int padPadding;

    Paint backgroundColor, elementsColor, padPaint;

    /* data from the model, to be added later */
    float playerLeftPosition = 500;
    float playerRightPosition = 250;

    public float getPlayerLeftPosition() {
        return playerLeftPosition;
    }

    public void setPlayerLeftPosition(float playerLeftPosition) {
        this.playerLeftPosition = playerLeftPosition;
    }

    public float getPlayerRightPosition() {
        return playerRightPosition;
    }

    public void setPlayerRightPosition(float playerRightPosition) {
        this.playerRightPosition = playerRightPosition;
    }

    private void init() {
        backgroundColor = new Paint();
        backgroundColor.setColor(getResources().getColor(R.color.pong_background));

        elementsColor = new Paint();
        elementsColor.setColor(getResources().getColor(android.R.color.white));

        padPaint = new Paint();
        padPaint.setColor(getResources().getColor(android.R.color.white));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        width = w;
        halfWidth = width / 2;

        height = h;

        middleLineWidth = (int) (0.006 * width);
        elementsColor.setStrokeWidth(middleLineWidth);

        padPadding = (int) (0.025 * width);
        padWidth = middleLineWidth;
        padHeight = (int) (0.2 * height);
        padPaint.setStrokeWidth(padWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBackground(canvas);
        drawLine(canvas);
        drawPads(canvas);
    }

    private void drawPads(Canvas canvas) {
        // left player
        canvas.drawLine(
                padPadding,
                playerLeftPosition,
                padPadding,
                playerLeftPosition + padHeight,
                padPaint
        );

        // right player
        canvas.drawLine(
                width - padPadding,
                playerRightPosition,
                width - padPadding,
                playerRightPosition + padHeight,
                padPaint
        );
    }

    private void drawBackground(Canvas canvas) {
        canvas.drawRect(0, 0, width, height, backgroundColor);
    }

    private void drawLine(Canvas canvas) {
        canvas.drawLine(
                halfWidth,
                0,
                halfWidth,
                height,
                elementsColor
        );
    }
}
