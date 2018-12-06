package wocap.neusoft.com.xiaolongbaomanage.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author wangmian E-mail:wangmian1994@outlook.com
 * @version 创建时间：2018/11/27 下午2:38
 */
public class ContactsBean implements Parcelable {
    /**
     * createTime : 1536291157000
     * id : 31
     * idNumber : 14042519940430001X
     * idType : 1
     * name : 王冕
     * schoolEndStation : 太原
     * schoolId : 176151551
     * schoolName : 沈阳大学
     * schoolStartStation : 大连
     * schoolTime : 4
     * updateTime : 1536291157000
     * userId : 22
     * userTyp : 2
     */

    private long createTime;
    private int id;
    private String idNumber;
    private int idType;
    private String name;
    private String schoolEndStation;
    private String schoolId;
    private String schoolName;
    private String schoolStartStation;
    private String schoolTime;
    private long updateTime;
    private int userId;
    private int userTyp;

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolEndStation() {
        return schoolEndStation;
    }

    public void setSchoolEndStation(String schoolEndStation) {
        this.schoolEndStation = schoolEndStation;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolStartStation() {
        return schoolStartStation;
    }

    public void setSchoolStartStation(String schoolStartStation) {
        this.schoolStartStation = schoolStartStation;
    }

    public String getSchoolTime() {
        return schoolTime;
    }

    public void setSchoolTime(String schoolTime) {
        this.schoolTime = schoolTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserTyp() {
        return userTyp;
    }

    public void setUserTyp(int userTyp) {
        this.userTyp = userTyp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.createTime);
        dest.writeInt(this.id);
        dest.writeString(this.idNumber);
        dest.writeInt(this.idType);
        dest.writeString(this.name);
        dest.writeString(this.schoolEndStation);
        dest.writeString(this.schoolId);
        dest.writeString(this.schoolName);
        dest.writeString(this.schoolStartStation);
        dest.writeString(this.schoolTime);
        dest.writeLong(this.updateTime);
        dest.writeInt(this.userId);
        dest.writeInt(this.userTyp);
    }

    public ContactsBean() {
    }

    protected ContactsBean(Parcel in) {
        this.createTime = in.readLong();
        this.id = in.readInt();
        this.idNumber = in.readString();
        this.idType = in.readInt();
        this.name = in.readString();
        this.schoolEndStation = in.readString();
        this.schoolId = in.readString();
        this.schoolName = in.readString();
        this.schoolStartStation = in.readString();
        this.schoolTime = in.readString();
        this.updateTime = in.readLong();
        this.userId = in.readInt();
        this.userTyp = in.readInt();
    }

    public static final Creator<ContactsBean> CREATOR = new Creator<ContactsBean>() {
        @Override
        public ContactsBean createFromParcel(Parcel source) {
            return new ContactsBean(source);
        }

        @Override
        public ContactsBean[] newArray(int size) {
            return new ContactsBean[size];
        }
    };
}
