package com.gui;
import javax.swing.JOptionPane;

/*A simple program that will use an OOPbasics.input-dialog to get the name
of the user and use a message dialogue ro display that name.
 */
public class MainDialog {
    public static void main(String[] args) {
        int sum;
        int a,b ;
        String msg;
        b=Integer.parseInt(JOptionPane.showInputDialog("Enter 1st number"));
        msg="1st number is "+b;
        a=Integer.parseInt(JOptionPane.showInputDialog("Enter 2nd number "));
        msg="2nd number is "+b;
        sum=a+b;
        msg="The sum is "+sum;

        JOptionPane.showMessageDialog(null,msg);

    }
}
