package com.project;
import javax.swing.*;

public class FlashingText extends JApplet implements Runnable {
    private JLabel jlblText = new JLabel("Welcome", JLabel.CENTER); // create a label

    public FlashingText() {
        add(jlblText); // add a label
        new Thread(this).start(); // start a thread
    }

    @Override // Set the text on/off ever 200 milliseconds
    public void run() {
        try {
            while(true) {
                if(jlblText.getText() == null)
                    jlblText.setText("Welcome");
                else
                    jlblText.setText(null);
                Thread.sleep(2000);
            }
        } catch (InterruptedException ex) {

        }
    }
}
