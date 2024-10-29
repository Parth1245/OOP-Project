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
}