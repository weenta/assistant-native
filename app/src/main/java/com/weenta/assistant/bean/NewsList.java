package com.weenta.assistant.bean;

import java.util.List;

/**
 * 新闻列表
 */
public class NewsList {
    private int ERRORCODE;
    private ResultList RESULT;

    public int getERRORCODE() {
        return ERRORCODE;
    }

    public void setERRORCODE(int ERRORCODE) {
        this.ERRORCODE = ERRORCODE;
    }

    public ResultList getRESULT() {
        return RESULT;
    }

    public void setRESULT(ResultList RESULT) {
        this.RESULT = RESULT;
    }

    @Override
    public String toString() {
        return "NewsList{" +
                "ERRORCODE=" + ERRORCODE +
                ", RESULT=" + RESULT +
                '}';
    }

    /**
     * result
     */
    public class ResultList {
        private List<NewsListItem> newsList;

        public List<NewsListItem> getNewsList() {
            return newsList;
        }

        public void setNewsList(List<NewsListItem> newsList) {
            this.newsList = newsList;
        }

        @Override
        public String toString() {
            return "ResultList{" +
                    "newsList=" + newsList +
                    '}';
        }
    }
}
