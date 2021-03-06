package wocap.neusoft.com.xiaolongbaomanage.presenter;

import android.content.Context;
import android.content.Intent;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import wocap.neusoft.com.xiaolongbaomanage.bean.BaseResponse;
import wocap.neusoft.com.xiaolongbaomanage.bean.GetOrder;
import wocap.neusoft.com.xiaolongbaomanage.bean.ResGetOrder;
import wocap.neusoft.com.xiaolongbaomanage.bean.ResSearchOrder;
import wocap.neusoft.com.xiaolongbaomanage.http.BaseSubscriber;
import wocap.neusoft.com.xiaolongbaomanage.manage.DataManager;
import wocap.neusoft.com.xiaolongbaomanage.presenter.pView.BaseView;
import wocap.neusoft.com.xiaolongbaomanage.presenter.pView.NormalView;

/**
 * Created by wangmian on 2017/4/28.
 * you can contact me with wangmian1994@outlook.com
 */

public class getOrderPresenter implements Presenter {
    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private NormalView<BaseResponse<ResGetOrder>> resultView;
    private BaseResponse<ResGetOrder> result;

    public getOrderPresenter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onCreate() {
        manager = new DataManager(mContext);
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {
        resultView.onStart();
    }

    @Override
    public void onStop() {
        if (mCompositeSubscription.hasSubscriptions()){
            mCompositeSubscription.unsubscribe();
            Logger.w("onStop");
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void attachView(BaseView view) {
        resultView = (NormalView<BaseResponse<ResGetOrder>>) view;
    }
    @Override
    public void attachIncomingIntent(Intent intent) {

    }


    public void getOrder(GetOrder order) {
        onStart();
        mCompositeSubscription.add(manager.getOrder(order)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<BaseResponse<ResGetOrder>>(mContext) {
                    @Override
                    public void onCompleted() {
                        if (resultView != null ) {
                            resultView.onSuccess(result);
                            Logger.w("oncomplete");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        resultView.onError("请求失败！！");
                        Logger.w("onerror");
                    }

                    @Override
                    public void onNext(BaseResponse<ResGetOrder> book) {
                        result = book;
                    }
                })
        );
    }
}
