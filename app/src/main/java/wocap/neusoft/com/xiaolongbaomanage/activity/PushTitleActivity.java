package wocap.neusoft.com.xiaolongbaomanage.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wocap.neusoft.com.xiaolongbaomanage.R;
import wocap.neusoft.com.xiaolongbaomanage.bean.BaseResponse;
import wocap.neusoft.com.xiaolongbaomanage.bean.PushTitle;
import wocap.neusoft.com.xiaolongbaomanage.presenter.pView.NormalView;
import wocap.neusoft.com.xiaolongbaomanage.presenter.pushTitlePresenter;
import wocap.neusoft.com.xiaolongbaomanage.util.AndroidTool;

public class PushTitleActivity extends BaseActivity {

    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.edt_feedback_content)
    EditText edtFeedbackContent;
    private pushTitlePresenter pushTitlePresenter;
    private NormalView<BaseResponse> resultView;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_push_title;
    }

    @Override
    protected void initView() {
        pushTitlePresenter = new pushTitlePresenter(PushTitleActivity.this);
        pushTitlePresenter.onCreate();
        resultView = new NormalView<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse object) {
                if (object.status == 200) {
                    AndroidTool.showToast(PushTitleActivity.this, "发送成功");
                } else {
                    AndroidTool.showToast(PushTitleActivity.this, "发送失败");
                }
            }

            @Override
            public void onError(String result) {
                AndroidTool.showToast(PushTitleActivity.this, "发送失败");
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onNoMore() {

            }
        };
        pushTitlePresenter.attachView(resultView);

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.iv_setting)
    public void push(){
        if(!TextUtils.isEmpty(edtFeedbackContent.getText().toString())){
            PushTitle pushTitle = new PushTitle();
            pushTitle.floatTitle = edtFeedbackContent.getText().toString();
            pushTitlePresenter.pushtitle(pushTitle);
        }
    }


}
