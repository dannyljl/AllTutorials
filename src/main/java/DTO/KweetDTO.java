package DTO;

import ORM.Entity.KweetEntity;

import java.io.Serializable;

public class KweetDTO implements Serializable {

    private int kweetId;
    private int userId;
    private String creatorName;
    private String content;
    private String date;

    public KweetDTO(KweetEntity kweet) {
        this.kweetId = kweet.getKweetId();
        this.userId = kweet.getUser().getUserId();
        this.creatorName = kweet.getUser().getName();
        this.content = kweet.getContent();
        this.date = kweet.getDate().toString();
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
}
