package baykov.com;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

public class AddDiagnosis implements ActionListener {

    private final JFrame frame = new JFrame("Add Diagnosis Information");
    private final JTextField patientIdText = new JTextField();
    private final JButton searchButton = new JButton("Search");
    private final JTable table = new JTable();
    private final JTextField symptomsText = new JTextField();
    private final JTextField diagnosisText = new JTextField();
    private final JTextField medicinesText = new JTextField();
    private final JCheckBox wardBox = new JCheckBox("Yes");
    private final JLabel wardType = new JLabel("Ward Type");
    private final JComboBox wardTypeBox;
    private final JButton saveButton = new JButton("Save");
    private final JButton closeButton = new JButton("Close");

    public AddDiagnosis() {

        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.getContentPane().setBackground(new Color(160, 216, 241).brighter());
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JLabel patientId = new JLabel("Patient ID");
        patientId.setBounds(150, 60, 100, 25);
        patientId.setFont(new Font("Ink Free", Font.BOLD, 15));

        patientIdText.setBounds(300, 60, 100, 25);
        patientIdText.setFont(new Font("Ink Free", Font.PLAIN, 15));

        searchButton.setBounds(500, 60, 120, 25);
        searchButton.setIcon(new ImageIcon("images/searchicon.png"));
        searchButton.setFont(new Font("Ink Free", Font.BOLD, 15));
        searchButton.setFocusable(false);
        searchButton.addActionListener(this);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(40, 100, 720, 150);
        scrollPane.setColumnHeaderView(table.getTableHeader());
        // gets headers names
        table.setBackground(Color.WHITE);
        table.setFont(new Font("Ink Free", Font.PLAIN, 15));
        table.setDefaultEditor(Object.class, null);

        JLabel symptoms = new JLabel("Symptoms");
        symptoms.setBounds(40, 300, 150, 25);
        symptoms.setFont(new Font("Ink Free", Font.BOLD, 15));

        symptomsText.setBounds(200, 300, 200, 25);
        symptomsText.setFont(new Font("Ink Free", Font.PLAIN, 15));

        JLabel diagnosis = new JLabel("Diagnosis");
        diagnosis.setBounds(40, 340, 150, 25);
        diagnosis.setFont(new Font("Ink Free", Font.BOLD, 15));

        diagnosisText.setBounds(200, 340, 200, 25);
        diagnosisText.setFont(new Font("Ink Free", Font.PLAIN, 15));

        JLabel medicines = new JLabel("Medicines");
        medicines.setBounds(40, 380, 150, 25);
        medicines.setFont(new Font("Ink Free", Font.BOLD, 15));

        medicinesText.setBounds(200, 380, 200, 25);
        medicinesText.setFont(new Font("Ink Free", Font.PLAIN, 15));

        JLabel ward = new JLabel("Ward Required ?");
        ward.setBounds(450, 300, 150, 25);
        ward.setFont(new Font("Ink Free", Font.BOLD, 15));

        wardBox.setBounds(620, 300, 70, 30);
        wardBox.setFont(new Font("Ink Free", Font.BOLD, 15));
        wardBox.setBackground(new Color(160, 216, 241).brighter());
        wardBox.addActionListener(this);

        wardType.setBounds(450, 340, 150, 25);
        wardType.setFont(new Font("Ink Free", Font.BOLD, 15));

        String[] wardTypeItems = {"General", "Single", "Duo"};
        wardTypeBox = new JComboBox<>(wardTypeItems);
        wardTypeBox.setBounds(620, 340, 120, 25);
        wardTypeBox.setFont(new Font("Ink Free", Font.BOLD, 15));

        closeButton.setBounds(600, 450, 120, 30);
        closeButton.setIcon(new ImageIcon("images/closeicon.png"));
        closeButton.setFont(new Font("Ink Free", Font.BOLD, 15));
        closeButton.setFocusable(false);
        closeButton.addActionListener(this);

        saveButton.setBounds(100, 450, 120, 30);
        saveButton.setIcon(new ImageIcon("images/saveicon.png"));
        saveButton.setFont(new Font("Ink Free", Font.BOLD, 15));
        saveButton.setFocusable(false);
        saveButton.addActionListener(this);

        frame.add(wardTypeBox);
        frame.add(wardType);
        frame.add(saveButton);
        frame.add(closeButton);
        frame.add(wardBox);
        frame.add(ward);
        frame.add(medicinesText);
        frame.add(medicines);
        frame.add(diagnosisText);
        frame.add(diagnosis);
        frame.add(symptomsText);
        frame.add(symptoms);
        frame.add(scrollPane);
        frame.add(searchButton);
        frame.add(patientIdText);
        frame.add(patientId);
        frame.setVisible(true);
    }

    private String wardBoxReturnStatement() {

        String ward;
        if (wardBox.isSelected()) {
            ward = "YES";
        } else {
            ward = "NO";
        }
        return ward;
    }

    private String wardTypeBoxReturnStatement() {

        String wardType;
        if (wardTypeBox.isVisible()) {
            wardType = Objects.requireNonNull(wardTypeBox.getSelectedItem()).toString();
        } else {
            wardType = "NO";
        }
        return wardType;
    }

    private void clear() {
        patientIdText.setText("");
        symptomsText.setText("");
        diagnosisText.setText("");
        medicinesText.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (wardBox.isSelected()) {
            wardType.setVisible(true);
            wardTypeBox.setVisible(true);
        } else {
            wardType.setVisible(false);
            wardTypeBox.setVisible(false);
        }

        if (e.getSource() == searchButton) {
            if (patientIdText.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter Patient ID");
            } else {
                String id = patientIdText.getText();
                try {
                    Connection connection = baykov.com.Connection.getConn();
                    Statement select = connection.createStatement();
                    ResultSet resultSet = select.executeQuery("SELECT * FROM patient WHERE patientId='" + id + "'");
                    table.setModel(DbUtils.resultSetToTableModel(resultSet));
                    select.close();
                    connection.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }

        if (e.getSource() == saveButton) {
            if (symptomsText.getText().isEmpty() || diagnosisText.getText().isEmpty() || medicinesText.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Missing Information");
            } else {
                try {
                    Connection connection = baykov.com.Connection.getConn();
                    PreparedStatement add = connection.prepareStatement
                            ("INSERT INTO patientrecord VALUES(?, ?, ?, ?, ?, ?)");
                    add.setString(1, patientIdText.getText());
                    add.setString(2, symptomsText.getText());
                    add.setString(3, diagnosisText.getText());
                    add.setString(4, medicinesText.getText());
                    add.setString(5, wardBoxReturnStatement());
                    add.setString(6, wardTypeBoxReturnStatement());

                    int row = add.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Patient Record Added");
                    add.close();
                    connection.close();
                    clear();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }

        if (e.getSource() == closeButton) {
            this.frame.dispose();
        }
    }
}
