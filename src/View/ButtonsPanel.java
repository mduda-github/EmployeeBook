package View;

import Model.PositionComponent;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class ButtonsPanel {
  private JPanel buttonsPanel;
  private JButton addButton;
  private JButton editButton;
  private JButton saveButton;
  private JButton deleteButton;

  public ButtonsPanel() {
    buttonsPanel = new JPanel(new GridBagLayout());

    // Add border
    buttonsPanel.setBorder(BorderFactory.createTitledBorder("Controls"));

    // Add buttons
    addButton = new JButton("Add");
    editButton = new JButton("Edit");
    saveButton = new JButton("Save");
    deleteButton = new JButton("Delete");

    // Set save button disabled
    saveButton.setEnabled(false);

    // Setting dimension for buttons
    Dimension addEmployeeLabelDimension = new Dimension(85, 25);
    addButton.setPreferredSize(addEmployeeLabelDimension);
    editButton.setPreferredSize(addEmployeeLabelDimension);
    saveButton.setPreferredSize(addEmployeeLabelDimension);
    deleteButton.setPreferredSize(addEmployeeLabelDimension);

    // Position elements of layout in panel
    GridBagConstraints c = new GridBagConstraints();
    c.insets = new Insets(0, 10, 0, 10);
    c.gridx = 0;
    c.gridy = 0;
    buttonsPanel.add(addButton, c);
    c.gridx = 0;
    c.gridy = 2;
    buttonsPanel.add(editButton, c);
    c.gridx = 0;
    c.gridy = 4;
    buttonsPanel.add(saveButton, c);
    c.gridx = 0;
    c.gridy = 6;
    buttonsPanel.add(deleteButton, c);
  }

  public JPanel getButtonsPanel() {
    return buttonsPanel;
  }

  public JButton getAddButton() {
    return addButton;
  }

  public JButton getEditButton() {
    return editButton;
  }

  public JButton getSaveButton() {
    return saveButton;
  }

  public JButton getDeleteButton() {
    return deleteButton;
  }
}
