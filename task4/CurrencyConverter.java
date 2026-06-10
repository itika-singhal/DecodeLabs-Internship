package task4;

import javax.swing.*;
import java.awt.event.*;

public class CurrencyConverter extends JFrame implements ActionListener {

    JComboBox<String> fromBox, toBox;
    JTextField amountField, resultField;
    JButton convertBtn, clearBtn;

    CurrencyConverter() {

        setTitle("Currency Converter");
        setSize(500, 350);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel heading = new JLabel("Currency Converter");
        heading.setBounds(180, 20, 200, 30);
        add(heading);

        String[] currencies = {
                "USD", "INR", "EUR", "GBP", "JPY", "AUD"
        };

        JLabel l1 = new JLabel("From Currency");
        l1.setBounds(50, 80, 120, 25);
        add(l1);

        fromBox = new JComboBox<>(currencies);
        fromBox.setBounds(200, 80, 150, 25);
        add(fromBox);

        JLabel l2 = new JLabel("To Currency");
        l2.setBounds(50, 120, 120, 25);
        add(l2);

        toBox = new JComboBox<>(currencies);
        toBox.setBounds(200, 120, 150, 25);
        add(toBox);

        JLabel l3 = new JLabel("Amount");
        l3.setBounds(50, 160, 120, 25);
        add(l3);

        amountField = new JTextField();
        amountField.setBounds(200, 160, 150, 25);
        add(amountField);

        JLabel l4 = new JLabel("Converted Amount");
        l4.setBounds(50, 200, 150, 25);
        add(l4);

        resultField = new JTextField();
        resultField.setBounds(200, 200, 150, 25);
        resultField.setEditable(false);
        add(resultField);

        convertBtn = new JButton("Convert");
        convertBtn.setBounds(100, 260, 100, 30);
        convertBtn.addActionListener(this);
        add(convertBtn);

        clearBtn = new JButton("Clear");
        clearBtn.setBounds(250, 260, 100, 30);
        clearBtn.addActionListener(this);
        add(clearBtn);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == convertBtn) {

            try {

                double amount =
                        Double.parseDouble(amountField.getText());

                String from =
                        fromBox.getSelectedItem().toString();

                String to =
                        toBox.getSelectedItem().toString();

                double rate =
                        ExchangeRateAPI.getRate(from, to);

                if (rate == -1) {

                    JOptionPane.showMessageDialog(
                            this,
                            "API Error!"
                    );
                    return;
                }

                double result = amount * rate;

                resultField.setText(
                        String.format("%.2f", result)
                );

            } catch (Exception e) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter Valid Amount!"
                );
            }
        }

        if (ae.getSource() == clearBtn) {

            amountField.setText("");
            resultField.setText("");
        }
    }

    public static void main(String[] args) {

        new CurrencyConverter();
    }
}