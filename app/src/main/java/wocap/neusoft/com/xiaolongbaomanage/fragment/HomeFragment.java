package wocap.neusoft.com.xiaolongbaomanage.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.leo.lu.llrecyclerview.ui.header.RentalsSunHeaderView;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrClassicDefaultFooter;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler2;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;
import wocap.neusoft.com.xiaolongbaomanage.R;
import wocap.neusoft.com.xiaolongbaomanage.activity.BaseFragment;
import wocap.neusoft.com.xiaolongbaomanage.bean.BaseResponse;
import wocap.neusoft.com.xiaolongbaomanage.bean.GetOrder;
import wocap.neusoft.com.xiaolongbaomanage.bean.ResGetOrder;
import wocap.neusoft.com.xiaolongbaomanage.presenter.pView.NormalView;
import wocap.neusoft.com.xiaolongbaomanage.presenter.searchOrderPresenter;


/**
 * @author wangmian E-mail:wangmian1994@outlook.com
 * @version 创建时间：2018/11/29 下午12:31
 */
public class HomeFragment extends BaseFragment {
    @BindView(R.id.recycle_view)
    RecyclerView recycleView;
    @BindView(R.id.ptr_classic_frame_layout)
    PtrClassicFrameLayout ptrClassicLayout;
    protected RentalsSunHeaderView header;
    protected boolean isRefresh, canPullRefresh;
    @BindView(R.id.select_time)
    TextView selectTime;
    @BindView(R.id.select_type)
    TextView selectType;
    private int pageIndex = 1;
    private searchOrderPresenter searchOrderPresenter;
    private NormalView<BaseResponse<ResGetOrder>> resultView;

    android.app.DatePickerDialog start;
    Calendar calendar;
    int myear, mmonth, mday;
    private String selecttime;

    @Override
    protected int initLayoutId() {
        return R.layout.layout_home_fragment;
    }

    @Override
    protected void initView() {

        header = new RentalsSunHeaderView(getActivity());
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setPadding(0, 15, 0, 10);
        header.setUp(ptrClassicLayout);

        PtrClassicDefaultFooter footer = new PtrClassicDefaultFooter(getActivity());
        footer.setPadding(0, 0, 0, PtrLocalDisplay.dp2px(15));
        ptrClassicLayout.setHeaderView(header);
        ptrClassicLayout.addPtrUIHandler(header);
        ptrClassicLayout.setFooterView(footer);
        ptrClassicLayout.addPtrUIHandler(footer);
        //mPtrFrame.setKeepHeaderWhenRefresh(true);//刷新时保持头部的显示，默认为true
        //mPtrFrame.disableWhenHorizontalMove(true);//如果是ViewPager，设置为true，会解决ViewPager滑动冲突问题。
        ptrClassicLayout.setPtrHandler(new PtrHandler2() {
            @Override
            public boolean checkCanDoLoadMore(PtrFrameLayout frame, View content, View footer) {
                // 默认实现，根据实际情况做改动
                return PtrDefaultHandler2.checkContentCanBePulledUp(frame, content, footer);
            }

            /**
             * 加载更多的回调
             * @param frame
             */
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {

            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                // 默认实现，根据实际情况做改动
                return canPullRefresh && PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            /**
             * 下拉刷新的回调
             * @param frame
             */
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                isRefresh = true;
                pageIndex = 1;
            }
        });
        searchOrderPresenter = new searchOrderPresenter(getActivity());
        resultView = new NormalView<BaseResponse<ResGetOrder>>() {
            @Override
            public void onSuccess(BaseResponse<ResGetOrder> object) {

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
        searchOrderPresenter.attachView(resultView);
        searchOrderPresenter.onCreate();
        getOrder();

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.select_time)
    public void selectTime(){
        calendar = Calendar.getInstance();
        // 获取当前对应的年、月、日的信息
        start = new android.app.DatePickerDialog(getActivity(), new android.app
                .DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myear = year;
                mmonth = monthOfYear + 1;
                mday = dayOfMonth;
                selecttime = myear + "-" + ((mmonth > 9) ? mmonth : ("0" + mmonth)) + "-" + mday;
                selectTime.setText(selecttime);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar
                .DAY_OF_MONTH));
        start.show();
    }


    private void getOrder() {
        GetOrder getOrder = new GetOrder();
        getOrder.setStartTime("");
        getOrder.setEndTime("");
        searchOrderPresenter.getOrder(getOrder);
    }
}
