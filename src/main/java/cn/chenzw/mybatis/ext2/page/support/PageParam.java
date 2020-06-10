package cn.chenzw.mybatis.ext2.page.support;

/**
 * 分页参数
 *
 * @author chenzw
 */
public class PageParam implements Pageable {

    private Integer offset = 0;
    private Integer limit = 25;
    private Long total = -1L;

    public PageParam(Integer offset, Integer limit) {
        this.offset = offset;
        this.limit = limit;
    }

    @Override
    public Integer getOffset() {
        return offset;
    }

    @Override
    public Integer getLimit() {
        return limit;
    }

    @Override
    public Long getTotalRows() {
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
