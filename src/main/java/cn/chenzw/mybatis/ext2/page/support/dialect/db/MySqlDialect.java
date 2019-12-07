package cn.chenzw.mybatis.ext2.page.support.dialect.db;


import cn.chenzw.mybatis.ext2.page.support.Pageable;
import cn.chenzw.mybatis.ext2.page.support.dialect.AbstractDialect;

/**
 * @author chenzw
 */
public class MySqlDialect extends AbstractDialect {

    @Override
    public String getPageSql(String sql, Pageable pageable) {
        int startRow = pageable.getLimit() * pageable.getOffset();
        StringBuilder sqlBuilder = new StringBuilder(sql.length() + 14);
        sqlBuilder.append(sql);
        if (startRow > 0) {
            sqlBuilder.append(" LIMIT ").append(startRow).append(",").append(pageable.getLimit());
        } else {
            sqlBuilder.append(" LIMIT ").append(pageable.getLimit());
        }
        return sqlBuilder.toString();
    }

}
