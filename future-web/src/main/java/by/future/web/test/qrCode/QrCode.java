package by.future.web.test.qrCode;


import by.future.common.utils.QrCodeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
/**
 * @Author：by@Deng
 * @Date：2018/10/16 15:33
 */
@RestController
@RequestMapping("qrCode")
public class QrCode {

    @GetMapping("testQrCode")
    public void testQrCode(HttpServletResponse response){

        byte [] bytes= QrCodeUtils.encodeQrCode("https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=1694786320,852202210&fm=173&app=25&f=JPG?w=640&h=441&s=E080DF12667B578E02F8B7F003005035",300,300);
        try{

            response.addHeader("Content-Disposition", "attachment;fileName="+ URLEncoder.encode("图片","UTF-8")+".jpeg");
            response.setContentType("image/jpeg");
            OutputStream stream = response.getOutputStream();
            stream.write(bytes);
            stream.flush();
            stream.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }



}
