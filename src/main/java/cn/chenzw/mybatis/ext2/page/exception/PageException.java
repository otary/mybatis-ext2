package cn.chenzw.mybatis.ext2.page.exception;

/**
 * @author chenzw
 */
public class PageException extends RuntimeException {

    public PageException(String message) {
        super(message);
    }

    public PageException(String message, Throwable cause) {
        super(message, cause);
    }

}
