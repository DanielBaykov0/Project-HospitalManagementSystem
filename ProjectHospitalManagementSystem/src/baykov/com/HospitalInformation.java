package baykov.com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HospitalInformation implements ActionListener {

    private final JFrame frame = new JFrame("Hospital Information");
    private final JButton closeButton = new JButton("Close");

    public HospitalInformation() {

        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setSize(800,600);
        frame.getContentPane().setBackground(new Color(	160, 216, 241 ).brighter());
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JLabel label = new JLabel();
        label.setBounds(100,20,600,200);
        label.setIcon(new ImageIcon("/home/baykov/IdeaProjects/ProjectHospitalManagementSystem/images/hospitalmanagement.jpg"));

        JTextArea textArea = new JTextArea();
        textArea.setBounds(20,250,760,250);
        textArea.setFont(new Font("Ink Free", Font.PLAIN, 15));
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setText("\tHospital management system is a computer system that helps manage the\n information " +
                "related to health care and aids in the job completion of health care providers effectively. \n" +
                "\n\tThe hospital management system helps register complete patient information. \n" +
                "\n\tIt captures and stores the medical history, treatment required, \ndetails of their previous visits, " +
                "upcoming appointments if any, reports, insurance details and more. \n" +
                "\n\tIt helps eliminate the need to get these details on every visit.");

        closeButton.setBounds(600, 520, 120, 30);
        closeButton.setIcon(new ImageIcon("/home/baykov/IdeaProjects/ProjectHospitalManagementSystem/images/closeicon.png"));
        closeButton.setFont(new Font("Ink Free", Font.BOLD, 15));
        closeButton.setFocusable(false);
        closeButton.addActionListener(this);

        frame.add(closeButton);
        frame.add(textArea);
        frame.add(label);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == closeButton) {
            this.frame.dispose();
        }
    }
}
