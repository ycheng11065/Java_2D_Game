package ui;

import model.Frame;
import model.Treasure;

import javax.swing.*;
import java.awt.*;

/**
 * Frame showing all the fortune message collected
 * Reference: SpaceInvader
 */

public class FortunePanel extends JFrame {
    private static final int LABEL_WIDTH = 50;
    private static final int LABEL_HEIGHT = 50;

    private static final String FORTUNE_TXT = "Your fortune: ";
    private Frame game;

    //EFFECT: Create a frame showing all fortune message from collected cookies
    public FortunePanel(Frame frame) {
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ychen\\OneDrive\\Desktop\\Fortune.jpg");
        setIconImage(icon);
        game = frame;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        setVisible(true);
        setTitle("Fortune Panel");

        GridLayout g1 = new GridLayout();
        g1.setRows(game.getPocket().getPocket().size() + 1);
        setLayout(g1);
        centerOnScreen();
    }

    //EFFECT: Create a label as header then turn all treasure fortune message within pocket into label
    public void update() {
        JLabel fortuneLbl = new JLabel(FORTUNE_TXT);
        fortuneLbl.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
        add(fortuneLbl);
        if (game.getisGameOver()) {
            for (Treasure next: game.getPocket().getPocket()) {
                JLabel a = new JLabel(next.getMsg());
                a.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
                add(a);
            }
        }
        pack();
    }

    //EFFECT: Centers frame
    private void centerOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }
}
