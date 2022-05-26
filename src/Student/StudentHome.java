package Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class StudentHome extends JFrame {
    private JPanel jPanel;
    private String name,password;
    private String fathers_name,mothers_name,gender,dateofbirth,phnno,cls;
    private int rollno;
    StudentHome(String name,String password){
        super("Student Home Page");
        this.name=name;
        this.password=password;
        gui();

    }

    private void gui() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 10, 1000, 600);
        setResizable(false);
        jPanel=new JPanel();
        setContentPane(jPanel);
        jPanel.setLayout(null);

        try{
            Class.forName("oracle.jdbc.OracleDriver");
            Connection connection= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","?","?");
            Statement statement=connection.createStatement();
            String query=String.format("select * from students where name='%s' and password='%s'",name,password);
            ResultSet rs=statement.executeQuery(query);
            if(rs.next()){
                mothers_name=rs.getString(2);
                fathers_name=rs.getString(3);
                gender=rs.getString(4);
                dateofbirth=rs.getString(5);
                rollno=rs.getInt(6);
                cls=rs.getString(7);
                phnno=rs.getString(9);
            }
            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        //heading
        JLabel heading =new JLabel("Student Home");
        heading.setBounds(375,20,270,93);
        heading.setFont(new Font("Times New Roman",Font.PLAIN,46));
        jPanel.add(heading);

        //settings
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 1008, 26);
        JMenu settings = new JMenu("Settings");
        menuBar.add(settings);
        jPanel.add(menuBar);

        //logout
        JMenuItem logout=new JMenuItem("Logout");
        settings.add(logout);
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a=JOptionPane.showConfirmDialog(logout,"Are you sure?");
                if(a==JOptionPane.YES_OPTION){
                    dispose();
                    StudentLogin studentLogin=new StudentLogin();
                    studentLogin.setVisible(true);
                }
            }
        });

        //change password
        JMenuItem change_password=new JMenuItem("Change Password");
        settings.add(change_password);
        change_password.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ChangePassword changePassword=new ChangePassword();
                changePassword.setVisible(true);
            }
        });

        //Name
        JLabel name_label=new JLabel("Name:");
        name_label.setBounds(70,90,200,50);
        name_label.setFont(new Font("Tahoma",Font.BOLD,30));
        jPanel.add(name_label);

        //Name Textfield
        JTextField student_name=new JTextField(name);
        student_name.setBounds(180,90,100,50);
        student_name.setFont(new Font("Times New Roman",Font.PLAIN,30));
        student_name.setEditable(false);
        jPanel.add(student_name);

        //Mothers Name
        JLabel mothers_name_label=new JLabel("Mother's Name:");
        mothers_name_label.setBounds(70,140,400,50);
        mothers_name_label.setFont(new Font("Tahoma",Font.BOLD,30));
        jPanel.add(mothers_name_label);

        //Mothers name textfield
        JTextField mothers_name_textfield=new JTextField(mothers_name);
        mothers_name_textfield.setBounds(330,140,200,50);
        mothers_name_textfield.setFont(new Font("Times New Roman",Font.PLAIN,30));
        mothers_name_textfield.setEditable(false);
        jPanel.add(mothers_name_textfield);

        //Father's name
        JLabel father_name_label=new JLabel("Father's Name:");
        father_name_label.setBounds(70,200,400,50);
        father_name_label.setFont(new Font("Tahoma",Font.BOLD,30));
        jPanel.add(father_name_label);

        //fathers name textfield
        JTextField father_name_textfield=new JTextField(fathers_name);
        father_name_textfield.setBounds(330,200,200,50);
        father_name_textfield.setFont(new Font("Times New Roman",Font.PLAIN,30));
        father_name_textfield.setEditable(false);
        jPanel.add(father_name_textfield);

        //Gender label
        JLabel gender_label=new JLabel("Gender:");
        gender_label.setBounds(70,260,150,50);
        gender_label.setFont(new Font("Tahoma",Font.BOLD,30));
        jPanel.add(gender_label);

        //gender textfield
        JTextField gender_textfield=new JTextField(gender);
        gender_textfield.setBounds(230,260,150,50);
        gender_textfield.setFont(new Font("times new roman",Font.PLAIN,30));
        gender_textfield.setEditable(false);
        jPanel.add(gender_textfield);

        //Rollno label
        JLabel rollno_label=new JLabel("Roll No:");
        rollno_label.setBounds(70,315,150,50);
        rollno_label.setFont(new Font("tahoma",Font.BOLD,30));
        jPanel.add(rollno_label);

        //rollno
        JTextField rollno_textfield=new JTextField(Integer.toString(rollno));
        rollno_textfield.setBounds(200,320,150,50);
        rollno_textfield.setFont(new Font("Times New Roman",Font.PLAIN,30));
        rollno_textfield.setEditable(false);
        jPanel.add(rollno_textfield);

        //phone number label
        JLabel phnno_label=new JLabel("Phn No:");
        phnno_label.setBounds(70,370,150,50);
        phnno_label.setFont(new Font("tahoma",Font.BOLD,30));
        jPanel.add(phnno_label);

        //phone number
        JTextField phnno_textfield=new JTextField(phnno);
        phnno_textfield.setBounds(200,370,200,50);
        phnno_textfield.setFont(new Font("Times New Roman",Font.PLAIN,30));
        phnno_textfield.setEditable(false);
        jPanel.add(phnno_textfield);

        //Class Label
        JLabel cls_label=new JLabel("Class:");
        cls_label.setBounds(70,420,150,50);
        cls_label.setFont(new Font("tahoma",Font.BOLD,30));
        jPanel.add(cls_label);

        //Class
        JTextField cls_textfield=new JTextField(cls);
        cls_textfield.setBounds(200,420,200,50);
        cls_textfield.setFont(new Font("Times New Roman",Font.PLAIN,30));
        cls_textfield.setEditable(false);
        jPanel.add(cls_textfield);

        //date of birth label
        JLabel dob_label=new JLabel("DOB:");
        dob_label.setBounds(70,470,150,50);
        dob_label.setFont(new Font("tahoma",Font.BOLD,30));
        jPanel.add(dob_label);

        //date of birth
        JTextField dob_textfield=new JTextField(dateofbirth);
        dob_textfield.setBounds(200,470,300,50);
        dob_textfield.setFont(new Font("Times New Roman",Font.PLAIN,30));
        dob_textfield.setEditable(false);
        jPanel.add(dob_textfield);
    }
}
