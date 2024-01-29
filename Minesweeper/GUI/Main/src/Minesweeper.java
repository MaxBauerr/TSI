import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Minesweeper {

    private final int boardSize;
    private final String difficulty;
    private boolean isFirstClick = true;
    private final ArrayList<JButton> buttons = new ArrayList<>();
    private final Set<Integer> mineLocations = new HashSet<>();
    private int remainingSafeCells;
    private JLabel remainingCellsLabel;
    private int totalMines;
    private JLabel totalMinesLabel;

    public Minesweeper(int boardSize, String difficulty) {
        this.boardSize = boardSize;
        this.difficulty = difficulty;
        this.remainingSafeCells = boardSize * boardSize; // Initially, all cells are safe
        initializeGameBoard();
    }

    private void initializeGameBoard() {
        JFrame gameFrame = new JFrame("Minesweeper Game");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setLayout(new BorderLayout());

        // Create labels
        remainingCellsLabel = new JLabel("Remaining Safe Cells: " + remainingSafeCells + " | ");
        totalMinesLabel = new JLabel("Total Mines: " + totalMines);  // Assuming totalMines is defined

        // Create a panel to hold the labels
        JPanel labelsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        labelsPanel.add(remainingCellsLabel);
        labelsPanel.add(totalMinesLabel);

        // Add the panel to the NORTH region of the BorderLayout
        gameFrame.add(labelsPanel, BorderLayout.NORTH);


        JPanel boardPanel = new JPanel(new GridLayout(boardSize, boardSize));
        gameFrame.add(boardPanel, BorderLayout.CENTER);

        // int frameSize = 400;
        // gameFrame.setSize(frameSize, frameSize + 50); // Additional space for the label
        gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        for (int i = 0; i < boardSize * boardSize; i++) {
            JButton button = new JButton();
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (SwingUtilities.isRightMouseButton(e)) {
                        // Right-click action for flag
                    } else {
                        // Left-click action
                        if (isFirstClick) {
                            isFirstClick = false;
                            generateBombs(buttons.indexOf(button));
                            updateRemainingSafeCells();
                        }
                        revealButton(buttons.indexOf(button));
                    }
                }
            });
            buttons.add(button);
            boardPanel.add(button);
        }

        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
    }

    private void generateBombs(int firstClickIndex) {
        int minMines, maxMines;
        int totalCells = boardSize * boardSize;
        Random random = new Random();

        // Determine the number of mines based on difficulty
        switch (difficulty) {
            case "Very Easy":
                minMines = totalCells * 5 / 100;
                maxMines = totalCells * 10 / 100;
                break;
            case "Easy":
                minMines = totalCells * 10 / 100;
                maxMines = totalCells * 20 / 100;
                break;
            case "Medium":
                minMines = totalCells * 20 / 100;
                maxMines = totalCells * 35 / 100;
                break;
            case "Hard":
                minMines = totalCells * 35 / 100;
                maxMines = totalCells * 55 / 100;
                break;
            case "Very Hard":
                minMines = totalCells * 55 / 100;
                maxMines = totalCells * 80 / 100;
                break;
            default:
                throw new IllegalStateException("Unexpected difficulty: " + difficulty);
        }

        // Randomly choose the number of mines between minMines and maxMines
        int numMines = random.nextInt(maxMines - minMines + 1) + minMines;

        // Place the mines
        while (mineLocations.size() < numMines) {
            int mineIndex = random.nextInt(totalCells);
            // Ensure the mine is not placed on the first click or where a mine already exists
            if (mineIndex != firstClickIndex) {
                mineLocations.add(mineIndex);
            }
        }

        // Update remaining safe cells count (total cells - number of mines)
        remainingSafeCells -= mineLocations.size();
        updateRemainingSafeCells();
        updateMineCount();
    }

    private void revealButton(int index) {
        JButton button = buttons.get(index);
        if (!button.isEnabled()) {
            return;
        }
        // If the button is a mine, end the game
        if (mineLocations.contains(index)) {
            button.setText("M");
            button.setForeground(Color.RED); // Set mine text to red for visibility
            JOptionPane.showMessageDialog(null, "Game Over! You hit a mine.");
            System.exit(0); // or reset the game
        } else {
            // Calculate adjacent mines
            int mines = countAdjacentMines(index);

            // Only set the text if mines are greater or equal to 0
            //button.setForeground(Color.RED);
            if (mines >= 0) {
                //System.out.println(mines);
                button.setText(String.valueOf(mines));
                button.setForeground(getColorForNumber(mines));
                //button.setForeground(Color.RED);
            }

            //button.setEnabled(false); // Disable the button after revealing
            remainingSafeCells--;

            // If no adjacent mines, recursively reveal surrounding cells
            if (mines == 0) {
                revealAdjacentCells(index);
            }

            updateRemainingSafeCells();
            // Check for win condition
            if (remainingSafeCells == 0) {
                JOptionPane.showMessageDialog(null, "Congratulations, you won!");
                System.exit(0); // or reset the game
            }

        }
    }

    private Color getColorForNumber(int number) {
        switch (number) {
            case 1: return Color.yellow;
            case 2: return Color.orange;
            case 3: return Color.pink;
            case 4: return Color.red;
            case 5: return Color.blue;
            case 6: return Color.CYAN;
            case 7: return Color.gray;
            case 0: return Color.green;
            default:
                throw new IllegalStateException("Unexpected value: " + number);
        }
    }


    private int countAdjacentMines(int index) {
        int mines = 0;
        int row = index / boardSize;
        int col = index % boardSize;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int adjRow = row + i;
                int adjCol = col + j;
                if (adjRow >= 0 && adjRow < boardSize && adjCol >= 0 && adjCol < boardSize) {
                    int adjIndex = adjRow * boardSize + adjCol;
                    if (mineLocations.contains(adjIndex)) {
                        mines++;
                    }
                }
            }
        }

        return mines;
    }

    private void revealAdjacentCells(int index) {
        int row = index / boardSize;
        int col = index % boardSize;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int adjRow = row + i;
                int adjCol = col + j;
                if (adjRow >= 0 && adjRow < boardSize && adjCol >= 0 && adjCol < boardSize) {
                    int adjIndex = adjRow * boardSize + adjCol;
                    JButton adjButton = buttons.get(adjIndex);
                    if (!adjButton.isEnabled() || mineLocations.contains(adjIndex)) {
                        continue; // Skip if already revealed or is a mine
                    }

                    // Recursively reveal adjacent cells with 0 adjacent mines
                    int adjacentMines = countAdjacentMines(adjIndex);
                    adjButton.setText(String.valueOf(adjacentMines));;
                    adjButton.setForeground(getColorForNumber(adjacentMines));
                    adjButton.setEnabled(false);
                    remainingSafeCells--;

                    if (adjacentMines == 0) {
                        revealAdjacentCells(adjIndex); // Recursive call
                    }
                }
            }
        }
    }


    private void updateRemainingSafeCells() {
        // Update remaining safe cells count
        remainingCellsLabel.setText("Remaining Safe Cells: " + remainingSafeCells + " | ");
    }

    private void updateMineCount() {
        totalMines = mineLocations.size();
        totalMinesLabel.setText(("Total Mines: " + totalMines));
    }
}
