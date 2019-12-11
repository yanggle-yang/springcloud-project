package com.yanggle.api.feign;

import com.yanggle.api.entity.User;
import com.yanggle.api.service.IMemberService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="app-project-member")//, url = "${service.member.url}")
public interface MemberServiceFeign extends IMemberService {

    @RequestMapping("/getMember")
    User getMember(@RequestParam("name")String name);
}
