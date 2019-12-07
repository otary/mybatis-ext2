package cn.chenzw.mybatis.ext2.page;

import cn.chenzw.mybatis.ext2.page.entity.User;
import cn.chenzw.mybatis.ext2.page.repository.UserMapper;
import cn.chenzw.mybatis.ext2.page.support.PageParam;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootApplication
public class MybatisPageTests {


    @Autowired
    UserMapper userMapper;

    @Test
    public void testListAll() {
        List<User> users = userMapper.listAll();

        System.out.println(users);
    }


    @Test
    public void testListByPage() {
        PageParam pageParam = new PageParam(1, 10);
        List<User> users = userMapper.listByPage(pageParam);

        Assert.assertEquals("[User{id=11, name='张三5'}, User{id=12, name='张三5'}, User{id=13, name='张三5'}, User{id=14, name='张三5'}, User{id=15, name='张三5'}, User{id=16, name='张三5'}, User{id=17, name='张三5'}, User{id=18, name='张三5'}, User{id=19, name='张三5'}, User{id=20, name='张三5'}]", users.toString());

        Assert.assertEquals(40, pageParam.getTotalRows());

    }
}
