package wocap.neusoft.com.xiaolongbaomanage.bean;

/**
 * @author wangmian E-mail:wangmian1994@outlook.com
 * @version 创建时间：2018/11/27 下午2:35
 */
public class GetOrder {

    /**
     * startTime : 20181101
     * endTime : 20181101
     * status : 10
     */

    private String startTime;
    private String endTime;
    private int status;
    private String custname;
    private int pageNum = 1;
    private int pageSize = 10;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
