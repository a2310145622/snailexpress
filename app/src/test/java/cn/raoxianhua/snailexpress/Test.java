package cn.raoxianhua.snailexpress;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by raoxianhua on 2018/1/5.
 */
public class Test {
    @org.junit.Test
    public void show() throws Exception {
        String i = "se000004";
//        IExpressService ExpressService = new ExpressServiceImpl();
//        IExpressService ExpressService1 = new ExpressServiceImpl();
//        List<Map<String, Object>> express = ExpressService.doSearch(i);
        List<Express> expressList=ExpressService.ExpressSearch("ZTO", "719953965833");
        System.out.println(expressList.get(0).getExpressTime().toString());
    }

}