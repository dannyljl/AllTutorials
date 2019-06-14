package ORM.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import AccountTypes.AccountType;

@Entity
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String name;

    @Column(unique = true)
    private String username;
    private String password;
    private String location;
    private String web;
    private String bio;
    private String image;
    private AccountType accountType = AccountType.USER;

    public UserEntity(){

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int id) {
        this.userId = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Collection<UserEntity> getFollowing() {
        return following;
    }

    public void setFollowing(Collection<UserEntity> following) {
        this.following = following;
    }

    public void setFollowing(List<UserEntity> following) {
        this.following = following;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="followers",
            joinColumns={@JoinColumn(name="followerId",referencedColumnName = "userId")},
            inverseJoinColumns={@JoinColumn(name="followedId",referencedColumnName = "userId")})
    private Collection<UserEntity> following = new LinkedHashSet<>();

    public void addFollowing(UserEntity userEntity){
        following.add(userEntity);
    }

    @ManyToMany(mappedBy = "following", fetch = FetchType.EAGER)
    private List<UserEntity> followingMe = new ArrayList<>();

    public List<UserEntity> getFollowingMe() {
        return followingMe;
    }

    public void setFollowingMe(List<UserEntity> followingMe) {
        this.followingMe = followingMe;
    }
}
