public class Facility
{
    public int facilityID;
    public String facilityName;
    public double pricePerHour;
    public String decommissionedUntilDate;

    public Facility(int facilityID, String facilityName, double pricePerHour, String decommissionedUntilDate)
    {
        this.facilityName = facilityName;
        this.decommissionedUntilDate = decommissionedUntilDate;
        this.facilityID = facilityID;
        this.pricePerHour = pricePerHour;
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
    public void setDecommissionedUntilDate(String decommissionedUntilDate)
    {
        this.decommissionedUntilDate = decommissionedUntilDate;
    }
    public int getFacilityID()
    {
        return  facilityID;
    }
    public void setFacilityID(int facilityID)
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
