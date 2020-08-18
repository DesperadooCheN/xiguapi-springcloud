package com.achen.rocket.service;

import org.springframework.web.bind.annotation.RequestMapping;

public interface Producer {

    @RequestMapping("/sendOrder")
    String sendOrder();
}
