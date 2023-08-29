import com.demo.SystemApplication;
import com.demo.domain.system.service.SysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: 王孟伟
 * @Date: 2023/1/17 14:11
 * @Description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SystemApplication.class})
public class SystemTest {

    @Autowired
    private SysMenuService sysMenuService;

    @Test
    public  void menuList(){
//        List<SysMenuPo> sysMenuPos = sysMenuService.queryMenuList(null);
//        System.out.println(sysMenuPos);
    }
}
