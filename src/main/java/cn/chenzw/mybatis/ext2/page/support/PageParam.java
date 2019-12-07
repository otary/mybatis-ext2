package cn.chenzw.mybatis.ext2.page.support;

/**
 * 分页参数
 *
 * @author chenzw
 */
public class PageParam implements Pageable {

    private int offset = 0;
    private int limit = 25;
    private long total = -1;

    public PageParam(int offset, int limit) {
        this.offset = offset;
        this.limit = limit;
    }

    @Override
    public int getOffset() {
        return offset;
    }

    @Override
    public int getLimit() {
        return limit;
    }

    @Override
    public long getTotalRows() {
        return total;
    }

    @Override
    public String toString() {
        return "PageParam{" +
                "offset=" + offset +
                ", limit=" + limit +
                ", total=" + total +
                '}';
    }
}
