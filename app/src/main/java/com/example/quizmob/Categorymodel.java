package com.example.quizmob;

public class Categorymodel {
    private  String catid,catname,catimg;
    public Categorymodel(){

    }

    public Categorymodel(String catid, String catname, String catimg) {
        this.catid = catid;
        this.catname = catname;
        this.catimg = catimg;
    }

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public String getCatimg() {
        return catimg;
    }

    public void setCatimg(String catimg) {
        this.catimg = catimg;
    }
}
