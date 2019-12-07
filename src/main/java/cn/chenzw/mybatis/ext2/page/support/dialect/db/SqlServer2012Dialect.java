package cn.chenzw.mybatis.ext2.page.support.dialect.db;


import cn.chenzw.mybatis.ext2.page.support.Pageable;
import cn.chenzw.mybatis.ext2.page.support.dialect.AbstractDialect;

public class SqlServer2012Dialect extends AbstractDialect {

    @Override
    public String getPageSql(String sql, Pageable pageable) {
        int startRow = pageable.getLimit() * pageable.getOffset();
        StringBuilder sqlBuilder = new StringBuilder(sql.length() + 120);
        sqlBuilder.append(sql).append(" OFFSET ").append(startRow).append(" ROWS FETCH NEXT ").append(pageable.getLimit()).append(" ROWS ONLY ");
        return sqlBuilder.toString();
    }
}
