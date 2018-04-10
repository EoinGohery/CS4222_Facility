import java.util.*;
import java.io.*;
import javax.swing.*;

public class Driver
{
  private static final String[] mainAdmin = { "Register a New User", "Add Facility", "View Facilities", "View User Statements", "Log Out" };
  private static final String[] subAdmin = { "View Availabilty", "Add Booking", "Decommission Facility", "Remove Facility", "Record Payment" };
  private static final String[] mainUser = { "View Bookings","View Statement" };
  ArrayList<String> facilityList = new ArrayList<String>();
  private static boolean loggedIn = false;
  private static boolean admin = false;
  private static File userInfo = new File ("userInfo.txt");
  private static File bookingInfo = new File ("bookings.txt");
  private static File facilityInfo = new File ("facilities.txt");
  public static List<Facility> facilities = new ArrayList<Facility>();
  public static List<User> users = new ArrayList<User>();
  public static List<Booking> bookings = new ArrayList<Booking>();
  public static Facility nullFacility = new Facility(0, null, 0.0, null);
  public static User nullUser = new User(0, null, null, 0);
  public static Booking nullBooking = new Booking(0, 0, 0, 0, null, null);

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

  public static void readUser()  throws IOException
  {
	  	PrintWriter  pw = new PrintWriter(new FileWriter(userInfo,true));
		String lineFromFile;
		int i = -1;
    String fileElements[];
    int userID;
    String email;
    int userType;
    String password;
		Scanner read = new Scanner(userInfo);
		while(read.hasNext())
		{
			i++;
			lineFromFile = read.nextLine();
			fileElements = lineFromFile.split(",");
			userID = (Integer.parseInt(fileElements[0]));
			email = (fileElements[1]);
			password = (fileElements[2]);
			userType = (Integer.parseInt(fileElements[3]));
			while (i<userID)
			{
				i++;
				users.add(nullUser);
			}
			User tempUser = new User(userID, email, password, userType);
			users.add(tempUser);
		}
		read.close();
  }
  public static void readBooking()  throws IOException
  {
		PrintWriter  pw = new PrintWriter(new FileWriter(bookingInfo,true));
		String lineFromFile;
    String fileElements[];
    int bookingID;
    int facilityID;
    int userID;
    int slot;
    String date;
    String paymentStatus;
	  int i = -1;
		Scanner read = new Scanner(bookingInfo);
		while(read.hasNext())
		{
			i++;
			lineFromFile = read.nextLine();
      fileElements = lineFromFile.split(",");
      bookingID = (Integer.parseInt(fileElements[0]));
      facilityID = (Integer.parseInt(fileElements[1]));
      userID = (Integer.parseInt(fileElements[2]));
      slot = (Integer.parseInt(fileElements[3]));
      date = (fileElements[4]);
      paymentStatus = (fileElements[5]);
	    while (i<bookingID)
			{
				i++;
				bookings.add(nullBooking);
			}
      Booking tempBooking = new Booking(bookingID, facilityID, userID, slot, date, paymentStatus);
      bookings.add(tempBooking);
		}
		read.close();
  }

  public static void readFacility()  throws IOException
  {

	  PrintWriter  pw = new PrintWriter(new FileWriter(facilityInfo,true));
		String lineFromFile;
    String fileElements[];
    int facilityID;
    String facilityName;
    double pricePerHour;
    String decommissionedUntilDate;
  	int i = 0;
		Scanner read = new Scanner(facilityInfo);
    facilities.add(nullFacility);
		while(read.hasNext())
		{
			i++;
			lineFromFile = read.nextLine();
      fileElements = lineFromFile.split(",");
      facilityID = (Integer.parseInt(fileElements[0]));
      facilityName = (fileElements[1]);
      pricePerHour = (Double.parseDouble(fileElements[2]));
      if (fileElements.length == 4)
      {
        decommissionedUntilDate = (fileElements[3]);
      } else
      {
        decommissionedUntilDate = null;
      }
	    while (i<facilityID)
			{
				i++;
				facilities.add(nullFacility);
			}
      Facility tempFacility = new Facility(facilityID, facilityName, pricePerHour, decommissionedUntilDate);
      facilities.add(tempFacility);
		}
		read.close();
  }

  public static void createUser()
  {

  }

  public static void login()
  {
	admin = true;
	loggedIn = true;
  }

  public static void createFacility() throws IOException
  {
	  String facilityName;
    double pricePerHour;
    boolean available = true;
    PrintWriter  pw = new PrintWriter(new FileWriter(facilityInfo,true));
    facilityName = (String) JOptionPane.showInputDialog(null,"Please enter the name of the new facility","");
	  for (int i=0; i<facilities.size(); i++)
	  {
		    if (facilityName.equals(facilities.get(i).getFacilityName()))
		    {
			     JOptionPane.showMessageDialog(null,"That facility name is already in use.");
           available = false;
           break;
		    } else
        {
           available = true;
        }
	  }
    if (available == true)
    {
	    pricePerHour = Double.parseDouble(JOptionPane.showInputDialog(null,"Please enter the price per hour of the new facility","00.00"));
      Facility tempFacility = new Facility(facilities.size(), facilityName, pricePerHour, null);
      facilities.add(tempFacility);
      pw.println(facilities.size()-1 + "," + facilityName + "," + pricePerHour + ",");
    }
    pw.close();
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
