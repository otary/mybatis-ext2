package cn.chenzw.mybatis.ext2.page.support;

import java.io.Serializable;

/**
 * 分页参数
 *
 * @author chenzw
 */
public interface Pageable extends Serializable {

    /**
     * 第几页
     *
     * @return
     */
    Integer getOffset();

    /**
     * 每页条数
     *
     * @return
     */
    Integer getLimit();

    /**
     * 总条数
     *
     * @return
     */
    Long getTotalRows();

}
