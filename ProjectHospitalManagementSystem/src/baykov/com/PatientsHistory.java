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

public class PatientsHistory implements ActionListener {

    private final JFrame frame = new JFrame("Full Patient History");
    private final JTable table = new JTable();
    private final JButton deleteButton = new JButton("Delete");
    private final JButton closeButton = new JButton("Close");

    public PatientsHistory() {

        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.getContentPane().setBackground(new Color(160, 216, 241).brighter());
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(40, 40, 720, 400);
        scrollPane.setColumnHeaderView(table.getTableHeader());
        table.setBackground(Color.WHITE);
        table.setFont(new Font("Ink Free", Font.PLAIN, 15));
        table.setDefaultEditor(Object.class, null);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        closeButton.setBounds(600, 480, 120, 30);
        closeButton.setIcon(new ImageIcon("images/closeicon.png"));
        closeButton.setFont(new Font("Ink Free", Font.BOLD, 15));
        closeButton.setFocusable(false);
        closeButton.addActionListener(this);

        deleteButton.setBounds(100, 480, 120, 30);
        deleteButton.setIcon(new ImageIcon("images/deleteicon.png"));
        deleteButton.setFont(new Font("Ink Free", Font.BOLD, 15));
        deleteButton.setFocusable(false);
        deleteButton.addActionListener(this);

        frame.add(deleteButton);
        frame.add(closeButton);
        frame.add(scrollPane);
        frame.setVisible(true);
    }

    public void displayPatients() {

        try {
            Connection connection = baykov.com.Connection.getConn();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery
                    ("SELECT * FROM patient INNER JOIN patientrecord WHERE patient.patientID = patientrecord.patientID");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == deleteButton) {
            int row = table.getSelectedRow();
            int delete = (int) table.getModel().getValueAt(row, 0);
            String delRow = "DELETE patient, patientrecord FROM patient INNER JOIN patientrecord ON patientrecord.patientId = patient.patientId WHERE patient.patientId=" + delete;

            try {
                Connection connection = baykov.com.Connection.getConn();
                PreparedStatement ps = connection.prepareStatement(delRow);
                ps.execute();
                JOptionPane.showMessageDialog(null, "Patient Deleted");
                ps.close();
                connection.close();
                displayPatients();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        if (e.getSource() == closeButton) {
            this.frame.dispose();
        }
    }
}
