package com.kido.board.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class Ad {

    @SerializedName("idAd")
    @Expose(serialize = true, deserialize = true)
    private long adNumer;

    @SerializedName("titleAd")
    @Expose(serialize = true, deserialize = true)
    private String adName;

    @SerializedName("descriptionAd")
    @Expose(serialize = true, deserialize = true)
    private String adDesc;

    @SerializedName("statusAd")
    @Expose(serialize = true, deserialize = true)
    private int adState;

    @SerializedName("dateAd")
    @Expose(serialize = true, deserialize = true)
    private Timestamp adDate;

    @SerializedName("imageAd")
    @Expose(serialize = true, deserialize = true)
    private String adUrl;

    @SerializedName("priceAd")
    @Expose(serialize = true, deserialize = true)
    private double adPrice;

    public double getAdPrice() {
        return adPrice;
    }

    public void setAdPrice(double adPrice) {
        this.adPrice = adPrice;
    }

    public long getAdNumer() {
        return adNumer;
    }

    public void setAdNumer(long adNumer) {
        this.adNumer = adNumer;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public String getAdDesc() {
        return adDesc;
    }

    public void setAdDesc(String adDesc) {
        this.adDesc = adDesc;
    }

    public int getAdState() {
        return adState;
    }

    public void setAdState(int adState) {
        this.adState = adState;
    }

    public Timestamp getAdDate() {
        return adDate;
    }

    public void setAdDate(Timestamp adDate) {
        this.adDate = adDate;
    }

    public String getAdUrl() {
        return adUrl;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }

}
