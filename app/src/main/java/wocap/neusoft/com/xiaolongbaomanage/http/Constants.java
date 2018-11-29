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

    public static final String SUFFIX_AAC = ".aac";
    public static final String SUFFIX_MP3 = ".mp3";
    public static final String SUFFIX_JPG = ".jpg";
    public static final String SUFFIX_WEBP = ".webp";
    public static final String SUFFIX_PNG = ".png";
    public static final String SUFFIX_WEBJ = ".jpg";
    public static final String SUFFIX_WAV = ".wav";
    public static final String SUFFIX_AMR = ".amr";
    public static final String SUFFIX_MP4 = ".mp4";
    public static final String SUFFIX_MOV = ".mov";

    public static final String WAV = "wav";
    public static final String MP3 = "mp3";
    public static final String MP4 = "mp4";
    public static final String MOV = "mov";



    //主页面的根路径
    public static String BASE_HOME_URL= "http://60.205.189.201/douban/";

    public static String VIDEO_EXANPLE = "http://baobab.wdjcdn.com/14564977406580.mp4";
    public static String VIDEO_CACHE = "http://111.198.24.133:83/yyy_login_server/pic/YB059284/97778276040859/1.mp4";
    public static String VIDEO_EXANPLE1 = "http://video.yijiaoxing.com/xixihahahhahaxixi";
    public static String VIDEO_URL = "http://47.89.42.150:8781/";
    public static final String NEW_FRIENDS_USERNAME = "item_new_friends";
    public static final String GROUP_USERNAME = "item_groups";
    public static final String CHAT_ROOM = "item_chatroom";
    public static final String ACCOUNT_REMOVED = "account_removed";
    public static final String ACCOUNT_CONFLICT = "conflict";
    public static final String ACCOUNT_FORBIDDEN = "user_forbidden";
    public static final String CHAT_ROBOT = "item_robots";
    public static final String MESSAGE_ATTR_ROBOT_MSGTYPE = "msgtype";
    public static final String ACTION_GROUP_CHANAGED = "action_group_changed";
    public static final String ACTION_CONTACT_CHANAGED = "action_contact_changed";

    //环信所需要的一些数据
    public static final String MESSAGE_ATTR_IS_VOICE_CALL = "is_voice_call";
    public static final String MESSAGE_ATTR_IS_VIDEO_CALL = "is_video_call";

    public static final String MESSAGE_ATTR_IS_BIG_EXPRESSION = "em_is_big_expression";
    public static final String MESSAGE_ATTR_EXPRESSION_ID = "em_expression_id";

    public static final String MESSAGE_ATTR_AT_MSG = "em_at_list";
    public static final String MESSAGE_ATTR_VALUE_AT_MSG_ALL = "ALL";

    public static final String PAY_LOAD = "wangmian";



    public static final int CHATTYPE_SINGLE = 1;
    public static final int CHATTYPE_GROUP = 2;
    public static final int CHATTYPE_CHATROOM = 3;

    public static final String EXTRA_CHAT_TYPE = "chatType";
    public static final String EXTRA_USER_ID = "userId";
    public final static String EXTRA_SHOW_GIF = "SHOW_GIF";
    public final static String EXTRA_SHOW_VIDEO = "EXTRA_SHOW_VIDEO";
    public final static String EXTRA_IS_ACTIVITY = "isActivity";
}
