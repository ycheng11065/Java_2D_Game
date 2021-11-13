package model;

import java.awt.*;

// SOURCE: SpaceInvaderBase

public class Player {

    public static final int HEALTH = 200;
    public static final int SIZEX = 20; // Appearance will be changed
    public static final int SIZEY = 20; // into animated image?
    public static final int Y_POS = Frame.HEIGHT - 40; // need to change?
    public static final Color COLOUR = new Color(153, 153, 255);
    public static final int RATEY = 2;
    public static final int RATEX = 2;


    private int health;
    private int xcoord;
    private int ycoord;
    private int food;
    private String direction;


    //EFFECT: Creates the player at the desired x y coordinate with health and direction
    public Player(int x, int y, int h, String d) {
        this.xcoord = x;
        this.ycoord = y;
        this.health = h;
        this.direction = d;
    }

    public void moveX(int vel) {
        xcoord += vel;
        xboundary();
    }

    public void moveY(int vel) {
        ycoord += vel;
        yboundary();
    }

//    //EFFECT: Return true if hit by enemies
//    public boolean isHit(Enemies e) {
//        Rectangle playerRectangle = new Rectangle(getXcoord() - SIZEX / 2,
//                getYcoord() - SIZEY / 2, SIZEX, SIZEY);
//        Rectangle enemyRectangle = new Rectangle(e.getXcoord() - SIZEX / 2,
//                e.getYcoord() - SIZEY / 2, e.SIZEX, e.SIZEY);
//        return playerRectangle.intersects(enemyRectangle);
//
//    }


    //MODIFY: this
    //EFFECT: Constrain player from leaving game boundary on x axis
    private void xboundary() {
        if (xcoord < 0) {
            xcoord = 0;
        } else if (xcoord > Frame.WIDTH) {
            xcoord = Frame.WIDTH;
        }

    }

    //MODIFY: this
    //EFFECT: Constrain player from leaving game boundary on y axis
    public void yboundary() {
        if (ycoord < 0) {
            ycoord = 0;
        } else if (ycoord > Frame.HEIGHT) {
            ycoord = Frame.HEIGHT;
        }

    }

    //MODIFY: this
    //EFFECT: Apply damage to player health
    public void healthDmg(int d) {
        health = health - d;
    }

    //EFFECT: Return x coordinate
    public int getXcoord() {
        return xcoord;
    }

    //EFFECT: Return y coordinate
    public int getYcoord() {
        return ycoord;
    }

    //EFFECT: Return direction
    public String getDirection() {
        return direction;
    }

    //EFFECT: Return player health
    public int getHealth() {
        return health;
    }

    //MODIFY: this
    //EFFECT: Change Direction of player
    public void playerChangeDirection(String d) {
        direction = d;
    }

    //MODIFY: this
    //EFFECTS: Move player to new x coord
    public void playerChangeXcoord(int n) {
        xcoord = n;
    }

    //MODIFY: this
    //EFFECTS: Move player to new y coord
    public void playerChangeYcoord(int n) {
        ycoord = n;
    }

    public void playerCollect() {
        this.food++;
    }

}
