package wocap.neusoft.com.xiaolongbaomanage.activity;

import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;


import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import wocap.neusoft.com.xiaolongbaomanage.MainApp;
import wocap.neusoft.com.xiaolongbaomanage.util.AndroidTool;

import static android.app.ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND;

public abstract class BaseActivity extends AppCompatActivity {
    ProgressDialog mProgress = null;

    protected LayoutInflater layoutInflater;

    protected MainApp app;

    protected Context mContext;
    protected Unbinder mUnbinder;

    private boolean isActive = false;
    private InputMethodManager mInputMethodManager;

    protected abstract int initLayoutId();

    protected abstract void initView();

    protected abstract void initData();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        stateBarInit();
        layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        setContentView(initLayoutId());
        ButterKnife.bind(this);
        mContext = this;
        initView();
        initData();
        initReveiver();
    }

    private void initReveiver() {
        IntentFilter netfilter = new IntentFilter();
        netfilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(NetChangeReciver,netfilter);
    }

    private BroadcastReceiver NetChangeReciver = new  BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkinfo = connectivityManager.getActiveNetworkInfo();
            if ((networkinfo != null) && (networkinfo.isAvailable())) {
                //暂时未定，本来想写登录的，但是好像这里不需要，如果后续加即时通讯的时候在进行改造
            }
        }
    };
    @Override
    protected void onDestroy() {
        unregisterReceiver(NetChangeReciver);
        super.onDestroy();
    }

    public void showProgress(int res) {
        showProgress(getString(res));
    }

    public void showProgress(String text) {
        if(mProgress == null)
            mProgress = new ProgressDialog(this);
        mProgress.setMessage(text);
        mProgress.show();

    }

    public void hideProgress() {
        if(mProgress != null)
            mProgress.dismiss();
    }
    protected void startActivity(Class activity) {
        startActivity(activity, false);
    }

    protected void startActivity(Class activity, String key, String extra) {
        Intent intent = new Intent(this, activity);
        intent.putExtra(key, extra);
        startActivity(intent);
    }

    protected void startActivity(Class activity, boolean finish) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
        if (finish) {
            finish();
        }
    }
    protected void toast(String msg) {
        AndroidTool.showToast(this,msg);
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
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4 全透明状态栏
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }
    public void setMarquee(TextView textView, int times){
        textView.setSingleLine();
        textView.setFocusable(true);
        textView.setSelected(true);
        textView.setFocusableInTouchMode(true);
        textView.setMarqueeRepeatLimit(times);
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
    }
    protected void hideKeyBoard() {
        if (mInputMethodManager == null) {
            mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        }
        mInputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }


public  boolean isForeground() {
    ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
    List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();
    if(runningAppProcesses == null){
        return false;
    }
    for(ActivityManager.RunningAppProcessInfo info:runningAppProcesses){
        if(info.processName.equals(getPackageName()) && (info.importance == IMPORTANCE_FOREGROUND)){
            return true;
        }
    }
    return false;
}
@Override
protected void onResume() {
    super.onResume();
    isActive = true;
}


}
