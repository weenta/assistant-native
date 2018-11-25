package com.weenta.assistant.bean;

import java.util.List;

/**
 * 笑话列表
 */
public class JokeList {
    private int error_code;
    private String reason;
    private JokeResult result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public JokeResult getResult() {
        return result;
    }

    public void setResult(JokeResult result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "JokeList{" +
                "error_code=" + error_code +
                ", reason='" + reason + '\'' +
                ", result=" + result +
                '}';
    }

    /**
     * JokeResult
     */
    public class JokeResult {
        private List<JokeItem> data;

        public List<JokeItem> getData() {
            return data;
        }

        public void setData(List<JokeItem> data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "JokeResult{" +
                    "data=" + data +
                    '}';
        }
    }

}
