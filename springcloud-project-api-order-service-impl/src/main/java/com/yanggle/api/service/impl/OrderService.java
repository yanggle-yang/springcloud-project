package com.yanggle.api.service.impl;

import com.yanggle.api.entity.User;
import com.yanggle.api.feign.MemberServiceFeign;
import com.yanggle.api.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@Slf4j
public class OrderService implements IOrderService {

    @Autowired
    MemberServiceFeign memberServiceFeign;

    @RequestMapping("/orderToMember")
    public String orderToMember(String name) {
        User user = memberServiceFeign.getMember(name);
        return (user == null)?"未知会员":user.toString();
    }

    @RequestMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadToMember(@RequestParam("file") MultipartFile file) {
        return memberServiceFeign.upload(file);
    }

    @RequestMapping(value = "/uploadLocal")
    public String uploadLocalToMember(String fileName) {
        if(fileName == null || fileName.isEmpty())
            fileName = "application.yml";

        //获取跟目录
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            log.error(e.toString(), e);
        }
        if(!path.exists()) path = new File("");
        log.info("path:"+path.getAbsolutePath());

        File file = new File(path.getAbsolutePath() + "/" + fileName);
        DiskFileItemFactory factory = new DiskFileItemFactory();
        DiskFileItem fileItem = (DiskFileItem) factory.createItem("file",
                /*"text/x-yaml"*/ MediaType.MULTIPART_FORM_DATA_VALUE, true, file.getName());
        try {
            int count = IOUtils.copy(new FileInputStream(file), fileItem.getOutputStream());
        } catch (IOException e) {
            log.error(e.toString(), e);
        }

        MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
        memberServiceFeign.upload(multipartFile);

        return path.getAbsolutePath();
    }
}
