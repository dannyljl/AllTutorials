package DTO;

public class FollowerDTO {
    private int userId;
    private String name;

    public FollowerDTO(int userId, String name) {
        this.userId = userId;
        this.name = name;
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
