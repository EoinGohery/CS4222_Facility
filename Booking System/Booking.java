public class Booking
{
    public int bookingID;
    public int facilityID;
    public int userID;
    public int slot;
    public String date;
    public String paymentStatus;

    public Booking(int bookingID, int facilityID, int userID, int slot, String date, String paymentStatus)
    {
        this.bookingID = bookingID;
        this.facilityID = facilityID;
        this.userID = userID;
        this.slot = slot;
        this.date = date;
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentStatus()
    {
        return  paymentStatus;
    }
    public void setPaymentStatus(String paymentStatus)
    {
        this.paymentStatus = paymentStatus;
    }
    public String getDate()
    {
        return  date;
    }
    public void setDate(String date)
    {
        this.date = date;
    }
    public int getFacilityID()
    {
        return  facilityID;
    }
    public void setFacilityID(int facilityID)
    {
        this.facilityID = facilityID;
    }
    public int getSlot()
    {
        return  slot;
    }
    public void setSlot(int slot)
    {
        this.slot = slot;
    }
    public int getBookingID()
    {
        return  bookingID;
    }
    public void setBookingID(int bookingID)
    {
        this.bookingID = bookingID;
    }
    public int getUserID()
    {
        return  userID;
    }
    public void setUserID(int userID)
    {
        this.userID = userID;
    }
}