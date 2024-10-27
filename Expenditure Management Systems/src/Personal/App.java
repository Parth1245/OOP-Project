package Personal;
import java.io.IOException;
// import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
public class App {
    Repository repo = Repository.getRepository();

    Scanner scan = new Scanner(System.in);
    private int choice;
    public void showMenu(){
        while(true){
            printMenu();
            switch (choice) {
                case 1:
                    addCategory();
                    pressAnyKeyToContinue();
                    break;
                case 2:
                    categoryList();
                    pressAnyKeyToContinue();
                    break;
                case 3:
                    addExpenseEntry();
                    pressAnyKeyToContinue();
                    break;
                case 4:
                    expenseList();
                    pressAnyKeyToContinue();
                    break;
                case 5:
                    monthlyExpenseList();
                    pressAnyKeyToContinue();
                    break;
                case 6:
                    yearlyExpenseList();
                    pressAnyKeyToContinue();
                    break;
                case 8:
                    System.exit(0);
                default:
                    break;
            }
        }
    }
    
    public void printMenu(){
        System.out.println("---------------------Personal Espense Manager----------------------");
        System.out.println("1. Add Category");
        System.out.println("2. Category List");
        System.out.println("3. Expense Entry");
        System.out.println("4. Expense List");
        System.out.println("5. Monthly Expense List");
        System.out.println("6. Yearly Expense List");
        System.out.println("7. Catagorised Expense List");
        System.out.println("8. exit");
        System.out.println("------------------------------------------------------------------");

        System.out.print("Enter your choice: ");
        choice = scan.nextInt();
    }

    public void addCategory(){
        scan.nextLine();// new line character is read here from the stream, it is not supposed to be used
        System.out.print("Enter Category Name: ");
        String catName = scan.nextLine();
        Category cat = new Category(catName);
        repo.catList.add(cat);
        System.out.println("Category Added");
    }

    public void categoryList(){
        System.out.println("Listing Categories...");
        List<Category> clist = repo.catList;
        for(int i = 0; i < clist.size(); i++){
            Category c = clist.get(i);
            System.out.println(i + 1 + ". " + c.getName() + ", " + c.getCategoryID());
        }
    }

    public void addExpenseEntry(){
        System.out.println("Adding Expense Entry...");
        categoryList();
        System.out.println("Choose Category: ");
        int catChoice = scan.nextInt();
        Category selectedCat = repo.catList.get(catChoice-1);
        System.out.print("Enter amount: ");
        float amount = scan.nextFloat();
        scan.nextLine();
        System.out.print("Enter Remark:");
        String remark = scan.nextLine();
        System.out.println("Enter Date(DD/MM/YYYY): ");
        String dateAsString = scan.nextLine();
        Date date = DateUtil.stringToDate(dateAsString);
        // LocalDate Date = LocalDate.now();

        // add expense detail in expense object
        Expense exp = new Expense();
        exp.setCategoryID(selectedCat.getCategoryID());
        exp.setAmount(amount);
        exp.setRemark(remark);
        exp.setDate(date);

        // store exp obj in database
        repo.expList.add(exp);
        System.out.println("Expense added.");
    }

    public void expenseList(){
        System.out.println("Listing Expenses: ");
        List<Expense> expList = repo.expList;
        for(int i = 0; i < expList.size(); i++){
            Expense exp = expList.get(i);
            String catName = getCategoryName(exp.getCategoryID());
            String dateString = DateUtil.dateToString(exp.getDate());
            System.out.println((i+1) + ". " + catName + ", Rs." + exp.getAmount() + ", " + exp.getRemark() + ", " + dateString);
        }
    }

    public void monthlyExpenseList(){
        System.out.println("Adding Category...");
        //todo
    }

    public void yearlyExpenseList(){
        System.out.println("Adding Category...");
        //todo
    }

    public void categorisedExpenseList(){
        System.out.println("Adding Category...");
        //todo
    }

    public void exit(){
        System.out.println("Exiting program....");
        System.exit(0);
    }

    public void pressAnyKeyToContinue(){
        try{
            System.out.println("Press any Key to Continue...");
            System.in.read();
        } 
        catch(IOException e){
            e.printStackTrace();
        }
    }
    String getCategoryName(Long cId) {
        for (Category c : repo.catList) {
            if (c.getCategoryID() == cId) { 
                return c.getName();
            }
        }
        return null; 
    }
    
    
    public static void main(String[] args) {
        
    }
}