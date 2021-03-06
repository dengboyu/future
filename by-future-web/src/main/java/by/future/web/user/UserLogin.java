package by.future.web.user;


import by.future.common.utils.ShortCodeUtils;
import by.future.common.utils.UUIDUtils;
import by.future.servicebiz.validation.RequestValidator;
import by.future.servicebiz.validation.instance.LoginRequestValidator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author：by@Deng
 * @Date：2018/9/1 16:33
 */
@RestController
@RequestMapping("login")
public class UserLogin {


    /**
     * 登录
     *
     * @Author: by@Deng
     * @Date: 2019-03-23 00:57
     */
    @GetMapping("login")
    public String login(@RequestParam Map<String,String> requestMap, HttpServletRequest request, HttpServletResponse response){

        RequestValidator<Map<String,String>> requestValidator = new LoginRequestValidator();
        requestValidator.validate(requestMap);

        String ret1 = requestMap.get("u1");
        String ret2 = ShortCodeUtils.toBase62(289324432l);

        Cookie cookie = new Cookie("token",UUIDUtils.getUUID());
        cookie.setMaxAge(2 * 60 * 60 *24);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        Cookie cookie1 = new Cookie("token1",UUIDUtils.getUUID());
        cookie1.setMaxAge(2 * 60 * 60 *24);
        cookie1.setPath("/");
        cookie1.setHttpOnly(true);
        response.addCookie(cookie1);

        /*int count =0;
        for(int i = 0;i<Integer.MAX_VALUE;i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(count++);
        }*/

        return String.format("%s----%s",ret1,"aaaa");
    }


    /**
     * 测试
     *
     * @Author: by@Deng
     * @Date: 2019-03-23 00:57
     */
    @GetMapping("test")
    public String test(@RequestParam Map<String,String> requestMap, HttpServletResponse response){

        
        String success = "success";
        if(requestMap.get("userId")!=null) success = requestMap.get("userId");
        return success;
    }

}
