package ui;

import model.Frame;
import model.GameFile;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Menu extends JFrame implements ActionListener {
    JButton button1;
    JButton button2;

    private static final String JSON_STORE = "./data/gameFile.json";
    private GameFile gameFile;
    private JsonReader jsonReader;

    public Menu() {

        gameFile = new GameFile("My gamefile");
//        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);


        button1 = new JButton("Start game");
        button1.addActionListener(this);

        button2 = new JButton("Load Fortune");
        button2.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(button1);
        panel.add(button2);

        add(panel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Fortune Chaser");
        setPreferredSize(new Dimension(model.Frame.WIDTH, Frame.HEIGHT));
        pack();
        setVisible(true);
        centerOnScreen();

    }

    private void centerOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            try {
                new FortuneChaser(gameFile, JSON_STORE);
                this.dispose();

            } catch (FileNotFoundException ex) {
                System.out.println("Unable to run application: file not found");
            }
        } else if (e.getSource() == button2) {
            loadSaveFile();
            System.out.println("load");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads gamefile from file
    private void loadSaveFile() {
        try {
            gameFile = jsonReader.read();
            System.out.println("Loaded " + gameFile.getName() + " from " + JSON_STORE);
//            FortunePanel panel = new FortunePanel();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}