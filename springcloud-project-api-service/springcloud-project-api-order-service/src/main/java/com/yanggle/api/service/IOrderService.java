package com.yanggle.api.service;

import org.springframework.web.bind.annotation.RequestMapping;

public interface IOrderService {

    @RequestMapping("/orderToMember")
    String orderToMember(String name);

    @RequestMapping("/upload")
    String uploadToMember(String name);
}
