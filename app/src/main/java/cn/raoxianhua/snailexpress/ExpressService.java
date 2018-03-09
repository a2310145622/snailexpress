package cn.raoxianhua.snailexpress;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.PipedWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by raoxianhua on 2018/1/11.
 */

public class ExpressService {


    public static List<Express> ExpressSearch(String ShipperCode, String LogisticCode) {
         List<Express> results =new ArrayList<>() ;
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址是电脑本机
                            .url("http://www.raoxianhua.cn/snail/doSearch?ShipperCode=&eid=se000004")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
//                    Gson gson = new Gson();
//                    results=gson.fromJson(responseData, new TypeToken<List<Express>>() {}.getType());
//                    System.out.println(responseData.toString());
                    parseJSONWithGSON(responseData);
//                    parseJSONWithJSONObject(responseData);
//                    parseXMLWithSAX(responseData);
//                    parseXMLWithPull(responseData);
//                    showResponse(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }

        return results;
    }

    static private void parseJSONWithGSON(String jsonData) {
        Gson gson = new Gson();
        List<Express> expressList  = gson.fromJson(jsonData, new TypeToken<List<Express>>() {}.getType());
        System.out.println(jsonData.toString());
        for (Express app : expressList) {
            Log.d("MainActivity", "id is " + app.getExpressTime());
            Log.d("MainActivity", "name is " + app.getExpressDetails());
        }
    }

//    //DEMO
//    public static List<Express> ExpressSearch(String ShipperCode, String LogisticCode) {
//        ExpressService api = new ExpressService();
//        List<Express> result = new ArrayList<>();
//        try {
//            Gson gson = new Gson();
//            result = gson.fromJson(api.getOrderTracesByJson(ShipperCode, LogisticCode), new TypeToken< List <Express>>() {
//            }.getType());
////            JsonObject object=new JsonParser().parse(result).getAsJsonObject();
////
////            JsonArray array=object.get("Traces").getAsJsonArray();    //得到为json的数组
////            for(int i=0;i<array.size();i++){
////                Map<String, Object> row = new HashMap<>();
////                JsonObject subObject=array.get(i).getAsJsonObject();
////                String colName = subObject.get("AcceptTime").getAsString();
////                Object value = subObject.get("AcceptStation").getAsString();
////                row.put("EXPRESSTIME", colName);
////                row.put("EXPRESSDETAILS", value);
////                result1.add(row);
////            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    private String ReqURL="http://www.raoxianhua.cn/snail/doSearch";
//
//    /**
//     * Json方式 查询订单物流轨迹
//     * @throws Exception
//     */
//    public String getOrderTracesByJson(String expCode, String expNo) throws Exception{
//        String requestData= "ShipperCode=" + expCode + "&eid=" + expNo;
//
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("RequestData", URLEncoder.encode(requestData, "UTF-8"));
//        params.put("RequestType", "1002");
//        params.put("DataType", "2");
//
////        String result=sendPost(ReqURL, params);
//        String result=sendPost(ReqURL, requestData);
//
//        //根据公司业务处理返回的信息......
//
//        return result;
//    }
//
//    /**
//     * 向指定 URL 发送POST方法的请求
//     *
//     * @param url
//     *            发送请求的 URL
//     * @param param
//     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
//     * @return 所代表远程资源的响应结果
//     */
//    public static String sendPost(String url, String param) {
//        PrintWriter out = null;
//        BufferedReader in = null;
//        String result = "";
//        try {
//            URL realUrl = new URL(url);
//            // 打开和URL之间的连接
//            URLConnection conn = realUrl.openConnection();
//            // 设置通用的请求属性
//            conn.setRequestProperty("accept", "*/*");
//            conn.setRequestProperty("connection", "Keep-Alive");
//            conn.setRequestProperty("user-agent",
//                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            // 发送POST请求必须设置如下两行
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            // 获取URLConnection对象对应的输出流
//            out = new PrintWriter(conn.getOutputStream());
//            // 发送请求参数
//            out.print(param);
//            // flush输出流的缓冲
//            out.flush();
//            // 定义BufferedReader输入流来读取URL的响应
//            in = new BufferedReader(
//                    new InputStreamReader(conn.getInputStream()));
//            String line;
//            while ((line = in.readLine()) != null) {
//                result += line;
//            }
//        } catch (Exception e) {
//            System.out.println("发送 POST 请求出现异常！"+e);
//            e.printStackTrace();
//        }
//        //使用finally块来关闭输出流、输入流
//        finally{
//            try{
//                if(out!=null){
//                    out.close();
//                }
//                if(in!=null){
//                    in.close();
//                }
//            }
//            catch(IOException ex){
//                ex.printStackTrace();
//            }
//        }
//        return result;
//    }


//    /**
//     * 向指定 URL 发送POST方法的请求
//     * @param url 发送请求的 URL
//     * @param params 请求的参数集合
//     * @return 远程资源的响应结果
//     */
//    @SuppressWarnings("unused")
//    private String sendPost(String url, Map<String, String> params) {
//        OutputStreamWriter out = null;
//        BufferedReader in = null;
//        StringBuilder result = new StringBuilder();
//        try {
//            URL realUrl = new URL(url);
//            HttpURLConnection conn =(HttpURLConnection) realUrl.openConnection();
//            // 发送POST请求必须设置如下两行
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            // POST方法
//            conn.setRequestMethod("POST");
//            // 设置通用的请求属性
//            conn.setRequestProperty("accept", "*/*");
//            conn.setRequestProperty("connection", "Keep-Alive");
//            conn.setRequestProperty("user-agent",
//                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            conn.connect();
//            // 获取URLConnection对象对应的输出流
//            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
//            // 发送请求参数
//            if (params != null) {
//                StringBuilder param = new StringBuilder();
//                for (Map.Entry<String, String> entry : params.entrySet()) {
//                    if(param.length()>0){
//                        param.append("&");
//                    }
//                    param.append(entry.getKey());
//                    param.append("=");
//                    param.append(entry.getValue());
//                    //System.out.println(entry.getKey()+":"+entry.getValue());
//                }
//                //System.out.println("param:"+param.toString());
//                out.write(param.toString());
//            }
//            // flush输出流的缓冲
//            out.flush();
//            // 定义BufferedReader输入流来读取URL的响应
//            in = new BufferedReader(
//                    new InputStreamReader(conn.getInputStream(), "UTF-8"));
//            String line;
//            while ((line = in.readLine()) != null) {
//                result.append(line);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        //使用finally块来关闭输出流、输入流
//        finally{
//            try{
//                if(out!=null){
//                    out.close();
//                }
//                if(in!=null){
//                    in.close();
//                }
//            }
//            catch(IOException ex){
//                ex.printStackTrace();
//            }
//        }
//        return result.toString();
//    }
}
