package com.yanggle.api.service;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface IOrderService {

    @RequestMapping("/orderToMember")
    String orderToMember(String name);

    @RequestMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadToMember(@RequestParam("file") MultipartFile file);

    @RequestMapping(value = "/uploadLocal", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadLocalToMember(String name);
}
