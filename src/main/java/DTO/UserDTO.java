package DTO;

import ORM.Entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    public UserDTO(UserEntity userEntity) {
        this.userId = userEntity.getUserId();
        this.name = userEntity.getName();
        this.username = userEntity.getUsername();
        this.location = userEntity.getLocation();
        this.web = userEntity.getWeb();
        this.bio = userEntity.getBio();
        this.image = userEntity.getImage();
        for (UserEntity user : userEntity.getFollowers()){
            followersId.add(user.getUserId());
        }
        for (UserEntity user : userEntity.getFollowing()){
            followingId.add(user.getUserId());
        }
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Integer> getFollowersId() {
        return followersId;
    }

    public void setFollowersId(List<Integer> followersId) {
        this.followersId = followersId;
    }

    public List<Integer> getFollowingId() {
        return followingId;
    }

    public void setFollowingId(List<Integer> followingId) {
        this.followingId = followingId;
    }

    private int userId;
    private String name;
    private String username;
    private String location;
    private String web;
    private String bio;
    private String image;
    private List<Integer> followersId = new ArrayList<>();
    private List<Integer> followingId = new ArrayList<>();
}
