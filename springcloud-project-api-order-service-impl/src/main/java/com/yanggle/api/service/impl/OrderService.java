package com.yanggle.api.service.impl;

import com.yanggle.api.entity.User;
import com.yanggle.api.feign.MemberServiceFeign;
import com.yanggle.api.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderService implements IOrderService {

    @Autowired
    MemberServiceFeign memberServiceFeign;

    @RequestMapping("/orderToMember")
    public String orderToMember(String name) {
        User user = memberServiceFeign.getMember(name);
        return (user == null)?"未知会员":user.toString();
    }
}
