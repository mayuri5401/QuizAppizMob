package com.example.quizmob;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class WithdrawRequest {
    private  String userid;
    private  String emailadress;
    private  String requstedby;
    public  WithdrawRequest(){

    }

    public WithdrawRequest(String userid, String emailadress, String requstedby) {
        this.userid = userid;
        this.emailadress = emailadress;
        this.requstedby = requstedby;

    }
    public  WithdrawRequest(Date createedAt){
        this.createedAt=createedAt;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getEmailadress() {
        return emailadress;
    }

    public void setEmailadress(String emailadress) {
        this.emailadress = emailadress;
    }

    public String getRequstedby() {
        return requstedby;
    }

    public void setRequstedby(String requstedby) {
        this.requstedby = requstedby;
    }

    public Date getCreateedAt() {
        return createedAt;
    }

    public void setCreateedAt(Date createedAt) {
        this.createedAt = createedAt;
    }

    @ServerTimestamp
    private Date createedAt;


}
