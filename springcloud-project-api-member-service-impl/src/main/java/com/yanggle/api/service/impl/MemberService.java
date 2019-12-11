package com.yanggle.api.service.impl;

import com.yanggle.api.entity.User;
import com.yanggle.api.service.IMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
