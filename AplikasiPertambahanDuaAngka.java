package com.example.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AplikasiPertambahanDuaAngka extends JFrame {
    private JTextField textField1, textField2, textFieldHasil;
    private JButton btnTambah, btnHapus, btnKeluar;

    public AplikasiPertambahanDuaAngka() {
        // Pengaturan JFrame
        setTitle("Aplikasi Pertambahan Dua Angka");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel utama
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        // Komponen GUI
        JLabel label1 = new JLabel("Angka Pertama:");
        JLabel label2 = new JLabel("Angka Kedua:");
        JLabel labelHasil = new JLabel("Hasil:");

        textField1 = new JTextField();
        textField2 = new JTextField();
        textFieldHasil = new JTextField();
        textFieldHasil.setEditable(false);

        btnTambah = new JButton("Tambah");
        btnHapus = new JButton("Hapus");
        btnKeluar = new JButton("Keluar");

        // Tambahkan komponen ke panel
        panel.add(label1);
        panel.add(textField1);
        panel.add(label2);
        panel.add(textField2);
        panel.add(labelHasil);
        panel.add(textFieldHasil);
        panel.add(btnTambah);
        panel.add(btnHapus);

        // Tambahkan panel ke JFrame
        add(panel, BorderLayout.CENTER);
        add(btnKeluar, BorderLayout.SOUTH);

        // Logika dan Event Handling
        btnTambah.addActionListener(e -> tambahAngka());
        btnHapus.addActionListener(e -> hapusInput());
        btnKeluar.addActionListener(e -> System.exit(0));

        // Validasi input numerik dengan KeyAdapter
        KeyAdapter angkaSaja = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                    JOptionPane.showMessageDialog(null, "Hanya angka yang diperbolehkan!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };

        textField1.addKeyListener(angkaSaja);
        textField2.addKeyListener(angkaSaja);

        // Implementasi FocusListener
        FocusListener bersihkanInput = new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                ((JTextField) e.getSource()).setText("");
            }
        };

        textField1.addFocusListener(bersihkanInput);
        textField2.addFocusListener(bersihkanInput);
    }

    private void tambahAngka() {
        try {
            int angka1 = Integer.parseInt(textField1.getText());
            int angka2 = Integer.parseInt(textField2.getText());
            int hasil = angka1 + angka2;
            textFieldHasil.setText(String.valueOf(hasil));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Masukkan angka yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void hapusInput() {
        textField1.setText("");
        textField2.setText("");
        textFieldHasil.setText("");
        textField1.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AplikasiPertambahanDuaAngka app = new AplikasiPertambahanDuaAngka();
            app.setVisible(true);
        });
    }
}
