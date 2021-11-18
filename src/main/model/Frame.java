package model;

import ui.FortunePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

// SOURCE: SpaceInvaderBase

public class Frame {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 500;
    public static final int HUNGER_DMG = 1;
    public static final Random RAND = new Random();

    private Player player;
    private Food food;
    private Treasure treasure;
    private Pocket pocket;

    private String msg;
    private boolean isGameOver;
    private int velX = 0;
    private int velY = 0;
    private int foodScore;
    private int treasureScore;
    private ArrayList<String> fortune;

    //EFFECT: Create empty list of bullets and enemies, spawns the player
    public Frame() {
        start();
    }

    //MODIFY: this
    //EFFECT: Start game, clear screen, spawn player
    public void start() {
        player = new Player(WIDTH / 2, HEIGHT / 2, Player.HEALTH);
        pocket = new Pocket();
        fortune = new ArrayList<String>();
        food = spawnFood();
        treasure = spawnTreasure();
        addMsg();
        isGameOver = false;
        foodScore = 0;
        treasureScore = 0;
    }

    //MODIFY: this
    //EFFECT: Update movement, Bullets and enemies
    public void update() {
        if (!isGameOver) {
            player.moveX(velX);
            player.moveY(velY);
            System.out.println(player.getXcoord());
            System.out.println(player.getYcoord());
            if (canEat()) {
                player.eat();
                foodScore++;
                food = spawnFood();
            }
            if (canPickUp()) {
                setMsg();
                pocket.addTreasure(treasure);
                treasureScore++;
                treasure = spawnTreasure();
            }
            if (player.getHealth() > 0) {
                playerHunger();
            }
            gameOver();
        }

    }

    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_W) {
            velY = -1 * player.RATEY;
        } else if (keyCode == KeyEvent.VK_S) {
            velY = player.RATEY;
        } else if (keyCode == KeyEvent.VK_A) {
            velX = -1 * player.RATEX;
        } else if (keyCode == KeyEvent.VK_D) {
            velX = player.RATEX;
        } else if (keyCode == KeyEvent.VK_R && isGameOver) {
            start();
        } else if (keyCode == KeyEvent.VK_I && isGameOver) {
            FortunePanel fp = new FortunePanel(this);
            fp.update();
        } else if (keyCode == KeyEvent.VK_X) {
            System.exit(0);
        }
    }

    public void keyReleased(int keyCode) {
        if (keyCode == KeyEvent.VK_W) {
            velY = 0;
        } else if (keyCode == KeyEvent.VK_S) {
            velY = 0;
        } else if (keyCode == KeyEvent.VK_A) {
            velX = 0;
        } else if (keyCode == KeyEvent.VK_D) {
            velX = 0;
        }
    }

    public void playerHunger() {
        player.healthDmg(HUNGER_DMG);
    }

    //MODIFY: this
    //EFFECT: Change value of isGameOver to true if player is dead, clear bullet and enemy
    // list if isGameOver is true
    public void gameOver() {
        if (player.getHealth() <= 0) {
            isGameOver = true;

        }
    }

    public Food spawnFood() {
        Random rand = new Random();
        int upperboundx = WIDTH - 10;
        int upperboundy = HEIGHT - 10;
        int lowerbound = 10;

        int xrandom = rand.nextInt(upperboundx - lowerbound) + lowerbound;
        int yrandom = rand.nextInt(upperboundy - lowerbound) + lowerbound;

        while (xrandom == player.getXcoord() && yrandom == player.getYcoord()) {
            xrandom = rand.nextInt(upperboundx - lowerbound) + lowerbound;
            yrandom = rand.nextInt(upperboundy - lowerbound) + lowerbound;
        }

        return new Food(xrandom, yrandom);
    }

    public Treasure spawnTreasure() {
        Random rand = new Random();
        int upperboundx = WIDTH - 10;
        int upperboundy = HEIGHT - 10;
        int lowerbound = 10;

        int xrandom = rand.nextInt(upperboundx - lowerbound) + lowerbound;
        int yrandom = rand.nextInt(upperboundy - lowerbound) + lowerbound;

        while ((xrandom == player.getXcoord() && yrandom == player.getYcoord())
                || (xrandom == food.getX() && yrandom == food.getY())) {
            xrandom = rand.nextInt(upperboundx - lowerbound) + lowerbound;
            yrandom = rand.nextInt(upperboundy - lowerbound) + lowerbound;
        }

        return new Treasure(xrandom, yrandom);
    }

    public Boolean canEat() {
        return food.hit(player);
    }

    public Boolean canPickUp() {
        return treasure.hit(player);
    }

    public void addMsg() {
        fortune.add("A beautiful, smart, and loving person will be coming into your life.");
        fortune.add("A dubious friend may be an enemy in camouflage.");
        fortune.add("A faithful friend is a strong defense.");
        fortune.add("A feather in the hand is better than a bird in the air.");
        fortune.add("A fresh start will put you on your way.");
        fortune.add("A friend asks only for your time not your money.");
        fortune.add("A friend is a present you give yourself.");
        fortune.add("A gambler not only will lose what he has, but also will lose what he doesn’t have.");
        fortune.add("A golden egg of opportunity falls into your lap this month.");
        fortune.add("A good friendship is often more important than a passionate romance.");
        fortune.add("A good time to finish up old tasks.");
        fortune.add("A hunch is creativity trying to tell you something.");
        addMsg2();
    }

    public void addMsg2() {
        fortune.add("A lifetime friend shall soon be made.");
        fortune.add("A lifetime of happiness lies ahead of you.");
        fortune.add("A light heart carries you through all the hard times.");
        fortune.add("Savor your freedom – it is precious.");
        fortune.add("Say hello to others. You will have a happier day.");
        fortune.add("Self-knowledge is a life long process.");
        fortune.add("Share your joys and sorrows with your family.");
        fortune.add("Sift through your past to get a better idea of the present.");
        fortune.add("Sloth makes all things difficult; industry all easy.");
        fortune.add("Small confidences mark the onset of a friendship.");
        fortune.add("Success is going from failure to failure without loss of enthusiasm.");
        fortune.add("Swimming is easy. Stay floating is hard.");
        addMsg3();
    }

    public void addMsg3() {
        fortune.add("The first man gets the oyster, the second man gets the shell.");
        fortune.add("The greatest achievement in life is to stand up again after falling.");
        fortune.add("The harder you work, the luckier you get.");
        fortune.add("The night life is for you.");
        fortune.add("The one that recognizes the illusion does not act as if it is real.");
        fortune.add("The only people who never fail are those who never try.");
        fortune.add("The person who will not stand for something will fall for anything.");
        fortune.add("The philosophy of one century is the common sense of the next.");
        fortune.add("The secret to good friends is no secret to you.");
        fortune.add("The small courtesies sweeten life, the greater ennoble it.");
        fortune.add("The sure way to predict the future is to invent it.");
        fortune.add("The truly generous share, even with the undeserving.");
        fortune.add("The value lies not within any particular thing, but in the desire placed on that thing.");
        fortune.add("There is a time for caution, but not for fear.");
        fortune.add("Those who care will make the effort.");
        fortune.add("Time and patience are called for many surprises await you!.");
        fortune.add("Welcome change.");
        fortune.add("What’s that in your eye? Oh…it’s a sparkle.");
    }

    public void setVelX(int x) {
        velX = x;
    }

    public void setVelY(int y) {
        velY = y;
    }

    public void setMsg() {
        Random r = new Random();
        if (fortune.size() == 0) {
            treasure.addMsg("Out of fortune");
        } else {
            msg = fortune.get(r.nextInt(fortune.size()));
            treasure.addMsg(msg);
            fortune.remove(msg);
        }
    }

    public void setFood(int x, int y) {
        food = new Food(x, y);
    }

    public void setTreasure(int x, int y) {
        treasure = new Treasure(x, y);
    }

    //EFFECT: Return player
    public Player getPlayer() {
        return player;
    }

    public Treasure getTreasure() {
        return treasure;
    }

    //EFFECT: Return true if game is over, false if game is not over
    public boolean getisGameOver() {
        return isGameOver;
    }

    public int getFoodScore() {
        return foodScore;
    }

    public int getTreasureScore() {
        return treasureScore;
    }

    public Pocket getPocket() {
        return pocket;
    }

    public int getVelY() {
        return velY;
    }

    public ArrayList<String> getFortune() {
        return fortune;
    }

    public Food getFood() {
        return food;
    }

    public int getVelX() {
        return velX;
    }
}
