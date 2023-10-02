package baykov.com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame implements ActionListener {

    private final JFrame frame = new JFrame("Hospital Management System");
    private final JButton newPatientButton = new JButton("Add New Patient");
    private final JButton diagnosisButton = new JButton("Add Diagnosis Information");
    private final JButton patientsHistoryButton = new JButton("Patients History");
    private final JButton updatePatientButton = new JButton("Update Patient Record");
    private final JButton hospitalInfoButton = new JButton("Hospital Information");
    private final JButton logOutButton = new JButton("Log Out");

    public MainFrame() {

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1152,685);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JLabel image = new JLabel();
        image.setIcon(new ImageIcon("images/hospital.jpg"));
        image.setBounds(0,0,1152,685);

        newPatientButton.setBounds(40, 60, 240, 60);
        newPatientButton.setFont(new Font("Ink Free", Font.PLAIN, 15));
        newPatientButton.setBackground(Color.WHITE);
        newPatientButton.setFocusable(false);
        newPatientButton.addActionListener(this);

        diagnosisButton.setBounds(40, 140, 240, 60);
        diagnosisButton.setFont(new Font("Ink Free", Font.PLAIN, 15));
        diagnosisButton.setBackground(Color.WHITE);
        diagnosisButton.setFocusable(false);
        diagnosisButton.addActionListener(this);

        patientsHistoryButton.setBounds(40, 220, 240, 60);
        patientsHistoryButton.setFont(new Font("Ink Free", Font.PLAIN, 15));
        patientsHistoryButton.setBackground(Color.WHITE);
        patientsHistoryButton.setFocusable(false);
        patientsHistoryButton.addActionListener(this);

        updatePatientButton.setBounds(40, 300, 240, 60);
        updatePatientButton.setFont(new Font("Ink Free", Font.PLAIN, 15));
        updatePatientButton.setBackground(Color.WHITE);
        updatePatientButton.setFocusable(false);
        updatePatientButton.addActionListener(this);

        hospitalInfoButton.setBounds(40, 380, 240, 60);
        hospitalInfoButton.setFont(new Font("Ink Free", Font.PLAIN, 15));
        hospitalInfoButton.setBackground(Color.WHITE);
        hospitalInfoButton.setFocusable(false);
        hospitalInfoButton.addActionListener(this);

        logOutButton.setBounds(40, 550, 240, 60);
        logOutButton.setFont(new Font("Ink Free", Font.PLAIN, 15));
        logOutButton.setBackground(Color.WHITE);
        logOutButton.setFocusable(false);
        logOutButton.addActionListener(this);


        frame.add(logOutButton);
        frame.add(hospitalInfoButton);
        frame.add(updatePatientButton);
        frame.add(patientsHistoryButton);
        frame.add(diagnosisButton);
        frame.add(newPatientButton);
        frame.add(image);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == newPatientButton) {
            new AddPatient();
        }

        if (e.getSource() == diagnosisButton) {
            new AddDiagnosis();
        }

        if (e.getSource() == patientsHistoryButton) {
            new PatientsHistory().displayPatients();
        }

        if (e.getSource() == updatePatientButton) {
            new UpdatePatient();
        }

        if (e.getSource() == hospitalInfoButton) {
            new HospitalInformation();
        }

        if (e.getSource() == logOutButton) {
            int answer = JOptionPane.showConfirmDialog(null, "Do you really want to logout ?", "Select", JOptionPane.YES_NO_OPTION);
            if (answer == 0) {
                this.frame.dispose();
                new LogIn();
            }
        }
    }
}
