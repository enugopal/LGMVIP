import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class CurrencyConverter extends JFrame {

    private JTextField inputField;
    private JComboBox<String> fromComboBox;
    private JComboBox<String> toComboBox;
    private JLabel resultLabel;

    private Map<String, Double> conversionRates;

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        JLabel inputLabel = new JLabel("Enter amount:");
        inputField = new JTextField(15);

        fromComboBox = new JComboBox<>();
        fromComboBox.addItem("USD");
        fromComboBox.addItem("GBP");
        fromComboBox.addItem("JPY");
        fromComboBox.addItem("CAD");
        fromComboBox.addItem("INR");
        fromComboBox.addItem("EUR");

        toComboBox = new JComboBox<>();
        toComboBox.addItem("USD");
        toComboBox.addItem("EUR");
        toComboBox.addItem("INR");
        toComboBox.addItem("JPY");
        toComboBox.addItem("CAD");
        toComboBox.addItem("GBP");

        JButton convertButton = new JButton("Convert");
        resultLabel = new JLabel();

        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fromCurrency = fromComboBox.getSelectedItem().toString();
                String toCurrency = toComboBox.getSelectedItem().toString();
                double amount = Double.parseDouble(inputField.getText());
                double convertedAmount = convertCurrency(amount, fromCurrency, toCurrency);
                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                resultLabel.setText(decimalFormat.format(amount) + " " + fromCurrency + " = "
                        + decimalFormat.format(convertedAmount) + " " + toCurrency);
            }
        });

        add(inputLabel);
        add(inputField);
        add(fromComboBox);
        add(new JLabel("to"));
        add(toComboBox);
        add(convertButton);
        add(resultLabel);

        setVisible(true);

        // Initialize the conversion rates
        initializeConversionRates();
    }

    private void initializeConversionRates() {
        conversionRates = new HashMap<>();
        conversionRates.put("USD", 1.0);
        conversionRates.put("EUR", 0.85);
        conversionRates.put("GBP", 0.72);
        conversionRates.put("JPY", 109.36);
        conversionRates.put("CAD", 1.21);
        conversionRates.put("INR", 74.57);

    }

    private double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        double fromRate = conversionRates.get(fromCurrency);
        double toRate = conversionRates.get(toCurrency);
        return amount / fromRate * toRate;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CurrencyConverter();
            }
        });
    }
}
