package View;

import Model.Employee;
import Model.TableModel;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.TableColumn;

public class Frame {
  private JFrame frame;
  private Dimension screenDim;
  private AddEmployeePanel addEmployeePanel;
  private ButtonsPanel buttonsPanel;
  private ImportExportPanel importExportPanel;

  private TableModel tableModel;
  private JTable table;
  private TableColumn column;
  private JScrollPane scrollPane;

  public Frame() {
    // Create frame
    frame = new JFrame();
    frame.getContentPane().setLayout(new BorderLayout());

    // Add Add employee panel to frame
    addEmployeePanel = new AddEmployeePanel();
    frame
      .getContentPane()
      .add(addEmployeePanel.getAddEmployeePanel(), BorderLayout.WEST);

    // Add Buttons panel to frame
    buttonsPanel = new ButtonsPanel();
    frame
      .getContentPane()
      .add(buttonsPanel.getButtonsPanel(), BorderLayout.CENTER);

    // Add ImportExport panel to frame
    importExportPanel = new ImportExportPanel();
    frame
      .getContentPane()
      .add(importExportPanel.getImportExportPanel(), BorderLayout.EAST);

    tableModel = new TableModel();
    table = new JTable(tableModel);

    for (int i = 0; i < 6; i++) {
      column = table.getColumnModel().getColumn(i);
      if (i == 0) {
        column.setPreferredWidth(20);
      } else if (i == 3 || i == 4) {
        column.setPreferredWidth(130);
      } else {
        column.setPreferredWidth(80);
      }
    }

    scrollPane = new JScrollPane(table);
    scrollPane.setBorder(
      BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)
    );

    frame.getContentPane().add(scrollPane, BorderLayout.SOUTH);

    // Add new employee to the table
    buttonsPanel.getAddButton().addActionListener(e -> addEmployeeAction());

    // Delete an employee from the table
    buttonsPanel
      .getDeleteButton()
      .addActionListener(e -> deleteEmployeeAction());

    // Edit an employee
    buttonsPanel.getEditButton().addActionListener(e -> editEmployeeAction());

    // Save an employee
    buttonsPanel.getSaveButton().addActionListener(e -> saveEmployeeAction());

    screenDim = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setSize(800, 650);
    frame.setLocation(screenDim.width / 4, screenDim.height / 4);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  public void addEmployeeAction() {
    if (addEmployeePanel.validateInputs()) {
      tableModel.addEmployee(addEmployeePanel.getEmployee());
      addEmployeePanel.clearInputs();
    }
    buttonsPanel.getAddButton().setFocusPainted(false);
  }

  public void deleteEmployeeAction() {
    if (table.getSelectedRow() != -1) {
      tableModel.deleteEmployee(table.getSelectedRow());
      addEmployeePanel.clearInputs();
      buttonsPanel.getDeleteButton().setFocusPainted(false);

      // Disable save button and enable add button
      buttonsPanel.getAddButton().setEnabled(true);
      buttonsPanel.getSaveButton().setEnabled(false);

      showDialog("Info", "Selected employee has been deleted");
    } else {
      buttonsPanel.getDeleteButton().setFocusPainted(false);
      showDialog("Error", "No employee has been selected");
    }
  }

  public void editEmployeeAction() {
    if (table.getSelectedRow() != -1) {
      // Change name of the panel to edit
      addEmployeePanel.changeTitle("Edit an employee");

      // Disable add button and enable save button
      buttonsPanel.getAddButton().setEnabled(false);
      buttonsPanel.getSaveButton().setEnabled(true);

      Employee employee = tableModel.getEmployee(table.getSelectedRow());
      addEmployeePanel.setEmployeeDataInInputs(employee);

      buttonsPanel.getEditButton().setFocusPainted(false);
    } else {
      buttonsPanel.getEditButton().setFocusPainted(false);
      showDialog("Error", "No employee has been selected");
    }
  }

  public void saveEmployeeAction() {
    if (table.getSelectedRow() != -1) {
      if (addEmployeePanel.validateInputs()) {
        tableModel.updateEmployee(
          table.getSelectedRow(),
          addEmployeePanel.getEmployee()
        );
        showDialog("Info", "Selected employee has been updated");

        // Disable save button and enable add button
        buttonsPanel.getAddButton().setEnabled(true);
        buttonsPanel.getSaveButton().setEnabled(false);

        // Change name of the panel
        addEmployeePanel.changeTitle("Add an employee");

        addEmployeePanel.clearInputs();
        buttonsPanel.getSaveButton().setFocusPainted(false);
      }
      buttonsPanel.getAddButton().setFocusPainted(false);
    }
  }

  public void showDialog(String type, String message) {
    if (type.equals("Info")) {
      JOptionPane.showMessageDialog(
        null,
        message,
        "Info",
        JOptionPane.INFORMATION_MESSAGE
      );
    }
    if (type.equals("Error")) {
      JOptionPane.showMessageDialog(
        null,
        message,
        "Error",
        JOptionPane.ERROR_MESSAGE
      );
    }
  }
}
