package org.example.bean;

import java.io.Serializable;
import java.util.Vector;

public class Model implements Serializable {
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getHollis(){
        return "hollischuang";
    }
}
