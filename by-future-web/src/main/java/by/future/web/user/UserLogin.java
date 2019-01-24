package by.future.web.user;


import by.future.common.utils.ShortCodeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author：by@Deng
 * @Date：2018/9/1 16:33
 */
@RestController
@RequestMapping("login")
public class UserLogin {


    @GetMapping("aaaaa")
    public String test(@RequestParam Map<String,String> requestMap, HttpServletRequest request){

        String ret1 = requestMap.get("u1");
        String ret2 = ShortCodeUtils.toBase62(289324432l);

        return String.format("%s----%s",ret1,"aaaa");
    }

}
