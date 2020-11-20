package com.oss.controller;

import com.oss.config.BaseController;
import com.oss.pojo.dto.DocumentDto;
import com.oss.tool.ErrorCodes;
import com.oss.tool.JsonResult;
import com.oss.tool.ResponseModel;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.*;

@RestController
public class TestController extends BaseController {

    @Validated
    @PostMapping("/textVolid")
    public ResponseModel textVolid(@Valid @RequestBody DocumentDto documentDto, BindingResult bindingResult)  {
//        if (bindingResult.hasErrors()){
//            return ResponseModel.errorWithMsg(ErrorCodes.PARAM_VALID_ERROR,bindingResult.getAllErrors().get(0).getDefaultMessage());
//        }
        return ResponseModel.success();
    }

    @PostMapping("/ioTest")
    @ResponseBody
    public JsonResult ioTest(MultipartFile file, HttpServletResponse response)  {
        //上传文件
        File newFile = new File("D:\\test\\testIo\\"+file.getOriginalFilename());
        if (!newFile.exists()) {
            newFile.getParentFile().mkdirs();
        }
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


        ServletOutputStream out = null;
        FileInputStream ips = null;
        String url = "D:\\test\\testIo\\mading.jpg";
        try {
            //获取图片存放路径
            File file1 = new File(url);
            if(!file1.exists()) {
                return null;
            }

            ips = new FileInputStream(file1);
            response.setContentType("multipart/form-data");
            out = response.getOutputStream();
            //读取文件流
            int len = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((len = ips.read(buffer)) != -1){
                out.write(buffer,0,len);
            }
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                ips.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }




        return JsonResult.success(out);
    }
}
