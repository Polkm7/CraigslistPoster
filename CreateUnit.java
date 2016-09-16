package com.mysite.SelTest2;


public class CreateUnit {
    String unitName = "2014 XR650L";
    String unitPrice = "$4499";
    String zipCode = "30721";
    String body = "Stop by Kirk's Cycle in Dalton to get this excellent XR650L. It has low miles, is in great condition, and is ready to ride";
    String closing=" Call us at 706-226-4090 and ask us about financing or visit us online at kirkcycle.com or at 929 N Glenwood Ave.";
    String location = "Dalton GA";
    String unitMake = "Honda";
    String unitCC = "650";
    String condition = "excellent";
    String unitYear = "2014";
    String unitColor = "red";
    String folder = "";



    public CreateUnit(String unitName, String unitPrice, String body, String unitMake, String unitCC, String condition, String unitYear, String unitColor, String folder) {
        this.unitName = unitName;
        this.unitPrice = unitPrice;
        this.body = body + closing;
        this.unitMake = unitMake;
        this.unitCC = unitCC;
        this.condition = condition;
        this.unitYear = unitYear;
        this.unitColor = unitColor;
        this.folder = folder;
    }
}
