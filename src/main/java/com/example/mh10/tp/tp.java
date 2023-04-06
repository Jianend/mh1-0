package com.example.mh10.tp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/tp")
public class tp {
    @Value("${basePath}")

    String basePath;
    static File dir ;
    static List<File> allFileList;





    @GetMapping
    public   void   tps(HttpServletResponse response) throws IOException {

        int v =(int)( Math.random() * allFileList.size());
        String fileall = allFileList.get(v).getName();
        response.setContentType("image/jpeg");
//        System.out.println(fileall);
        FileInputStream fileInputStream = new FileInputStream(new File(basePath + fileall));
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] bytes=new byte[1024];
        int  len=0;
        while ((len=(fileInputStream.read(bytes)))!=-1){
            outputStream.write(bytes,0,len);
            outputStream.flush();
        }
        outputStream.close();
        fileInputStream.close();
    }

    public tp(@Value("${basePath}") String basePath){
        dir= new File(basePath);

        allFileList = new ArrayList<>();
        // 判断文件夹是否存在
        if (!dir.exists()) {
            System.out.println("目录不存在");

        }
        getAllFile(dir, allFileList);


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
