package Model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {
  final String[] columnNames = new String[] {
    "#",
    "First Name",
    "Last Name",
    "Position",
    "Length of service",
    "Salary",
  };
  private ArrayList<Employee> employees;

  public TableModel() {
    employees = new ArrayList<>();
  }

  public TableModel(ArrayList<Employee> employeeArrayList) {
    employees = employeeArrayList;
  }

  @Override
  public int getColumnCount() {
    return columnNames.length;
  }

  @Override
  public int getRowCount() {
    return employees.size();
  }

  public String getColumnName(int columnIndex) {
    return columnNames[columnIndex];
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    if (employees.size() == 0) {
      return "";
    }

    Employee employee = employees.get(rowIndex);

    switch (columnIndex) {
      case 0:
        return rowIndex + 1;
      case 1:
        return employee.getName();
      case 2:
        return employee.getSurname();
      case 3:
        return employee.getPosition();
      case 4:
        return employee.getDateOfEmployment();
      case 5:
        return employee.getSalary();
      default:
        return null;
    }
  }

  public void addEmployee(Employee employee) {
    employees.add(employee);
    fireTableRowsInserted(0, getRowCount() - 1);
  }

  public void deleteEmployee(int rowIndex) {
    employees.remove(rowIndex);
    fireTableRowsDeleted(rowIndex, rowIndex);
  }

  public Employee getEmployee(int rowIndex) {
    return employees.get(rowIndex);
  }

  public void updateEmployee(int rowIndex, Employee employee) {
    employees.set(rowIndex, employee);
    fireTableRowsUpdated(rowIndex, rowIndex);
  }

  public Class getColumnClass(int c) {
    return getValueAt(0, c).getClass();
  }

  public boolean isCellEditable(int row, int col) {
    return false;
  }
}
