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

    public static enum Player {
        LEFT, RIGHT
    }

//    int ballX, ballY

    int width, height, halfWidth;
    int middleLineWidth;

    int padWidth, padHeight;
    int padPadding;

    Paint backgroundColor, elementsColor, padPaint;

    /* data from the model, to be added later */
    float playerLeftPosition = 500;
    float playerRightPosition = 250;

    public void changePlayerPosition(Player player, float positionChange) {
        if (player == Player.LEFT) {
            playerLeftPosition += positionChange;
            if (playerLeftPosition < 0) {
                playerLeftPosition = 0;
            } else if (playerLeftPosition + padHeight > height) {
                playerLeftPosition = height - padHeight;
            }
        } else if (player == Player.RIGHT) {
            playerRightPosition += positionChange;
            if (playerRightPosition < 0) {
                playerRightPosition = 0;
            } else if (playerRightPosition + padHeight > height) {
                playerRightPosition = height - padHeight;
            }
        }

        invalidate();
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

        playerLeftPosition = (height - padHeight) / 2;
        playerRightPosition = playerLeftPosition;
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
