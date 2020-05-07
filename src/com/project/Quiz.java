package com.project;

import javax.swing.JOptionPane;

public class Quiz {
    public static void main(String[] args) {
        String question = "What is Superman real name ? \n";

        question += "A. Mark Kent \n";
        question += "B. Clark Kent \n";
        question += "C. Ckark Bent \n";
        question += "D. Makr Kent \n";


        String Answer = JOptionPane.showInputDialog(question);

        Answer = Answer.toUpperCase();

        if (Answer.equals("A")) {
            JOptionPane.showMessageDialog(null, "Incorrect, Try again");
        }
        if (Answer.equals("B")) {
            JOptionPane.showMessageDialog(null, "Correct");
        }

        if (Answer.equals("CD")) {
            JOptionPane.showMessageDialog(null, "Invalid answer, Try again");
        }


    }
}
