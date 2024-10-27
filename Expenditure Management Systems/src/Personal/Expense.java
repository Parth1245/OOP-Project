package Personal;
// import java.time.LocalDate;
import java.util.Date;
public class Expense {
    private Long expenseID = System.currentTimeMillis();
    private Long categoryID;
    private Float amount;
    private Date date = new Date();
    private String remark;
    
    public Expense(){
        
    }

    public Expense(Long categoryID, Float amount, Date date, String remark) {
        // this.expenseID = expenseID;
        this.categoryID = categoryID;
        this.amount = amount;
        this.date = date;
        this.remark = remark;
    }

    public Long getExpenseID() {
        return expenseID;
    }

    public void setExpenseID(Long expenseID) {
        this.expenseID = expenseID;
    }

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date2) {
        date = date2;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    

    

}
