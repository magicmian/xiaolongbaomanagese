package wocap.neusoft.com.xiaolongbaomanage.customeView;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;



import java.text.ParseException;
import java.util.List;

import wocap.neusoft.com.xiaolongbaomanage.R;
import wocap.neusoft.com.xiaolongbaomanage.adapter.CustomAdapter;
import wocap.neusoft.com.xiaolongbaomanage.adapter.CustomViewHolder;

/**
 * Created by wangmian on 2017/12/14.
 * you can contact me with wangmian1994@outlook.com
 */

public class BottomDialog {
    private BottomSheetDialog dialog;
    RecyclerView recyclerView;
    LayoutInflater layoutInflater;
    CustomAdapter myAdapter;
    Context context;
    LinearLayout view;
    private GridLayoutManager gridLayoutManager;
    private OnItemClickListener onItemClickListener;
    Paint paint = new Paint();
    private final static int SEQUENTIAL = 1;
    private final static int RANDOM = 2;
    private final static int SINGLE = 3;

    public BottomDialog(Context context ) {
        this.context = context;
        init();
    }


    private void init(){
        layoutInflater = LayoutInflater.from(context);

    }
    public interface OnItemClickListener<T>{
        void onClick(T t);
    }
    private void setHorizontalDividerItemDecoration(int leftMargin, int rightMargin) {
        paint.setStrokeWidth(2);
        paint.setColor(context.getResources().getColor(R.color.line_color));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(context).margin(leftMargin, rightMargin).paint(paint).build());
    }

    public void setData(List<BottomItem> list , String titlestring, final OnItemClickListener<BottomItem> listener) {
        myAdapter = new CustomAdapter<BottomItem>(context, R.layout.bottom_item){
            @Override
            protected void convert(CustomViewHolder holder, int position) throws ParseException {
                if(holder.tag != null){
                    final BottomItem info = (BottomItem) holder.tag;
                    holder.setText(R.id.str,info.text);
                    holder.setOnClickListener(R.id.option, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(null != listener){
                                listener.onClick(info);
                                dismiss();
                            }
                        }
                    });

                }
            }
        };
        view = (LinearLayout) layoutInflater.inflate(R.layout.bottom_sheet, null);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(titlestring);
        TextView close = (TextView) view.findViewById(R.id.sure);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);
        dialog = new BottomSheetDialog(context, R.style.BottomSheetDialog);
        setHorizontalDividerItemDecoration(0,0);
        recyclerView.setAdapter(myAdapter);
        dialog.setContentView(view);
        myAdapter.appendData(list);
    }

    public void show() {
        if (dialog != null) {
            dialog.show();
        }
    }

    public void dismiss() {

        if (dialog != null ) {
            dialog.dismiss();
        }
    }

    public void setDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        dialog.setOnDismissListener(onDismissListener);
    }
    public void notifyItemChanged(int position) {
        myAdapter.notifyItemChanged(position);
    }


}
