package com.gui;
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;     //for event handling
import java.awt.event.ActionEvent;
   //parent component:
public class RegisterFrame extends JFrame implements ActionListener{
    //Declare components
    JLabel lblName, lblId;
    JTextField txtName, txtId;
    JButton btnRegister;


    //child component:
    public RegisterFrame() {
        super("OOPbasics.Welcome ");
        setLayout(new FlowLayout());

        //Initialize child components
        lblName=new JLabel("Name:");
        txtName=new JTextField(10);
        lblId=new JLabel("Id No.");
        txtId=new JTextField(10);
        btnRegister=new JButton("Register");

        //link button to handler
        btnRegister.addActionListener(this);

        //Add components to RegisterFrame
        add(lblName);
        add(txtName);
        add(lblId);
        add(txtId);
        add(btnRegister);
    }
    public static void main(String[] args) {
        RegisterFrame rFrame=new RegisterFrame();
        rFrame.setSize(200,300);
        rFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rFrame.setVisible(true);
    }

       @Override
       public void actionPerformed(ActionEvent e) {
        //This is event handler
           /*If user clicks on button register -print his name*/
           if(e.getSource()==btnRegister){
               String msg="hello "+txtName.getText();
               JOptionPane.showMessageDialog(null,msg);
               System.out.println(msg);
           }
           if(e.getSource()==btnRegister){
               String msg="ID number: "+txtId.getText();
               JOptionPane.showMessageDialog(null,msg);
               System.out.println(msg);
           }
       }
   }
