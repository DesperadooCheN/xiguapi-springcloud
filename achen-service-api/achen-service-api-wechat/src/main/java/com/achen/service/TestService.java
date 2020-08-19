package com.achen.service;

import org.springframework.web.bind.annotation.GetMapping;

public interface TestService  {
    @GetMapping("/test")
    Object Test(int id);

//    String Test(@RequestParam("userId")String userId);
}
