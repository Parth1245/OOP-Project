package Personal;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {
    public static final String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    public static Date stringToDate(String dateAsString) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            return df.parse(dateAsString);
        } catch (ParseException ex) {
            System.out.println("Invalid date format. Please use dd/MM/yyyy.");
            return null;
        }
    }
    
    public static String dateToString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(date);
    }
    
    public static String getYearMonth(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
        return df.format(date);
    } 
    
    public static Integer getYear(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        return Integer.parseInt(df.format(date));
    } 

    public static String getMonthName(int monthNo){
        return MONTHS[monthNo - 1];
    }
}
