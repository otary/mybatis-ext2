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

    public void withDefault(int offset, int limit) {
        if(this.offset == null) {
            this.offset = offset;
        }
        if (this.limit == null) {
            this.limit = limit;
        }
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
