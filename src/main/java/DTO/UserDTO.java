package DTO;

import ORM.Entity.TokenEntity;
import ORM.Entity.UserEntity;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public class UserDTO implements Principal {

    private int userId;
    private String name;
    private String username;
    private String location;
    private String web;
    private String bio;
    private String image;
    private String token;

    private List<FollowerDTO> followers = new ArrayList<>();
    private List<FollowerDTO> followings = new ArrayList<>();

    public UserDTO(UserEntity userEntity) {
        this.userId = userEntity.getUserId();
        this.name = userEntity.getName();
        this.username = userEntity.getUsername();
        this.location = userEntity.getLocation();
        this.web = userEntity.getWeb();
        this.bio = userEntity.getBio();
        this.image = userEntity.getImage();
        for (UserEntity user : userEntity.getFollowers()){
            followers.add(new FollowerDTO(user.getUserId(),user.getName()));
        }
        for (UserEntity user : userEntity.getFollowing()){
            followings.add(new FollowerDTO(user.getUserId(),user.getName()));
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

    public List<FollowerDTO> getFollowers() {
        return followers;
    }

    public void setFollowers(List<FollowerDTO> followers) {
        this.followers = followers;
    }

    public List<FollowerDTO> getFollowings() {
        return followings;
    }

    public void setFollowings(List<FollowerDTO> followings) {
        this.followings = followings;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
