package wocap.neusoft.com.xiaolongbaomanage.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wocap.neusoft.com.xiaolongbaomanage.R;
import wocap.neusoft.com.xiaolongbaomanage.fragment.HomeFragment;
import wocap.neusoft.com.xiaolongbaomanage.fragment.MyFragment;
import wocap.neusoft.com.xiaolongbaomanage.fragment.SearchFragment;
import wocap.neusoft.com.xiaolongbaomanage.util.SnackBarUtil;

public class MainActivity extends BaseActivity {


    @BindView(R.id.main_fragment_container)
    FrameLayout mainFragmentContainer;
    @BindView(R.id.main_home_icon)
    ImageView mainHomeIcon;
    @BindView(R.id.main_home)
    TextView mainHome;
    @BindView(R.id.home_home)
    LinearLayout homeHome;
    @BindView(R.id.main_search_icon)
    ImageView mainSearchIcon;
    @BindView(R.id.main_search)
    TextView mainSearch;
    @BindView(R.id.home_search)
    LinearLayout homeSearch;
    @BindView(R.id.main_my_icon)
    ImageView mainMyIcon;
    @BindView(R.id.main_my)
    TextView mainMy;
    @BindView(R.id.home_my)
    LinearLayout homeMy;
    @BindView(R.id.main_layout)
    LinearLayout mainLayout;
    private HomeFragment homeFragment;
    private SearchFragment searchFragment;
    private MyFragment myFragment;

    private boolean isBackPressed;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.home_home, R.id.home_search, R.id.home_my})
    public void onClickEdit(View v) {
        switch (v.getId()) {
            case R.id.home_home:
                setSelected();
                mainHome.setSelected(true);
                mainHomeIcon.setSelected(true);
                homeFragment = new HomeFragment();
                replaceFragment(homeFragment, "home");
                break;
            case R.id.home_search:
                setSelected();
                mainSearch.setSelected(true);
                mainSearchIcon.setSelected(true);
                searchFragment = new SearchFragment();
                replaceFragment(searchFragment, "search");
                break;
            case R.id.home_my:
                setSelected();
                mainMy.setSelected(true);
                mainMyIcon.setSelected(true);
                myFragment = new MyFragment();
                replaceFragment(myFragment, "order");
                break;
        }
    }

    private void replaceFragment(BaseFragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fragment_container, fragment, tag);
        transaction.commit();
    }

    //重置所有文本的选中状态
    private void setSelected() {
        mainHome.setSelected(false);
        mainHomeIcon.setSelected(false);
        mainSearchIcon.setSelected(false);
        mainSearch.setSelected(false);
        mainMyIcon.setSelected(false);
        mainMy.setSelected(false);
    }
    @Override
    public void onBackPressed() {
        if (isBackPressed) {
            super.onBackPressed();
            return;
        }

        isBackPressed = true;

        SnackBarUtil.show(mainFragmentContainer, R.string.back_pressed_tip);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isBackPressed = false;
            }
        }, 2000);
    }

}
