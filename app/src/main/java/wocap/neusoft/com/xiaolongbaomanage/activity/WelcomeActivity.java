package wocap.neusoft.com.xiaolongbaomanage.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;
import wocap.neusoft.com.xiaolongbaomanage.R;
import wocap.neusoft.com.xiaolongbaomanage.bean.BaseResponse;
import wocap.neusoft.com.xiaolongbaomanage.bean.Reslogin;
import wocap.neusoft.com.xiaolongbaomanage.event.CanInitEvent;
import wocap.neusoft.com.xiaolongbaomanage.event.CanStartApp;
import wocap.neusoft.com.xiaolongbaomanage.presenter.LoginPresenter;
import wocap.neusoft.com.xiaolongbaomanage.presenter.pView.NormalView;
import wocap.neusoft.com.xiaolongbaomanage.util.AndroidTool;

/**
 * @author wangmian E-mail:wangmian1994@outlook.com
 * @version 创建时间：2018/11/27 下午3:21
 */
@RuntimePermissions
public class WelcomeActivity extends AppCompatActivity {
    boolean isForGround = true;
    boolean canGo = false;
    ImageView background;
    View decorView;
    private LoginPresenter loginPresenter;
    private boolean isLogin = false;
    private NormalView<BaseResponse<Reslogin>> thumb_starNormalView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        stateBarInit();
        setContentView(R.layout.activity_welcome);
        background = findViewById(R.id.background);
        //welco
        WelcomeActivityPermissionsDispatcher.initeventWithCheck(this);
        Glide.with(this).load(R.mipmap.welcome).into(background);
        loginPresenter = new LoginPresenter(this);
        thumb_starNormalView = new NormalView<BaseResponse<Reslogin>>() {
            @Override
            public void onSuccess(BaseResponse<Reslogin> object) {
                if(object.status == 200){
                    isLogin = true;
                    if(isForGround){
                        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        canGo = true;
                    }
                }
            }

            @Override
            public void onError(String result) {

            }

            @Override
            public void onStart() {

            }

            @Override
            public void onNoMore() {

            }
        };
        loginPresenter.onCreate();
        loginPresenter.attachView(thumb_starNormalView);
        loginPresenter.login();
    }

    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE})
    public void initevent() {
        isForGround = true;
        EventBus.getDefault().post(new CanInitEvent());
    }

    @OnPermissionDenied({Manifest.permission.READ_EXTERNAL_STORAGE})
    public void showToase(){
        canGo = false;
        AndroidTool.showToast(this,"您必须提供必须的权限才能使用本app");
        finish();
    }

    //用于改变标题颜色状态栏颜色，各机型不一致，
    private void stateBarInit() {
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//           getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//           getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//           getWindow().setStatusBarColor(Color.TRANSPARENT);
//
//        } else
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0 全透明状态栏
            decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4 全透明状态栏
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onCanStartApp(CanStartApp event){
        if(isForGround&&isLogin){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            canGo = true;
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        WelcomeActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @Override
    protected void onDestroy() {
        Logger.w("onDestroy");
        EventBus.getDefault().unregister(this);
        background = null;
        decorView = null;
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Logger.w("onStart");
        isForGround = true;
        if(canGo&&isLogin){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    protected void onPause() {
        isForGround = false;
        Logger.w("onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Logger.w("onStop");
        super.onStop();

    }
}
