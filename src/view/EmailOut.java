/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Dimension;
import model.ReadData;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import user.User;
import java.awt.Component;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Waffiq Aziz / 123190070
 */
public class EmailOut {
  DefaultTableCellRenderer cellRenderer;
  ReadData rd = new ReadData();

//DEKLARASI KOMPONEN
  JFrame window = new JFrame("MAIL - Email Out");
  JPanel panel = new JPanel();
  
  JTable tabel;
  DefaultTableModel tableModel;
  JScrollPane scrollPane;
  Object namaKolom[] = {"Subject", "Content"};
  JButton btnBack = new JButton("Kembali");

  public void resizeColumnWidth(JTable table) {
    final TableColumnModel columnModel = table.getColumnModel();
    for (int column = 0; column < table.getColumnCount(); column++) {
        int width = 15; // Min width
        for (int row = 0; row < table.getRowCount(); row++) {
            TableCellRenderer renderer = table.getCellRenderer(row, column);
            Component comp = table.prepareRenderer(renderer, row, column);
            width = Math.max(comp.getPreferredSize().width +1 , width);
        }
        if(width > 300)
            width=300;
        columnModel.getColumn(column).setPreferredWidth(width);
    }
}
  
  public EmailOut(User n) {
    window.setLayout(null);
    window.setSize(720, 568);
    window.setVisible(true);
    window.setLocationRelativeTo(null);
    window.setResizable(false);
    window.setDefaultCloseOperation(EXIT_ON_CLOSE); // running program berhenti jika tombol close ditekan

    window.add(btnBack);
    window.add(panel);

    

    if (rd.readEmailOut(n) == null) {
      JOptionPane.showMessageDialog(null, "Tidak Ada Data");
      tabel = new JTable(null, namaKolom); //tabel merupakan variabel untuk tabelnya dengan isi tablemodel
    } else {
      tabel = new JTable(rd.readEmailOut(n), namaKolom); //tabel merupakan variabel untuk tabelnya dengan isi tablemodel
    }
    
//  ubah bagian dimensi untuk mengubah ukuran table
    tabel.setPreferredScrollableViewportSize(new Dimension(500,430));
    tabel.setFillsViewportHeight(true);
    scrollPane = new JScrollPane(tabel);
    panel.add(scrollPane);
    
    // sett bounds(m,n,o,p) >>> (sumbu-x,user1@gmail.comsumbu-y,panjang komponen, tinggi komponen)
    panel.setBounds(100,30,600,600);
    tabel.setBounds(100,20,500,500);
    
    btnBack.setBounds(0, 230, 80, 30);
    tabel.getColumnModel().getColumn(0).setPreferredWidth(20);
//    tabel.getColumnModel().getColumn(0).setPreferredWidth(1);
    tabel.getColumnModel().getColumn(1).setPreferredWidth(100);
    
// action listener
    btnBack.addActionListener((ActionEvent arg0) -> {
      window.dispose();
      System.out.println("Yes");
      new MainMenu(n);
    });
  }
}
