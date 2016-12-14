package org.pyn.message;

import org.json.JSONObject;

/**
 * Created by pyn on 2016/12/14.
 */
public class OnlineNumMessage extends Message{
    public int onlineNum;

    public int getOnlineNum() {
        return onlineNum;
    }

    public void setOnlineNum(int onlineNum) {
        this.onlineNum = onlineNum;
    }

    public String encode() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "onlineNumMessage");
        jsonObject.put("onlineNum", onlineNum);
        return jsonObject.toString();
    }
}
