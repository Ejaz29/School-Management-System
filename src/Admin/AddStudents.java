package Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class AddStudents extends JFrame {
    private JPanel jPanel;
    private String name,mothersname,fathersname,gender,phnno,cls,dob,password;
    private int rollno;
    private JTextField student_name_textfield,mothers_name_textfield,father_name_textfield,rollno_textfield,phnno_textfield,cls_textfield;
    private JPasswordField passwordField;
    private JRadioButton male,female;
    private JComboBox days,month,years;
    AddStudents(){
        super("Add Students");
        gui();
    }

    private void gui() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(0,0,1000,600);
        jPanel=new JPanel();
        jPanel.setLayout(null);
        setContentPane(jPanel);

        //Name label
        JLabel name_label=new JLabel("Name:");
        name_label.setBounds(50,60,200,50);
        name_label.setFont(new Font("Tahoma",Font.BOLD,30));
        jPanel.add(name_label);

        //Name Textfield
        student_name_textfield =new JTextField();
        student_name_textfield.setBounds(160,60,150,50);
        student_name_textfield.setFont(new Font("Times New Roman",Font.PLAIN,30));
        jPanel.add(student_name_textfield);

        //Mothers Name
        JLabel mothers_name_label=new JLabel("Mother's Name:");
        mothers_name_label.setBounds(50,130,400,50);
        mothers_name_label.setFont(new Font("Tahoma",Font.BOLD,30));
        jPanel.add(mothers_name_label);

        //Mothers name textfield
        mothers_name_textfield=new JTextField();
        mothers_name_textfield.setBounds(310,130,200,50);
        mothers_name_textfield.setFont(new Font("Times New Roman",Font.PLAIN,30));
        jPanel.add(mothers_name_textfield);

        //Father's name
        JLabel father_name_label=new JLabel("Father's Name:");
        father_name_label.setBounds(50,210,400,50);
        father_name_label.setFont(new Font("Tahoma",Font.BOLD,30));
        jPanel.add(father_name_label);

        //Fathers name textfield
        father_name_textfield=new JTextField();
        father_name_textfield.setBounds(310,210,200,50);
        father_name_textfield.setFont(new Font("Times New Roman",Font.PLAIN,30));
        jPanel.add(father_name_textfield);

        //Gender label
        JLabel gender_label=new JLabel("Gender:");
        gender_label.setBounds(50,285,150,50);
        gender_label.setFont(new Font("Tahoma",Font.BOLD,30));
        jPanel.add(gender_label);

        //Gender
        male = new JRadioButton("Male");
        male.setBounds(200,270,120,90);
        female = new JRadioButton("Female");
        female.setBounds(320,270,120,90);
        male.setFont(new Font("Tahoma",Font.BOLD,20));
        female.setFont(new Font("Tahoma",Font.BOLD,20));
        ButtonGroup bg=new ButtonGroup();
        bg.add(male);
        bg.add(female);
        jPanel.add(male);
        jPanel.add(female);

        //Rollno label
        JLabel rollno_label=new JLabel("Roll No:");
        rollno_label.setBounds(50,355,150,50);
        rollno_label.setFont(new Font("tahoma",Font.BOLD,30));
        jPanel.add(rollno_label);

        //Rollno textfield
        rollno_textfield=new JTextField();
        rollno_textfield.setBounds(180,360,150,50);
        rollno_textfield.setFont(new Font("Times New Roman",Font.PLAIN,30));
        jPanel.add(rollno_textfield);

        //Phone number label
        JLabel phnno_label=new JLabel("Phn No:");
        phnno_label.setBounds(530,60,140,50);
        phnno_label.setFont(new Font("tahoma",Font.BOLD,30));
        jPanel.add(phnno_label);

        //Phone number
        phnno_textfield=new JTextField();
        phnno_textfield.setBounds(650,60,200,50);
        phnno_textfield.setFont(new Font("Times New Roman",Font.PLAIN,30));
        jPanel.add(phnno_textfield);

        //Class Label
        JLabel cls_label=new JLabel("Class:");
        cls_label.setBounds(530,130,150,50);
        cls_label.setFont(new Font("tahoma",Font.BOLD,30));
        jPanel.add(cls_label);

        //Class textfield
        cls_textfield=new JTextField();
        cls_textfield.setBounds(650,130,130,50);
        cls_textfield.setFont(new Font("Times New Roman",Font.PLAIN,30));
        jPanel.add(cls_textfield);

        //date of birth label
        JLabel dob_label=new JLabel("DOB:");
        dob_label.setBounds(530,200,150,50);
        dob_label.setFont(new Font("tahoma",Font.BOLD,30));
        jPanel.add(dob_label);

        //date of birth
        days =new JComboBox();
        for(int i=1;i<=31;i++){
            days.addItem(String.valueOf(i));
        }
        days.setBounds(620,200,80,50);
        jPanel.add(days);

        String[] months={"Jan","Feb","March","Apr","May","June","July","Aug","Sept","Oct","Nov","Dec"};
        month=new JComboBox(months);
        month.setBounds(700,200,80,50);
        jPanel.add(month);

        years=new JComboBox();
        for(int i=1990;i<=2015;i++){
            years.addItem(String.valueOf(i));
        }
        years.setBounds(780,200,80,50);
        jPanel.add(years);

        //password label
        JLabel password_label=new JLabel("Password:");
        password_label.setBounds(530,270,220,50);
        password_label.setFont(new Font("tahoma",Font.BOLD,30));
        jPanel.add(password_label);

        //password textfield
        passwordField =new JPasswordField();
        passwordField.setBounds(690,270,200,50);
        passwordField.setFont(new Font("Times New Roman",Font.PLAIN,30));
        jPanel.add(passwordField);

        //Add button
        JButton addStudent=new JButton("ADD");
        addStudent.setBounds(500,400,100,40);
        addStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check();
            }
        });
        jPanel.add(addStudent);

        JButton back = new JButton("Back");
        back.setBounds(0,0,120,40);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        jPanel.add(back);
    }

    private void check() {
        name=student_name_textfield.getText();
        mothersname=mothers_name_textfield.getText();
        fathersname=father_name_textfield.getText();
        if(male.isSelected()){
            gender="male";
        }
        else if(female.isSelected()){
            gender="female";
        }
        if(!rollno_textfield.getText().equals("")){
            rollno= Integer.parseInt(rollno_textfield.getText());
        }
        phnno=phnno_textfield.getText();
        cls=cls_textfield.getText();
        dob= (String)days.getSelectedItem()+"/"+(String)month.getSelectedItem()+"/"+(String)years.getSelectedItem();
        password= String.valueOf(passwordField.getPassword());
        if(name.equals("")&&mothersname.equals("")&&fathersname.equals("")&&rollno_textfield.getText().equals("")
        &&phnno.equals("")&&cls.equals("")&&password.equals("")){
            JOptionPane.showMessageDialog(new JFrame(),"Enter the fields","Error",JOptionPane.WARNING_MESSAGE);
        }
        else{
            try {
                Class.forName("oracle.jdbc.OracleDriver");
                Connection connection= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","?","?");
                Statement statement=connection.createStatement();
                String query=String.format("insert into students values ('%s','%s','%s','%s','%s','%s','%s','%s','%s')",
                        name,mothersname,fathersname,gender,dob,rollno,cls,password,phnno);
                statement.executeUpdate(query);
                JOptionPane.showMessageDialog(new JFrame(),"Information is successfully added","Succes",JOptionPane.INFORMATION_MESSAGE);
                student_name_textfield.setText("");
                mothers_name_textfield.setText("");
                father_name_textfield.setText("");
                gender="";
                male.setSelected(false);
                female.setSelected(false);
                rollno_textfield.setText("");
                phnno_textfield.setText("");
                cls_textfield.setText("");
                passwordField.setText("");
                days.setSelectedItem("1");
                month.setSelectedItem("Jan");
                years.setSelectedItem("1990");
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
