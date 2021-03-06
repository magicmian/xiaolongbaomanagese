package wocap.neusoft.com.xiaolongbaomanage.api;

import java.util.ArrayList;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;
import wocap.neusoft.com.xiaolongbaomanage.bean.BaseResponse;
import wocap.neusoft.com.xiaolongbaomanage.bean.GetOrder;
import wocap.neusoft.com.xiaolongbaomanage.bean.LoginBean;
import wocap.neusoft.com.xiaolongbaomanage.bean.PushTitle;
import wocap.neusoft.com.xiaolongbaomanage.bean.ResGetOrder;
import wocap.neusoft.com.xiaolongbaomanage.bean.ResSearchOrder;
import wocap.neusoft.com.xiaolongbaomanage.bean.Reslogin;
import wocap.neusoft.com.xiaolongbaomanage.bean.UpdateOrder;

/**
 * @author wangmian E-mail:wangmian1994@outlook.com
 * @version 创建时间：2018/11/26 下午12:39
 */
public interface xiaolongbaoapi {
    /**
     * 登陆
     *
     * @return
     */
    @POST("manage/user/login.do")
    Observable<BaseResponse<Reslogin>> login(@Body LoginBean bean);
    /**
     * 获取订单
     * @return
     */
    @POST("manage/order/get_order.do")
    Observable<BaseResponse<ResGetOrder>> getOrder(@Body GetOrder getOrder);
    /**
     * 查找订单
     * @return
     */
    @POST("manage/order/search_order.do")
    Observable<BaseResponse<ArrayList<ResSearchOrder>>> searchOrder(@Body GetOrder getOrder);

    /**
     * 更新订单状态
     * @return
     */
    @POST("manage/order/update_order.do")
    Observable<BaseResponse> updateOrder(@Body UpdateOrder getOrder);


    @POST("info/save_float_title.do")
    Observable<BaseResponse> pushInfo(@Body PushTitle pushTitle);

}
