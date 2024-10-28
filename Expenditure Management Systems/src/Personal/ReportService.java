package Personal;
import java.util.Map;
import java.util.TreeMap;
import java.util.Date;
public class ReportService {
    Repository repo = Repository.getRepository();

    public Map<String,Float> calculateMonthlyTotal(){
        Map<String, Float> m = new TreeMap<>();

        for(Expense e: repo.expList){
            Date expDate = e.getDate();
            String yearMonth = DateUtil.getYearMonth(expDate);
            if(m.containsKey(yearMonth)){
                Float total = m.get(yearMonth);
                total += e.getAmount();
                m.put(yearMonth, total);
            }
            else{
                m.put(yearMonth, e.getAmount());
            }
        }
        return m;
    }
}
