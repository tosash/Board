package com.kido.board.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

public class AdDetail {

    @SerializedName("idAd")
    @Expose(serialize = true, deserialize = true)
    private BigInteger adNumer;

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
    private Timestamp name;

    @SerializedName("imagesOfAd")
    @Expose(serialize = true, deserialize = true)
    private List<String> adUrls;


    @SerializedName("priceAd")
    @Expose(serialize = true, deserialize = true)
    private double adPrice;

    public double getAdPrice() {
        return adPrice;
    }

    public void setAdPrice(double adPrice) {
        this.adPrice = adPrice;
    }

    public BigInteger getAdNumer() {
        return adNumer;
    }

    public void setAdNumer(BigInteger adNumer) {
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

    public Timestamp getName() {
        return name;
    }

    public void setName(Timestamp name) {
        this.name = name;
    }

    public List<String> getAdUrls() {
        return adUrls;
    }

    public void setAdUrls(List<String> adUrls) {
        this.adUrls = adUrls;
    }
}
