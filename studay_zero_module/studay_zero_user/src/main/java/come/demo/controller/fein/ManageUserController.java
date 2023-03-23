package come.demo.controller.fein;

import com.demo.api.ZeroResult;
import com.demo.service.RemoteSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author wangmengwei
 * @date 2022年06月08日 17:11
 */
@RestController
@RequestMapping("/userFein")
public class ManageUserController {

    @Autowired
    private RemoteSystemService remoteSystemService;

    @PostMapping("/testFein")
    public ZeroResult send(){
        return remoteSystemService.send();
    }
}
