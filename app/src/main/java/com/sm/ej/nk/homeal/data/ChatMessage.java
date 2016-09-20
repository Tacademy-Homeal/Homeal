package com.sm.ej.nk.homeal.data;

/**
 * Created by Tacademy on 2016-08-29.
 */
public class ChatMessage {
    private String date;
    private User sender;
    private long groupId;
    private String message;

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getSender() {
        return this.sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public long getGroupId() {
        return this.groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
