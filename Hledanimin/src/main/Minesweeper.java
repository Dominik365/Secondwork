/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

// Minesweeper class extends JFrame and implements a GUI for the game.
public class Minesweeper extends JFrame {
    // 2D array of JButtons for the game grid
    private JButton[][] buttons;
    // 2D array of booleans to store the presence of mines
    private boolean[][] mines;
    // 2D array of integers to store the number of mines surrounding each cell
    private int[][] surroundingMines;
    // variable to keep track of the number of cells uncovered
    private int uncoveredCells;

    // constructor sets up the GUI and initializes the game state
    public Minesweeper() {
        // sets the title of the window to "Minesweeper"
        setTitle("Minesweeper");
        // exits the program when the user closes the window
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // sets the layout of the window to a 10x10 grid of cells
        setLayout(new GridLayout(12, 12));

        // initializes the buttons array with JButtons
        buttons = new JButton[12][12];
        // initializes the mines array with false values (no mines placed yet)
        mines = new boolean[12][12];
        // initializes the surroundingMines array with 0 values (no mines counted yet)
        surroundingMines = new int[12][12];
        // sets the initial number of uncovered cells to 0
        uncoveredCells = 0;

        // loops through each cell in the grid and adds a JButton to the window
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                buttons[i][j] = new JButton();
                // adds a CellClickListener to each JButton to handle user input
                buttons[i][j].addActionListener(new CellClickListener(i, j));
                add(buttons[i][j]);
            }
        }

        // places mines randomly in the grid
        placeMines();
        // counts the number of mines surrounding each cell
        countSurroundingMines();

        // resizes the window to fit the buttons
        pack();
        // makes the window visible
        setVisible(true);
    }

    // Method to place mines randomly on the game board
private void placeMines() {
    // Create a Random object to generate random numbers
    Random random = new Random();
    // Keep track of the number of mines placed
    int placedMines = 0;
    // Loop until 10 mines have been placed
    while (placedMines < 20) {
        // Generate random x and y coordinates for a cell on the game board
        int i = random.nextInt(12);
        int j = random.nextInt(12);
        // If the cell at (i, j) does not already have a mine
        if (!mines[i][j]) {
            // Place a mine at (i, j)
            mines[i][j] = true;
            // Increase the count of placed mines
            placedMines++;
        }
    }
}


   private void countSurroundingMines() {
    // Loop through all cells of the game board
    for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 10; j++) {
            // If the current cell does not contain a mine
            if (!mines[i][j]) {
                int count = 0;
                // Check the cells surrounding the current cell
                // and increment the count if there is a mine
                // Top Cell
                if (i > 0 && mines[i - 1][j]) count++; // Top Cell
                if (i < 11 && mines[i + 1][j]) count++; // Bottom Cell
                if (j > 0 && mines[i][j - 1]) count++; // Left Cell
                if (j < 11 && mines[i][j + 1]) count++; // Right Cell
                if (i > 0 && j > 0 && mines[i - 1][j - 1]) count++; // Top Left Diagonal
                if (i < 11 && j < 11 && mines[i + 1][j + 1]) count++; // Bottom Right Diagonal
                if (i > 0 && j < 11 && mines[i - 1][j + 1]) count++; // Top Right Diagonal
                if (i < 11 && j > 0 && mines[i + 1][j - 1]) count++; // Bottom Left Diagonal
                // Store the count in the "surroundingMines" array
                surroundingMines[i][j] = count;
            }
        }
    }
}


   private void uncoverCell(int i, int j) {
    // If the current cell is already uncovered, do nothing
    if (!buttons[i][j].isEnabled()) return;

    // Disable the button to indicate that it has been uncovered
    buttons[i][j].setEnabled(false);
    // Increment the count of uncovered cells
    uncoveredCells++;

    // If the current cell contains a mine, the game is over
    if (mines[i][j]) {
        // Set the text of the button to "X" to indicate that it contains a mine
        buttons[i][j].setText("X");
        // Display a message box to inform the user that the game is over
        JOptionPane.showMessageDialog(this, "Game Over!");
        // Close the window to exit the program
        dispose();
    } else {
        // Set the text of the button to the number of surrounding mines
        buttons[i][j].setText(Integer.toString(surroundingMines[i][j]));
        // If the current cell has no surrounding mines, recursively uncover the surrounding cells
        if (surroundingMines[i][j] == 0) {
            uncoverSurroundingCells(i, j);
        }
        // If all non-mine cells have been uncovered, the game is won
        if (uncoveredCells == 100 - 10) {
            // Display a message box to inform the user that the game is won
            JOptionPane.showMessageDialog(this, "You Win!");
            // Close the window to exit the program
            dispose();
        }
    }
}

    // method to uncover surrounding cells
private void uncoverSurroundingCells(int i, int j) {
    // check if the cell above is within bounds and enabled, and if so, uncover it
    if (i > 0 && buttons[i - 1][j].isEnabled()) uncoverCell(i - 1, j);
    // check if the cell below is within bounds and enabled, and if so, uncover it
    if (i < 9 && buttons[i + 1][j].isEnabled()) uncoverCell(i + 1, j);
    // check if the cell to the left is within bounds and enabled, and if so, uncover it
    if (j > 0 && buttons[i][j - 1].isEnabled()) uncoverCell(i, j - 1);
    // check if the cell to the right is within bounds and enabled, and if so, uncover it
    if (j < 9 && buttons[i][j + 1].isEnabled()) uncoverCell(i, j + 1);
    // check if the top-left cell is within bounds and enabled, and if so, uncover it
    if (i > 0 && j > 0 && buttons[i - 1][j - 1].isEnabled()) uncoverCell(i - 1, j - 1);
    // check if the bottom-right cell is within bounds and enabled, and if so, uncover it
    if (i < 9 && j < 9 && buttons[i + 1][j + 1].isEnabled()) uncoverCell(i + 1, j + 1);
    // check if the top-right cell is within bounds and enabled, and if so, uncover it
    if (i > 0 && j < 9 && buttons[i - 1][j + 1].isEnabled()) uncoverCell(i - 1, j + 1);
    // check if the bottom-left cell is within bounds and enabled, and if so, uncover it
    if (i < 9 && j > 0 && buttons[i + 1][j - 1].isEnabled()) uncoverCell(i + 1, j - 1);
}


   // method to display "You won!" message and end the game
private void winGame() {
    // show a message dialog with the message "You won!"
    JOptionPane.showMessageDialog(this, "You won!");
    // exit the game with status code 0 (success)
    System.exit(0);
}

   // method to display the minefield and a "You lost." message, and end the game
private void loseGame() {
    // loop through the 2D array of buttons
    for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 10; j++) {
            // if the current cell is a mine, set its text to "*"
            if (mines[i][j]) {
                buttons[i][j].setText("*");
            }
            // disable the current button
            buttons[i][j].setEnabled(false);
        }
    }
    // show a message dialog with the message "You lost."
    JOptionPane.showMessageDialog(this, "You lost.");
    // exit the game with status code 0 (success)
    System.exit(0);
}


// inner class that implements the ActionListener interface to handle button clicks
private class CellClickListener implements ActionListener {
    // instance variables to store the row and column indices of the button
    private int i;
    private int j;

    // constructor to initialize the instance variables
    public CellClickListener(int i, int j) {
        this.i = i;
        this.j = j;
    }

    // method to handle button clicks
    public void actionPerformed(ActionEvent e) {
        // call the uncoverCell method with the row and column indices of the button
        uncoverCell(i, j);
    }
}


}
