package cn.chenzw.mybatis.ext2.page.config;

import cn.chenzw.mybatis.ext2.page.plugin.PagePlugin;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {"cn.chenzw.mybatis.ext2.page.repository"})
public class MybatisConfig {

    /**
     * 开启分页插件
     *
     * @return
     */
    @Bean
    public PagePlugin pagePlugin() {
        return new PagePlugin();
    }
}
