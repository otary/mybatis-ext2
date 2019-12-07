package cn.chenzw.mybatis.ext2.page.repository;

import cn.chenzw.mybatis.ext2.page.entity.User;
import cn.chenzw.mybatis.ext2.page.support.Pageable;

import java.util.List;

public interface UserMapper {

    List<User> listAll();

    List<User> listByPage(Pageable pageable);
}
