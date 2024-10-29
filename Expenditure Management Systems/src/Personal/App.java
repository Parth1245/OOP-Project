package Personal;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
// import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.Scanner;
public class App {
    Repository repo = Repository.getRepository();
    ReportService reportService = new ReportService();

    Scanner scan = new Scanner(System.in);
    private int choice;
    
    public App(){
        loadData();
    }
    // public float repo.Balance = 0.0f;
    float Balance = 0.0f;
    @SuppressWarnings("unchecked")
    private void loadData() {
        List<Expense> expList = (List<Expense>)deserialize("expenses.ser");
        List<Category> catList = (List<Category>)deserialize("categories.ser");
        Balance = (deserialize("balance.ser") instanceof Float) ? (Float) deserialize("balance.ser") : 0.0f;

        if(expList != null){
            //load to repo
            repo.expList = expList;
        }
        if(catList != null){
            repo.catList = catList;
        }
        if(Balance != 0.0){
            repo.Balance = Balance;
        }
    }

    public void showMenu(){
        if(Balance == 0.0f){
            System.out.print("Enter your bank balance: ");
            repo.Balance = scan.nextFloat();
        }
        //sampleData();
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
                case 7:
                    categorisedExpenseList();
                    pressAnyKeyToContinue();
                    break;
                case 8:
                    addBalance();
                    pressAnyKeyToContinue();
                    break;
                case 9:
                    viewBalance();
                    pressAnyKeyToContinue();
                    break;
                case 10:
                    exit();
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
        System.out.println("8. Add Balance");        
        System.out.println("9. View Balance");        
        System.out.println("10. exit");
        System.out.println("-------------------------------------------------------------------");

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
        repo.Balance = repo.Balance - exp.getAmount();
        System.out.println("Bank Balance: Rs." + repo.Balance);
    }

    public void expenseList(){
        System.out.println("Listing Expenses: ");
        List<Expense> expList = repo.expList;
        for(int i = 0; i < expList.size(); i++){
            Expense exp = expList.get(i);
            String catName = reportService.getCategoryName(exp.getCategoryID());
            String dateString = DateUtil.dateToString(exp.getDate());
            System.out.println((i+1) + ". " + catName + ", Rs." + exp.getAmount() + ", " + exp.getRemark() + ", " + dateString);
        }
    }

    public void monthlyExpenseList(){
        System.out.println("Total Monthly Expense: ");
        Map<String,Float> resultMap = reportService.calculateMonthlyTotal();
        Set<String> keys = resultMap.keySet();
        for(String yearMonth : keys){
            String[] arr = yearMonth.split("-");
            String year = arr[0];
            int monthNo = Integer.parseInt(arr[1]);
            String month = DateUtil.getMonthName(monthNo);
            System.out.println(year + ", " + month + ": Rs." + resultMap.get(yearMonth));
        }
    }

    public void yearlyExpenseList(){
        System.out.println("Yearly Expense: ");
        Map<Integer,Float> resultMap = reportService.calculateYearlyTotal();
        Set<Integer> years = resultMap.keySet();
        float t = 0.0f;

        for(Integer year: years){
            float exp = resultMap.get(year);
            t += exp;
            System.out.println(year + ": Rs." + exp);
        }
        System.out.println("Total Expense till date is: Rs."+ t);
    }

    public void categorisedExpenseList(){
        System.out.println("Categorized Expense List: ");
        Map<String,Float> resultMap = reportService.calculateCategorizedTotal();
        Set<String> categories = resultMap.keySet();
        float total = 0.0f;
        for(String categoryName : categories){
            float catWiseTotal = resultMap.get(categoryName);
            total += catWiseTotal;
            System.out.println(categoryName + ": Rs." + resultMap.get(categoryName));
        }
        System.out.println("Category Wise Total: Rs." + total);
    }

    public void addBalance(){
        System.out.print("Enter amount you want to add: ");
        float income = scan.nextFloat();
        repo.Balance += income;
        System.out.println("Your Bank Balance is: Rs." + repo.Balance);
    }

    public void viewBalance(){
        System.out.println("Your Account Balance is: Rs." + repo.Balance);
    }

    public void exit(){
        saveData();
        System.out.println("Exiting program....");
        System.exit(0);
    }

    private void saveData() {
        serialize("expenses.ser", repo.expList);
        serialize("categories.ser", repo.catList);
        serialize("balance.ser", repo.Balance);
    }

    public void serialize(String file, Object obj){
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
            oos.close();
            fos.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object deserialize(String file){
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            Object obj = ois.readObject();
            return obj;
        } catch (Exception e) {
            System.out.println("No existing data.");
            return null;
        }
        finally {
            try {
                if (ois != null) ois.close();
                if (fis != null) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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
    // String getCategoryName(Long cId) {
    //     for (Category c : repo.catList) {
    //         if (c.getCategoryID() == cId) { 
    //             return c.getName();
    //         }
    //     }
    //     return null; 
    // }
    
    //this is to demonstrate sample data, when complete no need for below function as user has to enter data.
    public void sampleData(){
        Category catParty = new Category("Party");
        delay();
        Category catRent = new Category("Rent");
        delay();
        Category catFood = new Category("Food");

        repo.catList.add(catParty);
        repo.catList.add(catRent);
        repo.catList.add(catFood);

        //Jan 2020
        Expense e1 = new Expense(catParty.getCategoryID(), 1000.0f, DateUtil.stringToDate("01/01/2020"), "birthday");
        repo.Balance = repo.Balance - e1.getAmount();
        delay();

        Expense e2 = new Expense(catParty.getCategoryID(), 2000.0f, DateUtil.stringToDate("02/01/2020"), "anniversary");
        repo.Balance = repo.Balance - e2.getAmount();
        delay();

        //Feb 2020
        Expense e3 = new Expense(catParty.getCategoryID(), 100.0f, DateUtil.stringToDate("01/02/2020"), "birthday");
        repo.Balance = repo.Balance - e3.getAmount();
        delay();

        Expense e4 = new Expense(catFood.getCategoryID(), 500.0f, DateUtil.stringToDate("02/02/2020"), "vegetables");
        repo.Balance = repo.Balance - e4.getAmount();
        delay();

        //Dec 2020
        Expense e5 = new Expense(catRent.getCategoryID(), 8000.0f, DateUtil.stringToDate("01/12/2020"), "house");
        repo.Balance = repo.Balance - e5.getAmount();
        delay();

        Expense e6 = new Expense(catFood.getCategoryID(), 2000.0f, DateUtil.stringToDate("02/12/2020"), "restaurant");
        repo.Balance = repo.Balance - e6.getAmount();
        delay();

        //Jan 2021
        Expense e7 = new Expense(catFood.getCategoryID(), 200.0f, DateUtil.stringToDate("01/01/2021"), "fruits");
        repo.Balance = repo.Balance - e7.getAmount();
        delay();

        //Feb 2021
        Expense e8 = new Expense(catRent.getCategoryID(), 1000.0f, DateUtil.stringToDate("01/02/2021"), "shop");
        repo.Balance = repo.Balance - e8.getAmount();
        delay();

        repo.expList.add(e1);
        repo.expList.add(e2);
        repo.expList.add(e3);
        repo.expList.add(e4);
        repo.expList.add(e5);
        repo.expList.add(e6);
        repo.expList.add(e7);
        repo.expList.add(e8);
    }

    private void delay(){
        try{
            Thread.sleep(10);
        }
        catch(InterruptedException ex){
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        
    }
}