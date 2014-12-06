package pl.pelotasplus.pongtv;

public final class PongModel {

    public static final int SHIFT = 10;
    private final float gameWidth, gameHeight;
    private final float padHeight;
    private float ballPositionX, ballPositionY;
    private float leftPosition, rightPosition;

    public PongModel(float gameWidth, float gameHeight) {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.padHeight = gameHeight / 5;
    }

    public void update() {

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
        leftPosition += SHIFT;
        if (leftPosition < 0) {
            leftPosition = 0;
        }
    }
}
