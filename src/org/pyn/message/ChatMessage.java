package org.pyn.message;

/**
 * Created by pyn on 2016/12/14.
 */
public class ChatMessage extends Message{
    public String account;
    public String content;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
