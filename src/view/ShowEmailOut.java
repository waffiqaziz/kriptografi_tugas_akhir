package view;

import control.ControlUser;
import control.RSAUtil;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import model.CountRow;
import model.ReadData;
import user.User;

public class ShowEmailOut {
  RSAUtil rsa = new RSAUtil();
  CountRow cr = new CountRow();
  ControlUser cu = new ControlUser();
  
  public ShowEmailOut(User n,int tampilkan) throws IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
    JFrame frame = new JFrame("MAIL - Out");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    createUI(frame,n,tampilkan);
    frame.setSize(568, 568);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
  
  private void createUI(final JFrame frame, User n, int tampilkan) throws IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
    ReadData rd = new ReadData();

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    JPanel panel = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    mainPanel.setBorder(new EmptyBorder(20, 0, 20, 0)); // set border
    JLabel labelMail = new JLabel("Mail : "+n.getEmail());


    mainPanel.add(panel3);
    mainPanel.add(panel);
    mainPanel.add(panel2);
    
    JButton btnBack = new JButton("Back");

    Object namaKolom[] = {"To", "Content"};
    JTable table;

    if (rd.readEmail(n, tampilkan) == null) {
      JOptionPane.showMessageDialog(null, "Tidak Ada Data");
      table = new JTable(null, namaKolom); //tabel merupakan variabel untuk tabelnya dengan isi tablemodel
    } else {
      table = new JTable(rd.readEmail(n, tampilkan), namaKolom); //tabel merupakan variabel untuk tabelnya dengan isi tablemodel
    }

  //ADD TO PANEL
    panel.add(new JScrollPane(table));
    frame.add(mainPanel);
    
    panel3.add(labelMail);
    panel2.add(btnBack);
    
  //ACTION LISTENER
    btnBack.addActionListener((var arg0) -> {
      frame.dispose();
      System.out.println("Yes");
      MainMenu mainMenu = new MainMenu(n);
      mainMenu.pack();
    });

  }
}
