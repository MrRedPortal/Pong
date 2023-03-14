package com.becroft.androidpong;

import android.graphics.RectF;

public class Bat {
    // Member variables m prefix, all private
    private RectF mRect;
    private float mLength;
    private float mXCoord;
    private float mBatSpeed;
    private int mScreenX;

    // Variables are public and final
    // Can be directly accesed by PongGame because same package
    // But cannot be changed

    final int STOPPED = 0;
    final int LEFT = 1;
    final int RIGHT = 2;

    // Keep track of if bat is moving and what direction
    private int mBatMoving = STOPPED;

    public Bat(int sX, int sY){
        // Bat needs to know horizontal screen resolution
        mScreenX = sX;

        // configure size of bat based on screen resolution
        // 1/8 screen width
        mLength = mScreenX/8;
        // 1/40 screen height
        float height = sY/40;

        // Configure starting location of bat
        // Roughly middle horizontally
        mXCoord = mScreenX/2;
        // Height of bat from bottom of screen
        float mYCoord = sY - height;

        //initialise mRect based on size and position
        mRect = new RectF(mXCoord, mYCoord,mXCoord + mLength, mYCoord + height);

        // configure speed of bat based on screen
        mBatSpeed = mScreenX;
    }

    // Return a reference to mRect object
    RectF getRect(){
        return mRect;
    }

    // Update movement based on touch event
    void setMovementState(int state){
        mBatMoving = state;
    }

    void update(long fps){
        // Move bat based on mBatMoving variable and the speed of previous frame

        if(mBatMoving == LEFT){
            mXCoord = mXCoord - mBatSpeed/fps;
        }
        if(mBatMoving == RIGHT){
            mXCoord = mXCoord + mBatSpeed/fps;
        }
        if(mXCoord<0){
            mXCoord = 0;
        } else if(mXCoord + mLength > mScreenX){
            mXCoord = mScreenX - mLength;
        }

        // update mRect based on results from code above
        mRect.left = mXCoord;
        mRect.right = mXCoord + mLength;

    }
}