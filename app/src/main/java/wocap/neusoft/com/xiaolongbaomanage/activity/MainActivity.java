package wocap.neusoft.com.xiaolongbaomanage.activity;

import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import butterknife.BindView;
import wocap.neusoft.com.xiaolongbaomanage.R;
import wocap.neusoft.com.xiaolongbaomanage.fragment.HomeFragment;
import wocap.neusoft.com.xiaolongbaomanage.fragment.MyFragment;
import wocap.neusoft.com.xiaolongbaomanage.fragment.SearchFragment;

public class MainActivity extends BaseActivity {


    @BindView(android.R.id.tabcontent)
    FrameLayout tabcontent;
    @BindView(R.id.real_content)
    FrameLayout realContent;
    @BindView(android.R.id.tabs)
    TabWidget tabs;
    @BindView(R.id.main_layout)
    wocap.neusoft.com.xiaolongbaomanage.customeView.FragmentTabHost tabHost;
    private HomeFragment homeFragment;
    private SearchFragment searchFragment;
    private MyFragment myFragment;
    protected Class[] classTab = {HomeFragment.class, SearchFragment.class, MyFragment.class};
    protected int[] ids = {R.drawable.tab_menu_home_icon, R.drawable.tab_menu_search_icon, R.drawable.tab_menu_my_icon};
    private boolean isBackPressed;
    String[] tabTag, tabTitle;
    TextView txt_count;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        tabTag = getResources().getStringArray(R.array.tabTag);
        tabTitle = getResources().getStringArray(R.array.tabTitle);
        tabHost.setup(this, getSupportFragmentManager(), R.id.real_content);
        for (int i = 0; i < tabTag.length; i++) {
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(tabTag[i]);
            tabSpec.setIndicator(buildIndicator(i));
            tabHost.addTab(tabSpec, classTab[i], null);
        }
        tabHost.setCurrentTab(0);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {

            }
        });
    }

        protected View buildIndicator(int position) {
        View view = layoutInflater.inflate(R.layout.tab_indicator, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.icon_tab);
        TextView textView = (TextView) view.findViewById(R.id.text_indicator);
        if (position == 1) {
            txt_count = (TextView) view.findViewById(R.id.txt_count);
        }
        imageView.setImageResource(ids[position]);
        textView.setText(tabTitle[position]);
        return view;
    }


    @Override
    protected void initData() {

    }




    @Override
    public void onBackPressed() {
        if (isBackPressed) {
            super.onBackPressed();
            return;
        }

        isBackPressed = true;


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isBackPressed = false;
            }
        }, 2000);
    }


}
