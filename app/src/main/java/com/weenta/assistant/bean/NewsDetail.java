package com.weenta.assistant.bean;


import java.util.List;


/**
 * 新闻详情
 */
public class NewsDetail {
    private String ERRORCODE;
    private ApiResults RESULT;

    public String getERRORCODE() {
        return ERRORCODE;
    }

    public void setERRORCODE(String ERRORCODE) {
        this.ERRORCODE = ERRORCODE;
    }

    public ApiResults getRESULT() {
        return RESULT;
    }

    public void setRESULT(ApiResults RESULT) {
        this.RESULT = RESULT;
    }

    @Override
    public String toString() {
        return "NewsDetail{" +
                "ERRORCODE='" + ERRORCODE + '\'' +
                ", RESULT=" + RESULT +
                '}';
    }

    /**
     * ApiResults
     */
    public class ApiResults {
        private List<String> imgUrl;
        private String content;

        public List<String> getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(List<String> imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public String toString() {
            return "Results{" +
                    "imgUrl=" + imgUrl +
                    ", content='" + content + '\'' +
                    '}';
        }
    }
}
