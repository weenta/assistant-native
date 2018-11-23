package com.weenta.assistant.bean;

/**
 * 新闻列表item
 */
public class NewsListItem {
    private String title;
    private String publishTime;
    private String newsId;
    private String newsImg;
    private String source;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getNewsImg() {
        return newsImg;
    }

    public void setNewsImg(String newsImg) {
        this.newsImg = newsImg;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "NewsListItem{" +
                "title='" + title + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", newsId='" + newsId + '\'' +
                ", newsImg='" + newsImg + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
