package com.example.addscartmerchant;

public class Order {
    String UserName;
    String userPhoneNo;
    String userLatLon;
    String userItems;
    String OrderId;

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setUserPhoneNo(String userPhoneNo) {
        this.userPhoneNo = userPhoneNo;
    }

    public void setUserLatLon(String userLatLon) {
        this.userLatLon = userLatLon;
    }

    public void setUserItems(String userItems) {
        this.userItems = userItems;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public Order(String userName, String userPhoneNo, String userLatLon, String userItems, String orderId) {
        UserName = userName;
        this.userPhoneNo = userPhoneNo;
        this.userLatLon = userLatLon;
        this.userItems = userItems;
        OrderId = orderId;
    }

    public String getUserName() {
        return UserName;
    }

    public String getUserPhoneNo() {
        return userPhoneNo;
    }

    public String getUserLatLon() {
        return userLatLon;
    }

    public String getUserItems() {
        return userItems;
    }

    public String getOrderId() {
        return OrderId;
    }
}
