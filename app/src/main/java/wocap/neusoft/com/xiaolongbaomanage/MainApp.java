package wocap.neusoft.com.xiaolongbaomanage;

import android.app.Application;
import android.content.Context;
import com.orhanobut.logger.Logger;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import wocap.neusoft.com.xiaolongbaomanage.event.CanInitEvent;
import wocap.neusoft.com.xiaolongbaomanage.event.CanStartApp;

/**
 * @author wangmian E-mail:wangmian1994@outlook.com
 * @version 创建时间：2018/11/27 下午3:16
 */
public class MainApp extends Application {
    private static MainApp mInstance;

    private static Context context = null;

    public static MainApp getInstance() {
        return mInstance;
    }

    @Override

    public void onCreate() {

        super.onCreate();

        //下面这一条的效果是完全一样的
        //修改默认实现的配置，记住，必须在第一次EventBus.getDefault()之前配置，且只能设置一次。建议在application.onCreate()调用
        EventBus.builder().throwSubscriberException(BuildConfig.DEBUG).installDefaultEventBus();
        EventBus.getDefault().register(this);

        //这里不要放任何的第三方包初始化，这里采用了eventbus双重循环，保证第一时间进入欢迎页面，同时初始化第三方包，app快速启动的一种实现方式

    }

    /**
     *
     * 请注意，所有的第三方包初始化全都放在这里，以后如果用到内部数据需要提前准备的也全放到这里
     */
    private void init() {
        mInstance = this;
        context = getApplicationContext();
        Logger.init("xiaolongbao")
                .methodCount(3)
                .hideThreadInfo();
        //初始化音乐加载
        EventBus.getDefault().post(new CanStartApp());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCanInitEvent(CanInitEvent event) {
        init();

    };
}
