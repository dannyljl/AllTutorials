package ORM.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;

@Entity
public class TokenEntity implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name="userId")
    private UserEntity user;
    private String token;
    private Date expirationDate;

    public TokenEntity() {}

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public TokenEntity(UserEntity user, String token, Date expirationDate) {
        this.user = user;
        this.token = token;
        this.expirationDate = expirationDate;
    }
}
