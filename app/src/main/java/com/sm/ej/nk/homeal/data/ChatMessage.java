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
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }
}
