package pageTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestDate {

	public static void main(String[] args) throws ParseException {
		String UITime= "10:30AM";
		String removeAM = UITime.substring(0,5);
		//String finaltimeleft = removeAM.split(":")[0];
		//String finaltimeright = removeAM.split(":")[1];
		
		String initialTime = removeAM;
		
		//int toAddHours = Integer.valueOf(finaltimeleft)/
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Date date = sdf.parse(initialTime);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		//cal.add(Calendar.HOUR_OF_DAY, 6);
		cal.add(Calendar.MINUTE, 180);

		int HH = cal.get(Calendar.HOUR);
		int MM = cal.get(Calendar.MINUTE);
		int AMPM = cal.get(Calendar.AM_PM);
		
		String local = AMPM == 0 ? "AM":"PM";
        System.out.println("Time is : " + HH+":"+MM+local);
        
        //Take schedule hours >> multiply the hour by 60 
        // >> Add it to the remaining minutes >> Get total of minutes
        // >> Pass it to cal.get(Calendar.MINUTE)
        // >> Print it in Satrt time
        
        
        
        
        
        
	}

}
