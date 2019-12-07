package cn.chenzw.mybatis.ext2.page.support.dialect.db;


import cn.chenzw.mybatis.ext2.page.support.Pageable;
import cn.chenzw.mybatis.ext2.page.support.dialect.AbstractDialect;

/**
 * HSQL
 *
 * @author chenzw
 */
public class HsqlDialect extends AbstractDialect {

    @Override
    public String getPageSql(String sql, Pageable pageable) {
        StringBuilder sqlBuilder = new StringBuilder(sql.length() + 50);
        sqlBuilder.append(sql);
        if (pageable.getLimit() > 0) {
            sqlBuilder.append(" LIMIT ").append(pageable.getLimit());
        }
        if (pageable.getOffset() > 0) {
            int startRow = pageable.getOffset() * pageable.getLimit();
            sqlBuilder.append(" OFFSET ").append(startRow);
        }
        return sqlBuilder.toString();
    }
}
