package wocap.neusoft.com.xiaolongbaomanage.fragment;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.wang.avi.AVLoadingIndicatorView;

import java.text.ParseException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wocap.neusoft.com.xiaolongbaomanage.R;
import wocap.neusoft.com.xiaolongbaomanage.activity.BaseFragment;
import wocap.neusoft.com.xiaolongbaomanage.activity.OrderDetailActivity;
import wocap.neusoft.com.xiaolongbaomanage.adapter.CustomAdapter;
import wocap.neusoft.com.xiaolongbaomanage.adapter.CustomViewHolder;
import wocap.neusoft.com.xiaolongbaomanage.bean.BaseResponse;
import wocap.neusoft.com.xiaolongbaomanage.bean.GetOrder;
import wocap.neusoft.com.xiaolongbaomanage.bean.ResGetOrder;
import wocap.neusoft.com.xiaolongbaomanage.bean.ResSearchOrder;
import wocap.neusoft.com.xiaolongbaomanage.customeView.HorizontalDividerItemDecoration;
import wocap.neusoft.com.xiaolongbaomanage.http.Constants;
import wocap.neusoft.com.xiaolongbaomanage.presenter.pView.NormalView;
import wocap.neusoft.com.xiaolongbaomanage.presenter.searchOrderPresenter;
import wocap.neusoft.com.xiaolongbaomanage.util.AndroidTool;
import wocap.neusoft.com.xiaolongbaomanage.util.KeyboardUtils;
import wocap.neusoft.com.xiaolongbaomanage.util.SharePrefUtil;

/**
 * @author wangmian E-mail:wangmian1994@outlook.com
 * @version 创建时间：2018/12/6 下午4:10
 */
public class SearchFragment extends BaseFragment {
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.recycle_view)
    RecyclerView recycleView;
    @BindView(R.id.search)
    ImageView search;
    @BindView(R.id.avi)
    AVLoadingIndicatorView avi;
    private NormalView<BaseResponse<ArrayList<ResSearchOrder>>> resultView;
    CustomAdapter<ResSearchOrder> adapter;
    private searchOrderPresenter searchOrderPresenter;

    @Override
    protected int initLayoutId() {
        return R.layout.layout_search_fragment;
    }

    @Override
    protected void initView() {
        adapter = new CustomAdapter<ResSearchOrder>(getActivity(), R.layout.layout_order_item) {
            @Override
            protected void convert(CustomViewHolder holder, int position) throws ParseException {
                final ResSearchOrder item = (ResSearchOrder) holder.tag;
                holder.setText(R.id.tv_customer, item.getCustomer());
                holder.setText(R.id.tv_orderid, item.getOrderNo() + "");
                holder.setText(R.id.tv_start_station, item.getStartStation());
                holder.setText(R.id.tv_seat, Constants.SeatEnum.codeOf(item.getSeat()).getValue());
                holder.setText(R.id.tv_end_station, item.getEndStation());
                holder.setText(R.id.tv_phone, item.getPhone());
                holder.setText(R.id.tv_time, item.getStartTime());
                holder.setText(R.id.tv_type, Constants.OrderStatusEnum.codeOf(item.getStatus()).getValue());
                if (SharePrefUtil.getBoolean(getActivity(), item.getOrderNo() + "", false)) {
                    holder.setVisible(R.id.unread, false);
                } else {
                    holder.setVisible(R.id.unread, true);
                }
                holder.setOnClickListener(R.id.option, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SharePrefUtil.saveBoolean(getActivity(), item.getOrderNo() + "", true);
                        Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("order", item);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }
        };
        Paint paint = new Paint();
        paint.setStrokeWidth(2);
        paint.setColor(getResources().getColor(R.color.line_color));
        //recycleView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity()).margin(0, 0).paint(paint).build());
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView.setHasFixedSize(true);
        recycleView.setAdapter(adapter);

        searchOrderPresenter = new searchOrderPresenter(getActivity());
        resultView = new NormalView<BaseResponse<ArrayList<ResSearchOrder>>>() {
            @Override
            public void onSuccess(BaseResponse<ArrayList<ResSearchOrder>> object) {
                stopAnim();
                if (object.status == 200) {
                    adapter.replaceAllData(object.data);
                } else {
                    AndroidTool.showToast(getActivity(), object.msg);
                }

            }

            @Override
            public void onError(String result) {
                stopAnim();
                AndroidTool.showToast(getActivity(), result);
            }

            @Override
            public void onStart() {
                startAnim();
            }

            @Override
            public void onNoMore() {

            }
        };
        searchOrderPresenter.attachView(resultView);
        searchOrderPresenter.onCreate();
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.search)
    public void search(){
        if(TextUtils.isEmpty(name.getText().toString().trim())){
            AndroidTool.showToast(getActivity(),"请输入姓名");
        }else{
            KeyboardUtils.hideSoftInput(getActivity());
            getOrder();
        }

    }


    private void getOrder() {
        GetOrder getOrder = new GetOrder();
        getOrder.setCustname(name.getText().toString().trim());
        getOrder.setStatus(10);
        searchOrderPresenter.searchOrder(getOrder);
    }
    void startAnim(){
        avi.setVisibility(View.VISIBLE);
        avi.smoothToShow();
        // or avi.smoothToShow();
    }

    void stopAnim(){
        avi.smoothToHide();
        avi.setVisibility(View.GONE);
        // or avi.smoothToHide();
    }


}
