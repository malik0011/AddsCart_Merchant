package com.example.addscartmerchant;

public class Order {
    String UserName;
    String userPhoneNo;
    String userLatLon;
    String userItems;
    String OrderId;
    String userLat,userLon;

    public Order(){}

    public Order(String userName, String userPhoneNo, String userLatLon, String userItems, String orderId, String userLat, String userLon) {
        UserName = userName;
        this.userPhoneNo = userPhoneNo;
        this.userLatLon = userLatLon;
        this.userItems = userItems;
        OrderId = orderId;
        this.userLat = userLat;
        this.userLon = userLon;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserPhoneNo() {
        return userPhoneNo;
    }

    public void setUserPhoneNo(String userPhoneNo) {
        this.userPhoneNo = userPhoneNo;
    }

    public String getUserLatLon() {
        return userLatLon;
    }

    public void setUserLatLon(String userLatLon) {
        this.userLatLon = userLatLon;
    }

    public String getUserItems() {
        return userItems;
    }

    public void setUserItems(String userItems) {
        this.userItems = userItems;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getUserLat() {
        return userLat;
    }

    public void setUserLat(String userLat) {
        this.userLat = userLat;
    }

    public String getUserLon() {
        return userLon;
    }

    public void setUserLon(String userLon) {
        this.userLon = userLon;
    }
    //    public void setUserName(String userName) {
//        UserName = userName;
//    }
//
//    public void setUserPhoneNo(String userPhoneNo) {
//        this.userPhoneNo = userPhoneNo;
//    }
//
//    public void setUserLatLon(String userLatLon) {
//        this.userLatLon = userLatLon;
//    }
//
//    public void setUserItems(String userItems) {
//        this.userItems = userItems;
//    }
//
//    public void setOrderId(String orderId) {
//        OrderId = orderId;
//    }
//
//    public Order(String userName, String userPhoneNo, String userLatLon, String userItems, String orderId) {
//        UserName = userName;
//        this.userPhoneNo = userPhoneNo;
//        this.userLatLon = userLatLon;
//        this.userItems = userItems;
//        OrderId = orderId;
//    }
//
//    public String getUserName() {
//        return UserName;
//    }
//
//    public String getUserPhoneNo() {
//        return userPhoneNo;
//    }
//
//    public String getUserLatLon() {
//        return userLatLon;
//    }
//
//    public String getUserItems() {
//        return userItems;
//    }
//
//    public String getOrderId() {
//        return OrderId;
//    }
}
