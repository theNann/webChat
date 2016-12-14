package org.pyn.message;

/**
 * Created by pyn on 2016/12/14.
 */
public abstract class Message {
    public String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
