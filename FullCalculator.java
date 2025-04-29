import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FullCalculator implements ActionListener {
    JFrame frame;
    JLabel displayLabel;
    String oldValue, operator;
    boolean isOperatorClicked = false;

    public FullCalculator() {
        frame = new JFrame("Calculator");
        frame.setSize(420, 600);
        frame.setLayout(null);
        frame.setLocation(400, 150);

        displayLabel = new JLabel(" ");
        displayLabel.setBounds(30, 40, 340, 60);
        displayLabel.setBackground(Color.BLACK);
        displayLabel.setOpaque(true);
        displayLabel.setForeground(Color.WHITE);
        displayLabel.setFont(new Font("Arial", Font.BOLD, 30));
        displayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        frame.add(displayLabel);

        String[] buttonTexts = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            ".", "0", "=", "+",
            "C"
        };

        int x = 30, y = 120;
        for (int i = 0; i < buttonTexts.length; i++) {
            JButton btn = new JButton(buttonTexts[i]);
            btn.setFont(new Font("Arial", Font.BOLD, 24));
            btn.setBounds(x, y, 80, 60);
            btn.addActionListener(this);
            frame.add(btn);
            x += 90;
            if ((i + 1) % 4 == 0) {
                x = 30;
                y += 70;
            }
        }

        // Clear button position adjustment
        frame.getContentPane().getComponent(frame.getContentPane().getComponentCount() - 1).setBounds(30, y, 360, 60);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new FullCalculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String clicked = ((JButton) e.getSource()).getText();

        if (clicked.matches("[0-9.]")) {
            if (isOperatorClicked) {
                displayLabel.setText(clicked);
                isOperatorClicked = false;
            } else {
                displayLabel.setText(displayLabel.getText().trim() + clicked);
            }
        } else if (clicked.matches("[+\\-*/]")) {
            oldValue = displayLabel.getText().trim();
            operator = clicked;
            isOperatorClicked = true;
        } else if (clicked.equals("=")) {
            try {
                String newValue = displayLabel.getText().trim();
                double op1 = Double.parseDouble(oldValue);
                double op2 = Double.parseDouble(newValue);
                double result = 0;

                switch (operator) {
                    case "+": result = op1 + op2; break;
                    case "-": result = op1 - op2; break;
                    case "*": result = op1 * op2; break;
                    case "/": 
                        if (op2 == 0) {
                            displayLabel.setText("Error: ÷0");
                            return;
                        }
                        result = op1 / op2; break;
                }

                displayLabel.setText(result + "");
            } catch (Exception ex) {
                displayLabel.setText("Error");
            }
        } else if (clicked.equals("C")) {
            displayLabel.setText(" ");
            oldValue = "";
            operator = "";
            isOperatorClicked = false;
        }
    }
}
