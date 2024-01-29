import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings {

    private JFrame settingsFrame;
    private int boardSize;
    private String difficulty;

    public Settings() {
        // Welcome message
        JOptionPane.showMessageDialog(null, "Welcome to Minesweeper!", "Welcome", JOptionPane.INFORMATION_MESSAGE);

        // Setup settings window
        setupSettingsWindow();
    }

    private void setupSettingsWindow() {
        settingsFrame = new JFrame("Minesweeper Settings");
        settingsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        settingsFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Board size input
        settingsFrame.add(new JLabel(" Board Size (Min 10):"), gbc);
        JTextField boardSizeInput = new JTextField(10);
        settingsFrame.add(boardSizeInput, gbc);

        // Difficulty selection
        settingsFrame.add(new JLabel(" Select Difficulty:"), gbc);
        JComboBox<String> difficultySelector = new JComboBox<>(new String[]{"Very Easy", "Easy", "Medium", "Hard", "Very Hard"});
        settingsFrame.add(difficultySelector, gbc);

        // Play button centered
        JButton playButton = new JButton("Play");
        gbc.fill = GridBagConstraints.CENTER;
        settingsFrame.add(playButton, gbc);

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    boardSize = Integer.parseInt(boardSizeInput.getText());
                    if (boardSize < 10) {
                        JOptionPane.showMessageDialog(settingsFrame, "Board size must be at least 10.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    difficulty = (String) difficultySelector.getSelectedItem();
                    settingsFrame.dispose(); // Close settings window
                    Minesweeper minesweeper = new Minesweeper(boardSize, difficulty); // Initialize game with selected settings
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(settingsFrame, "Please enter a valid integer for board size.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        settingsFrame.pack();
        settingsFrame.setLocationRelativeTo(null);
        settingsFrame.setVisible(true);
    }
}
