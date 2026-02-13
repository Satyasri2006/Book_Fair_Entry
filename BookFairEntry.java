import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
public class BookFairEntry {
    public static Connection con;
    public static void main(String[] args) throws Exception {
        //DB Connection
        String dbURL, dbUser, dbPassword;
        dbURL="jdbc:mysql://localhost:3306/swingdb";
        dbUser="root";
        dbPassword="Satya";

        con=DriverManager.getConnection(dbURL, dbUser, dbPassword);
        if(con!=null)System.out.println("Connected successfully");

        //Frame
        JFrame frame=new JFrame("Book Fair Entry");
        frame.setSize(720, 750);
        frame.setLayout(null);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Font font1=new Font("Times New Roman", Font.BOLD, 26);
        Font font2=new Font("comic Sans Ms", Font.BOLD, 18);
        
        //labels

        //REGISTER

        JLabel register=new JLabel("Register here");
        register.setBounds(10, 10, 480, 30);
        register.setFont(font1);
        frame.add(register);

        JLabel name=new JLabel("Enter your name");
        name.setBounds(10, 60, 250, 20);
        name.setFont(font2);
        frame.add(name);    
        
        JLabel age=new JLabel("Enter your age");
        age.setBounds(10, 95, 250, 20);
        age.setFont(font2);
        frame.add(age);

        JLabel gender=new JLabel("Select your gender");
        gender.setBounds(10, 130, 250, 20);
        gender.setFont(font2);
        frame.add(gender);

        JLabel city=new JLabel("Enter your city name");
        city.setBounds(10, 165, 250, 20);
        city.setFont(font2);
        frame.add(city);

        JLabel phone=new JLabel("Enter your phone number");
        phone.setBounds(10,200, 250, 20);
        phone.setFont(font2);
        frame.add(phone);

        JLabel interest=new JLabel("Do you read books?");
        interest.setBounds(10, 235, 250, 20);
        interest.setFont(font2);
        frame.add(interest);

        //text fields

        JTextField tfname=new JTextField();
        tfname.setBounds(320, 60, 350, 20);
        frame.add(tfname);

        JTextField tfage=new JTextField();
        tfage.setBounds(320, 95, 90, 20);
        frame.add(tfage);

        //radio buttons
        JRadioButton rbmale=new JRadioButton("Male");
        rbmale.setBounds(320, 130, 80, 20);
        JRadioButton rbfemale=new JRadioButton("Female");
        rbfemale.setBounds(440, 130, 120, 20);
        JRadioButton rbothers=new JRadioButton("Others");
        rbothers.setBounds(560, 130, 120, 20);
        ButtonGroup bggender=new ButtonGroup();
        bggender.add(rbmale);
        bggender.add(rbfemale);
        bggender.add(rbothers);
        frame.add(rbmale);
        frame.add(rbfemale);
        frame.add(rbothers);
        
        JTextField tfcity=new JTextField();
        tfcity.setBounds(320, 165, 300, 20);
        frame.add(tfcity);
        
        JTextField tfphone=new JTextField();
        tfphone.setBounds(320, 200, 350, 20);
        frame.add(tfphone);

        //combo box
        String interests[]={"Very Frequently", "Often", "Sometimes", "Rarely", "Never before :)"};
        JComboBox<String> cbinterest=new JComboBox<>(interests);
        cbinterest.setBounds(320, 235, 220, 20);
        frame.add(cbinterest);

        // button
        JButton registerButton=new JButton("Submit");
        registerButton.setBounds(400, 265, 160, 30);
        registerButton.setFont(new Font("comic sans Ms", Font.BOLD, 24));
        registerButton.setFocusable(true);
        frame.add(registerButton);

        
        //action
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String n=tfname.getText().trim();
                String a=tfage.getText().trim();
                String g=rbmale.isSelected()?"Male":rbfemale.isSelected()?"Female":rbothers.isSelected()?"Others":"";
                String c=tfcity.getText().trim();
                String p=tfphone.getText().trim();
                String i=cbinterest.getSelectedItem().toString();
                if(n.isEmpty()||a.isEmpty()||g.isEmpty()||c.isEmpty()||p.isEmpty()||i.isEmpty()){
                    JOptionPane.showMessageDialog(frame,"Required data missing", "Error", JOptionPane.ERROR_MESSAGE);
                }
                //adding into db
                insertRegisterDataToDB(n,a,g,c,p,i);
                //clear after submit
                tfname.setText("");
                tfage.setText("");
                bggender.clearSelection();
                tfcity.setText("");
                tfphone.setText("");
                cbinterest.setSelectedItem(null);
            }
        });

        //UPDATE

        JLabel update=new JLabel("Update your info here (fill out only the required fields)");
        update.setBounds(10, 305, 650, 30);
        update.setFont(font1);
        frame.add(update);

        JLabel upname=new JLabel("Enter your name");
        upname.setBounds(10, 355, 250, 20);
        upname.setFont(font2);
        frame.add(upname);

        JLabel upage=new JLabel("Update your age");
        upage.setBounds(10, 390, 250, 20);
        upage.setFont(font2);
        frame.add(upage);

        JLabel upphone=new JLabel("Update your phone number");
        upphone.setBounds(10, 425, 250, 20);
        upphone.setFont(font2);
        frame.add(upphone);

        JLabel upemail=new JLabel("Update your email id");
        upemail.setBounds(10,460, 250, 20);
        upemail.setFont(font2);
        frame.add(upemail);

        JLabel upinterest=new JLabel("Update your habit");
        upinterest.setBounds(10, 495, 250, 20);
        upinterest.setFont(font2);
        frame.add(upinterest);

        //text fields

        JTextField tfupname=new JTextField();
        tfupname.setBounds(320, 355, 350, 20);
        frame.add(tfupname);

        JTextField tfupage=new JTextField();
        tfupage.setBounds(320, 390, 90, 20);
        frame.add(tfupage);
        
        JTextField tfupphone=new JTextField();
        tfupphone.setBounds(320, 425, 300, 20);
        frame.add(tfupphone);
        
        JTextField tfupemail=new JTextField();
        tfupemail.setBounds(320, 460, 350, 20);
        frame.add(tfupemail);

        //combo box
        JComboBox<String> cbupinterest=new JComboBox<>(interests);
        cbupinterest.setBounds(320, 495, 220, 20);
        frame.add(cbupinterest);

        // button
        JButton updateButton=new JButton("Submit");
        updateButton.setBounds(400, 525, 160, 30);
        updateButton.setFont(new Font("comic sans Ms", Font.BOLD, 24));
        updateButton.setFocusable(true);
        frame.add(updateButton);

        
        //action
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String n=tfupname.getText().trim();
                String a=tfupage.getText().trim();
                String p=tfupphone.getText().trim();
                String em=tfupemail.getText().trim();
                String i=cbupinterest.getSelectedItem().toString();
                //updating the db
                updateDataInDB(n,a,p,em,i);
                //clear after submit
                tfupname.setText("");
                tfupage.setText("");
                tfupphone.setText("");
                tfupemail.setText("");
                cbupinterest.setSelectedItem(null);
            }
        });

        //DELETE

        JLabel delete=new JLabel("Opt out here");
        delete.setBounds(10, 580, 480, 30);
        delete.setFont(font1);
        frame.add(delete);

        JLabel optout=new JLabel("Do you want to opt out of the Book Fair entry?");
        optout.setBounds(10, 630, 450, 25);
        optout.setFont(font2);
        frame.add(optout);

        JRadioButton rbyes=new JRadioButton("Yes");
        rbyes.setBounds(470, 630, 60, 20);
        JRadioButton rbno=new JRadioButton("No");
        rbno.setBounds(540, 630, 60, 20);
        ButtonGroup bgdelete=new ButtonGroup();
        bgdelete.add(rbyes);
        bgdelete.add(rbno);
        frame.add(rbyes);
        frame.add(rbno);

        JLabel deletePhone=new JLabel("Enter your registered phone number");
        deletePhone.setBounds(10, 670, 450, 25);
        deletePhone.setFont(font2);
        frame.add(deletePhone);
        JTextField tfdel=new JTextField();
        tfdel.setBounds(400, 670, 160, 25);
        frame.add(tfdel);

        // button
        JButton deleteButton=new JButton("Submit");
        deleteButton.setBounds(400, 710, 160, 30);
        deleteButton.setFont(new Font("comic sans Ms", Font.BOLD, 24));
        deleteButton.setFocusable(true);
        frame.add(deleteButton);

        //action
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String p=tfdel.getText().trim();
                if(p.isEmpty()){
                    JOptionPane.showMessageDialog(frame,"Required data missing", "Error", JOptionPane.ERROR_MESSAGE);
                }
                //deleting from db
                deleteDataFromDB(p);
                //clear after submit
                tfdel.setText("");
                bgdelete.clearSelection();
            }
        });
    }
    public static void insertRegisterDataToDB(String n, String a, String g, String c, String p, String i){
        try{
            String sql="INSERT INTO entries (name, age, gender, city, phone, interest) VALUES(?,?,?,?,?,?)";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,n);
            ps.setInt(2,Integer.parseInt(a));
            ps.setString(3,g);
            ps.setString(4,c);
            ps.setLong(5,Long.parseLong(p));
            ps.setString(6,i);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Thanks for registering!","success",JOptionPane.INFORMATION_MESSAGE);

        }
        catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "Phone number already exists!", "Duplicate Entry", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void updateDataInDB(String n, String a, String p, String em, String i){
        try{
            if(!a.isEmpty()){
                String sql="UPDATE entries SET age=? WHERE name=?";
                PreparedStatement ps=con.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(a));
                ps.setString(2, n);
                ps.executeUpdate();
            }
            if(!p.isEmpty()){
                String sql="UPDATE entries SET phone=? WHERE name=?";
                PreparedStatement ps=con.prepareStatement(sql);
                ps.setLong(1, Long.parseLong(p));
                ps.setString(2, n);
                ps.executeUpdate();
            }
            if(!em.isEmpty()){
                String sql="UPDATE entries SET email=? WHERE name=?";
                PreparedStatement ps=con.prepareStatement(sql);
                ps.setString(1, em);
                ps.setString(2, n);
                ps.executeUpdate();

            }
            if(!i.isEmpty()){
                String sql="UPDATE entries SET interest=? WHERE name=?";
                PreparedStatement ps=con.prepareStatement(sql);
                ps.setString(1, i);
                ps.setString(2, n);
                ps.executeUpdate();
            }
            JOptionPane.showMessageDialog(null,"Data updated sucessfully","success",JOptionPane.INFORMATION_MESSAGE);

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void deleteDataFromDB(String p){
        try{
            String sql="DELETE FROM entries WHERE phone=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setLong(1,Long.parseLong(p));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Deleted sucessfully","success",JOptionPane.INFORMATION_MESSAGE);

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
