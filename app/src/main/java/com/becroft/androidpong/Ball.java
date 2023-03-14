package com.becroft.androidpong;

import android.graphics.RectF;

public class Ball {

    // Member Variables - Private Will have prefix 'm' to denote member variables
    private RectF mRect;
    private float mYVelocity;
    private float mXVelocity;
    private float mBallHeight;
    private float mBallWidth;

    public Ball(int screenX){

        // Make the ball square and 1% of screen width
        mBallWidth = screenX/100;
        mBallHeight = screenX/100;

        // initialise Rectf with 0,0,0,0
        mRect = new RectF(0,0,0,0);
    }

    RectF getmRect(){
        return mRect;
    }

    // Move the ball based on frame/loop
    void update(long fps){
        // move the ball based on horizontal (mXVelocity) and vertical (mYVelocity) speed
        // and current frame rate

        // Move the top left corner
        mRect.left = mRect.left + (mXVelocity/fps);
        mRect.top = mRect.top + (mYVelocity/fps);
        // Move bottom right corner based on ball size
        mRect.right = mRect.left + mBallWidth;
        mRect.bottom = mRect.top + mBallHeight;
    }

    void reverseYVelocity(){
        mYVelocity = -mYVelocity;
    }

    void reverseXVelocity(){
        mXVelocity = -mXVelocity;
    }

    void reset(int x, int y){
        // initialise four points of rectangle to form square ball

        mRect.top = 0;
        mRect.left = x/2;
        mRect.right = x/2 + mBallWidth;
        mRect.bottom = mBallHeight;

        // How fast the ball will travel
        // Could be varied, Could increase in speed as game progresses
        mYVelocity = -(y/3);
        mXVelocity = x/2;

    }

    void increaseVelocity(){
        // increase the speed %10
        mYVelocity = mYVelocity * 1.1f;
        mXVelocity = mXVelocity * 1.1f;
    }

    // Bounce the ball backk based on whether it hits the left or right side of the bat
    void batBounce(RectF batPosition){

        // Detect center of bat
        float batCenter = batPosition.left + (batPosition.width()/2);

        // Detect the center of the ball
        float ballCenter = mRect.left + (mBallWidth/2);

        // Where the did ball hit
        float relativeIntersect = (ballCenter - batCenter);

        // Pick bounce direction
        if(relativeIntersect < 0){
            // Go right
            mXVelocity = Math.abs(mXVelocity);
            // Math.abs is a static method that strips any negative values from a value
            // -1 becomes 1 and 1 stays as 1
        } else {
            // Go left
            mXVelocity = -Math.abs(mXVelocity);
        }

        // reverse yVelocity

        reverseYVelocity();
    }
}














