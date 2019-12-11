package com.yanggle.api.service;

import com.yanggle.api.entity.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

public interface IMemberService {

    @RequestMapping("/getMember")
    User getMember(String name);

    @RequestMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String upload(@RequestParam("file") MultipartFile file);
}
