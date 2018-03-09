package cn.raoxianhua.snailexpress;

public interface HttpCallbackListener {

    void onFinish(String response);

    void onError(Exception e);

}