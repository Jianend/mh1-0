package com.example.mh10.tp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/tp")
public class tp {
    @Value("${basePath}")

    String basePath;
    static File dir ;
    static List<File> allFileList;
    @Autowired
    tpUrl tpUrl;



    @GetMapping
    public   String     tps(HttpServletResponse response) throws IOException {
        List<String> arrayList = tpUrl.tpUrls();
        int v =(int)( Math.random() * arrayList.size());
        String fileall = arrayList.get(v);
        System.out.println(fileall);
//        return"redirect:"+fileall;
        return "<img src=\""+fileall+"\">";

    }


    public static void getAllFile(File fileInput, List<File> allFileList) {
//        fileInput.

        // 获取文件列表
        File[] fileList = fileInput.listFiles();
        assert fileList != null;
        for (File file : fileList) {
            if (file.isDirectory()) {
                // 递归处理文件夹
                // 如果不想统计子文件夹则可以将下一行注释掉
                getAllFile(file, allFileList);
            } else {
                // 如果是文件则将其加入到文件数组中
                String suffix = StringUtils.getFilenameExtension(file.getName());
                if ("jpg".equals(suffix)||"png".equals(suffix)){
                    allFileList.add(file);
                }
            }
        }
    }

}
