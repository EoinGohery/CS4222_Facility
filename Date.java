import java.time.*;
import java.time.format.*;
public class Date {
		String dateAsString = "";
		int Year;
		int Month;
		int Day;
			
			
		public Date(int Year, int Month, int Day) {
		this.Year = Year;
		this.Month = Month;
		this.Day = Day;
		
		
		}
		
		
		public void setYear(int Year)
		{
			this.Year = Year;
		}
		public int getYear() 
		{
			return Year;
		}
		
		public void setMonth(int Month)
		{
			this.Month = Month;
		}
		public int getMonth()
		{
			return Month;
		}
		
		public void setDay(int Day)
		{
			this.Day = Day;
		}
		public int getDay()
		{
			return Day;
		}
		
		public String dateAsString() {
			String x = (Day + "-" + Month + "-" + Year);
			return x;
		}
				
}