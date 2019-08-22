package by.future.web.test.springboot.demotest;

import by.future.servicebiz.callback.Button;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * demo测试类
 *
 * @author by@Deng
 * @create 2019-03-10 21:06
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class DemoTest {

    @Test
    public void testCallBack(){

        //点击按钮
        Button likeButton = new Button();
        likeButton.setOnClickListener(()-> System.out.println("用户点赞了文章"));

        //收藏按钮
        Button collectionButton = new Button();
        collectionButton.setOnClickListener(()-> System.out.println("用户收藏了文章"));

        likeButton.mockClick();
        collectionButton.mockClick();

    }



}
