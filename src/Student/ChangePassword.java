package Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ChangePassword extends JFrame {
    private JPanel jPanel;
    private String new_password,rollno;
    private JPasswordField new_password_passwordfield;
    private JTextField rollno_textfield;
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

        //roll no label
        JLabel rollno_label=new JLabel("Roll NO:");
        rollno_label.setBounds(130,100,150,50);
        rollno_label.setFont(new Font("Tahoma",Font.BOLD,30));
        jPanel.add(rollno_label);

        //roll textfield
        rollno_textfield=new JTextField();
        rollno_textfield.setBounds(290,100,200,50);
        rollno_textfield.setFont(new Font("Times New Roman",Font.PLAIN,30));
        jPanel.add(rollno_textfield);

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
        rollno=rollno_textfield.getText();
        if(new_password.equals("")){
            JOptionPane.showMessageDialog(new JFrame(),"Enter New Password","Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(rollno.equals("")){
            JOptionPane.showMessageDialog(new JFrame(),"Enter Roll no","Error",JOptionPane.ERROR_MESSAGE);
        }
        else{
            try{
                Class.forName("oracle.jdbc.OracleDriver");
                Connection connection= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","?","?");
                Statement statement=connection.createStatement();
                String query=String.format("select password from students where rollno='%s'",rollno);
                ResultSet resultSet=statement.executeQuery(query);
                if(resultSet.next()){
                    String update_query=String.format("update students set password='%s' where rollno='%s'",new_password,rollno);
                    statement.executeUpdate(update_query);
                    JOptionPane.showMessageDialog(new JFrame(),"password changed","Change",JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    StudentLogin studentLogin=new StudentLogin();
                    studentLogin.setVisible(true);
                }
                else {
                    JOptionPane.showMessageDialog(new JFrame(), "Invalid old password", "Error",JOptionPane.ERROR_MESSAGE);
                    rollno_textfield.setText("");
                    new_password_passwordfield.setText("");
                }
            }
            catch (Exception e){

            }
        }
    }
}
