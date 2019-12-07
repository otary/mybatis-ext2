package cn.chenzw.mybatis.ext2.page.support;

/**
 * 分页参数
 *
 * @author chenzw
 */
public interface Pageable {

    /**
     * 第几页
     *
     * @return
     */
    int getOffset();

    /**
     * 每页条数
     *
     * @return
     */
    int getLimit();

    /**
     * 总条数
     *
     * @return
     */
    long getTotalRows();

}
