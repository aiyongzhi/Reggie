package com.ayz.reggie.controller;
import com.ayz.reggie.common.R;
import com.ayz.reggie.utils.GenerateUUID;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/*
* 实现文件上传和下载的Controller
* */
@RestController
public class CommonController {
    @Value("${file.upLoad.path}")
    private String FILE_UPLOAD_PATH;


    //返回值:文件上传成功时返回文件名称
    @RequestMapping("/common/upload")
    public R<String> fileUpLoadController(MultipartFile file){
        //文件上传时，接收到前端传输文件的file会默认在服务器生成一个临时存储文件
        //当这个方法执行完毕后，该临时存储文件会被销毁
        //所以我们需要将文件进行转存,转存到指定磁盘目录
        //为了防止文件名重复,用UUID生成文件名
        if(file.isEmpty()){
            return R.error("文件上传失败");
        }else{
            //1:如果FILE_UPLOAD_PATH文件夹没创建则需要创建
            File dic=new File(FILE_UPLOAD_PATH);
            if(!dic.exists()){
                dic.mkdirs();
            }
            String filename=GenerateUUID.getByFilename(file.getOriginalFilename());
            String realPath=FILE_UPLOAD_PATH+ filename;
            try {
                file.transferTo(new File(realPath));
            } catch (IOException e) {
                e.printStackTrace();
                return R.error("文件上传失败!");
            }
            return R.success(filename);
        }
    }

    @RequestMapping("/common/download")
    public void download(String name, HttpServletResponse response){
        //1:输入流和输出流
        //将磁盘中的文件以输入流的方式读进内存
        //将内存中的文件写入response当中
        String realPath=FILE_UPLOAD_PATH+name;
        InputStream inputStream=null;
        ServletOutputStream outputStream=null;
        response.setContentType("image/jpg");
        try {
            inputStream=new FileInputStream(new File(realPath));
            outputStream = response.getOutputStream();
            IOUtils.copy(inputStream,outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
