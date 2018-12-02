package com.weenta.assistant.bean;

import java.util.List;

/**
 * 美女福利list
 */
public class GirlList {
    private boolean error;
    private List<GirlItem> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<GirlItem> getResults() {
        return results;
    }

    public void setResults(List<GirlItem> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "GirlList{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }
}
