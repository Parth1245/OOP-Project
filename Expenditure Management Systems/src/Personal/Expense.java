package Personal;
import java.time.LocalDate;
public class Expense {
    private Long expenseID = System.currentTimeMillis();
    private Long categoryID;
    private Float amount;
    private LocalDate Date = LocalDate.now();
    private String remark;
    
    public Expense(){
        
    }

    public Expense(Long categoryID, Float amount, LocalDate date, String remark) {
        // this.expenseID = expenseID;
        this.categoryID = categoryID;
        this.amount = amount;
        Date = date;
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

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    

    

}
