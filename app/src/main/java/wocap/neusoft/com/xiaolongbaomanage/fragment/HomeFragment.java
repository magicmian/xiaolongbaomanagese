package wocap.neusoft.com.xiaolongbaomanage.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrClassicDefaultFooter;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler2;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;
import in.srain.cube.views.ptr.indicator.PtrIndicator;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;
import wocap.neusoft.com.xiaolongbaomanage.R;
import wocap.neusoft.com.xiaolongbaomanage.activity.BaseFragment;
import wocap.neusoft.com.xiaolongbaomanage.activity.OrderDetailActivity;
import wocap.neusoft.com.xiaolongbaomanage.adapter.CustomAdapter;
import wocap.neusoft.com.xiaolongbaomanage.adapter.CustomViewHolder;
import wocap.neusoft.com.xiaolongbaomanage.bean.BaseResponse;
import wocap.neusoft.com.xiaolongbaomanage.bean.GetOrder;
import wocap.neusoft.com.xiaolongbaomanage.bean.ResGetOrder;
import wocap.neusoft.com.xiaolongbaomanage.bean.ResSearchOrder;
import wocap.neusoft.com.xiaolongbaomanage.customeView.BottomDialog;
import wocap.neusoft.com.xiaolongbaomanage.customeView.BottomItem;
import wocap.neusoft.com.xiaolongbaomanage.customeView.HorizontalDividerItemDecoration;
import wocap.neusoft.com.xiaolongbaomanage.http.Constants;
import wocap.neusoft.com.xiaolongbaomanage.presenter.getOrderPresenter;
import wocap.neusoft.com.xiaolongbaomanage.presenter.pView.NormalView;
import wocap.neusoft.com.xiaolongbaomanage.presenter.searchOrderPresenter;
import wocap.neusoft.com.xiaolongbaomanage.util.AndroidTool;
import wocap.neusoft.com.xiaolongbaomanage.util.SharePrefUtil;


/**
 * @author wangmian E-mail:wangmian1994@outlook.com
 * @version 创建时间：2018/11/29 下午12:31
 */
public class HomeFragment extends BaseFragment {
    @BindView(R.id.recycle_view)
    RecyclerView recycleView;
    @BindView(R.id.ptr_classic_frame_layout)
    PtrClassicFrameLayout ptrClassicLayout;
    protected boolean canLoadMore, canPullRefresh;
    @BindView(R.id.select_time)
    TextView selectTime;
    @BindView(R.id.select_type)
    TextView selectType;
    private int pageIndex = 1;
    private getOrderPresenter searchOrderPresenter;
    private NormalView<BaseResponse<ResGetOrder>> resultView;
    CustomAdapter<ResSearchOrder> adapter;
    android.app.DatePickerDialog start;
    Calendar calendar;
    int myear, mmonth, mday;
    private String selecttime;
    private String orderStatus = "10";
    private SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    @Override
    protected int initLayoutId() {
        return R.layout.layout_home_fragment;
    }

    @Override
    protected void initView() {
        adapter =  new CustomAdapter<ResSearchOrder>(getActivity(),R.layout.layout_order_item) {
            @Override
            protected void convert(CustomViewHolder holder, int position) throws ParseException {
                final ResSearchOrder item = (ResSearchOrder) holder.tag;
                holder.setText(R.id.tv_customer,item.getCustomer());
                holder.setText(R.id.tv_orderid,item.getOrderNo()+"");
                holder.setText(R.id.tv_start_station,item.getStartStation());
                holder.setText(R.id.tv_seat, Constants.SeatEnum.codeOf(item.getSeat()).getValue());
                holder.setText(R.id.tv_end_station,item.getEndStation());
                holder.setText(R.id.tv_phone,item.getPhone());
                holder.setText(R.id.tv_time,item.getStartTime());
                holder.setText(R.id.tv_type,Constants.OrderStatusEnum.codeOf(item.getStatus()).getValue());
                if(SharePrefUtil.getBoolean(getActivity(),item.getOrderNo()+"",false)){
                    holder.setVisible(R.id.unread,false);
                }else{
                    holder.setVisible(R.id.unread,true);
                }
                holder.setOnClickListener(R.id.option, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SharePrefUtil.saveBoolean(getActivity(),item.getOrderNo()+"",true);
                        Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("order",item);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }
        };
        PtrClassicDefaultHeader defaultHeader = new PtrClassicDefaultHeader(getActivity());
        PtrClassicDefaultFooter footer = new PtrClassicDefaultFooter(getActivity());
        footer.setPadding(0, 0, 0, PtrLocalDisplay.dp2px(15));
        ptrClassicLayout.setHeaderView(defaultHeader);
        ptrClassicLayout.setFooterView(footer);
        ptrClassicLayout.addPtrUIHandler(footer);
        Paint paint = new Paint();
        paint.setStrokeWidth(2);
        paint.setColor(getResources().getColor(R.color.line_color));
        recycleView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity()).margin(0, 0).paint(paint).build());
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView.setHasFixedSize(true);
        recycleView.setAdapter(adapter);
        ptrClassicLayout.setPtrHandler(new PtrHandler2() {
            @Override
            public boolean checkCanDoLoadMore(PtrFrameLayout frame, View content, View footer) {
                // 默认实现，根据实际情况做改动
                return canLoadMore && PtrDefaultHandler2.checkContentCanBePulledUp(frame, content, footer);
            }

            /**
             * 加载更多的回调
             * @param frame
             */
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                getOrder();
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
                canPullRefresh = false;
                pageIndex = 1;
                getOrder();
            }
        });
        searchOrderPresenter = new getOrderPresenter(getActivity());
        resultView = new NormalView<BaseResponse<ResGetOrder>>() {
            @Override
            public void onSuccess(BaseResponse<ResGetOrder> object) {
                ptrClassicLayout.refreshComplete();
                if(object.status == 200){
                    if(pageIndex == 1){
                        adapter.replaceAllData(object.data.list);
                        canPullRefresh = true;
                    }else {
                        adapter.appendData(object.data.list);
                    }
                    if(!object.data.hasNextPage){
                        canLoadMore = false;
                    }else{
                        canLoadMore = true;
                    }
                    pageIndex++;
                }else{
                    AndroidTool.showToast(getActivity(),object.msg);
                }

            }

            @Override
            public void onError(String result) {
                AndroidTool.showToast(getActivity(),result);
                ptrClassicLayout.refreshComplete();
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
        selectType.setText("未处理");
        Date nowTime = new Date(System.currentTimeMillis());
        String nowDate = format.format(nowTime);
        selectTime.setText(nowDate);
        selecttime = nowDate;
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
                selecttime = myear+""  + ((mmonth > 9) ? mmonth : ("0" + mmonth))  + mday+"";
                selectTime.setText(selecttime);
                getOrder();
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar
                .DAY_OF_MONTH));
        start.show();
    }

    @OnClick(R.id.select_type)
    public void selestType(){
        List<BottomItem> list = new ArrayList<>();
        final BottomItem bottom1 = new BottomItem();
        bottom1.id = "10";
        bottom1.text = "未处理";
        BottomItem bottom2 = new BottomItem();
        bottom2.id = "20";
        bottom2.text = "刷票中";
        BottomItem bottom3 = new BottomItem();
        bottom3.id = "40";
        bottom3.text = "已出票";
        BottomItem bottom4 = new BottomItem();
        bottom4.id = "50";
        bottom4.text = "已完成";
        BottomItem bottom5 = new BottomItem();
        bottom5.id = "90";
        bottom5.text = "全部";
        list.add(bottom1);
        list.add(bottom2);
        list.add(bottom3);
        list.add(bottom4);
        list.add(bottom5);
        AndroidTool.showBottomForOnCLick(getActivity(), list, "选择类型", new BottomDialog.OnItemClickListener<BottomItem>() {
            @Override
            public void onClick(BottomItem bottomItem) {
                selectType.setText(bottomItem.text);
                orderStatus = bottomItem.id;
                getOrder();
            }
        });
    }

    private void getOrder() {
        if(TextUtils.isEmpty(selecttime)){
            AndroidTool.showToast(getActivity(),"请选择日期");
            return;
        }
        if(TextUtils.isEmpty(orderStatus)){
            AndroidTool.showToast(getActivity(),"请选择订单状态");
            return;
        }
        GetOrder getOrder = new GetOrder();
        getOrder.setStartTime(selecttime);
        getOrder.setStatus(Integer.parseInt(orderStatus));
        getOrder.setPageNum(pageIndex);
        getOrder.setPageSize(10);
        getOrder.setEndTime(selecttime);
        searchOrderPresenter.getOrder(getOrder);
    }
}
