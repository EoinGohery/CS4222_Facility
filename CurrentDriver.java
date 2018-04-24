import java.util.*;
import java.io.*;
import javax.swing.*;
import java.text.*;

public class CurrentDriver
{
  private static final String[] mainAdmin = { "Register a New User", "Add Facility", "View Facilities", "Record Payment",  "View User Statements", "Log Out" };
  private static final String[] subAdmin = { "View Availabilty", "Add Booking", "Decommission Facility", "Remove Facility"};
  private static final String[] mainUser = { "View Bookings","View Statement" };
  private static final String[] slots = { " 9:00 - 10:00","10:00 - 11:00", "11:00 - 12:00", "12:00 - 13:00", "13:00 - 14:00", "14:00 - 15:00", "15:00 - 16:00", "16:00 - 17:00", "17:00 - 18:00" };
  private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
  public static Date now = new Date();
  private static boolean loggedIn = false;
  private static boolean admin = false;
  public static int currentFacilityNum;
  public static int currentUserNum;
  private static File userInfo = new File ("users.txt");
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
        } else if(section =="Record Payment") {
            recordPayment();
        } else if(section=="View Facilities") {
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
    int i = 0;
    String fileElements[];
    int userID;
    String email;
    int userType;
    String password;
    Scanner read = new Scanner(userInfo);
    users.add(nullUser);
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
      int i = 0;
      bookings.add(nullBooking);
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
        String lineFromFile, date;
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
        try
        {
          Date tempDate = formatter.parse(decommissionedUntilDate);
          if ((tempDate.before(now)) || (tempDate.equals(now)))
          {
            decommissionedUntilDate = null;
          }
        } catch (ParseException e) {}
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
        updateFacilities();
  }

  public static void createUser() throws IOException
  {
    int userID = users.size();
    String email = JOptionPane.showInputDialog(null,"Please enter the email");
	String generatedPass = "";
	int userType = 1;
	for (int i = 0;i < 6;i++)
	{
    int x =( (int) Math.random() * 5 + 1);
	    if ( x == 1)
	    { generatedPass += "a";
	       }
	    if ( x == 2)
	    { generatedPass += "b";
	       }
	       if ( x == 3)
	    { generatedPass += "c";
	       }
	       if ( x == 4)
	    { generatedPass += "d";
	       }
	       if ( x == 5)
	    { generatedPass += "e";
	       }
	   }
    String password = generatedPass;
    JOptionPane.showMessageDialog(null,"your password is " + generatedPass);
        User tempUser = new User(userID,email,password,userType);
        users.add(tempUser);
        PrintWriter pw = new PrintWriter(new FileWriter(userInfo,true));
        pw.println(userID + "," + email + "," + password + "," + userType);
        pw.close();
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

  public static void viewAvailabilty() throws IOException
  {
    String start, end;
    start = (String) JOptionPane.showInputDialog(null,"On what date would you like to start viewing? Format: dd/mm/yyyy","//");
    while (!isDateValid(start))
    {
      start = (String) JOptionPane.showInputDialog(null,"On what date would you like to start viewing? Format: dd/mm/yyyy","//");
    }
    end = (String) JOptionPane.showInputDialog(null,"On what date would you like to finish viewing? Format: dd/mm/yyyy","//");
    while (!isDateValid(end))
    {
      end = (String) JOptionPane.showInputDialog(null,"On what date would you like to finish viewing? Format: dd/mm/yyyy","//");
    }
    try
    {
    Date startDate = formatter.parse(start);
    Date endDate = formatter.parse(end);
    shiftDate(endDate);
    while (startDate.before(endDate))
    {
      System.out.println("       " + formatter.format(startDate));
      for (int slot=0; slot<slots.length; slot++)
      {
          for (int i=0; i<bookings.size(); i++)
          {
            if (bookings.get(i).getDate() != null)
            {
              Date checkDate = formatter.parse(bookings.get(i).getDate());
              if (bookings.get(i).getSlot()==slot+1 && bookings.get(i).getFacilityID()==currentFacilityNum && startDate.equals(checkDate))
              {
                System.out.println(slots[slot] + ": Unavailable");
                break;
              } else if (i==bookings.size()-1)
              {
                System.out.println(slots[slot] + ": Available");
              }
            }
          }
        }
        shiftDate(startDate);
        System.out.println();
      }
      } catch (ParseException e) {}
    }


  public static void shiftDate(Date d)
  {
    Calendar c = Calendar.getInstance();
    c.setTime(d);
    c.add(Calendar.DATE, 1);
    d.setTime(c.getTimeInMillis());
  }

  public static void addBooking() throws IOException
  {
    String chosenDate;
    int slot = 0;
    boolean valid = false;
    chooseUser();
    chosenDate = (String) JOptionPane.showInputDialog(null,"On what date do you wish to make the booking. Format: dd/mm/yyyy","//");
    while (!isDateValid(chosenDate))
    {
      chosenDate = (String) JOptionPane.showInputDialog(null,"On what date do you wish to make the booking. Format: dd/mm/yyyy","//");
    }
    while (!valid)
    {
      slot = (Arrays.asList(slots).indexOf((String) JOptionPane.showInputDialog(null, "Choose a time.","",JOptionPane.QUESTION_MESSAGE, null, slots, slots[0]))) +1;
      for (int i=0; i<bookings.size(); i++)
      {
        if (bookings.get(i).getDate() != null)
        {
          try
          {
            Date existingDate = formatter.parse(bookings.get(i).getDate());
            if (bookings.get(i).getSlot()==slot && bookings.get(i).getFacilityID()==currentFacilityNum && existingDate.equals(chosenDate))
            {
              JOptionPane.showMessageDialog(null,"The Facility is currently booked for this date and time.");
              valid = false;
              break;
            }
            valid = true;
          } catch (ParseException e) {}
        }
      }
    }
    Booking tempBooking = new Booking(bookings.size(), currentFacilityNum, currentUserNum, slot,  chosenDate, "N");
    bookings.add(tempBooking);
    updateBookings();
  }

  public static void decommissionFacility() throws IOException
  {
    String facilityLine, facilityName, decommissionedUntilDate;
    int facilityID;
    double pricePerHour;
    decommissionedUntilDate = (String) JOptionPane.showInputDialog(null,"On what date do you wish the facility to be recommissioned. Format: dd/mm/yyyy","//");
    while (!isDateValid(decommissionedUntilDate))
    {
    decommissionedUntilDate = (String) JOptionPane.showInputDialog(null,"On what date do you wish the facility to be recommissioned. Format: dd/mm/yyyy","//");
    }
    facilities.get(currentFacilityNum).setDecommissionedUntilDate(decommissionedUntilDate);
    PrintWriter  pw = new PrintWriter(new FileWriter(facilityInfo));
    for (int i=0; i<facilities.size(); i++)
    {
      facilityName = facilities.get(i).getFacilityName();
      if ((facilityName != null) && (facilityName.length() > 0))
      {
        facilityName = facilities.get(i).getFacilityName();
        facilityID = facilities.get(i).getFacilityID();
        pricePerHour = facilities.get(i).getPricePerHour();
        decommissionedUntilDate = facilities.get(i).getDecommissionedUntilDate();
        if (decommissionedUntilDate == null)
        {
          pw.println(facilityID + "," + facilityName + "," + pricePerHour + ",");
        } else
        {
          pw.println(facilityID + "," + facilityName + "," + pricePerHour + "," + decommissionedUntilDate);
        }
      }
    }
    pw.close();
  }

  public static void removeFacility() throws IOException
  {

    facilities.get(currentFacilityNum).setFacilityID(0);
    facilities.get(currentFacilityNum).setFacilityName(null);
    facilities.get(currentFacilityNum).setPricePerHour(0.0);
    facilities.get(currentFacilityNum).setDecommissionedUntilDate(null);
    updateFacilities();
  }

  public static void updateFacilities() throws IOException
  {
    String facilityLine;
    String facilityName, decommissionedUntilDate;
    int facilityID;
    double pricePerHour;
    PrintWriter  pw = new PrintWriter(new FileWriter(facilityInfo));
    for (int i=0; i<facilities.size(); i++)
    {
      facilityName = facilities.get(i).getFacilityName();
      if ((facilityName != null) && (facilityName.length() > 0))
      {
        facilityID = facilities.get(i).getFacilityID();
        pricePerHour = facilities.get(i).getPricePerHour();
        decommissionedUntilDate = facilities.get(i).getDecommissionedUntilDate();
        if (decommissionedUntilDate == null)
        {
          pw.println(facilityID + "," + facilityName + "," + pricePerHour + ",");
        } else
        {
          pw.println(facilityID + "," + facilityName + "," + pricePerHour + "," + decommissionedUntilDate);
        }
      }
    }
    pw.close();
  }

  public static void updateBookings() throws IOException
  {
    int bookingID;
    int facilityID;
    int userID;
    int slot;
    String date;
    String paymentStatus;
    PrintWriter  pw = new PrintWriter(new FileWriter(bookingInfo));
    for (int i=0; i<bookings.size(); i++)
    {
      bookingID = bookings.get(i).getBookingID();
      if (bookingID > 0)
      {
        facilityID = bookings.get(i).getFacilityID();
        userID = bookings.get(i).getUserID();
        slot = bookings.get(i).getSlot();
        date = bookings.get(i).getDate();
        paymentStatus = bookings.get(i).getPaymentStatus();
        pw.println(bookingID + "," + facilityID + "," + userID + "," + slot + "," + date + "," + paymentStatus);
      }
    }
    pw.close();
  }

  public static void recordPayment() throws IOException
  {
    int bookingNum;
    int bookingID, facilityID, userID, slot;
    String date, paymentStatus, facilityName;
    for (int i=0; i<bookings.size(); i++)
    {
      bookingID = bookings.get(i).getBookingID();
      facilityID = bookings.get(i).getFacilityID();
      userID = bookings.get(i).getUserID();
      slot = bookings.get(i).getSlot();
      date = bookings.get(i).getDate();
      paymentStatus = bookings.get(i).getPaymentStatus();
      facilityName = facilities.get(facilityID).getFacilityName();
      if (paymentStatus != "Y" && paymentStatus != null)
      {
        System.out.println("Number " + bookingID + ": " + facilityName);
        System.out.println("Date: " + date +  " Time: " + slots[slot-1]);
        System.out.println();
      }
    }
    bookingNum = Integer.parseInt(JOptionPane.showInputDialog(null,"What is the number of the booking that you would like to mark as paid?",""));
    bookings.get(bookingNum).setPaymentStatus("Y");
    updateBookings();
  }

  public static void viewStatement()
  {
    int x,y,z,q;
    double g;
    double toBePaid = 0.0;
    double total = 0.0;
    String k,n,m;
    for (int i = 0;i<bookings.size();i++)
    {
      x = bookings.get(i).getUserID();
      y = bookings.get(i).getFacilityID();
      k = facilities.get(y).getFacilityName();
      g = facilities.get(y).getPricePerHour();
      q = bookings.get(i).getBookingID();
      if (q > 0)
      {
        if (x == currentUserNum)
        {
          n = bookings.get(i).getDate();
          z = bookings.get(i).getSlot();
          m = bookings.get(i).getPaymentStatus();
          System.out.println("Facility name: " + k + " Price : " + g + " Date: " + n + " Time: " + slots[z-1] + " Paid: " + m);
          total += g;
          if (m != "Y" && m != null)
          {
            toBePaid += g;
          }
        }
      }
    }
    System.out.println("        Total: " + total);
    System.out.println("        To Be Paid: " + toBePaid);
  }

  public static void chooseUser()
  {
    String email;
    ArrayList<String> userList = new ArrayList<String>();
    for (int i=0; i<users.size(); i++)
    {
      email = users.get(i).getEmail();
      if (email != null)
      {
        userList.add(email);
      }
    }
    if (userList.size() == 0)
    {
      JOptionPane.showMessageDialog(null, "No users have been registered yet.");
    } else
    {
      Object[] userArray = userList.toArray();
      email = (String) JOptionPane.showInputDialog(null, "Users:","",JOptionPane.QUESTION_MESSAGE, null, userArray, userArray[0]);
      if ((email != null) && (email.length() > 0))
      {
        for (int i=0; i<users.size(); i++)
        {
            if (email.equals(users.get(i).getEmail()))
            {
              currentUserNum = i;
              break;
           }
        }
      } else
      {
        return;
      }
    }
  }

  public static void chooseFacility() throws IOException
  {
      String facilityName, decommissionedUntilDate;
      ArrayList<String> facilityList = new ArrayList<String>();
      for (int i=0; i<facilities.size(); i++)
      {
        facilityName = facilities.get(i).getFacilityName();
        decommissionedUntilDate = facilities.get(i).getDecommissionedUntilDate();
        if ((facilityName != null) && (facilityName.length() > 0) && (decommissionedUntilDate == null))
        {
          facilityList.add(facilityName);
        }
      }
      if (facilityList.size() == 0)
      {
        JOptionPane.showMessageDialog(null, "No facilities have been created yet.");
      } else
      {
        Object[] facilityArray = facilityList.toArray();
        facilityName = (String) JOptionPane.showInputDialog(null, "Menu","",JOptionPane.QUESTION_MESSAGE, null, facilityArray, facilityArray[0]);
        if ((facilityName != null) && (facilityName.length() > 0))
        {
          for (int i=0; i<facilities.size(); i++)
            {
                  if (facilityName.equals(facilities.get(i).getFacilityName()))
                  {
                      currentFacilityNum = i;
                      break;
                  }
            }
       } else
       {
         return;
       }
     }
  }

  public static void viewBookings()
  {
    int x,c;
    String y,z;
    for (int i = 0;i <bookings.size();i++)
    {
      x = bookings.get(i).getFacilityID();
      if (x > 0)
      {
        y = bookings.get(i).getDate();
        z = facilities.get(x).getFacilityName();
        c = bookings.get(i).getSlot();
        System.out.println("Facility name: " + z + "Date: " + y + " Time: " + slots[c-1]);
      }
    }
   }

  public static boolean isDateValid(String date)
  {
    Calendar cal = Calendar.getInstance();
    try
    {
      Date checkDate = formatter.parse(date);
      formatter.setLenient(false);
      formatter.parse(date);
      cal.setTime(checkDate);
      return true;
    }
    catch (ParseException e)
    {
      JOptionPane.showMessageDialog(null, "Invalid date");
      return false;
    }
  }
}
