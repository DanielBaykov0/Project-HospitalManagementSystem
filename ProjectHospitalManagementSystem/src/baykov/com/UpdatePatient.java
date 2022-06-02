package baykov.com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

public class UpdatePatient implements ActionListener {

    private final JFrame frame = new JFrame("Update Patient Information");
    private final JTextField patientIdText = new JTextField();
    private final JButton searchButton = new JButton("Search");
    private final JTextField nameText = new JTextField();
    private final JTextField contactNumberText = new JTextField();
    private final JTextField ageText = new JTextField();
    private final JComboBox genderBox;
    private final JTextField bloodGroupText = new JTextField();
    private final JTextField addressText = new JTextField();
    private final JTextField majorDiseaseText = new JTextField();
    private final JTextField symptomsText = new JTextField();
    private final JTextField diagnosisText = new JTextField();
    private final JTextField medicinesText = new JTextField();
    private final JCheckBox wardBox = new JCheckBox("Yes");
    private final JComboBox wardTypeBox;
    private final JButton updateButton = new JButton("Update");
    private final JButton closeButton = new JButton("Close");

    public UpdatePatient() {

        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setSize(800,600);
        frame.getContentPane().setBackground(new Color(	160, 216, 241 ).brighter());
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JLabel patientId = new JLabel("Patient ID");
        patientId.setBounds(40, 40, 100, 25);
        patientId.setFont(new Font("Ink Free", Font.BOLD, 15));

        patientIdText.setBounds(160,40,100,25);
        patientIdText.setFont(new Font("Ink Free", Font.PLAIN, 15));

        searchButton.setBounds(280, 40, 120,25);
        searchButton.setIcon(new ImageIcon("/home/baykov/IdeaProjects/ProjectHospitalManagementSystem/images/searchicon.png"));
        searchButton.setFont(new Font("Ink Free", Font.BOLD, 15));
        searchButton.setFocusable(false);
        searchButton.addActionListener(this);

        JLabel name = new JLabel("Name");
        name.setBounds(80, 80, 100, 25);
        name.setFont(new Font("Ink Free", Font.BOLD, 15));

        nameText.setBounds(200,80,200,25);
        nameText.setFont(new Font("Ink Free", Font.PLAIN, 15));

        JLabel contactNumber = new JLabel("Contact No");
        contactNumber.setBounds(80,120,100,25);
        contactNumber.setFont(new Font("Ink Free", Font.BOLD, 15));

        contactNumberText.setBounds(200,120,200,25);
        contactNumberText.setFont(new Font("Ink Free", Font.PLAIN, 15));

        JLabel age = new JLabel("Age");
        age.setBounds(80,160,100,25);
        age.setFont(new Font("Ink Free", Font.BOLD, 15));

        ageText.setBounds(200,160,200,25);
        ageText.setFont(new Font("Ink Free", Font.PLAIN, 15));

        JLabel gender = new JLabel("Gender");
        gender.setBounds(80,200,100,25);
        gender.setFont(new Font("Ink Free", Font.BOLD, 15));

        String[] genderItems = {"Male", "Female"};
        genderBox = new JComboBox<>(genderItems);
        genderBox.setBounds(200,200,200,25);
        genderBox.setFont(new Font("Ink Free", Font.PLAIN, 15));

        JLabel bloodGroup = new JLabel("Blood Group");
        bloodGroup.setBounds(80,240,100,25);
        bloodGroup.setFont(new Font("Ink Free", Font.BOLD, 15));

        bloodGroupText.setBounds(200,240,200,25);
        bloodGroupText.setFont(new Font("Ink Free", Font.PLAIN, 15));

        JLabel address = new JLabel("Address");
        address.setBounds(80,280,100,25);
        address.setFont(new Font("Ink Free", Font.BOLD, 15));

        addressText.setBounds(200,280,200,25);
        addressText.setFont(new Font("Ink Free", Font.PLAIN, 15));

        JLabel majorDisease = new JLabel("Any Major Disease Suffered Earlier");
        majorDisease.setBounds(150,350,300,25);
        majorDisease.setFont(new Font("Ink Free", Font.BOLD, 15));

        majorDiseaseText.setBounds(150,400,470,40);
        majorDiseaseText.setFont(new Font("Ink Free", Font.PLAIN, 15));

        JLabel symptoms = new JLabel("Symptoms");
        symptoms.setBounds(420,80, 100, 25);
        symptoms.setFont(new Font("Ink Free", Font.BOLD, 15));

        symptomsText.setBounds(540,80,200,25);
        symptomsText.setFont(new Font("Ink Free", Font.PLAIN, 15));

        JLabel diagnosis = new JLabel("Diagnosis");
        diagnosis.setBounds(420,120, 100, 25);
        diagnosis.setFont(new Font("Ink Free", Font.BOLD, 15));

        diagnosisText.setBounds(540,120,200,25);
        diagnosisText.setFont(new Font("Ink Free", Font.PLAIN, 15));

        JLabel medicines = new JLabel("Medicines");
        medicines.setBounds(420,160, 100, 25);
        medicines.setFont(new Font("Ink Free", Font.BOLD, 15));

        medicinesText.setBounds(540,160,200,25);
        medicinesText.setFont(new Font("Ink Free", Font.PLAIN, 15));

        JLabel ward = new JLabel("Ward Required ?");
        ward.setBounds(420, 200, 150, 25);
        ward.setFont(new Font("Ink Free", Font.BOLD, 15));

        wardBox.setBounds(580, 200, 70, 30);
        wardBox.setFont(new Font("Ink Free", Font.BOLD, 15));
        wardBox.setBackground(new Color(160, 216, 241 ).brighter());

        JLabel wardType = new JLabel("Ward Type");
        wardType.setBounds(540, 240, 100, 25);
        wardType.setFont(new Font("Ink Free", Font.BOLD, 15));

        String[] wardTypeItems = {"General", "Single", "Duo", "No"};
        wardTypeBox = new JComboBox<>(wardTypeItems);
        wardTypeBox.setBounds(540, 280, 100, 25);
        wardTypeBox.setFont(new Font("Ink Free", Font.BOLD, 15));

        closeButton.setBounds(600, 480, 120, 30);
        closeButton.setIcon(new ImageIcon("/home/baykov/IdeaProjects/ProjectHospitalManagementSystem/images/closeicon.png"));
        closeButton.setFont(new Font("Ink Free", Font.BOLD, 15));
        closeButton.setFocusable(false);
        closeButton.addActionListener(this);

        updateButton.setBounds(100, 480, 120, 30);
        updateButton.setIcon(new ImageIcon("/home/baykov/IdeaProjects/ProjectHospitalManagementSystem/images/saveicon.png"));
        updateButton.setFont(new Font("Ink Free", Font.BOLD, 15));
        updateButton.setFocusable(false);
        updateButton.addActionListener(this);

        frame.add(updateButton);
        frame.add(closeButton);
        frame.add(majorDiseaseText);
        frame.add(majorDisease);
        frame.add(addressText);
        frame.add(address);
        frame.add(bloodGroupText);
        frame.add(bloodGroup);
        frame.add(genderBox);
        frame.add(gender);
        frame.add(ageText);
        frame.add(age);
        frame.add(contactNumberText);
        frame.add(contactNumber);
        frame.add(nameText);
        frame.add(name);
        frame.add(wardBox);
        frame.add(ward);
        frame.add(medicinesText);
        frame.add(medicines);
        frame.add(diagnosisText);
        frame.add(diagnosis);
        frame.add(symptomsText);
        frame.add(symptoms);
        frame.add(wardTypeBox);
        frame.add(wardType);
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
        nameText.setText("");
        contactNumberText.setText("");
        ageText.setText("");
        bloodGroupText.setText("");
        addressText.setText("");
        majorDiseaseText.setText("");
        symptomsText.setText("");
        diagnosisText.setText("");
        medicinesText.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == searchButton) {
            if (patientIdText.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Wrong Patient ID");
            } else {
                try {
                    int id = Integer.parseInt(patientIdText.getText());
                    Connection connection = baykov.com.Connection.getConn();
                    Statement search = connection.createStatement();
                    ResultSet resultSet = search.executeQuery
                            ("SELECT * FROM patient WHERE patientId =" + id);
                    if (resultSet.next()) {
                        nameText.setText(resultSet.getString(2));
                        contactNumberText.setText(resultSet.getString(3));
                        ageText.setText(resultSet.getString(4));
                        bloodGroupText.setText(resultSet.getString(6));
                        addressText.setText(resultSet.getString(7));
                        majorDiseaseText.setText(resultSet.getString(8));
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }

        if (e.getSource() == searchButton) {
            if (patientIdText.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Wrong Patient ID");
            } else {
                try {
                    int id = Integer.parseInt(patientIdText.getText());
                    Connection connection = baykov.com.Connection.getConn();
                    Statement search = connection.createStatement();
                    ResultSet resultSet = search.executeQuery
                            ("SELECT * FROM patientrecord WHERE patientId =" + id);
                    if (resultSet.next()) {
                        symptomsText.setText(resultSet.getString(2));
                        diagnosisText.setText(resultSet.getString(3));
                        medicinesText.setText(resultSet.getString(4));
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }

        if (e.getSource() == updateButton) {
            if (patientIdText.getText().isEmpty() || nameText.getText().isEmpty() || contactNumberText.getText().isEmpty() ||
                    ageText.getText().isEmpty() || bloodGroupText.getText().isEmpty() || addressText.getText().isEmpty() ||
                    majorDiseaseText.getText().isEmpty() || symptomsText.getText().isEmpty() || diagnosisText.getText().isEmpty() ||
                    medicinesText.getText().isEmpty() || !wardBox.isSelected()) {
                JOptionPane.showMessageDialog(null, "Missing Information");
            } else {
                try {
                    int id = Integer.parseInt(patientIdText.getText());
                    Connection connection = baykov.com.Connection.getConn();
                    PreparedStatement update = connection.prepareStatement
                            ("UPDATE patient SET name=?, contactNumber=?, age=?, gender=?, bloodGroup=?, address=?, anyMajorDisease=? WHERE patientId =" + id);
                    update.setString(1, nameText.getText());
                    update.setString(2, contactNumberText.getText());
                    update.setString(3, ageText.getText());
                    update.setString(4, Objects.requireNonNull(genderBox.getSelectedItem()).toString());
                    update.setString(5, bloodGroupText.getText());
                    update.setString(6, addressText.getText());
                    update.setString(7, majorDiseaseText.getText());

                    int row = update.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Patient Updated");
                    update.close();
                    connection.close();
                    clear();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }

        if (e.getSource() == updateButton) {
            if (patientIdText.getText().isEmpty() || nameText.getText().isEmpty() || contactNumberText.getText().isEmpty() ||
                    ageText.getText().isEmpty() || bloodGroupText.getText().isEmpty() || addressText.getText().isEmpty() ||
                    majorDiseaseText.getText().isEmpty() || symptomsText.getText().isEmpty() || diagnosisText.getText().isEmpty() ||
                    medicinesText.getText().isEmpty() || !wardBox.isSelected()) {
                JOptionPane.showMessageDialog(null, "Missing Information");
            } else {
                try {
                    int id = Integer.parseInt(patientIdText.getText());
                    Connection connection = baykov.com.Connection.getConn();
                    PreparedStatement update = connection.prepareStatement
                            ("UPDATE patientrecord SET symptom=?, diagnosis=?, medicines=?, wardReq=?, typeWard=? WHERE patientId =" + id);
                    update.setString(1, symptomsText.getText());
                    update.setString(2, diagnosisText.getText());
                    update.setString(3, medicinesText.getText());
                    update.setString(4, wardBoxReturnStatement());
                    update.setString(5, wardTypeBoxReturnStatement());

                    int row = update.executeUpdate();
                    update.close();
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
