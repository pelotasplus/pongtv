package pl.pelotasplus.pongtv;

import java.util.Random;

public final class PongModel {

    public static final int SHIFT = 10;
    public static final int BALL_SHIFT = 10;
    private final float gameWidth, gameHeight;
    private final float padHeight;
    private float ballPositionX, ballPositionY;
    private float leftPosition, rightPosition;
    private int directionX, directionY;
    private int leftPoints;
    private int rightPoints;

    public PongModel(float gameWidth, float gameHeight) {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.padHeight = gameHeight / 5;
        reset();
    }

    private void reset() {
        this.leftPosition = (gameHeight - padHeight) / 2;
        this.rightPosition = (gameHeight - padHeight) / 2;
        this.ballPositionX = gameWidth / 2;
        this.ballPositionY = gameHeight / 2;
        Random r = new Random();
        directionX = r.nextInt(2) * 2 - 1;
        directionY = r.nextInt(2) * 2 - 1;
    }

    public void update() {
        ballPositionX += BALL_SHIFT * directionX;
        ballPositionY += BALL_SHIFT * directionY;
        if (ballPositionY < 0) {
            ballPositionY = -ballPositionY;
            directionY = -directionY;
        } else if (ballPositionY > gameHeight) {
            ballPositionY = -ballPositionY + 2 * gameHeight;
            directionY = -directionY;
        }
        if (ballPositionX < 0) {
            rightPoints++;
            reset();
        } else if (ballPositionX > gameWidth) {
            leftPoints++;
            reset();
        }
    }

    public float getLeftPosition() {
        return leftPosition;
    }

    public float getRightPosition() {
        return rightPosition;
    }

    public void leftPositionDown() {
        leftPosition += SHIFT;
        if (leftPosition > gameHeight - padHeight) {
            leftPosition = gameHeight - padHeight;
        }
    }

    public void rightPositionDown() {
        rightPosition += SHIFT;
        if (rightPosition > gameHeight - padHeight) {
            rightPosition = gameHeight - padHeight;
        }
    }

    public void leftPositionUp() {
        leftPosition -= SHIFT;
        if (leftPosition < 0) {
            leftPosition = 0;
        }
    }

    public void rightPositionUp() {
        rightPosition -= SHIFT;
        if (rightPosition < 0) {
            rightPosition = 0;
        }
    }

    public float getBallPositionX() {
        return ballPositionX;
    }

    public float getBallPositionY() {
        return ballPositionY;
    }

    public float getPadHeight() {
        return padHeight;
    }

    public int getLeftPoints() {
        return leftPoints;
    }

    public int getRightPoints() {
        return rightPoints;
    }
}
