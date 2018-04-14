package com.msemu.commons.utils.types;

/**
 * Created by Weber on 2018/3/28.
 */
public class Rect {
    private int left, top, right, bottom;

    public Rect() {

    }

    public Rect(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public Rect(Position lt, Position rb) {
        this.left = lt.getX();
        this.top = lt.getY();
        this.right = rb.getX();
        this.bottom = rb.getY();
    }

    /**
     * Top left x coord
     * @return
     */
    public int getLeft() {
        return left;
    }

    /**
     * Top left x coord
     * @param left
     */
    public void setLeft(int left) {
        this.left = left;
    }

    /**
     * Top left y coord
     * @return
     */
    public int getTop() {
        return top;
    }

    /**
     * Top left y coord
     * @param top
     */
    public void setTop(int top) {
        this.top = top;
    }

    /**
     * Bottom right x coord
     * @return
     */
    public int getRight() {
        return right;
    }

    /**
     * Bottom right x coord
     * @param right
     */
    public void setRight(int right) {
        this.right = right;
    }

    /**
     * Bottom right y coord
     * @return
     */
    public int getBottom() {
        return bottom;
    }

    /**
     * Bottom right y coord
     * @param bottom
     */
    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    /**
     * Returns the width of this Rect.
     * @return the width of this Rect.
     */
    public int getWidth() {
        return Math.abs(getLeft() - getRight());
    }

    /**
     * Returns the height of this Rect.
     * @return The height of this Rect.
     */
    public int getHeight() {
        return Math.abs(getTop() - getBottom());
    }

    /**
     * Returns whether or not a {@link Position} is inside this Rect.
     * @param position The Position to check.
     * @return if the position is not null and inside this Rect (rect.left < pos.x < rect.right &&
     * rect.top < pos.y < rect.bottom.
     */
    public boolean hasPositionInside(Position position) {
        if(position == null) {
            return false;
        }
        int x = position.getX();
        int y = position.getY();
        return x >= left && y >= top && x <= right && y <= bottom;
    }

    /**
     * Move this Rect left by the width, effectively flipping around the left edge.
     * @return The resulting Rect from the move.
     */
    public Rect moveLeft() {
        return new Rect(getLeft() - getWidth(), getTop(), getLeft(), getBottom());
    }

    /**
     * Move this Rect right by the width, effectively flipping around the right edge.
     * @return The resulting Rect from the move.
     */
    public Rect moveRight() {
        return new Rect(getRight(), getTop(), getRight() + getWidth(), getBottom());
    }
}
