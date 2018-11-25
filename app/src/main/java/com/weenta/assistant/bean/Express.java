package com.weenta.assistant.bean;

import java.util.List;

/**
 * 物流查询
 */
public class Express {
    private int ERRORCODE;
    private ExpressResult RESULT;

    public int getERRORCODE() {
        return ERRORCODE;
    }

    public void setERRORCODE(int ERRORCODE) {
        this.ERRORCODE = ERRORCODE;
    }

    public ExpressResult getRESULT() {
        return RESULT;
    }

    public void setRESULT(ExpressResult RESULT) {
        this.RESULT = RESULT;
    }

    @Override
    public String toString() {
        return "Express{" +
                "ERRORCODE=" + ERRORCODE +
                ", RESULT=" + RESULT +
                '}';
    }

    /**
     * ExpressResult
     */
    public class ExpressResult {
        // 快递公司
        private String com;
        // 轨迹
        private List<ExpressItem> context;

        public String getCom() {
            return com;
        }

        public void setCom(String com) {
            this.com = com;
        }

        public List<ExpressItem> getContext() {
            return context;
        }

        public void setContext(List<ExpressItem> context) {
            this.context = context;
        }

        @Override
        public String toString() {
            return "ExpressResult{" +
                    "com='" + com + '\'' +
                    ", context=" + context +
                    '}';
        }
    }


}
