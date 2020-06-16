package com.gupaoedu.security;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.URL;


/**
 * Created by Tom on 2018/12/27.
 */
public class DianpingCrawlTest {

    public static void main(String[] args) {

        for (int buildingId = 26232810; buildingId < 26233809; buildingId++) {
            try {
                String url = "http://www.dianping.com/shop/" + buildingId;

                Connection conn = Jsoup.connect(url);
                Document doc = header(conn,buildingId).get();
                System.out.println("==============================");
                parse(doc);
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
                continue;
            }
        }
    }

    /**
     * 解析正文内容
     * @param doc
     */
    private static void parse(Document doc){
        String shopName = doc.select(".shop-name").text().replaceAll("手机买单|积分抵现|添加分店","").trim();
        String address = doc.select(".expand-info.address").text();
        String tel = doc.select(".expand-info.tel").text();
        System.out.println(shopName + "\r\n" + address + "\r\n" + tel);
    }

    /**
     * 设置请求头信息
     * @param conn
     * @param buildingId
     * @return
     */
    private static Connection header(Connection conn,int buildingId){
        return conn.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
                .header("Host","www.dianping.com")
                .header("Referer","http://www.dianping.com/shop/" + (buildingId-1) + "")
                .header("Cookie","Hm_lvt_e6f449471d3527d58c46e24efb4c343e=1525519957; _lxsdk_cuid=16330133adec8-0c7a579580574d-3961430f-1fa400-16330133adec8; _lxsdk=16330133adec8-0c7a579580574d-3961430f-1fa400-16330133adec8; _hc.v=16214e30-d41f-a2e3-dab6-c4e1bfecdeac.1525519957; aburl=1; cy=1; cye=shanghai; _lxsdk_s=167fefd1f29-478-254-f09%7C%7C21");
    }

}
