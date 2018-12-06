package wocap.neusoft.com.xiaolongbaomanage.http;

/**
 * Created by wangm on 2017/2/22.
 */

public class Constants {
    //返回成功
    public final  static int WEB_RESP_CODE_SUCCESS = 0000;
    //token失效
    public final  static int TOKEN_EXPRIED = 1111;

    public static class ErrorCode {
        public static final int USER_ALREADY_EXIST = 202;
        public static final int USER_PASSWORD_ERROR = 101;
    }
    public static class Extra {
        public static final String USER_NAME = "user_name";
        public static final String REASION = "user_name";
    }
    public static class Database {
        public static final String DATABASE_NAME = "im_db";
    }

    public static String DOUBAN_NAME = "DOUBAN_NAME";
    public static String DOUBAN_PASS = "DOUBAN_PASS";
    public static String BASE_URL = "http://m.xiaolongbaotech.cn/" ;

    public enum CUstomerType{
        ADULT(1,"成人"),
        STUDENT(2,"学生"),
        CHLDREN(3,"儿童");

        private int code;
        private String value;

        CUstomerType(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public static CUstomerType codeOf(int code){
            for(CUstomerType orderStatusEnum : values()){
                if(orderStatusEnum.getCode() == code){
                    return orderStatusEnum;
                }
            }
            throw new RuntimeException("么有找到对应的枚举");
        }
    }


    public enum CardTypeEnum{
        ERDAI(1,"二代身份证"),
        GAOAO(2,"港澳通行证"),
        TAIWAN(3,"台湾通行证"),
        HUZHAO(4,"护照");

        private int code;
        private String value;

        CardTypeEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public static CardTypeEnum codeOf(int code){
            for(CardTypeEnum orderStatusEnum : values()){
                if(orderStatusEnum.getCode() == code){
                    return orderStatusEnum;
                }
            }
            throw new RuntimeException("么有找到对应的枚举");
        }
    }


    public enum SeatEnum{
        BUSSINESSSEAT(0,"商务特等座"),
        FIRSTRATESEAT(10,"一等座"),
        SECONDRATESEAT(20,"二等座"),
        TOPSOFTSLEEP(30,"高级软卧"),
        SOFTSLEEP(40,"软卧"),
        DSOFTSLEEP(50,"动卧"),
        TOUGHSLEEP(60,"硬卧"),
        SOFTSEAT(70,"软座"),
        TOUGHSEAT(80,"硬座"),
        NOSEAT(90,"无座")
        ;

        SeatEnum(int code ,String value) {
            this.value = value;
            this.code = code;
        }

        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
        public static SeatEnum codeOf(int code){
            for(SeatEnum orderStatusEnum : values()){
                if(orderStatusEnum.getCode() == code){
                    return orderStatusEnum;
                }
            }
            throw new RuntimeException("么有找到对应的枚举");
        }

    }


    public enum OrderStatusEnum{
        CANCELED(0,"已取消"),
        NO_PAY(10,"未处理"),
        PAID(20,"刷票中"),
        SHIPPED(40,"已出票"),
        ORDER_SUCCESS(50,"交易成功"),
        ORDER_CLOSE(60,"交易关闭");


        OrderStatusEnum(int code,String value){
            this.code = code;
            this.value = value;
        }
        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }

        public static OrderStatusEnum codeOf(int code){
            for(OrderStatusEnum orderStatusEnum : values()){
                if(orderStatusEnum.getCode() == code){
                    return orderStatusEnum;
                }
            }
            throw new RuntimeException("么有找到对应的枚举");
        }
    }
    public interface  AlipayCallback{
        String TRADE_STATUS_WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
        String TRADE_STATUS_TRADE_SUCCESS = "TRADE_SUCCESS";

        String RESPONSE_SUCCESS = "success";
        String RESPONSE_FAILED = "failed";
    }



    public enum PayPlatformEnum{
        ALIPAY(1,"支付宝");

        PayPlatformEnum(int code,String value){
            this.code = code;
            this.value = value;
        }
        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }

    public enum ifUseUserAccount{
        USE(0,"使用客户自己的账号"),
        NOUSE(1,"不适用客户的账号");

        ifUseUserAccount(int code,String value){
            this.code = code;
            this.value = value;
        }
        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }

    public enum PaymentTypeEnum{
        ONLINE_PAY(1,"在线支付");

        PaymentTypeEnum(int code,String value){
            this.code = code;
            this.value = value;
        }
        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }


        public static PaymentTypeEnum codeOf(int code){
            for(PaymentTypeEnum paymentTypeEnum : values()){
                if(paymentTypeEnum.getCode() == code){
                    return paymentTypeEnum;
                }
            }
            throw new RuntimeException("么有找到对应的枚举");
        }

    }
}
