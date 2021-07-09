package com.example.mockmvcdemo.service;

import com.example.mockmvcdemo.entity.MockEntity;

/**
 * @author Terry.Liu
 * @date 2021/7/9 17:35
 */
public interface MockService {
    void testVoid(int var);
    String testCall(int var);
    MockEntity testCall2(int var);
    String testCall3(int var);
}
