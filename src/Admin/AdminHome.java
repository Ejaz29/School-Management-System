package Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

class AdminHome extends JFrame {
    private JPanel jPanel;
    private String name,password;
    private JTextField phn_no_textfield,designation_textfield,subject_textfield;
    AdminHome(String name,String password){
        super("Admin Home");
        this.name=name;
        this.password=password;
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
        JLabel heading =new JLabel("Admin Home");
        heading.setBounds(375,20,270,93);
        heading.setFont(new Font("Times New Roman",Font.PLAIN,46));
        jPanel.add(heading);

        //Name
        JLabel name_label=new JLabel("Name:");
        name_label.setBounds(70,90,200,50);
        name_label.setFont(new Font("Tahoma",Font.BOLD,30));
        jPanel.add(name_label);

        //Name Textfield
        JTextField admin_name_textfield=new JTextField(name);
        admin_name_textfield.setBounds(180,90,100,50);
        admin_name_textfield.setFont(new Font("Times New Roman",Font.PLAIN,30));
        admin_name_textfield.setEditable(false);
        jPanel.add(admin_name_textfield);

        //Phn no
        JLabel phn_no_label=new JLabel("Phn no:");
        phn_no_label.setBounds(70,160,400,50);
        phn_no_label.setFont(new Font("Tahoma",Font.BOLD,30));
        jPanel.add(phn_no_label);

        //Phn no textfield
        phn_no_textfield=new JTextField();
        phn_no_textfield.setBounds(250,160,200,50);
        phn_no_textfield.setFont(new Font("Times New Roman",Font.PLAIN,30));
        phn_no_textfield.setEditable(false);
        jPanel.add(phn_no_textfield);

        //Designation label
        JLabel designation_label=new JLabel("Designation:");
        designation_label.setBounds(70,240,400,50);
        designation_label.setFont(new Font("Tahoma",Font.BOLD,30));
        jPanel.add(designation_label);

        //Designation textfield
        designation_textfield=new JTextField();
        designation_textfield.setBounds(300,240,280,50);
        designation_textfield.setFont(new Font("Times New Roman",Font.PLAIN,30));
        designation_textfield.setEditable(false);
        jPanel.add(designation_textfield);

        //Subject label
        JLabel subject_label=new JLabel("Subject:");
        subject_label.setBounds(70,320,150,50);
        subject_label.setFont(new Font("Tahoma",Font.BOLD,30));
        jPanel.add(subject_label);

        //Subject textfield
        subject_textfield=new JTextField();
        subject_textfield.setBounds(240,320,200,50);
        subject_textfield.setFont(new Font("times new roman",Font.PLAIN,25));
        subject_textfield.setEditable(false);
        jPanel.add(subject_textfield);

        //data retrieval
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "?", "?");
            Statement statement=connection.createStatement();
            String query = String.format("select * from admins where name='%s' and password='%s'", name, password);
            ResultSet rs=statement.executeQuery(query);
            if(rs.next()){
                phn_no_textfield.setText(rs.getString(2));
                designation_textfield.setText(rs.getString(3));
                subject_textfield.setText(rs.getString(4));
            }
            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        JMenuBar jMenuBar=new JMenuBar();
        jMenuBar.setBounds(0,0,1000,28);
        JMenu settings=new JMenu("Settings");
        jMenuBar.add(settings);
        jPanel.add(jMenuBar);

        JMenuItem logout = new JMenuItem("Logout");
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a=JOptionPane.showConfirmDialog(new JFrame(), "Are you sure?");
                if (a == JOptionPane.YES_OPTION) {
                    dispose();
                    AdminLogin adminLogin=new AdminLogin();
                    adminLogin.setVisible(true);
                }
            }
        });
        settings.add(logout);

        JMenuItem changePassword = new JMenuItem("Change Password");
        changePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ChangePassword cp=new ChangePassword();
                cp.setVisible(true);
            }
        });
        settings.add(changePassword);

        JButton addStudents = new JButton("Add Students");
        addStudents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddStudents as=new AddStudents();
                as.setVisible(true);
            }
        });
       addStudents.setBounds(450,400,150,50);
       jPanel.add(addStudents);
    }
}
