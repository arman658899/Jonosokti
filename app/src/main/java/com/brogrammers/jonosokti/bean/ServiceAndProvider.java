package com.brogrammers.jonosokti.bean;

import java.io.Serializable;
import java.util.List;

public class ServiceAndProvider implements Serializable {

    String providerName,companyName,uid,profilePic;
    String categoryName,categoryId,subCatName,subCatId,subCatPhoto,serviceFee;
    String documentId;
    List<String> locations;

    public ServiceAndProvider() {
    }

    public ServiceAndProvider(String providerName, String companyName, String uid, String profilePic, String categoryName, String categoryId, String subCatName, String subCatId, String subCatPhoto, String serviceFee, String documentId, List<String> locations) {
        this.providerName = providerName;
        this.companyName = companyName;
        this.uid = uid;
        this.profilePic = profilePic;
        this.categoryName = categoryName;
        this.categoryId = categoryId;
        this.subCatName = subCatName;
        this.subCatId = subCatId;
        this.subCatPhoto = subCatPhoto;
        this.serviceFee = serviceFee;
        this.documentId = documentId;
        this.locations = locations;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
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

    public String getSubCatName() {
        return subCatName;
    }

    public void setSubCatName(String subCatName) {
        this.subCatName = subCatName;
    }

    public String getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(String subCatId) {
        this.subCatId = subCatId;
    }

    public String getSubCatPhoto() {
        return subCatPhoto;
    }

    public void setSubCatPhoto(String subCatPhoto) {
        this.subCatPhoto = subCatPhoto;
    }

    public String getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }
}
