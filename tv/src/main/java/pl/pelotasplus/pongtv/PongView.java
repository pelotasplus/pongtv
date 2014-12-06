package pl.pelotasplus.pongtv;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

/**
 * Created by alek on 06/12/14.
 */
public class PongView extends View {
    private static final String TAG = PongView.class.getSimpleName();
    private PongModel model;
    private Bitmap ball;

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

    public boolean tick() {
        boolean resultChanged = model.update();
        invalidate();
        return resultChanged;
    }

    public int getLeftScore() {
        return model.getLeftPoints();
    }

    public int getRightScore() {
        return model.getRightPoints();
    }

    public static enum Player {
        LEFT, RIGHT
    }

//    int ballX, ballY

    int width, height, halfWidth;
    int middleLineWidth;

    int padWidth;//padHeight;
    int padPadding;

    int ballSize;

    Paint backgroundColor, elementsColor, padPaint, ballPaint;

    public void changePlayerPosition(Player player, int keyCode) {
        if (player == Player.LEFT) {
            if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
                model.leftPositionDown();
            } else if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
                model.leftPositionUp();
            }
        } else if (player == Player.RIGHT) {
            if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
                model.rightPositionDown();
            } else if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
                model.rightPositionUp();
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

        ballPaint = new Paint();

        ball = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        ballSize = ball.getWidth();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        width = w;
        halfWidth = width / 2;

        height = h;

        middleLineWidth = (int) (0.006 * width);
        elementsColor.setStrokeWidth(middleLineWidth);

        padWidth = middleLineWidth;
        padPadding = padWidth; //(int) (0.025 * width);
        padPaint.setStrokeWidth(padWidth);

        model = new PongModel(w, h, padWidth + ballSize / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBackground(canvas);
        drawLine(canvas);
        drawPads(canvas);
        drawBall(canvas);
    }

    private void drawPads(Canvas canvas) {
        // left player
        canvas.drawLine(
                padPadding,
                model.getLeftPosition(),
                padPadding,
                model.getLeftPosition() + model.getPadHeight(),
                padPaint
        );

        // right player
        canvas.drawLine(
                width - padPadding,
                model.getRightPosition(),
                width - padPadding,
                model.getRightPosition() + model.getPadHeight(),
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

    private void drawBall(Canvas canvas) {
        canvas.drawBitmap(
                ball,
                model.getBallPositionX() - ballSize / 2,
                model.getBallPositionY() - ballSize / 2,
                padPaint);
    }
}
