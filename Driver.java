import java.util.*;
import java.io.*;
import javax.swing.*;

public class Driver
{
  private static final String[] mainAdmin = { "Register a New User", "Add Facility", "View Facilities", "View User Statements", "Log Out" };
  private static final String[] subAdmin = { "View Availabilty", "Add Booking", "Decommission Facility", "Remove Facility", "Record Payment" };
  private static final String[] mainUser = { "View Bookings","View Statement" };
  private static final String[] facilities = { };
  private boolean loggedIn = false;
  private boolean Admin = false;

  public static void main(String[] args) throws IOException
  {
    readUser();
    readBooking();
    readFacility();
    while (loggedIn = false)
    {
      Login();
    }
    boolean main = true;
    if (admin)
    {
      while (main) {
        String section = (String) JOptionPane.showInputDialog(null, "Menu","",JOptionPane.QUESTION_MESSAGE, null, mainAdmin, mainAdmin[0]);
        if(section=="Register a New User") {
            createUser();
        }else if(section=="Add Facility") {
            createFacility();
        }else if(section=="View Facilities") {
          boolean sub = true;
          while (sub) {
              String subSection = (String) JOptionPane.showInputDialog(null, "Menu","",JOptionPane.QUESTION_MESSAGE, null, subAdmin, subAdmin[0]);
              if(subSection=="View Availabilty")
              {
                viewAvailabilty();
              } else if(subSection=="Add Booking")
              {
                addBooking();
              } else if(subSection =="Decommission Facility")
              {
                decommissionFacility();
              } else if(subSection =="Remove Facility")
              {
                removeFacility();
              } else if(subSection =="Record Payment")
              {
                recordPayment();
              } else  {
                  sub = false;
              }
          }
        } else if(section=="View User Statements")
        {
            viewStatement();
        } else
          main = false;
     }
   } else
   {
     while (main) {
       String section = (String) JOptionPane.showInputDialog(null, "Menu","",JOptionPane.QUESTION_MESSAGE, null, mainUser, mainUser[0]);
       if(section=="View Bookings") {
           viewBookings();
       }else if(section=="View Statement") {
           viewStatement();
       }else
         main = false;
     }
   }
   JOptionPane.showMessageDialog(null, "You have been logged out");
   System.exit(0);
  }
}
