package wocap.neusoft.com.xiaolongbaomanage.activity;


import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wocap.neusoft.com.xiaolongbaomanage.R;
import wocap.neusoft.com.xiaolongbaomanage.adapter.CustomAdapter;
import wocap.neusoft.com.xiaolongbaomanage.adapter.CustomViewHolder;
import wocap.neusoft.com.xiaolongbaomanage.bean.BaseResponse;
import wocap.neusoft.com.xiaolongbaomanage.bean.ContactsBean;
import wocap.neusoft.com.xiaolongbaomanage.bean.ResSearchOrder;
import wocap.neusoft.com.xiaolongbaomanage.bean.UpdateOrder;
import wocap.neusoft.com.xiaolongbaomanage.customeView.BottomDialog;
import wocap.neusoft.com.xiaolongbaomanage.customeView.BottomItem;
import wocap.neusoft.com.xiaolongbaomanage.customeView.HorizontalDividerItemDecoration;
import wocap.neusoft.com.xiaolongbaomanage.http.Constants;
import wocap.neusoft.com.xiaolongbaomanage.presenter.pView.NormalView;
import wocap.neusoft.com.xiaolongbaomanage.presenter.updateOrderPresenter;
import wocap.neusoft.com.xiaolongbaomanage.util.AndroidTool;

public class OrderDetailActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.unread)
    ImageView unread;
    @BindView(R.id.tv_orderid)
    TextView tvOrderid;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_start_station)
    TextView tvStartStation;
    @BindView(R.id.tv_end_station)
    TextView tvEndStation;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_seat)
    TextView tvSeat;
    @BindView(R.id.option)
    CardView option;
    @BindView(R.id.recycle_view)
    RecyclerView recycleView;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.remark)
    TextView remark;
    @BindView(R.id.copy)
    ImageView copy;
    @BindView(R.id.bmb)
    BoomMenuButton bmb;
    private ResSearchOrder order;
    CustomAdapter<ContactsBean> adapter;
    private NormalView<BaseResponse> resultView;
    private updateOrderPresenter updateOrderPresenter;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override
    protected void initView() {
        order = getIntent().getParcelableExtra("order");
        adapter = new CustomAdapter<ContactsBean>(OrderDetailActivity.this,R.layout.layout_customer_item) {
            @Override
            protected void convert(CustomViewHolder holder, int position) throws ParseException {
                final ContactsBean item = (ContactsBean) holder.tag;
                holder.setText(R.id.name,item.getName());
                holder.setText(R.id.id_no,item.getIdNumber());
                holder.setText(R.id.type,Constants.CUstomerType.codeOf(item.getUserTyp()).getValue());
                holder.setText(R.id.id_type,Constants.CardTypeEnum.codeOf(item.getIdType()).getValue());
                if(item.getUserTyp() == 2){
                    holder.setVisible(R.id.school_option,true);
                    holder.setText(R.id.school_time,item.getSchoolTime());
                    holder.setText(R.id.school_name,item.getSchoolName());
                    holder.setText(R.id.school_id,item.getSchoolId());
                    holder.setText(R.id.school_startstation,item.getSchoolStartStation());
                    holder.setText(R.id.school_endstation,item.getSchoolEndStation());
                }else{
                    holder.setVisible(R.id.school_option,false);
                }
            }
        };
        Paint paint = new Paint();
        paint.setStrokeWidth(2);
        paint.setColor(getResources().getColor(R.color.line_color));
        recycleView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(OrderDetailActivity.this).margin(0, 0).paint(paint).build());
        recycleView.setLayoutManager(new LinearLayoutManager(OrderDetailActivity.this));
        recycleView.setHasFixedSize(true);
        recycleView.setAdapter(adapter);
        resultView = new NormalView<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse object) {
                if(object.status ==200){
                    AndroidTool.showToast(OrderDetailActivity.this,"订单状态更新成功");
                }else{
                    AndroidTool.showToast(OrderDetailActivity.this,object.msg);
                }
            }

            @Override
            public void onError(String result) {
                AndroidTool.showToast(OrderDetailActivity.this,result);
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onNoMore() {

            }
        };
        updateOrderPresenter = new updateOrderPresenter(OrderDetailActivity.this);
        updateOrderPresenter.attachView(resultView);
        updateOrderPresenter.onCreate();
        initSelectTypeList();
    }

    @Override
    protected void initData() {
        setInfo();
    }

    private void setInfo() {
        tvStartStation.setText(order.getStartStation());
        tvEndStation.setText(order.getEndStation());
        tvOrderid.setText(order.getOrderNo()+"");
        tvPhone.setText(order.getPhone());
        remark.setText(order.getRemark());
        tvSeat.setText(Constants.SeatEnum.codeOf(order.getSeat()).getValue());
        tvType.setText(Constants.OrderStatusEnum.codeOf(order.getStatus()).getValue());
        tvTime.setText(order.getStartTime());
        adapter.replaceAllData(order.getContacts());
    }

    @OnClick(R.id.tv_phone)
    public void call(){
        diallPhone(order.getPhone());
    }

    @OnClick(R.id.copy)
    public void copy(){
        String msg = "";
        for(ContactsBean constants:order.getContacts()){
            msg+=constants.getName()+"----"+constants.getIdNumber()+"\n";
        }
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        // 将文本内容放到系统剪贴板里。
        cm.setText(msg);
        AndroidTool.showToast(OrderDetailActivity.this,"复制成功");
    }


    private void initSelectTypeList() {
        final List<BottomItem> list = new ArrayList<>();
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
        for (int i = 0; i < bmb.getButtonPlaceEnum().buttonNumber(); i++) {
            bmb.addBuilder(new HamButton.Builder()
                    .normalText(list.get(i).text)
                    .highlightedColorRes(R.color.white)
                    .normalTextColorRes(R.color.black)
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            UpdateOrder updateOrder = new UpdateOrder();
                            updateOrder.orderId = order.getId();
                            updateOrder.orderType = order.getOrderType();
                            updateOrder.status = Integer.parseInt(list.get(index).id);
                            updateOrderPresenter.update(updateOrder);
                        }
                    })
                    .normalColorRes(R.color.white));
        }
    }



    @OnClick(R.id.btn_login)
    public void changeType(){
       bmb.boom();
    }

    public void diallPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }



}
