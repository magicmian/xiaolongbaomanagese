package wocap.neusoft.com.xiaolongbaomanage.api;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;
import wocap.neusoft.com.xiaolongbaomanage.bean.BaseResponse;
import wocap.neusoft.com.xiaolongbaomanage.bean.GetOrder;
import wocap.neusoft.com.xiaolongbaomanage.bean.LoginBean;
import wocap.neusoft.com.xiaolongbaomanage.bean.ResGetOrder;
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
    @POST("mmanage/order/get_order.do")
    Observable<BaseResponse<ResGetOrder>> getOrder(@Body GetOrder getOrder);

    /**
     * 查找订单
     * @return
     */
    @POST("mmanage/order/search_order.do")
    Observable<BaseResponse<ResGetOrder>> searchOrder(@Body GetOrder getOrder);


    /**
     * 更新订单状态
     * @return
     */
    @POST("mmanage/order/update_order.do")
    Observable<BaseResponse> updateOrder(@Body UpdateOrder getOrder);
}
