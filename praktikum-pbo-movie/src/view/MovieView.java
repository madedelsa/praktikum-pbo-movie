package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MovieView extends JFrame {
    public JTextField tfJudul = new JTextField();
    public JTextField tfAlur = new JTextField();
    public JTextField tfPenokohan = new JTextField();
    public JTextField tfAkting = new JTextField();
    public JTextField tfRating = new JTextField();

    public JButton btnTambah = new JButton("Tambah");
    public JButton btnUpdate = new JButton("Update");
    public JButton btnDelete = new JButton("Delete");
    public JButton btnReset = new JButton("Reset");

    public JTable table;
    public DefaultTableModel tableModel;

    public MovieView() {
        setTitle("Data Movie");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);

        // Panel utama vertikal
        JPanel panelUtama = new JPanel();
        panelUtama.setLayout(new BoxLayout(panelUtama, BoxLayout.Y_AXIS));
        panelUtama.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel form
        JPanel panelForm = new JPanel(new GridLayout(5, 2, 10, 10));
        panelForm.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        panelForm.add(new JLabel("Judul"));
        panelForm.add(tfJudul);
        panelForm.add(new JLabel("Alur Cerita (0-5)"));
        panelForm.add(tfAlur);
        panelForm.add(new JLabel("Penokohan (0-5)"));
        panelForm.add(tfPenokohan);
        panelForm.add(new JLabel("Akting (0-5)"));
        panelForm.add(tfAkting);
        panelForm.add(new JLabel("Rating"));
        tfRating.setEnabled(false);
        panelForm.add(tfRating);

        // Panel tombol
        JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelButton.add(btnTambah);
        panelButton.add(btnUpdate);
        panelButton.add(btnDelete);
        panelButton.add(btnReset);

        // Tabel
        String[] columnNames = {"Judul", "Alur", "Penokohan", "Akting", "Rating"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(650, 300));

        // Tambahkan ke panel utama
        panelUtama.add(panelForm);
        panelUtama.add(Box.createRigidArea(new Dimension(0, 10)));
        panelUtama.add(panelButton);
        panelUtama.add(Box.createRigidArea(new Dimension(0, 10)));
        panelUtama.add(scrollPane);

        add(panelUtama);
        setVisible(true);
    }
}
