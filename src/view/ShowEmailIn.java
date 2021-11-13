package view;

import control.ControlUser;
import control.RSAUtil;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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
/**
 *
 * @author Waffiq Aziz / 123190070
 */
public class ShowEmailIn {
  RSAUtil rsa = new RSAUtil();
  CountRow cr = new CountRow();
  ControlUser cu = new ControlUser();
  
  public ShowEmailIn(User n,int tampilkan) throws IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
    JFrame frame = new JFrame("MAIL - In");
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
    mainPanel.setBorder(new EmptyBorder(20, 0, 20, 0)); // set border

    
    mainPanel.add(panel);
    mainPanel.add(panel2);
    
    JButton btnBack = new JButton("Back");
    final JLabel label = new JLabel();

    Object namaKolom[] = {"From", "Content"};
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
    
    panel2.add(label);
    panel2.add(btnBack);
    
  //ACTION LISTENER
    table.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        JTable source = (JTable) evt.getSource();
        int row = source.rowAtPoint(evt.getPoint());
        int column = source.columnAtPoint(evt.getPoint());
        //get ciphertext
        String cipherText = source.getModel().getValueAt(row, column) + "";
     
        //get private key
        String plainText = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int option = fileChooser.showOpenDialog(frame);
        String privateKey = null;
        if (option == JFileChooser.APPROVE_OPTION) {
          String pathFile = fileChooser.getSelectedFile().toString();
          Path fileName = Path.of(pathFile);
          
          try {
            privateKey = Files.readString(fileName);
          } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ShowEmailIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }

          System.out.println(privateKey);

        } else {
          label.setText("Decryption Failed!!!");
        }

        try {
          plainText = rsa.rsaDecryption(cipherText, privateKey);
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException ex) {
          Logger.getLogger(ShowEmailIn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JOptionPane.showMessageDialog(null, plainText, "Content", JOptionPane.PLAIN_MESSAGE);
      }
    });
    
    btnBack.addActionListener((var arg0) -> {
      frame.dispose();
      System.out.println("Yes");
      new MainMenu(n);
    });

  }
}
