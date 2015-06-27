package com.kido.board.model.serverResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kido.board.model.Ad;

import java.util.List;

public class ServerAds {
    @SerializedName("message")
    @Expose(serialize = false, deserialize = true)
    private String message;

    @SerializedName("success")
    @Expose(serialize = false, deserialize = true)
    private int statusResponse;

    @SerializedName("value")
    @Expose(serialize = true, deserialize = true)
    private List<Ad> value;

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

    public List<Ad> getValue() {
        return value;
    }

    public void setValue(List<Ad> value) {
        this.value = value;
    }
}
