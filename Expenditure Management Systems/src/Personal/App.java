package Personal;
import java.io.IOException;
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
        // System.out.println("Enter Date: ");
        Date date = new Date();

    }

    public void expenseList(){
        System.out.println("Adding Category...");
        //todo
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
    public static void main(String[] args) {
        
    }
}