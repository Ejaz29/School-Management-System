package Home;

import Admin.AdminLogin;
import Student.StudentLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame {
    private JPanel jPanel;
    private JButton adminLogin,studentLogin;
    public Home(){
        super("Home Page");
        gui();
    }

    private void gui() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(0,0,1000,600);
        jPanel=new JPanel();
        jPanel.setLayout(null);
        setContentPane(jPanel);

        //Heading
        JLabel heading =new JLabel("Home Page");
        heading.setBounds(375,20,270,93);
        heading.setFont(new Font("Times New Roman",Font.PLAIN,46));
        jPanel.add(heading);

        //Admin login button
        adminLogin=new JButton("Admin Login");
        adminLogin.setBounds(130,200,330,100);
        adminLogin.setFont(new Font("Tahoma",Font.BOLD,40));
        adminLogin.setBackground(Color.lightGray);
        jPanel.add(adminLogin);

        //Student login button
        studentLogin=new JButton("Student Login");
        studentLogin.setBounds(500,200,330,100);
        studentLogin.setFont(new Font("Tahoma",Font.BOLD,40));
        studentLogin.setBackground(Color.lightGray);
        jPanel.add(studentLogin);
        jPanel.setBackground(Color.cyan);

        adminLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AdminLogin adminLogin=new AdminLogin();
                adminLogin.setVisible(true);
            }
        });

        studentLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                StudentLogin studentLogin=new StudentLogin();
                studentLogin.setVisible(true);
            }
        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Home home=new Home();
                home.setVisible(true);
            }
        });
    }
}
