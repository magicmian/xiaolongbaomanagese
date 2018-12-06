package wocap.neusoft.com.xiaolongbaomanage.bean;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import wocap.neusoft.com.xiaolongbaomanage.bean.ContactsBean;

/**
 * @author wangmian E-mail:wangmian1994@outlook.com
 * @version 创建时间：2018/11/27 下午2:38
 */
public class ResSearchOrder implements Parcelable {

    /**
     * contacts : [{"createTime":1536291157000,"id":31,"idNumber":"14042519940430001X","idType":1,"name":"王冕","schoolEndStation":"太原","schoolId":"176151551","schoolName":"沈阳大学","schoolStartStation":"大连","schoolTime":"4","updateTime":1536291157000,"userId":22,"userTyp":2}]
     * createTime : 1541140771000
     * customer : 白丽丽,王冕
     * endStation : 北京
     * endTime : 20181023
     * id : 142
     * orderNo : 181102023931041152
     * orderType : 1
     * phone : 17615040413
     * remark : 哈哈
     * seat : 0
     * startStation : 沈阳
     * startTime : 20181012
     * status : 10
     * useaccount : 1
     * userId : 22
     */

    private long createTime;
    private String customer;
    private String endStation;
    private String endTime;
    private int id;
    private long orderNo;
    private String orderType;
    private String phone;
    private String remark;
    private int seat;
    private String startStation;
    private String startTime;
    private int status;
    private int useaccount;
    private int userId;
    private ArrayList<ContactsBean> contacts;

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(long orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUseaccount() {
        return useaccount;
    }

    public void setUseaccount(int useaccount) {
        this.useaccount = useaccount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ArrayList<ContactsBean> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<ContactsBean> contacts) {
        this.contacts = contacts;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.createTime);
        dest.writeString(this.customer);
        dest.writeString(this.endStation);
        dest.writeString(this.endTime);
        dest.writeInt(this.id);
        dest.writeLong(this.orderNo);
        dest.writeString(this.orderType);
        dest.writeString(this.phone);
        dest.writeString(this.remark);
        dest.writeInt(this.seat);
        dest.writeString(this.startStation);
        dest.writeString(this.startTime);
        dest.writeInt(this.status);
        dest.writeInt(this.useaccount);
        dest.writeInt(this.userId);
        dest.writeList(this.contacts);
    }

    public ResSearchOrder() {
    }

    protected ResSearchOrder(Parcel in) {
        this.createTime = in.readLong();
        this.customer = in.readString();
        this.endStation = in.readString();
        this.endTime = in.readString();
        this.id = in.readInt();
        this.orderNo = in.readLong();
        this.orderType = in.readString();
        this.phone = in.readString();
        this.remark = in.readString();
        this.seat = in.readInt();
        this.startStation = in.readString();
        this.startTime = in.readString();
        this.status = in.readInt();
        this.useaccount = in.readInt();
        this.userId = in.readInt();
        this.contacts = new ArrayList<ContactsBean>();
        in.readList(this.contacts, ContactsBean.class.getClassLoader());
    }

    public static final Creator<ResSearchOrder> CREATOR = new Creator<ResSearchOrder>() {
        @Override
        public ResSearchOrder createFromParcel(Parcel source) {
            return new ResSearchOrder(source);
        }

        @Override
        public ResSearchOrder[] newArray(int size) {
            return new ResSearchOrder[size];
        }
    };
}
