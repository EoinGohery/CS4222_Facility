import java.util.*;
import java.io.*;
import javax.swing.*;

public class Driver
{
  private static final String[] mainAdmin = { "Register a New User", "Add Facility", "View Facilities", "View User Statements", "Log Out" };
  private static final String[] subAdmin = { "View Availabilty", "Add Booking", "Decommission Facility", "Remove Facility", "Record Payment" };
  private static final String[] mainUser = { "View Bookings","View Statement" };
  private static final String[] facilities = { };
  private static boolean loggedIn = false;
  private static boolean admin = false;
  private static File userInfo = new File ("userInfo.txt");
  private static File bookingInfo = new File ("bookings.txt");
  private static File facilityInfo = new File ("facilities.txt");
  public static List<Facility> facilities = new ArrayList<Facility>();
  public static List<User> users = new ArrayList<User>();
  public static List<Booking> bookings = new ArrayList<Booking>();

  public static void main(String[] args) throws IOException
  {
    readUser();
    readBooking();
    readFacility();
    while (!loggedIn)
    {
      login();
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
          chooseFacility();
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
            chooseUser();
            viewStatement();
        } else
          main = false;
     }
   } else
   {
     while (main)
     {
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

  public static void readUser()
  {
    Scanner read;
		String lineFromFile;
    String FileElements[];
    int userID;
    String email;
    int userType;
    String password;
		read = new Scanner(userInfo);
		while(read.hasNext())
		{
			lineFromFile = read.nextLine();
      fileElements = lineFromFile.split(",");
      userID = (Integer.parseInt(fileElements[0]));
      email = (fileElements[1])
      password = (fileElements[2])
      userType = (Integer.parseInt(fileElements[3]));
      User tempUser = new User(userID, email, password, userType);
      users.add(tempUser);
		}
		read.close();
  }

  public static void readBooking()
  {
    Scanner read;
		String lineFromFile;
    String FileElements[];
    int bookingID;
    int facilityID;
    int userID;
    int slot;
    String date;
    String paymentStatus;
		read = new Scanner(userInfo);
		while(read.hasNext())
		{
			lineFromFile = read.nextLine();
      fileElements = lineFromFile.split(",");
      bookingID = (Integer.parseInt(fileElements[0]));
      facilityID = (Integer.parseInt(fileElements[1]));
      userID = (Integer.parseInt(fileElements[2]));
      slot = (Integer.parseInt(fileElements[3]));
      date = (fileElements[4])
      paymentStatus = (fileElements[5])
      Booking tempBooking = new Booking(bookingID, facilityID, userID, slot, date, paymentStatus);
      bookings.add(tempBooking);
		}
		read.close();
  }

  public static void readFacility()
  {
    Scanner read;
		String lineFromFile;
    String FileElements[];
    int facilityID;
    String facilityName;
    double pricePerHour;
    String decommissionedUntilDate;
		read = new Scanner(userInfo);
		while(read.hasNext())
		{
			lineFromFile = read.nextLine();
      fileElements = lineFromFile.split(",");
      facilityID = (Integer.parseInt(fileElements[0]));
      facilityName = (fileElements[1]);
      pricePerHour = (Double.parseDouble(fileElements[2]));
      decommissionedUntilDate = (fileElements[3])
      Booking tempFacility = new Facility(facilityID, facilityName, pricePerHour, decommissionedUntilDate);
      facilities.add(tempFacility);
		}
		read.close();
  }

  public static void createUser()
  {

  }

  public static void login()
  {

  }

  public static void createFacility()
  {

  }

  public static void viewAvailabilty()
  {

  }

  public static void addBooking()
  {

  }

  public static void decommissionFacility()
  {

  }

  public static void removeFacility()
  {

  }

  public static void recordPayment()
  {

  }

  public static void viewStatement()
  {

  }

  public static void chooseUser()
  {

  }

  public static void chooseFacility()
  {

  }

  public static void viewBookings()
  {

  }
}
