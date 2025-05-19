package controller;

import dao_datamovie.DataMovieDAO;
import dao_implements.DataMovieImplements;
import model.DataMovie;
import view.MovieView;

import java.awt.event.*;
import java.util.List;
import javax.swing.JOptionPane;

public class MovieController {
    MovieView view;
    DataMovieImplements dao;
    List<DataMovie> data;

    public MovieController(MovieView view) {
        this.view = view;
        dao = new DataMovieDAO();
        this.data = dao.getAll();

        showTable();

        view.btnTambah.addActionListener(e -> insertData());
        view.btnUpdate.addActionListener(e -> updateData());
        view.btnDelete.addActionListener(e -> deleteData());
        view.btnReset.addActionListener(e -> {
            clearForm();
            JOptionPane.showMessageDialog(view, "Form berhasil direset.", "Reset", JOptionPane.INFORMATION_MESSAGE);
        });

        view.table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = view.table.getSelectedRow();
                if (row >= 0) {
                    view.tfJudul.setText(view.table.getValueAt(row, 0).toString());
                    view.tfAlur.setText(view.table.getValueAt(row, 1).toString());
                    view.tfPenokohan.setText(view.table.getValueAt(row, 2).toString());
                    view.tfAkting.setText(view.table.getValueAt(row, 3).toString());
                    view.tfRating.setText(view.table.getValueAt(row, 4).toString());
                }
            }
        });
    }

    void showTable() {
        view.tableModel.setRowCount(0);
        data = dao.getAll();
        for (DataMovie m : data) {
            Object[] row = {
                m.getJudul(), m.getAlur(), m.getPenokohan(), m.getAkting(), m.getRating()
            };
            view.tableModel.addRow(row);
        }
    }

    void insertData() {
        try {
            String judul = view.tfJudul.getText();
            double alur = Double.parseDouble(view.tfAlur.getText());
            double penokohan = Double.parseDouble(view.tfPenokohan.getText());
            double akting = Double.parseDouble(view.tfAkting.getText());

            if (!isValidRange(alur) || !isValidRange(penokohan) || !isValidRange(akting)) {
                JOptionPane.showMessageDialog(view, "Semua nilai harus di antara 0 dan 5", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double rating = Math.round(((alur + penokohan + akting) / 3.0) * 100.0) / 100.0;
            view.tfRating.setText(String.valueOf(rating));

            DataMovie movie = new DataMovie(judul, alur, penokohan, akting, rating);
            dao.insert(movie);
            showTable();
            clearForm();
            JOptionPane.showMessageDialog(view, "Data berhasil ditambahkan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Masukkan angka yang valid untuk semua nilai!", "Format Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Gagal menambahkan data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void updateData() {
        try {
            String judul = view.tfJudul.getText();
            double alur = Double.parseDouble(view.tfAlur.getText());
            double penokohan = Double.parseDouble(view.tfPenokohan.getText());
            double akting = Double.parseDouble(view.tfAkting.getText());

            if (!isValidRange(alur) || !isValidRange(penokohan) || !isValidRange(akting)) {
                JOptionPane.showMessageDialog(view, "Semua nilai harus di antara 0 dan 5", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double rating = Math.round(((alur + penokohan + akting) / 3.0) * 100.0) / 100.0;
            view.tfRating.setText(String.valueOf(rating));

            DataMovie movie = new DataMovie(judul, alur, penokohan, akting, rating);
            dao.update(movie);
            showTable();
            clearForm();
            JOptionPane.showMessageDialog(view, "Data berhasil diupdate!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Masukkan angka yang valid untuk semua nilai!", "Format Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Gagal mengupdate data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void deleteData() {
        try {
            String judul = view.tfJudul.getText();
            dao.delete(judul);
            showTable();
            clearForm();
            JOptionPane.showMessageDialog(view, "Data berhasil dihapus!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Gagal menghapus data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void clearForm() {
        view.tfJudul.setText("");
        view.tfAlur.setText("");
        view.tfPenokohan.setText("");
        view.tfAkting.setText("");
        view.tfRating.setText("");
    }

    boolean isValidRange(double value) {
        return value >= 0.0 && value <= 5.0;
    }
}