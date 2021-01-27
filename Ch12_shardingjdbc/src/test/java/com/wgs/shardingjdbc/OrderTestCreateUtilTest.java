package com.wgs.shardingjdbc;

import com.wgs.shardingjdbc.service.OrderTestCreateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShardingjdbcApplication.class)
public class OrderTestCreateUtilTest {

    @Autowired
    private OrderTestCreateUtil orderTestCreateUtil;

    @Test
    public void initOrder() {
        orderTestCreateUtil.initOrder();
    }
}
