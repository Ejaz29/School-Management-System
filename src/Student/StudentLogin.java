package Student;

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

public class StudentLogin extends JFrame {
    private JPanel jPanel;
    private JTextField user_textfield;
    private JPasswordField jPasswordField;
    private JButton sign_in;
    public StudentLogin(){
        super("Login");
        GUI();
    }

    private void GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 10, 1000, 600);
        setResizable(false);
        jPanel=new JPanel();
        setContentPane(jPanel);
        jPanel.setLayout(null);

        //Student login heading
        JLabel heading=new JLabel("Student Login");
        heading.setBounds(400,20,270,93);
        heading.setFont(new Font("Times New Roman",Font.PLAIN,46));
        jPanel.add(heading);

        //Username
        JLabel username=new JLabel("Username: ");
        username.setBounds(280,170,220,50);
        username.setFont(new Font("Tahoma",Font.BOLD,25));
        jPanel.add(username);

        //Username textfield
        user_textfield=new JTextField();
        user_textfield.setBounds(430,170,230,35);
        user_textfield.setFont(new Font("Tahoma",Font.PLAIN,20));
        jPanel.add(user_textfield);

        //Password label
        JLabel password=new JLabel("Password: ");
        password.setBounds(280,270,220,50);
        password.setFont(new Font("Tahoma",Font.BOLD,25));
        jPanel.add(password);

        //Password textfield
        jPasswordField=new JPasswordField();
        jPasswordField.setBounds(430,270,230,35);
        jPanel.add(jPasswordField);

        //Sign in button
        sign_in=new JButton("Sign In");
        sign_in.setBounds(380,360,150,40);
        sign_in.setFont(new Font("Tahoma",Font.BOLD,20));
        jPanel.add(sign_in);

        sign_in.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check();
            }
        });

        jPasswordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
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
        String username_check=user_textfield.getText();
        String password_check= String.valueOf(jPasswordField.getPassword());
        if (username_check.equals("")) {
            JFrame f=new JFrame();
            JOptionPane.showMessageDialog(f,"Enter username","Warning",JOptionPane.WARNING_MESSAGE);

        }
        else if (password_check.equals("")) {
            JFrame f=new JFrame();
            JOptionPane.showMessageDialog(f,"Enter password","Warning",JOptionPane.WARNING_MESSAGE);
        }
        else{
            try{
                Class.forName("oracle.jdbc.OracleDriver");
                Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","?","?");
                Statement statement=con.createStatement();
                String query=String.format("select name,password from students where name='%s' and password='%s'",username_check,password_check);
                ResultSet rs=statement.executeQuery(query);
                if(rs.next()){
                    dispose();
                    StudentHome studentHome=new StudentHome(username_check,password_check);
                    studentHome.setVisible(true);
                    JOptionPane.showMessageDialog(sign_in,"you have successfully logged in");
                    con.close();
                }
                else{
                    JFrame j=new JFrame();
                    JOptionPane.showMessageDialog(j,"Wrong username or password","Error",JOptionPane.ERROR_MESSAGE);
                    user_textfield.setText("");
                    jPasswordField.setText("");
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
