package View;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddEmployeePanel {

    private JPanel addEmployeePanel;
    private JLabel nameLabel;
    private JLabel validationLabel;
    private JLabel surnameLabel;
    private JLabel positionLabel;
    private JLabel dateOfEmploymentLabel;
    private JLabel salaryLabel;
    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JComboBox positionComboBox;
    private JFormattedTextField dateOfEmploymentFormattedTextField;
    private JFormattedTextField salaryFormattedTextField;


    public AddEmployeePanel() {

        addEmployeePanel = new JPanel(new GridBagLayout());

        // Add border title
        TitledBorder title = BorderFactory.createTitledBorder("Add an employee");
        addEmployeePanel.setBorder(title);

        // Add labels
        nameLabel = new JLabel("Name :");
        surnameLabel = new JLabel("Surname :");
        positionLabel = new JLabel("Position :");
        dateOfEmploymentLabel = new JLabel("Date of employment :");
        salaryLabel = new JLabel("Salary :");
        validationLabel = new JLabel("");
        validationLabel.setForeground(Color.red);

        // Add inputs
        nameTextField = new JTextField();
        surnameTextField = new JTextField();
        positionComboBox = new JComboBox(Model.PositionComponent.values());
        dateOfEmploymentFormattedTextField = new JFormattedTextField();
        dateOfEmploymentFormattedTextField.setText("dd-mm-yyyy");

        // Add formatter for salary input
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);
        salaryFormattedTextField = new JFormattedTextField();

        // Setting dimension for labels
        Dimension addEmployeeLabelDimension = new Dimension(140,25);
        nameLabel.setPreferredSize(addEmployeeLabelDimension);
        surnameLabel.setPreferredSize(addEmployeeLabelDimension);
        positionLabel.setPreferredSize(addEmployeeLabelDimension);
        dateOfEmploymentLabel.setPreferredSize(addEmployeeLabelDimension);
        salaryLabel.setPreferredSize(addEmployeeLabelDimension);
        validationLabel.setPreferredSize(addEmployeeLabelDimension);

        // Setting dimension for inputs
        Dimension addEmployeeTextFieldDimension = new Dimension(200,25);
        nameTextField.setPreferredSize(addEmployeeTextFieldDimension);
        surnameTextField.setPreferredSize(addEmployeeTextFieldDimension);
        positionComboBox.setPreferredSize(addEmployeeTextFieldDimension);
        dateOfEmploymentFormattedTextField.setPreferredSize(addEmployeeTextFieldDimension);
        salaryFormattedTextField.setPreferredSize(addEmployeeTextFieldDimension);

        // Position elements of layout in panel
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(2,10,2,10);
        c.gridx = 0;
        c.gridy = 1;
        addEmployeePanel.add(nameLabel, c);
        c.gridx = 1;
        c.gridy = 1;
        addEmployeePanel.add(nameTextField, c);
        c.gridx = 0;
        c.gridy = 2;
        addEmployeePanel.add(surnameLabel, c);
        c.gridx = 1;
        c.gridy = 2;
        addEmployeePanel.add(surnameTextField, c);
        c.gridx = 0;
        c.gridy = 3;
        addEmployeePanel.add(positionLabel, c);
        c.gridx = 1;
        c.gridy = 3;
        addEmployeePanel.add(positionComboBox, c);
        c.gridx = 0;
        c.gridy = 4;
        addEmployeePanel.add(dateOfEmploymentLabel, c);
        c.gridx = 1;
        c.gridy = 4;
        addEmployeePanel.add(dateOfEmploymentFormattedTextField, c);
        c.gridx = 0;
        c.gridy = 5;
        addEmployeePanel.add(salaryLabel, c);
        c.gridx = 1;
        c.gridy = 5;
        addEmployeePanel.add(salaryFormattedTextField, c);
        c.gridx = 1;
        c.gridy = 6;
        addEmployeePanel.add(validationLabel, c);

    }

    protected void clearInputs() {
        nameTextField.setText("");
        surnameTextField.setText("");
        positionComboBox.setSelectedIndex(0);
        dateOfEmploymentFormattedTextField.setText("dd-mm-yyyy");
        salaryFormattedTextField.setText("");
        validationLabel.setText("");
    }

    protected boolean validateInputs() {

        String stringPattern = "^[a-zA-ZĄąĆćĘęŁłŃńÓóŚśŹźŻż-]+$";
        String datePattern = "^(0[1-9]|[12]\\d|3[01])-(0[1-9]|1[012])-(19|20)\\d\\d$";
        String salaryPattern = "^\\d*$";

        if (!validateInput(nameTextField.getText(), stringPattern)) {
            validationLabel.setText("Incorrect name");
            return false;
        }

        if (!validateInput(surnameTextField.getText(), stringPattern)) {
            validationLabel.setText("Incorrect surname");
            return false;
        }

        if (!validateInput(dateOfEmploymentFormattedTextField.getText(), datePattern)) {
            validationLabel.setText("Incorrect date");
            return false;
        }

        if (!validateInput(salaryFormattedTextField.getText(), salaryPattern)) {
            validationLabel.setText("Incorrect salary");
            return false;
        }


        return true;
    }

    protected boolean validateInput(String text, String pat) {
        if (text == "") {
            return false;
        }

        Pattern pattern = Pattern.compile(pat);
        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()) {
            return true;
        }

        return false;
    }

    public JPanel getAddEmployeePanel() {
        return addEmployeePanel;
    }
    public JTextField getNameTextField() {
        return nameTextField;
    }
    public JTextField getSurnameTextField() {
        return surnameTextField;
    }
    public JComboBox getPositionComboBox() {
        return positionComboBox;
    }
    public JFormattedTextField getDateOfEmploymentFormattedTextField() {
        return dateOfEmploymentFormattedTextField;
    }
    public JFormattedTextField getSalaryFormattedTextField() {
        return salaryFormattedTextField;
    }

}
