package Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ChangePassword extends JFrame {
    private JPanel jPanel;
    private String new_password, old_password;
    private JPasswordField new_password_passwordfield;
    private JPasswordField old_password_passwordfield;
    ChangePassword(){
        super("Password Change");
        gui();
    }
    private void gui(){
        setResizable(false);
        setBounds(0,0,1000,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jPanel=new JPanel();
        setContentPane(jPanel);
        jPanel.setLayout(null);

        //old password label
        JLabel old_password_label=new JLabel("Old Password:");
        old_password_label.setBounds(130,100,270,50);
        old_password_label.setFont(new Font("Tahoma",Font.BOLD,30));
        jPanel.add(old_password_label);

        //old password password field
        old_password_passwordfield =new JPasswordField();
        old_password_passwordfield.setBounds(350,100,200,50);
        old_password_passwordfield.setFont(new Font("Times New Roman",Font.PLAIN,30));
        jPanel.add(old_password_passwordfield);

        //new password label
        JLabel new_password_label=new JLabel("New Password:");
        new_password_label.setBounds(130,200,250,50);
        new_password_label.setFont(new Font("Tahoma",Font.BOLD,30));
        jPanel.add(new_password_label);

        //new password passwordfield
        new_password_passwordfield=new JPasswordField();
        new_password_passwordfield.setBounds(370,200,200,50);
        new_password_passwordfield.setFont(new Font("Times New Roman",Font.PLAIN,30));
        jPanel.add(new_password_passwordfield);

        //Change Button
        JButton change=new JButton("Change");
        change.setBounds(260,300,100,30);
        jPanel.add(change);
        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check();
            }
        });

        new_password_passwordfield.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    check();
                }
            }
        });
    }

    private void check(){
        new_password= String.valueOf(new_password_passwordfield.getPassword());
        old_password = String.valueOf(old_password_passwordfield.getPassword());
        if(old_password.equals("")){
            JOptionPane.showMessageDialog(new JFrame(),"Enter old password","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(new_password.equals("")){
            JOptionPane.showMessageDialog(new JFrame(),"Enter New Password","Error",JOptionPane.ERROR_MESSAGE);
        }
        else {
            try {
                Class.forName("oracle.jdbc.OracleDriver");
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "?", "?");
                Statement statement = connection.createStatement();
                String query = String.format("select password from admins where password='%s'", old_password);
                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    String update_query = String.format("update admins set password='%s' where password='%s'", new_password, old_password);
                    statement.executeUpdate(update_query);
                    JOptionPane.showMessageDialog(new JFrame(), "password changed", "Change", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    AdminLogin adminLogin = new AdminLogin();
                    adminLogin.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Invalid old password", "Error", JOptionPane.ERROR_MESSAGE);
                    old_password_passwordfield.setText("");
                    new_password_passwordfield.setText("");
                }
            } catch (Exception e) {

            }
        }
    }
}
