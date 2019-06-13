package ORM.Entity;

public class FollowerEntity {
    private int userId;
    private String name;

    public FollowerEntity(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public FollowerEntity(){

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
