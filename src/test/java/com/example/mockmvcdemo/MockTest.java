package com.example.mockmvcdemo;

import com.example.mockmvcdemo.service.MockService;
import com.example.mockmvcdemo.service.MockServiceImpl;
import com.example.mockmvcdemo.util.DemoUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * 打桩测试
 * @author Terry.Liu
 * @date 2021/7/9 17:34
 */
@SpringBootTest
@Slf4j
@RunWith(PowerMockRunner.class) // 步骤1
@PowerMockRunnerDelegate(SpringRunner.class)// 步骤2
@PowerMockIgnore( {"javax.management.*", "javax.net.*"})// 步骤3
@PrepareForTest(DemoUtil.class)// 步骤4：指定提前mock哪个类
public class MockTest {
    @Resource
    private MockService mockService;
    @Test
    public void test1(){
        Assertions.assertEquals(mockService.testCall(1),"1");
    }

    @Test
    public void test2(){
        mockService = Mockito.mock(MockServiceImpl.class);
        BDDMockito.when(mockService.testCall(BDDMockito.anyInt())).thenReturn("1");
        Assertions.assertEquals(mockService.testCall(2),"1");
        //不可以真实使用其他方法
        Assertions.assertNull(mockService.testCall3(2));
    }
    @Test
    public void test3(){
        mockService = Mockito.spy(MockServiceImpl.class);
        BDDMockito.when(mockService.testCall(BDDMockito.anyInt())).thenReturn("1");
        Assertions.assertEquals(mockService.testCall(2),"1");
        //可以真实使用其他方法
        Assertions.assertEquals(mockService.testCall3(2),"2");
    }
    @Test
    public void test4(){
        mockService = Mockito.spy(MockServiceImpl.class);
        BDDMockito.doNothing().when(mockService).testVoid(BDDMockito.anyInt());
        mockService.testVoid(1);
    }

    @Test
    public void test5(){
        //given+willReturn 与when+thenReturn 类似
        mockService = Mockito.spy(MockServiceImpl.class);
        BDDMockito.given(mockService.testCall(BDDMockito.anyInt())).willReturn("1");
        Assertions.assertEquals(mockService.testCall(2),"1");
        Assertions.assertEquals(mockService.testCall3(2),"2");
    }
    @Test
    public void test6(){
        mockService = Mockito.mock(MockServiceImpl.class);
        //thenCallRealMethod 可以使用真实方法
        BDDMockito.when(mockService.testCall(BDDMockito.anyInt())).thenCallRealMethod();
        Assertions.assertEquals(mockService.testCall(2),"2");
    }

    @Test
    public void test7(){
        mockService = Mockito.mock(MockServiceImpl.class);
        BDDMockito.when(mockService.testCall(BDDMockito.anyInt())).thenReturn("1","2","3","4");
        Assertions.assertEquals(mockService.testCall(2),"1");
        Assertions.assertEquals(mockService.testCall(2),"2");
        Assertions.assertEquals(mockService.testCall(2),"3");
        Assertions.assertEquals(mockService.testCall(2),"4");
        Assertions.assertEquals(mockService.testCall(2),"4");
    }

    @Test
    public void test8(){
        //接口也可以创建测试
        mockService = Mockito.mock(MockService.class);
        BDDMockito.when(mockService.testCall(BDDMockito.anyInt())).thenReturn("1");
        Assertions.assertEquals(mockService.testCall(2),"1");
    }

    @Test
    public void test9(){
        //接口也可以创建测试
        List list = Mockito.mock(List.class);
        BDDMockito.when(list.size()).thenReturn(12);
        Assertions.assertEquals(list.size(),12);
    }

    @org.junit.Test
    public void test10(){
        PowerMockito.mockStatic(DemoUtil.class); // 步骤5
        PowerMockito.when(DemoUtil.get(Mockito.anyInt())).thenReturn("22"); // 步骤6
        Assertions.assertEquals(DemoUtil.get(2),"22");
    }

    @Test
    public void test11(){
    }
}
