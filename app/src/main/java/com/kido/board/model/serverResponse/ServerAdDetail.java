package com.kido.board.model.serverResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kido.board.model.Ad;

public class ServerAdDetail {
    @SerializedName("message")
    @Expose(serialize = false, deserialize = true)
    private String message;

    @SerializedName("success")
    @Expose(serialize = false, deserialize = true)
    private int statusResponse;

    @SerializedName("value")
    @Expose(serialize = true, deserialize = true)
    private Ad value;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusResponse() {
        return statusResponse;
    }

    public void setStatusResponse(int statusResponse) {
        this.statusResponse = statusResponse;
    }

    public Ad getValue() {
        return value;
    }

    public void setValue(Ad value) {
        this.value = value;
    }
}
