package com.example.addscartmerchant;

public class Order {
    String UserName;
    String userPhoneNo;
    String userLatLon;
    String userItems;
    String OrderId;
    String userLat,userLon;
    String address,mode;
    String Userdate;

    public Order(String userName, String userPhoneNo, String userLatLon, String userItems, String orderId, String userLat, String userLon, String address, String mode, String userdate) {
        UserName = userName;
        this.userPhoneNo = userPhoneNo;
        this.userLatLon = userLatLon;
        this.userItems = userItems;
        OrderId = orderId;
        this.userLat = userLat;
        this.userLon = userLon;
        this.address = address;
        this.mode = mode;
        Userdate = userdate;
    }

    public Order(){}

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getUserdate() {
        return Userdate;
    }

    public void setUserdate(String userdate) {
        Userdate = userdate;
    }

    //    public Order(String userName, String userPhoneNo, String userLatLon, String userItems, String orderId, String userLat, String userLon) {
//        UserName = userName;
//        this.userPhoneNo = userPhoneNo;
//        this.userLatLon = userLatLon;
//        this.userItems = userItems;
//        OrderId = orderId;
//        this.userLat = userLat;
//        this.userLon = userLon;
//    }

//    public String getUserName() {
//        return UserName;
//    }
//
//    public void setUserName(String userName) {
//        UserName = userName;
//    }
//
//    public String getUserPhoneNo() {
//        return userPhoneNo;
//    }
//
//    public void setUserPhoneNo(String userPhoneNo) {
//        this.userPhoneNo = userPhoneNo;
//    }
//
//    public String getUserLatLon() {
//        return userLatLon;
//    }
//
//    public void setUserLatLon(String userLatLon) {
//        this.userLatLon = userLatLon;
//    }
//
//    public String getUserItems() {
//        return userItems;
//    }
//
//    public void setUserItems(String userItems) {
//        this.userItems = userItems;
//    }
//
//    public String getOrderId() {
//        return OrderId;
//    }
//
//    public void setOrderId(String orderId) {
//        OrderId = orderId;
//    }
//
//    public String getUserLat() {
//        return userLat;
//    }
//
//    public void setUserLat(String userLat) {
//        this.userLat = userLat;
//    }
//
//    public String getUserLon() {
//        return userLon;
//    }
//
//    public void setUserLon(String userLon) {
//        this.userLon = userLon;
//    }
}
