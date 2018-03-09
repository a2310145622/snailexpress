package cn.raoxianhua.snailexpress;

import com.google.gson.annotations.SerializedName;

/**
 * Created by raoxianhua on 2018/1/9.
 */

public class Express {

    @SerializedName("EXPRESSTIME")
    private String expressTime;
    @SerializedName("EXPRESSDETAILS")
    private String expressDetails;

    public Express(String expressTime,String expressDetails){
        this.expressTime = expressTime;
        this.expressDetails = expressDetails;
    }

    public void setExpressTime(String expressTime){expressTime=this.expressTime;}
    public void setExpressDetails(String password){expressDetails=this.expressDetails;}
    public String getExpressTime(){return expressTime;}
    public String getExpressDetails(){return expressDetails;}

}
