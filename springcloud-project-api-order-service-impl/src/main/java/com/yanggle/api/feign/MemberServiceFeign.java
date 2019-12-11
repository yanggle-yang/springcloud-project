package com.yanggle.api.feign;

import com.yanggle.api.entity.User;
import com.yanggle.api.service.IMemberService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value="app-project-member")//, url = "${service.member.url}")
public interface MemberServiceFeign extends IMemberService {

    @RequestMapping("/getMember")
    User getMember(@RequestParam("name")String name);

    @RequestMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String upload(@RequestPart("file") MultipartFile file);
}
