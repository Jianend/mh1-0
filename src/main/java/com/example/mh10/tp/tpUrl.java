package com.example.mh10.tp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Controller

public class tpUrl {
    public String tpurlxx() throws IOException {
        StringBuilder jeremytsai = new StringBuilder();
        try {
            SslUtils.ignoreSsl();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        try {
            //创建一个URL实例
            URL url = new URL("https://github.com/Jianend/tp");

            try {
                //通过URL的openStrean方法获取URL对象所表示的自愿字节输入流
                InputStream is = url.openStream();
                InputStreamReader isr = new InputStreamReader(is, "utf-8");

                //为字符输入流添加缓冲
                BufferedReader br = new BufferedReader(isr);
                String data = br.readLine();//读取数据

                while (data != null) {//循环读取数据
//                    System.out.println(data);//输出数据
                    jeremytsai.append(data);
//                    str = str + data;
                    data = br.readLine();
                }
                br.close();
                isr.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return jeremytsai.toString();
    }


    public List<String> tpUrls() throws IOException {
        String tpurlxx = tpurlxx();
        Document doc = Jsoup.parse(tpurlxx);

        List<String> arrayLists = new ArrayList<String>();
        Elements pngs = doc.select("a[href$=.jpg]");
        for (Element png : pngs) {
            String linkInnerH = png.attr("href");
            String s = "https://github.com" + linkInnerH + "?raw=true";


            arrayLists.add(s);
//            System.out.println(s);
        }
        return arrayLists;


    }
}


