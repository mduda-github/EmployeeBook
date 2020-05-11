package View;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;

public class SearchBySalaryPanel {
  private JPanel searchBySalaryPanel;
  private JLabel fromLabel;
  private JLabel toLabel;
  private JFormattedTextField fromTextField;
  private JFormattedTextField toTextField;
  private JButton searchButton;
  private JButton clearButton;

  public SearchBySalaryPanel() {
    searchBySalaryPanel = new JPanel();
    searchBySalaryPanel.setLayout(new GridBagLayout());

    // Add border
    searchBySalaryPanel.setBorder(
      BorderFactory.createTitledBorder("Search by salary")
    );

    // Add labels
    fromLabel = new JLabel("From :");
    toLabel = new JLabel("To :");

    // Add inputs
    fromTextField = new JFormattedTextField();
    toTextField = new JFormattedTextField();

    // Setting dimension for labels
    Dimension labelDimension = new Dimension(140, 25);
    fromTextField.setPreferredSize(labelDimension);
    toTextField.setPreferredSize(labelDimension);

    // Setting dimension for inputs
    Dimension textFieldDimension = new Dimension(200, 25);
    fromTextField.setPreferredSize(textFieldDimension);
    toTextField.setPreferredSize(textFieldDimension);

    // Add search button
    searchButton = new JButton("Search");
    clearButton = new JButton("Clear");

    // Position elements of layout in panel
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.insets = new Insets(2, 10, 2, 10);
    c.gridx = 0;
    c.ipadx = 50;
    searchBySalaryPanel.add(fromLabel, c);
    c.gridx = 1;
    c.ipadx = 50;
    searchBySalaryPanel.add(fromTextField, c);
    c.gridx = 2;
    c.ipadx = 50;
    searchBySalaryPanel.add(toLabel, c);
    c.gridx = 3;
    c.ipadx = 50;
    searchBySalaryPanel.add(toTextField, c);
    c.gridx = 4;
    c.ipadx = 50;
    searchBySalaryPanel.add(searchButton, c);
    c.gridx = 5;
    c.ipadx = 50;
    searchBySalaryPanel.add(clearButton, c);

    clearButton.addActionListener(e -> clearInputs());
  }

  public JPanel getSearchBySalaryPanel() {
    return searchBySalaryPanel;
  }

  protected void clearInputs() {
    fromTextField.setText("");
    fromTextField.setBorder(BorderFactory.createEmptyBorder());
    toTextField.setText("");
    toTextField.setBorder(BorderFactory.createEmptyBorder());
  }

  protected boolean validateTextField(JTextField textField) {
    if (!validateInput(textField.getText())) {
      textField.setBorder(BorderFactory.createLineBorder(Color.RED));
      return false;
    }

    textField.setBorder(BorderFactory.createEmptyBorder());
    return true;
  }

  protected boolean validateInputs() {
    if (!validateTextField(fromTextField) && !validateTextField(toTextField)) {
      return false;
    }
    if (getFromSalary() > getToSalary()) {
      Frame.showDialog("Error", "Enter correct values");
      return false;
    }
    return true;
  }

  protected boolean validateInput(String text) {
    if (text.equals("")) {
      return false;
    }

    Pattern pattern = Pattern.compile("^\\d+$");
    Matcher matcher = pattern.matcher(text);

    return matcher.matches();
  }

  public int getFromSalary() {
    return Integer.parseInt(fromTextField.getText());
  }

  public int getToSalary() {
    return Integer.parseInt(toTextField.getText());
  }

  public JButton getSearchButton() {
    return searchButton;
  }

  public JButton getClearButton() {
    return clearButton;
  }
}
