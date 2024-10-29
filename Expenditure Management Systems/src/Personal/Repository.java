package Personal;
import java.util.ArrayList;
import java.util.List;
// the class is used as database/repository and its a singleton(only one object will be provided)
public class Repository {
    public List<Expense> expList = new ArrayList<>();
    public List<Category> catList = new ArrayList<>();
    public float Balance = 0.0f;
    private static Repository repository;
    private Repository(){
    }

    public static Repository getRepository(){ // will give you repo
        if (repository == null){
            repository = new Repository();
        }
        return repository;
    }

    // Method to search expenses by category name
    public List<Expense> getExpensesByCategoryName(String categoryName) {
        List<Expense> result = new ArrayList<>();
        Long categoryId = null;

        // Find the categoryID for the given category name
        for (Category category : catList) {
            if (category.getName().equalsIgnoreCase(categoryName)) {
                categoryId = category.getCategoryID();
                break;
            }
        }

        // If category found, search for expenses with matching categoryID
        if (categoryId != null) {
            for (Expense expense : expList) {
                if (expense.getCategoryID().equals(categoryId)) {
                    result.add(expense);
                }
            }
        }

        return result; // Returns expenses or empty list if no match found
    }

    // Method to search expenses by remark keyword
    public List<Expense> searchExpensesByRemark(String keyword) {
        List<Expense> result = new ArrayList<>();
        for (Expense expense : expList) {
            if (expense.getRemark() != null && expense.getRemark().contains(keyword)) {
                result.add(expense);
            }
        }
        return result; // Returns expenses with matching remark
    }
}