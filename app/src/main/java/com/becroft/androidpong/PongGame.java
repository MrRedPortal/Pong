package com.becroft.androidpong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class PongGame extends SurfaceView implements Runnable {
    //Are we debugging?
    private final boolean DEBUGGING = true;

    // Drawing Objects
    private SurfaceHolder surfaceHolder;
    private Canvas canvas;
    private Paint paint;

    // fps tracking
    private long FPS;
    // Seconds in Millisecond
    private final int MILLIS_IN_SECOND = 1000;

    // Resolution on screen
    private int screenX;
    private int screenY;

    // Text sizing
    private int fontSize;
    private int fontMargin;

    // Game Objects
    private Bat bat;
    private Ball ball;

    // Score Tracking
    private int score;
    private int lives;

    // Create thread and two control variables
    private Thread gameThread = null;

    // Volatile variable can be accessed from inside and outside the thread
    private volatile boolean playing;
    private boolean paused = true;


    public PongGame(Context context, int x, int y) {
        super(context);

        // Initialise screen size
        screenX = x;
        screenY = y;

        // Font 5% screen width
        fontSize = screenX / 20;
        // Font margin 1.5%
        fontMargin = screenX / 75;

        // Initialise drawings on screen
        surfaceHolder = getHolder();
        paint = new Paint();

        // Initalise ball and bat

        // Start the game
        startNewGame();

    }

    private void startNewGame() {

        // Put the ball in start position

        // reset score and lives
        score = 0;
        lives = 3;

    }

    private void draw() {
        if(surfaceHolder.getSurface().isValid()){
            // Lock the canvas
            canvas = surfaceHolder.lockCanvas();

            // Fill screen with solid colour
            canvas.drawColor(Color.argb(255,26,128,182));

            // Choose colour to paint with
            paint.setColor(Color.argb(255,255,255,255));

            // Draw Bat and Ball

            // Choose font size
            paint.setTextSize(fontSize);

            // Draw the HUD
            canvas.drawText("Score: " + score + " Lives: " + lives, fontMargin, fontSize, paint);

            if(DEBUGGING){
                printDebuggingText();
            }
            // draw canvas to screen
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    // Called by PongActivity when player exits the game
    public void pause(){
        // Set playing to false

        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            Log.e("Error: ", "joining thread");
        }
    }

    //Called by PongActivity when player starts game
    public void resume(){
        playing = true;

        // Initialise thread instance
        gameThread = new Thread(this);

        //start thread
        gameThread.start();
    }


    private void printDebuggingText(){
        int debugSize = fontSize / 2;
        int debugStart = 150;
        paint.setTextSize(debugSize);
        canvas.drawText("FPS: " + FPS, 10, debugStart + debugSize, paint);
    }

    // thread starts GameThread.start();
    // This method is run continuously by android because we implemented the runnable interface
    // gameThread.join(); will stop thread
    @Override
    public void run() {
        // playing boolean gives us finer
        // control rather than just relying on calls to run
        // playing must be true AND the thread must be running
        // for main loop to execute
        while(playing){

            // What time is it now at start of loop
            long frameStartTime = System.currentTimeMillis();

            // If game isn't paused call the update method
            if(!paused){
                update();
                // Now the bat and ball are in their new positions we can detect collisions
                detectCollisions();
            }
            // Draw updated positions
            draw();

            // How long did frame take/loop take?
            //Store answer in timeThisFrame
            long timeThisFrame = System.currentTimeMillis() - frameStartTime;

            // ensure timeThisFrame is at least 1 millisecond
            if(timeThisFrame>0){
                //store current frame rate in FPS
                //Ready to pass the update methods of bat and ball next loop
                FPS = MILLIS_IN_SECOND / timeThisFrame;
            }
        }
    }

    private void update(){
        //update bat ball

    }

    private void detectCollisions() {
        // Has bat hit ball

        // Has bat hit edge of screen

        // Bottom

        // Top

        // Left

        // Right

    }
}





