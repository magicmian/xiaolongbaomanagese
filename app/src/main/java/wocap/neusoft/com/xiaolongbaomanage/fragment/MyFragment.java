package wocap.neusoft.com.xiaolongbaomanage.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wocap.neusoft.com.xiaolongbaomanage.R;
import wocap.neusoft.com.xiaolongbaomanage.activity.BaseFragment;
import wocap.neusoft.com.xiaolongbaomanage.activity.OrderDetailActivity;
import wocap.neusoft.com.xiaolongbaomanage.activity.PushTitleActivity;

/**
 * @author wangmian E-mail:wangmian1994@outlook.com
 * @version 创建时间：2018/11/29 下午12:31
 */
public class MyFragment extends BaseFragment {
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.rl_push_title)
    RelativeLayout rlPushTitle;

    @Override
    protected int initLayoutId() {
        return R.layout.layout_my_fragment;
    }

    @Override
    protected void initView() {

    }

    @OnClick(R.id.rl_push_title)
    public void pushTutle(){
        Intent intent = new Intent(getActivity(), PushTitleActivity.class);
        startActivity(intent);
    }

    @Override
    protected void initData() {

    }
}
