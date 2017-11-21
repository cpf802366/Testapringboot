import com.cpf.boottest.TestapringbootApplication;
import com.cpf.boottest.dao.RoleRepository;
import com.cpf.boottest.dao.UserRepository;
import com.cpf.boottest.pojo.ModuleEntity;
import com.cpf.boottest.pojo.RoleEntity;
import com.cpf.boottest.pojo.UserEntity;
import com.cpf.boottest.service.UserReService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by cpf on 2017/11/16.
 */
@RunWith(SpringRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！

@SpringBootTest(classes = TestapringbootApplication.class)
@WebAppConfiguration // 由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
public class MemberTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserReService userReService;
    @Autowired
    private RoleRepository rolerRepository;

    @Test
    public void test() {
        System.out.println("TestapringbootApplication-----" + userRepository);
    }

    @Test
    public void testA() {
        UserEntity userEntity = userRepository.findByUsernameAndPassword("123", "123");
        System.out.println(userEntity);

    }
    @Test
    public void testB() {

        Collection<String> roles = new ArrayList<String> ();
        roles.add("1");
        roles.add("2");
        roles.add("3");
        List<RoleEntity> byRidIn = rolerRepository.findByRidIn(roles);
        System.out.print(byRidIn);
        /* List<String> roleName = userReService.findRoleName("123");*/
      /*  System.out.println(roleName);*/

    }
    @Test
    public void testC() {


        List<ModuleEntity> permissionlist = userReService.findPermissionlist("123");
        System.out.println(permissionlist);

    }
}
