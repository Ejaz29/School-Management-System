package Admin;

import Home.Home;

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

public class AdminLogin extends JFrame {
    private JPanel jPanel;
    private String username,password;
    private JTextField username_textfield;
    private JPasswordField passwordField;
    public AdminLogin(){
        super("Admin Login");
        gui();
    }
    private void gui(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0,0,1000,600);
        setResizable(false);
        jPanel=new JPanel();
        jPanel.setLayout(null);
        setContentPane(jPanel);

        //Heading
        JLabel heading=new JLabel("Admin Login");
        heading.setBounds(400,20,270,93);
        heading.setFont(new Font("Times New Roman",Font.PLAIN,40));
        jPanel.add(heading);

        //Username
        JLabel username=new JLabel("Username: ");
        username.setBounds(280,170,220,50);
        username.setFont(new Font("Tahoma",Font.BOLD,25));
        jPanel.add(username);

        //Username textfield
        username_textfield=new JTextField();
        username_textfield.setBounds(430,170,230,35);
        username_textfield.setFont(new Font("Tahoma",Font.PLAIN,20));
        jPanel.add(username_textfield);

        //Password Label
        JLabel password=new JLabel("Password: ");
        password.setBounds(280,270,220,50);
        password.setFont(new Font("Tahoma",Font.BOLD,25));
        jPanel.add(password);

        //Password textfield
        passwordField=new JPasswordField();
        passwordField.setBounds(430,270,230,35);
        jPanel.add(passwordField);

        //sign in button
        JButton sign_in=new JButton("Sign In");
        sign_in.setBounds(380,360,150,40);
        sign_in.setFont(new Font("Tahoma",Font.BOLD,20));
        jPanel.add(sign_in);

        sign_in.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check();
            }
        });
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    check();
                }
            }
        });

        //Back button
        JButton back = new JButton("Back");
        back.setBounds(0,0,100,40);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Home home=new Home();
                home.setVisible(true);
            }
        });
        jPanel.add(back);
    }

    private void check() {
        username=username_textfield.getText();
        password= String.valueOf(passwordField.getPassword());
        if(username.equals("")){
            JOptionPane.showMessageDialog(new Frame(),"Enter username","Error",JOptionPane.WARNING_MESSAGE);
        }
        else if(password.equals("")){
            JOptionPane.showMessageDialog(new Frame(),"Enter password","Error",JOptionPane.WARNING_MESSAGE);
        }
        else{
            try {
                Class.forName("oracle.jdbc.OracleDriver");
                Connection connection= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","?","?");
                Statement statement=connection.createStatement();
                String query=String.format("select * from admins where name='%s' and password='%s'",username,password);
                ResultSet resultSet=statement.executeQuery(query);
                if(resultSet.next()){
                    dispose();
                    AdminHome adminHome=new AdminHome(username,password);
                    adminHome.setVisible(true);
                    JOptionPane.showMessageDialog(new JFrame(),"You have successfully logged in","Login",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(new JFrame(),"Wrong username or password","Error",JOptionPane.ERROR_MESSAGE);
                }
                connection.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
