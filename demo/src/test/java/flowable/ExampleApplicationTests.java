package flowable;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName ExampleApplicationTests.java
 * @Description TODO
 * @createTime 2021年12月14日
 */

import com.ykkj.YkkjDemoApplication;
import org.flowable.engine.IdentityService;
import org.flowable.form.engine.test.FormDeploymentAnnotation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest(classes = YkkjDemoApplication.class)
class ExampleApplicationTests {

    @Autowired
    private IdentityService identityService;

    @Test
    @FormDeploymentAnnotation
    public void identityServiceTest() {
        long result1 = identityService.createUserQuery().userId("1").count();
        long result2 = identityService.createUserQuery().userId("4").count();
        long result3 = identityService.createUserQuery().userIds(Arrays.asList("1", "2", "4")).count();
        Assertions.assertEquals(1, result1);
        Assertions.assertEquals(0, result2);
        Assertions.assertEquals(2, result3);
    }

}
