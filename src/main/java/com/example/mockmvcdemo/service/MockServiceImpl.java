package com.example.mockmvcdemo.service;

import com.example.mockmvcdemo.entity.MockEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Terry.Liu
 * @date 2021/7/9 17:40
 */
@Service
@Slf4j
public class MockServiceImpl implements MockService{
    @Override
    public void testVoid(int var) {
        log.info("=========={}",var);
    }

    @Override
    public String testCall(int var) {
        log.info("=========={}",var);
        return String.valueOf(var);
    }

    @Override
    public MockEntity testCall2(int var) {
        log.info("=========={}",var);
        MockEntity mockEntity = new MockEntity();
        mockEntity.setId(var);
        mockEntity.setName("liu"+var);
        return mockEntity;
    }

    @Override
    public String testCall3(int var) {
        log.info("=========={}",var);
        return String.valueOf(var);
    }
}
