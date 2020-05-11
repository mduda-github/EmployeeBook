package View;

import java.awt.event.*;
import javax.swing.*;

public class SearchPanel {
  private JPanel searchPanel;
  private JTextField searchTextField;

  public SearchPanel() {
    searchPanel = new JPanel();
    searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));

    // Add border
    searchPanel.setBorder(BorderFactory.createTitledBorder("Search"));

    // Add default text
    searchTextField = new JTextField("Search by any criteria");

    searchTextField.addFocusListener(
      new FocusListener() {

        @Override
        public void focusGained(FocusEvent e) {
          searchTextField.setText("");
        }

        @Override
        public void focusLost(FocusEvent e) {
          searchTextField.setText("Search by any criteria");
        }
      }
    );

    searchPanel.add(searchTextField);
  }

  public JPanel getSearchPanel() {
    return searchPanel;
  }

  public JTextField getSearchTextField() {
    return searchTextField;
  }
}
