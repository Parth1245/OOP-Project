package Personal;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {
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
    
}
