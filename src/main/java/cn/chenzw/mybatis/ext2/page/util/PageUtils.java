package cn.chenzw.mybatis.ext2.page.util;

import cn.chenzw.mybatis.ext2.page.support.Pageable;

/**
 * 分页计算工具类
 *
 * @author chenzw
 */
public class PageUtils {

    private PageUtils() {
    }


    /**
     * 计算总页数
     *
     * @param pageable
     * @return
     */
    public static int countTotalPage(Pageable pageable) {
        return countTotalPage(pageable.getTotalRows(), pageable.getLimit());
    }

    /**
     * 计算总页数
     *
     * @param totalCount 总记录数
     * @param pageSize   每页记录数
     * @return 总页数
     */
    public static int countTotalPage(final long totalCount, final int pageSize) {
        if (totalCount == 0) {
            return 1;
        }
        if (totalCount % pageSize == 0) {
            // 刚好整除
            return (int) (totalCount / pageSize);
        } else {
            // 不能整除则总页数 + 1
            return (int) (totalCount / pageSize + 1);
        }
    }

    /**
     * 计算当前分页的起始记录索引
     *
     * @param offset   当前第几页
     * @param pageSize 每页记录数
     * @return 当前页的起始记录索引
     */
    public static int countStartOffset(final int offset, final int pageSize) {
        return (offset - 1) * pageSize;
    }

    /**
     * 计算当前分页的起始记录索引
     *
     * @param pageable
     * @return
     */
    public static int countStartOffset(Pageable pageable) {
        return countStartOffset(pageable.getOffset(), pageable.getLimit());
    }
}
