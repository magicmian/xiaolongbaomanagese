package wocap.neusoft.com.xiaolongbaomanage.bean;

/**
 * @author wangmian E-mail:wangmian1994@outlook.com
 * @version 创建时间：2018/11/27 下午2:31
 */
public class BaseResponse<T> {
    public String msg;
    public int status;
    public T data;
}
