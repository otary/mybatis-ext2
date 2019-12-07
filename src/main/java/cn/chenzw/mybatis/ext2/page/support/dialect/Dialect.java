package cn.chenzw.mybatis.ext2.page.support.dialect;


import cn.chenzw.mybatis.ext2.page.support.Pageable;

/**
 * @author chenzw
 */
public interface Dialect {


    /**
     * 获取分页SQL
     *
     * @param sql
     * @param pageable
     * @return
     */
    String getPageSql(String sql, Pageable pageable);


    /**
     * 获取CountSQL
     *
     * @param sql
     * @return
     */
    String getCountSql(String sql);

}
