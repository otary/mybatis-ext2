package cn.chenzw.mybatis.ext2.page.plugin;

import cn.chenzw.mybatis.ext2.page.exception.PageException;
import cn.chenzw.mybatis.ext2.page.support.Pageable;
import cn.chenzw.mybatis.ext2.page.support.dialect.Dialect;
import cn.chenzw.mybatis.ext2.page.support.dialect.factory.DialectFactory;
import cn.chenzw.toolkit.commons.ReflectExtUtils;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

/**
 * 分页插件
 *
 * @author chenzw
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class PagePlugin implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
        BaseStatementHandler delegate = (BaseStatementHandler) ReflectExtUtils.getFieldValue(handler, "delegate");

        BoundSql boundSql = delegate.getBoundSql();
        Optional<Pageable> pageableOptional = getPageableParameter(boundSql.getParameterObject());
        if (!pageableOptional.isPresent()) {
            return invocation.proceed();
        }

        // 存在Pageable参数
        Pageable pageable = pageableOptional.get();

        Connection connection = (Connection) invocation.getArgs()[0];
        Dialect dialect = DialectFactory.getDialect(connection.getMetaData().getURL());
        String sql = boundSql.getSql();

        // 添加分页参数
        ReflectExtUtils.setFieldValue(boundSql, "sql", dialect.getPageSql(sql, pageable));

        // 计算并添加总数
        long total = countTotal(dialect.getCountSql(sql), connection, delegate.getParameterHandler());
        ReflectExtUtils.setFieldValue(pageable, "total", total);


        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }


    /**
     * 获取Pageable参数
     *
     * @param parameter
     * @return
     */
    private Optional<Pageable> getPageableParameter(Object parameter) {
        if (parameter != null) {
            if (parameter instanceof Map) {
                Map paramMap = (Map) parameter;
                for (Object param : paramMap.values()) {
                    if (param instanceof Pageable) {
                        return Optional.ofNullable((Pageable) param);
                    }
                }
            }
            if (parameter instanceof Pageable) {
                return Optional.ofNullable((Pageable) parameter);
            }
        }
        return Optional.empty();
    }


    /**
     * 计算总行数
     *
     * @param countSql
     * @param connection
     * @param ph
     * @return
     */
    private long countTotal(String countSql, Connection connection, ParameterHandler ph) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement(countSql);
            ph.setParameters(ps);
            ps.execute();
            rs = ps.getResultSet();
            rs.next();

            return rs.getLong(1);
        } catch (SQLException e) {
            throw new PageException("Execute [" + countSql + "] with exception!", e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
