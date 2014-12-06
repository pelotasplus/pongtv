package pl.pelotasplus.pongtv;

import java.util.Random;

public final class PongModel {

    private static final int SHIFT = 10;
    private final float gameWidth, gameHeight;
    private final float padHeight;
    private float ballPositionX, ballPositionY;
    private float leftPosition, rightPosition;
    private float directionX, directionY;
    private int leftPoints;
    private int rightPoints;
    private float padding;
    private int updateCount;
    private int ballShift;

    public PongModel(float gameWidth, float gameHeight, float padding) {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.padding = padding;
        this.padHeight = gameHeight / 5;
        this.leftPosition = (gameHeight - padHeight) / 2;
        this.rightPosition = (gameHeight - padHeight) / 2;
        reset();
    }

    private void reset() {
        this.ballPositionX = gameWidth / 2;
        this.ballPositionY = gameHeight / 2;
        Random r = new Random();
        directionX = r.nextInt(2) * 2 - 1;
        directionY = r.nextInt(2) * 2 - 1;
        ballShift = 3;
        updateCount = 0;
    }

    public boolean update() {
        ballPositionX += ballShift * directionX;
        ballPositionY += ballShift * directionY;
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
            return true;
        } else if (ballPositionX > gameWidth) {
            leftPoints++;
            reset();
            return true;
        } else if (directionX < 0 && ballPositionX < padding && ballPositionY > leftPosition && ballPositionY < leftPosition + padHeight) {
            directionX = -directionX;
            updateDirectionY(leftPosition);
        } else if (directionX > 0 && ballPositionX > gameWidth - padding && ballPositionY > rightPosition && ballPositionY < rightPosition + padHeight) {
            directionX = -directionY;
            updateDirectionY(rightPosition);
        }
        updateCount++;
        if (updateCount % 1000 == 0) {
            ballShift++;
        }
        return false;
    }

    private void updateDirectionY(float padPosition) {
        if (ballPositionY < padPosition + padHeight / 3.0f) {
            if (directionY < 3) {
                directionY += new Random().nextDouble() / 2;
            }
        } else if (ballPositionY > padPosition + 2 * padHeight / 3.0f) {
            if (directionY > -3) {
                directionY -= new Random().nextDouble() / 2;
            }
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
