package cn.cvzhanshi.wechatpush;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WechatpushApplicationTests {

    @Test
    void contextLoads() {
        WechatpushApplication application = new WechatpushApplication();
        application.goodMorning();
    }

}
