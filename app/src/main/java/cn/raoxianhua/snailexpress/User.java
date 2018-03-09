package cn.raoxianhua.snailexpress;

import com.google.gson.annotations.SerializedName;

/**
 * Created by raoxianhua on 2018/1/12.
 */

public class User {


    @SerializedName("cid")
    private String username;
    @SerializedName("cpwd")
    private String password;

    public User(String username,String password){
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username){username=this.username;}
    public void setPassword(String password){username=this.password;}
    public String getUsername(){return username;}
    public String getPassword(){return password;}

}
