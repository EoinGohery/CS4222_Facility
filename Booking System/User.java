public class User
{
    public int userID;
    public String email;
    public int userType;
    public String password;

    public User(int userID, String email, String password, int userType)
    {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public int getUserID()
    {
        return  userID;
    }
    public void setUserID(int userID)
    {
        this.userID = userID;
    }
    public String getEmail()
    {
        return  email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getPassword()
    {
        return  password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public int getUserType()
    {
        return  userType;
    }
    public void setUserType(int userType)
    {
        this.userType = userType;
    }
}