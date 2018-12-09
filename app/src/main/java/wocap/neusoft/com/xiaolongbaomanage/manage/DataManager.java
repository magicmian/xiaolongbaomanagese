package wocap.neusoft.com.xiaolongbaomanage.manage;

import android.content.Context;


import java.util.ArrayList;

import rx.Observable;
import wocap.neusoft.com.xiaolongbaomanage.api.xiaolongbaoapi;
import wocap.neusoft.com.xiaolongbaomanage.bean.BaseResponse;
import wocap.neusoft.com.xiaolongbaomanage.bean.GetOrder;
import wocap.neusoft.com.xiaolongbaomanage.bean.LoginBean;
import wocap.neusoft.com.xiaolongbaomanage.bean.PushTitle;
import wocap.neusoft.com.xiaolongbaomanage.bean.ResGetOrder;
import wocap.neusoft.com.xiaolongbaomanage.bean.ResSearchOrder;
import wocap.neusoft.com.xiaolongbaomanage.bean.Reslogin;
import wocap.neusoft.com.xiaolongbaomanage.bean.UpdateOrder;

/**
 * Created by wangmian on 2017/4/28.
 * you can contact me with wangmian1994@outlook.com
 */

public class DataManager {

    private xiaolongbaoapi xiaolongbaoApi;
    public DataManager(Context context){
        this.xiaolongbaoApi =  RetrofitHelper.getInstance(context).getDoubanapi();
    }

    public Observable<BaseResponse<Reslogin>> login(LoginBean bean){
        return xiaolongbaoApi.login(bean);
    }
    public Observable<BaseResponse<ResGetOrder>> getOrder(GetOrder bean){
        return xiaolongbaoApi.getOrder(bean);
    }

    public Observable<BaseResponse<ArrayList<ResSearchOrder>>> searchOrder(GetOrder bean){
        return xiaolongbaoApi.searchOrder(bean);
    }
    public Observable<BaseResponse> update(UpdateOrder bean){
        return xiaolongbaoApi.updateOrder(bean);
    }

    public Observable<BaseResponse> pushtitle(PushTitle bean){
        return xiaolongbaoApi.pushInfo(bean);
    }
}
