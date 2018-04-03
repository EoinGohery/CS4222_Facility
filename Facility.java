public class Facility
{
    public int facilityID;
    public String facilityName;
    public double pricePerHour;
    public String decommissionedUntilDate;

    public Track(int facilityID, String facilityName, double pricePerHour, String decommissionedUntilDate)
    {
        this.facilityName = facilityName;
        this.decommissionedUntilDate = decommissionedUntilDate;
        this.facilityID = facilityID;
        this.decommissionedUntilDate = decommissionedUntilDate;
    }

    public String getFacilityName()
    {
        return  facilityName;
    }
    public void setFacilityName(String facilityName)
    {
        this.facilityName = facilityName;
    }
    public String getDecommissionedUntilDate()
    {
        return  decommissionedUntilDate;
    }
    public void setDecommissionedUntilDate(String artist)
    {
        this.decommissionedUntilDate = decommissionedUntilDate;
    }
    public int getFacilityID()
    {
        return  facilityID;
    }
    public void setFacilityID(int year)
    {
        this.facilityID = facilityID;
    }
    public double getPricePerHour()
    {
        return  pricePerHour;
    }
    public void setPricePerHour(int pricePerHour)
    {
        this.pricePerHour = pricePerHour;
    }
}
