package com.example.webdemo.service.biz;

import com.example.webdemo.common.utils.DemoUtil;
import com.example.webdemo.common.vo.PageVo;
import com.example.webdemo.dao.RoleMapper;
import com.example.webdemo.service.impl.RoleServiceImpl;
import com.example.webdemo.vo.request.RoleRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;

import static org.mockito.Matchers.any;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DemoUtil.class, RoleServiceImpl.class})
public class RoleServiceTest {
    @InjectMocks
    private RoleServiceImpl roleService;

    // 模拟接口
    @Mock
    private RoleMapper roleMapper;

    @Before
    public void before() {
        // 扫描注解
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void queryTest() {
        RoleRequest req = new RoleRequest();
        Mockito.when(roleMapper.selectByExample(any())).thenReturn(new ArrayList<>());
        Mockito.when(roleMapper.countByExample(any())).thenReturn(10);
        PageVo query = roleService.query(req);
    }

    /**
     * 1.类上添加
     *
     * @RunWith(PowerMockRunner.class)
     * @PrepareForTest(DemoUtil.class) // 需要静态的类class
     * 2.具体使用
     * PowerMockito.mockStatic(DemoUtil.class);
     * Mockito.when(DemoUtil.m1()).thenReturn("mock2");
     */
    @Test
    public void mockitoStaticTest() {
        // 模拟静态方法
        PowerMockito.mockStatic(DemoUtil.class);
        Mockito.when(DemoUtil.m1()).thenReturn("mock2");
        roleService.mockitoStaticTest();
    }

    /**
     * 1.类添加
     *
     * @throws Exception
     * @RunWith(PowerMockRunner.class)
     * @PrepareForTest(RoleServiceImpl.class) // 需要调用私有的类class
     * 2.使用
     * RoleServiceImpl spy = PowerMockito.spy(roleService);
     * PowerMockito.when(spy, "mockitoPrivate").thenReturn("xxx");
     * spy.mockitoStaticTest(); // 是spy.
     */
    @Test
    public void mockitoStaticTest2() throws Exception {
        // 模拟私有方法
        RoleServiceImpl spy = PowerMockito.spy(roleService);
        PowerMockito.when(spy, "mockitoPrivate").thenReturn("xxx");
        spy.mockitoStaticTest();
    }
}