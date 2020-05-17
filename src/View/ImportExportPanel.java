package View;

import static View.Frame.showDialog;

import Model.Employee;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;

public class ImportExportPanel {
  private JPanel importExportPanel;
  private JButton importButton;
  private JButton exportButton;

  private FileWriter fileWriter;

  public ImportExportPanel() {
    importExportPanel = new JPanel(new GridBagLayout());

    // Add border
    importExportPanel.setBorder(BorderFactory.createTitledBorder("File"));

    // Add buttons
    importButton = new JButton("Import");
    exportButton = new JButton("Export");

    // Setting dimension for buttons
    Dimension addEmployeeLabelDimension = new Dimension(85, 25);
    importButton.setPreferredSize(addEmployeeLabelDimension);
    exportButton.setPreferredSize(addEmployeeLabelDimension);

    // Position elements of layout in panel
    GridBagConstraints c = new GridBagConstraints();
    c.insets = new Insets(0, 10, 0, 10);
    c.gridx = 0;
    c.gridy = 0;
    importExportPanel.add(importButton, c);
    c.gridx = 0;
    c.gridy = 2;
    importExportPanel.add(exportButton, c);
  }

  private boolean exportToCSV(TableModel model, String pathToExportTo) {
    try {
      fileWriter = new FileWriter(new File(pathToExportTo));

      // Write table headers
      for (int i = 0; i < model.getColumnCount(); i++) {
        if (i == model.getColumnCount() - 1) {
          fileWriter.write(model.getColumnName(i));
        } else {
          fileWriter.write(model.getColumnName(i) + ",");
        }
      }
      fileWriter.write("\n");

      // Looping through rows and columns to write each cell
      for (int i = 0; i < model.getRowCount(); i++) {
        for (int j = 0; j < model.getColumnCount(); j++) {
          if (j == model.getColumnCount() - 1) {
            fileWriter.write(model.getValueAt(i, j).toString());
          } else {
            fileWriter.write(model.getValueAt(i, j).toString() + ",");
          }
        }
        fileWriter.write("\n");
      }

      fileWriter.flush();
      fileWriter.close();
      return true;
    } catch (IOException e) {
      showDialog("Error", "Export unsuccessful");
    }
    return false;
  }

  private String checkFileExtension(String path) {
    Pattern pattern = Pattern.compile("\\.csv$");
    Matcher matcher = pattern.matcher(path);
    if (!matcher.find()) {
      return String.join("", path, ".csv");
    }
    return path;
  }

  protected void exportAction(JTable table) {
    Model.TableModel tm = (Model.TableModel) table.getModel();
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    fileChooser.setAcceptAllFileFilterUsed(false);
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
      "CSV File",
      "csv"
    );
    fileChooser.addChoosableFileFilter(filter);

    int initDialog = fileChooser.showSaveDialog(null);
    if (initDialog == JFileChooser.APPROVE_OPTION) {
      String path = fileChooser.getSelectedFile().getAbsolutePath();
      String fullFilePath = checkFileExtension(path);

      if (exportToCSV(tm, fullFilePath)) {
        showDialog("Info", "Export completed");
      } else {
        showDialog("Info", "Export unsuccessful");
      }
    } else {
      showDialog("Info", "Export cancelled");
    }
  }

  private boolean validateAttributes(String[] attr) {
    if (attr.length != 6) {
      return false;
    }
    try {
      Enum.valueOf(
        Model.PositionComponent.class,
        attr[3].toUpperCase().replace(" ", "_")
      );
    } catch (IllegalArgumentException e) {
      return false;
    }
    try {
      Integer.parseInt(attr[5]);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  private ArrayList<Employee> importFromCSV(String fullFilePath) {
    ArrayList<Employee> employees = new ArrayList<>();
    Path pathToFile = Paths.get(fullFilePath);
    try (BufferedReader br = Files.newBufferedReader(pathToFile)) {
      br.readLine();
      String line1 = null;
      while ((line1 = br.readLine()) != null) {
        String[] attributes = line1.split(",");
        if (validateAttributes(attributes)) {
          employees.add(
            new Employee(
              attributes[1],
              attributes[2],
              Enum.valueOf(
                Model.PositionComponent.class,
                attributes[3].toUpperCase().replace(" ", "_")
              ),
              attributes[4],
              Integer.parseInt(attributes[5])
            )
          );
        } else {
          showDialog("Error", "Import incomplete");
        }
      }
    } catch (IOException e) {
      showDialog("Error", "Import unsuccessful");
    }

    return employees;
  }

  protected void importAction(JTable table) {
    JFileChooser fileOpener = new JFileChooser();
    fileOpener.setFileSelectionMode(JFileChooser.FILES_ONLY);
    fileOpener.setAcceptAllFileFilterUsed(false);
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
      "CSV File",
      "csv"
    );
    fileOpener.addChoosableFileFilter(filter);

    int initDialog = fileOpener.showOpenDialog(null);
    if (initDialog == JFileChooser.APPROVE_OPTION) {
      String path = fileOpener.getSelectedFile().getAbsolutePath();
      String fullFilePath = checkFileExtension(path);

      ArrayList<Employee> incomingData = importFromCSV(fullFilePath);
      if (incomingData.size() > 0) {
        Model.TableModel tableModel = new Model.TableModel(incomingData);
        table.setModel(tableModel);
        tableModel.fireTableDataChanged();
      } else {
        showDialog("Info", "Import unsuccessful");
      }
    } else {
      showDialog("Info", "Import cancelled");
    }
  }

  public JPanel getImportExportPanel() {
    return importExportPanel;
  }

  public JButton getImportButton() {
    return importButton;
  }

  public JButton getExportButton() {
    return exportButton;
  }
}
