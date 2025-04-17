import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends Game implements ActionListener {
    private JFrame frame;
    private JButton[][] buttons;
    private JLabel statusLabel;
    private JButton restartButton;

    public TicTacToe() {
        frame = new JFrame("Tic Tac Toe");
        frame.getContentPane().setBackground(new Color(0,255,255));
        frame.setResizable(false);
        buttons = new JButton[3][3];
        statusLabel = new JLabel("                                Player " + currentPlayer + "'s turn");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 20));
        statusLabel.setForeground(Color.black);
    
        JPanel panel = new JPanel(new GridLayout(3, 3));
        panel.setPreferredSize(new Dimension(400, 400));
        Font font = new Font("Arial", Font.BOLD, 100);
    
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(font);
                buttons[i][j].setContentAreaFilled(false);
                buttons[i][j].setOpaque(true);
                buttons[i][j].setFocusable(false);
                buttons[i][j].setBorder(BorderFactory.createLineBorder(new Color(106, 121, 133), 3));
                buttons[i][j].addActionListener(this);
                if ((i + j) % 2 != 0) {
                    buttons[i][j].setBackground(new Color(240, 128, 128));
                } else {
                    buttons[i][j].setBackground(new Color(0, 128, 128));
                }
                buttons[i][j].setBackground(new Color(17, 27, 38));
                panel.add(buttons[i][j]);
            }
        }
    
        restartButton = new JButton("Restart");
        restartButton.setFont(new Font("Arial", Font.BOLD, 28));
        restartButton.setForeground(new Color(22, 12, 19));
        restartButton.setBackground(new Color(215, 51, 133));
        restartButton.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        restartButton.setFocusable(false);
        restartButton.setOpaque(true);
        restartButton.setVisible(false);
        restartButton.setEnabled(false);
        restartButton.addActionListener(this);
    
        JPanel paddedPanel = new JPanel(new BorderLayout());
        paddedPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        paddedPanel.add(panel, BorderLayout.CENTER);
        paddedPanel.add(restartButton, BorderLayout.SOUTH);
        paddedPanel.setBackground(new Color(17, 27, 38));
    
        frame.setLayout(new BorderLayout());
        frame.add(statusLabel, BorderLayout.NORTH);
        frame.add(paddedPanel, BorderLayout.CENTER);
    
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == restartButton) {
            resetGame();
            return;
        }
    
        JButton clicked = (JButton) e.getSource();
    
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (clicked == buttons[i][j]) {
                    if (board[i][j] == '-' && clicked.getText().equals("")) {
                        board[i][j] = currentPlayer;
                        if (currentPlayer == 'X') {
                            clicked.setText(String.valueOf(currentPlayer));
                            clicked.setForeground(new Color(24, 188, 156));
                        } else {
                            clicked.setText(String.valueOf(currentPlayer));
                            clicked.setForeground(new Color(239, 102, 118));
                        }
    
                        if (checkWin()) {
                            statusLabel.setForeground(new Color(255,215,0));
                            frame.getContentPane().setBackground(new Color(100, 13, 95));
                            statusLabel.setText("                                Player " + currentPlayer + " wins!");
                            disableButtons();
                            restartButton.setVisible(true);
                            restartButton.setEnabled(true);
                        } else if (isBoardFull()) {
                            statusLabel.setText("                                   It's a draw!");
                            restartButton.setVisible(true);
                            restartButton.setEnabled(true);
                        } else {
                            switchPlayer();
                            statusLabel.setText("                                Player " + currentPlayer + "'s turn");
                        }
                    }
                }
            }
        }
    }
    
    private void disableButtons() {
        for (JButton[] row : buttons)
            for (JButton btn : row)
                btn.setEnabled(false);
    }
    
    private void resetGame() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
                board[i][j] = '-';
            }
        statusLabel.setForeground(Color.BLACK);
        frame.getContentPane().setBackground(new Color(0, 255, 255));
        if(currentPlayer == 'X'){
            currentPlayer = 'O';
        }
        else{
            currentPlayer = 'X';
        }
        statusLabel.setText("                                Player " + currentPlayer + "'s turn");
        restartButton.setVisible(false);
        restartButton.setEnabled(false);
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}