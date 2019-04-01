package ORM.Entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class KweetEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int kweetId;

    public int getKweetId() {
        return kweetId;
    }

    public void setKweetId(int kweetId) {
        this.kweetId = kweetId;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @ManyToOne
    @JoinColumn(name="userId")
    private UserEntity user;
    private String content;
    private Date date;


}
