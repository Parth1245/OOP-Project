package Personal;

public class Category {
    private long categoryID = System.currentTimeMillis();
    private String name;

    public Category(String name){
        this.name = name;
    }

    public Category(Long categoryID, String name){
        this.categoryID = categoryID;
        this.name = name;
    }

    public long getCategoryID() {
        return categoryID;
    }

    public String getName() {
        return name;
    }

    
}