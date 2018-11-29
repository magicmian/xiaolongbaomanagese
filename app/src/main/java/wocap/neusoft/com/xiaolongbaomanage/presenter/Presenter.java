package wocap.neusoft.com.xiaolongbaomanage.presenter;

import android.content.Intent;

import wocap.neusoft.com.xiaolongbaomanage.presenter.pView.BaseView;


/**
 * Created by wangmian on 2017/4/28.
 * you can contact me with wangmian1994@outlook.com
 */

public interface Presenter {
    void onCreate();

    void onStart();//暂时没用到

    void onStop();

    void pause();//暂时没用到

    void attachView(BaseView view);

    void attachIncomingIntent(Intent intent);//暂时没用到
}
