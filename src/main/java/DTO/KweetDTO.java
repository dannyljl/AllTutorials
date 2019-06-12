package DTO;

import ORM.Entity.KweetEntity;

import java.io.Serializable;
import java.sql.Date;

public class KweetDTO implements Serializable {

    public KweetDTO(KweetEntity kweet) {
        this.kweetId = kweet.getKweetId();
        this.userId = kweet.getUser().getUserId();
        this.creatorName = kweet.getUser().getName();
        this.content = kweet.getContent();
        this.date = kweet.getDate();
    }

    public int getKweetId() {
        return kweetId;
    }

    public void setKweetId(int kweetId) {
        this.kweetId = kweetId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
    private int kweetId;
    private int userId;
    private String creatorName;
    private String content;
    private Date date;
}
