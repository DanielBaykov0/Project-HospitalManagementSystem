package baykov.com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.Objects;

public class AddPatient implements ActionListener {

    private final JFrame frame = new JFrame("Add New Patient");
    private final JTextField nameText = new JTextField();
    private final JTextField contactNumberText = new JTextField();
    private final JTextField ageText = new JTextField();
    private final JComboBox genderBox;
    private final JTextField bloodGroupText = new JTextField();
    private final JTextField addressText = new JTextField();
    private final JTextField majorDiseaseText = new JTextField();
    private final JButton saveButton = new JButton("Save");
    private final JButton closeButton = new JButton("Close");

    public AddPatient() {

        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setSize(800,600);
        frame.getContentPane().setBackground(new Color(	160, 216, 241 ).brighter());
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JLabel name = new JLabel("Name");
        name.setBounds(150, 60, 100, 25);
        name.setFont(new Font("Ink Free", Font.BOLD, 15));

        nameText.setBounds(320,60,300,25);
        nameText.setFont(new Font("Ink Free", Font.PLAIN, 15));

        JLabel contactNumber = new JLabel("Contact Number");
        contactNumber.setBounds(150,110,150,25);
        contactNumber.setFont(new Font("Ink Free", Font.BOLD, 15));

        contactNumberText.setBounds(320,110,300,25);
        contactNumberText.setFont(new Font("Ink Free", Font.PLAIN, 15));

        JLabel age = new JLabel("Age");
        age.setBounds(150,160,100,25);
        age.setFont(new Font("Ink Free", Font.BOLD, 15));

        ageText.setBounds(320,160,300,25);
        ageText.setFont(new Font("Ink Free", Font.PLAIN, 15));

        JLabel gender = new JLabel("Gender");
        gender.setBounds(150,210,100,25);
        gender.setFont(new Font("Ink Free", Font.BOLD, 15));

        String[] genderItems = {"Male", "Female"};
        genderBox = new JComboBox<>(genderItems);
        genderBox.setBounds(320,210,300,25);
        genderBox.setFont(new Font("Ink Free", Font.PLAIN, 15));

        JLabel bloodGroup = new JLabel("Blood Group");
        bloodGroup.setBounds(150,260,150,25);
        bloodGroup.setFont(new Font("Ink Free", Font.BOLD, 15));

        bloodGroupText.setBounds(320,260,300,25);
        bloodGroupText.setFont(new Font("Ink Free", Font.PLAIN, 15));

        JLabel address = new JLabel("Address");
        address.setBounds(150,310,100,25);
        address.setFont(new Font("Ink Free", Font.BOLD, 15));

        addressText.setBounds(320,310,300,25);
        addressText.setFont(new Font("Ink Free", Font.PLAIN, 15));

        JLabel majorDisease = new JLabel("Any Major Disease Suffered Earlier");
        majorDisease.setBounds(150,360,300,25);
        majorDisease.setFont(new Font("Ink Free", Font.BOLD, 15));

        majorDiseaseText.setBounds(150,400,470,25);
        majorDiseaseText.setFont(new Font("Ink Free", Font.PLAIN, 15));

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

        frame.add(saveButton);
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
        frame.setVisible(true);
    }

    private void clear() {
        nameText.setText("");
        contactNumberText.setText("");
        ageText.setText("");
        bloodGroupText.setText("");
        addressText.setText("");
        majorDiseaseText.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == saveButton) {
            if (nameText.getText().isEmpty() || contactNumberText.getText().isEmpty() || ageText.getText().isEmpty() ||
            bloodGroupText.getText().isEmpty() || addressText.getText().isEmpty() || majorDiseaseText.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Missing Information");
            } else {
                try {
                    java.sql.Connection connection = Connection.getConn();
                    PreparedStatement add = connection.prepareStatement
                            ("INSERT INTO patient(name, contactNumber, age, gender, bloodGroup, address, anyMajorDisease) VALUES(?, ?, ?, ?, ?, ?, ?)");
                    add.setString(1, nameText.getText());
                    add.setString(2, contactNumberText.getText());
                    add.setString(3, ageText.getText());
                    add.setString(4, Objects.requireNonNull(genderBox.getSelectedItem()).toString());
                    add.setString(5, bloodGroupText.getText());
                    add.setString(6, addressText.getText());
                    add.setString(7, majorDiseaseText.getText());

                    int row = add.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Patient Added");
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
