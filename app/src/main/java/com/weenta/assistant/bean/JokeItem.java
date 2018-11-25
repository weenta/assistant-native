package com.weenta.assistant.bean;

/**
 * 笑话
 */
public class JokeItem {
    private String content;
    private String hashId;
    private String updatetime;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return "JokeItem{" +
                "content='" + content + '\'' +
                ", hashId='" + hashId + '\'' +
                ", updatetime='" + updatetime + '\'' +
                '}';
    }
}
