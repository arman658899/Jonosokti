package com.brogrammers.jonosokti.bean;

import java.io.Serializable;

public class Order implements Serializable {
    private String userUid,userName,orderAddress,instructions;
    private String selectedDate,selectedTime;
    private String categoryName, orderDetails;
    private String providerName,providerCompany,providerPhoto,providerUid;
    private String orderId,documentId;
    private String placeTime,acceptedTime,workingTime,completedTime;
    private boolean placed,accepted,working,completed,cancelledByMe,cancelledByProvider;
    private long createdTime;

    public Order() {
    }

    public Order(String userUid, String userName, String orderAddress, String instructions, String selectedDate, String selectedTime, String categoryName, String orderDetails, String providerName, String providerCompany, String providerPhoto, String providerUid, String orderId, String documentId, String placeTime, String acceptedTime, String workingTime, String completedTime, boolean placed, boolean accepted, boolean working, boolean completed, boolean cancelledByMe, boolean cancelledByProvider, long createdTime) {
        this.userUid = userUid;
        this.userName = userName;
        this.orderAddress = orderAddress;
        this.instructions = instructions;
        this.selectedDate = selectedDate;
        this.selectedTime = selectedTime;
        this.categoryName = categoryName;
        this.orderDetails = orderDetails;
        this.providerName = providerName;
        this.providerCompany = providerCompany;
        this.providerPhoto = providerPhoto;
        this.providerUid = providerUid;
        this.orderId = orderId;
        this.documentId = documentId;
        this.placeTime = placeTime;
        this.acceptedTime = acceptedTime;
        this.workingTime = workingTime;
        this.completedTime = completedTime;
        this.placed = placed;
        this.accepted = accepted;
        this.working = working;
        this.completed = completed;
        this.cancelledByMe = cancelledByMe;
        this.cancelledByProvider = cancelledByProvider;
        this.createdTime = createdTime;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
    }

    public String getSelectedTime() {
        return selectedTime;
    }

    public void setSelectedTime(String selectedTime) {
        this.selectedTime = selectedTime;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderPhoto() {
        return providerPhoto;
    }

    public void setProviderPhoto(String providerPhoto) {
        this.providerPhoto = providerPhoto;
    }

    public String getProviderCompany() {
        return providerCompany;
    }

    public void setProviderCompany(String providerCompany) {
        this.providerCompany = providerCompany;
    }

    public String getProviderUid() {
        return providerUid;
    }

    public void setProviderUid(String providerUid) {
        this.providerUid = providerUid;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getPlaceTime() {
        return placeTime;
    }

    public void setPlaceTime(String placeTime) {
        this.placeTime = placeTime;
    }

    public String getAcceptedTime() {
        return acceptedTime;
    }

    public void setAcceptedTime(String acceptedTime) {
        this.acceptedTime = acceptedTime;
    }

    public String getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(String workingTime) {
        this.workingTime = workingTime;
    }

    public String getCompletedTime() {
        return completedTime;
    }

    public void setCompletedTime(String completedTime) {
        this.completedTime = completedTime;
    }

    public boolean isPlaced() {
        return placed;
    }

    public void setPlaced(boolean placed) {
        this.placed = placed;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public boolean isWorking() {
        return working;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isCancelledByMe() {
        return cancelledByMe;
    }

    public void setCancelledByMe(boolean cancelledByMe) {
        this.cancelledByMe = cancelledByMe;
    }

    public boolean isCancelledByProvider() {
        return cancelledByProvider;
    }

    public void setCancelledByProvider(boolean cancelledByProvider) {
        this.cancelledByProvider = cancelledByProvider;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }
}
