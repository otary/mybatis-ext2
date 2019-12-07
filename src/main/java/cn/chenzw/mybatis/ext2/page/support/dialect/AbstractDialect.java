package cn.chenzw.mybatis.ext2.page.support.dialect;

/**
 * @author chenzw
 */
public abstract class AbstractDialect implements Dialect {

    @Override
    public String getCountSql(String sql) {
        return getSimpleCountSql(sql, "0");
    }

    public String getSimpleCountSql(final String sql, String name) {
        StringBuilder stringBuilder = new StringBuilder(sql.length() + 40);
        stringBuilder.append("select count(");
        stringBuilder.append(name);
        stringBuilder.append(") from (");
        stringBuilder.append(sql);
        stringBuilder.append(") tmp_count");
        return stringBuilder.toString();
    }

}
