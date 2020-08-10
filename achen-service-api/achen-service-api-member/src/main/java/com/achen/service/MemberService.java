package com.achen.service;


import org.springframework.web.bind.annotation.GetMapping;

public interface MemberService {
    @GetMapping("getTest")
    Object getTest();
}
