package com.yanggle.api.service.impl;

import com.yanggle.api.entity.User;
import com.yanggle.api.service.IMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@Slf4j
public class MemberService implements IMemberService {

    @RequestMapping("/getMember")
    public User getMember(String name){
        log.info("name=" + name);
        User user = new User();
        user.setName(name);
        user.setAge(40);
        return user;
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public String upload(@RequestPart("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }

        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            log.error(e.toString(), e);
        }
        if(!path.exists()) path = new File("");
        log.info("path:"+path.getAbsolutePath());

        File upload = new File(path.getAbsolutePath(),"upload/");
        if(!upload.exists()) upload.mkdirs();
        log.info("upload url:"+upload.getAbsolutePath());

        String fileName = file.getOriginalFilename();
        File dest = new File(upload.getAbsolutePath() + "/" + fileName);
        try {
            file.transferTo(dest);
            log.info("上传成功");
            return "上传成功";
        } catch (IOException e) {
            log.error(e.toString(), e);
        }

        return "上传失败！";
    }
}
