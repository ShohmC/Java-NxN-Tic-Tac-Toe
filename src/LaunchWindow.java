import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LaunchWindow implements ActionListener {
    int dimensions;
    JFrame frame = new JFrame();
    JTextField textField = new JTextField();
    JLabel label = new JLabel("Pick a specific dimension you want to play with");
    JLabel label2 = new JLabel("Please pick an integer between 1 - 30");
    JButton button;

    LaunchWindow() {
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label2.setFont(new Font("Arial", Font.PLAIN, 14));

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(label, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(label2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        textField.setPreferredSize(new Dimension(100, 30));
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(textField, gbc);

        button = new JButton("Press Me");
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        button.setFocusable(false);
        button.addActionListener(this);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(button, gbc);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            int enteredDimension = getInput();
            if (enteredDimension >= 1 && enteredDimension <= 30) {
                dimensions = enteredDimension;
                frame.dispose();
                new TicTacToe(dimensions);
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter a number between 1 and 30", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public int getInput() {
        try {
            String text = textField.getText();
            return Integer.parseInt(text);
        } catch (NumberFormatException ex) {
            return -1;
        }
    }
}

