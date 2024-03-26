package src.main;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
//All imported Package Classes:-

public class CurrencyConverter extends JFrame implements ActionListener {

    private JLabel labelAmount, labelFrom, labelTo, labelResult;
    private JTextField textFieldAmount;
    private JComboBox<String> comboBoxFrom, comboBoxTo;
    private JButton convertButton;
    private final double usdToInrRate = 83.40; // USD to INR conversion rate
    private final double inrToUsdRate = 0.012; // INR to USD conversion rate
    private final double usdToEuroRate = 0.92; // USD to Euro conversion rate
    private final double euroToUsdRate = 1.08; // Euro to USD conversion rate
    private final double inrToEuroRate = 0.011; // INR to Euro conversion rate
    private final double euroToInrRate = 90.40; // Euro to INR conversion rate
    public CurrencyConverter() {
        super("Currency Converter");

        // Set background color for the frame :-
        getContentPane().setBackground(new Color(168, 214, 230)); // Light blue color

        // Create labels :-
        labelAmount = new JLabel("Enter amount:");
        labelFrom = new JLabel("From currency:");
        labelTo = new JLabel("To currency:");
        labelResult = new JLabel("Converted amount:"); // New label for result

        // Setup for labels:-
        Font labelFont = new Font(Font.MONOSPACED, Font.BOLD, 16);
        labelAmount.setFont(labelFont);
        labelAmount.setForeground(Color.DARK_GRAY);
        labelAmount.setHorizontalAlignment(SwingConstants.CENTER);
        labelFrom.setFont(labelFont);
        labelFrom.setForeground(Color.BLACK);
        labelFrom.setHorizontalAlignment(SwingConstants.CENTER);
        labelTo.setFont(labelFont);
        labelTo.setForeground(Color.BLACK);
        labelTo.setHorizontalAlignment(SwingConstants.CENTER);
        labelResult.setFont(labelFont);
        labelResult.setForeground(Color.MAGENTA);
        labelResult.setHorizontalAlignment(SwingConstants.CENTER);
        // Create text field
        textFieldAmount = new JTextField(5); // Shortened size

        // Set font size for text field
        Font textFont = new Font(Font.SANS_SERIF, Font.PLAIN, 50);
        textFieldAmount.setFont(textFont);

        // Create combo boxes:-
        String[] currencies = {"USD", "INDIAN RUPEE", "Euro"};
        comboBoxFrom = new JComboBox<>(currencies);
        comboBoxTo = new JComboBox<>(currencies);
        // Set font size for combo boxes:-
        Font comboBoxFont = new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, 16);
        comboBoxFrom.setFont(comboBoxFont);
        comboBoxTo.setFont(comboBoxFont);

        // Create button
        convertButton = new JButton("Convert");
        convertButton.addActionListener(this); // Register this class as the listener

        // Layout components
        JPanel panel = new JPanel(new BorderLayout()); // Used BorderLayout for more flexible sizing
        panel.setBackground(new Color(156, 202, 217)); // Light blue color
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10)); // GridLayout for input components
        inputPanel.setBackground(new Color(174, 221, 233)); // Light blue color
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(labelAmount);
        panel.add(textFieldAmount);
        panel.add(labelFrom);
        panel.add(comboBoxFrom);
        panel.add(labelTo);
        panel.add(comboBoxTo);
        panel.add(labelResult);
        panel.add(convertButton);

        panel.add(inputPanel, BorderLayout.CENTER);
        panel.add(convertButton, BorderLayout.SOUTH);

        // Add panel to frame
        add(panel);

        //Set frame properties
        setSize(590, 400); 
        centerFrame();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void centerFrame() {
        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Calculate frame location for centering
        int x = (int) ((screenSize.getWidth() - getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - getHeight()) / 2);

        // Set frame location
        setLocation(x, y);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == convertButton) {
            String amountString = textFieldAmount.getText();
            try {
                double amount = Double.parseDouble(amountString);
                double convertedAmount;

                String fromCurrency = (String) comboBoxFrom.getSelectedItem();
                String toCurrency = (String) comboBoxTo.getSelectedItem();

                if (fromCurrency.equals(toCurrency)) {
                    JOptionPane.showMessageDialog(this, "Please select different currencies to convert.");
                    return;
                }

                if (fromCurrency.equals("USD")) {
                    if (toCurrency.equals("INDIAN RUPEE"))
                        convertedAmount = amount * usdToInrRate;
                    else if (toCurrency.equals("Euro"))
                        convertedAmount = amount * usdToEuroRate;
                    else
                        convertedAmount = amount; // No conversion needed
                } else if (fromCurrency.equals("INDIAN RUPEE")) {
                    if (toCurrency.equals("USD"))
                        convertedAmount = amount * inrToUsdRate;
                    else if (toCurrency.equals("Euro"))
                        convertedAmount = amount * inrToEuroRate;
                    else
                        convertedAmount = amount; 
                } else { // fromCurrency.equals("Euro")
                    if (toCurrency.equals("USD"))
                        convertedAmount = amount * euroToUsdRate;
                    else if (toCurrency.equals("INDIAN RUPEE"))
                        convertedAmount = amount * euroToInrRate;
                    else
                        convertedAmount = amount;
                }
                labelResult.setText(" = " + String.format("%.2f", convertedAmount) + " " + toCurrency);

                // Display result in a separate window:-
                JFrame resultFrame = new JFrame("Conversion Result");
                resultFrame.getContentPane().setBackground(new Color(160, 211, 228)); // Light blue color
                //resultFrame.setLocationRelativeTo(null);
                resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this frame
                Font resultFont = new Font(Font.SANS_SERIF, Font.BOLD, 24);
                JLabel resultLabel = new JLabel("    Converted amount is : " + String.format("%.2f", convertedAmount) + " " + toCurrency);
                resultLabel.setFont(resultFont); // Set font for result label
                resultFrame.add(resultLabel);
                resultFrame.pack();
                int x = (int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - resultFrame.getWidth()) / 2);
                resultFrame.setLocation(x, 0);
                resultFrame.setVisible(true);
                resultFrame.setSize(600, 200); // Set properties for result label




            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a number.");
            }
        }
    }

    public static void main(String[] args) {
        new CurrencyConverter();
    }
}
