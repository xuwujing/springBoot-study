package com.pancm;


import com.pancm.commons.config.YamlConfigurerUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author pancm
 * @Description 配置文件获取测试
 * @Date 2020/8/6
 * @Param
 * @return
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class YmlTest {


    @Test
    public void getYml() {
        String val = YamlConfigurerUtil.getStrYmlVal("banner.charset");
        System.out.println(val);
    }


}
