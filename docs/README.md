# mybatis-ext2

Mybatis扩展

## 文档

[中文文档](https://otary.github.io/mybatis-ext2/#/)

## 功能
 - [x] 分页
 - [x] 慢SQL输出
 
 
## 依赖

```
<dependency>
   <groupId>cn.chenzw.mybatis</groupId>
   <artifactId>mybatis-ext2</artifactId>
   <version>1.0.0</version>
</dependency>
```
 
## 使用

``` java
@Configuration
public class MybatisConfig {


    ...

    /**
     * 开启分页插件
     *
     * @return
     */
    @Bean
    public PagePlugin pagePlugin(){
        return new PagePlugin();
    }
    
}
```

``` java
import cn.chenzw.mybatis.ext2.page.support.Pageable;

public interface UserMapper {

    ...

    // 使用Pageable来传递分页参数
    List<User> listByPage(Pageable pageable);
}
```

``` java
import cn.chenzw.mybatis.ext2.page.support.PageParam;
import cn.chenzw.mybatis.ext2.page.util.PageUtils;

@Test
public void testListByPage() {
    PageParam pageParam = new PageParam(1, 10);
    List<User> users = userMapper.listByPage(pageParam);

    // 执行完之后pageParam中包含了总条数值
    pageParam.getTotalRows();  // => 40
    pageParam.getLimit(); // =>10
    pageParam.getOffset(); // => 1
    
     // 计算总页数
     int totalPage = PageUtils.countTotalPage(pageParam);  // => 4

     // 计算起始页码
     int startOffset = PageUtils.countStartOffset(pageParam);   // => 0
}
```


## 应用场景

- 对比于PageHelper插件

PageHelper插件返回的是个Page对象（重写List对象）, 总条数等信息放在Page对象上，但当我们需要将返回值转成DTO对象时就会丢失Page对象，所以此插件适用于返回值需要转Dto的场景

