package com.brogrammers.jonosokti.bean;

public class Provider {
    String providerName,providerMobile,companyName,uid,profilePic,nidFront,nidBack,registrationFee;
    String categoryName,categoryId;
    boolean status,companyReg;
    long createdTime;
    public Provider() {
    }

    public Provider(String providerName, String providerMobile, String companyName, String uid, String profilePic, String nidFront, String nidBack, String registrationFee, String categoryName, String categoryId, boolean status, boolean companyReg, long createdTime) {
        this.providerName = providerName;
        this.providerMobile = providerMobile;
        this.companyName = companyName;
        this.uid = uid;
        this.profilePic = profilePic;
        this.nidFront = nidFront;
        this.nidBack = nidBack;
        this.registrationFee = registrationFee;
        this.categoryName = categoryName;
        this.categoryId = categoryId;
        this.status = status;
        this.companyReg = companyReg;
        this.createdTime = createdTime;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderMobile() {
        return providerMobile;
    }

    public void setProviderMobile(String providerMobile) {
        this.providerMobile = providerMobile;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getNidFront() {
        return nidFront;
    }

    public void setNidFront(String nidFront) {
        this.nidFront = nidFront;
    }

    public String getNidBack() {
        return nidBack;
    }

    public void setNidBack(String nidBack) {
        this.nidBack = nidBack;
    }

    public String getRegistrationFee() {
        return registrationFee;
    }

    public void setRegistrationFee(String registrationFee) {
        this.registrationFee = registrationFee;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isCompanyReg() {
        return companyReg;
    }

    public void setCompanyReg(boolean companyReg) {
        this.companyReg = companyReg;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }
}
