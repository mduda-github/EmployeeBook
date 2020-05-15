package View;

import Model.Employee;
import Model.PositionComponent;
import Model.TableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

public class Frame {
  private JFrame frame;
  private Dimension screenDim;
  private AddEmployeePanel addEmployeePanel;
  private ButtonsPanel buttonsPanel;
  private ImportExportPanel importExportPanel;
  private SearchPanel searchPanel;
  private SearchBySalaryPanel searchBySalaryPanel;

  private TableModel tableModel;
  private JTable table;
  private TableRowSorter rowSorter;
  private TableColumn column;
  private JScrollPane scrollPane;

  public Frame() {
    // Create frame
    frame = new JFrame();
    frame.getContentPane().setLayout(new BorderLayout());

    // Add Center panel
    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new GridBagLayout());

    // Add Add employee panel to Center panel
    addEmployeePanel = new AddEmployeePanel();
    GridBagConstraints constrains = new GridBagConstraints();
    constrains.fill = GridBagConstraints.BOTH;
    constrains.gridx = 0;
    constrains.gridy = 0;
    centerPanel.add(addEmployeePanel.getAddEmployeePanel(), constrains);

    // Add Buttons panel to Center panel
    buttonsPanel = new ButtonsPanel();
    constrains = new GridBagConstraints();
    constrains.fill = GridBagConstraints.BOTH;
    constrains.gridx = 1;
    constrains.gridy = 0;
    centerPanel.add(buttonsPanel.getButtonsPanel(), constrains);

    // Add ImportExport panel to Center panel
    importExportPanel = new ImportExportPanel();
    constrains.fill = GridBagConstraints.BOTH;
    constrains.gridx = 2;
    constrains.gridy = 0;
    centerPanel.add(importExportPanel.getImportExportPanel(), constrains);

    // Add Search panel to Center panel
    searchPanel = new SearchPanel();
    constrains.fill = GridBagConstraints.BOTH;
    constrains.gridx = 0;
    constrains.gridy = 1;
    constrains.gridwidth = 3;
    centerPanel.add(searchPanel.getSearchPanel(), constrains);

    // Add SearchBySalary panel to Center panel
    searchBySalaryPanel = new SearchBySalaryPanel();
    constrains.fill = GridBagConstraints.BOTH;
    constrains.gridx = 0;
    constrains.gridy = 2;
    constrains.gridwidth = 3;
    centerPanel.add(searchBySalaryPanel.getSearchBySalaryPanel(), constrains);

    tableModel = new TableModel();
    table = new JTable(tableModel);
    table.setAutoCreateRowSorter(true);

    rowSorter = new TableRowSorter(table.getModel());
    table.setRowSorter(rowSorter);

    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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

    // Add Search panel to Center panel
    scrollPane = new JScrollPane(table);
    scrollPane.setBorder(
      BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)
    );
    scrollPane.setHorizontalScrollBarPolicy(
      JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
    );
    scrollPane.setVerticalScrollBarPolicy(
      JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
    );
    constrains.fill = GridBagConstraints.BOTH;
    constrains.gridx = 0;
    constrains.gridy = 3;
    constrains.gridwidth = 3;
    constrains.ipady = 300;
    constrains.insets = new Insets(5, 5, 5, 5);
    centerPanel.add(scrollPane, constrains);

    // Add Center panel to frame
    frame.getContentPane().add(centerPanel, BorderLayout.CENTER);

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

    // Search an employee
    searchPanel
      .getSearchTextField()
      .addKeyListener(
        new KeyAdapter() {

          @Override
          public void keyReleased(KeyEvent e) {
            rowSorter.setRowFilter(
              RowFilter.regexFilter(
                "(?i)" + searchPanel.getSearchTextField().getText()
              )
            );
          }
        }
      );

    // Search by salary
    searchBySalaryPanel
      .getSearchButton()
      .addActionListener(e -> searchBySalaryAction());

    // Clear the search by salary filter
    searchBySalaryPanel
      .getClearButton()
      .addActionListener(e -> clearSearchBySalaryAction());

    // Export employee list to CSV file
    importExportPanel
      .getExportButton()
      .addActionListener(e -> importExportPanel.exportAction(table));

    // Import employee list from CSV file
    importExportPanel
      .getImportButton()
      .addActionListener(e -> importExportPanel.importAction(table));

    screenDim = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setSize(800, 650);
    frame.setLocation(screenDim.width / 4, screenDim.height / 4);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  public void addEmployeeAction() {
    if (addEmployeePanel.validateInputs()) {
      TableModel tm = (TableModel) table.getModel();
      tm.addEmployee(addEmployeePanel.getEmployee());
      addEmployeePanel.clearInputs();
    }
    buttonsPanel.getAddButton().setFocusPainted(false);
  }

  public void deleteEmployeeAction() {
    if (table.getSelectedRow() != -1) {
      TableModel tm = (TableModel) table.getModel();
      tm.deleteEmployee(table.getSelectedRow());
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

      TableModel tm = (TableModel) table.getModel();
      Employee employee = tm.getEmployee(table.getSelectedRow());
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
        TableModel tm = (TableModel) table.getModel();
        tm.updateEmployee(
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

  public void searchBySalaryAction() {
    if (searchBySalaryPanel.validateInputs()) {
      rowSorter.setRowFilter(
        new RowFilter<TableModel, Integer>() {

          @Override
          public boolean include(
            Entry<? extends TableModel, ? extends Integer> entry
          ) {
            int from = searchBySalaryPanel.getFromSalary();
            int to = searchBySalaryPanel.getToSalary();
            int x = Integer.parseInt(entry.getStringValue(5));
            return x >= from && x <= to;
          }
        }
      );
    }
    searchBySalaryPanel.getSearchButton().setFocusPainted(false);
  }

  public void clearSearchBySalaryAction() {
    rowSorter.setRowFilter(null);
    searchBySalaryPanel.clearInputs();
  }

  public static void showDialog(String type, String message) {
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
