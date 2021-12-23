package com.example.e_krushi_sellerapp;

public class Data1 {
    String Name; String Phone_no; String Pincode; String Address; String Shop_Name; String Licence_no;

    public Data1() {
    }



    public Data1(String Name, String Phone_no, String Pincode, String Address, String Shop_Name, String Licence_no) {
        this.Name=Name;
        this.Phone_no=Phone_no;
        this.Pincode=Pincode;
        this.Address=Address;
        this.Shop_Name=Shop_Name;
        this.Licence_no=Licence_no;
    }

    public String getName() {
        return Name;
    }

    public String getPhone_no() {
        return Phone_no;
    }

    public String getPincode() {
        return Pincode;
    }

    public String getAddress() {
        return Address;
    }

    public String getShop_Name() {
        return Shop_Name;
    }

    public String getLicence_no() {
        return Licence_no;
    }
}

