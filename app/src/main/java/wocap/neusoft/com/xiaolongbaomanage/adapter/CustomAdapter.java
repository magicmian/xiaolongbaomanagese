package wocap.neusoft.com.xiaolongbaomanage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by neusoft on 2016/4/29.
 */
public abstract class CustomAdapter<T> extends RecyclerView.Adapter<CustomViewHolder>{
    private LayoutInflater mInflater = null;
    private int mItemViewId;
    private List<T> mDataList = new ArrayList<T>();

    public CustomAdapter(Context context, int itemViewId, List<T> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mItemViewId = itemViewId;
        if (data  != null) {
            this.mDataList = data;
        }
    }


    public CustomAdapter(Context context, int itemViewId) {
        this.mInflater = LayoutInflater.from(context);
        this.mItemViewId = itemViewId;
    }
    /**
     * dynamic add data
     */
    public void appendData(List<T> appendData) {
        if (appendData != null && appendData.size() > 0) {
            mDataList.addAll(appendData);
            notifyDataSetChanged();
        }
    }

    /**
     * replace all data
     */
    public void replaceAllData(ArrayList<T> appendData) {
        if (appendData != null) {
            mDataList = appendData;
            notifyDataSetChanged();
        }
    }

    /**
     * remove the opposite position data
     * @param position position
     */
    public void remove(int position){
        if (position >= 0 && position < mDataList.size()) {
            mDataList.remove(position);
            notifyDataSetChanged();
        }
    }
    /**
     * remove all data
     */
    public void removeAll(){
        mDataList.clear();
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.tag = mDataList.get(position);
        holder.position = position;
        holder.setTag(R.id.option, holder);
        try {
            convert(holder,position);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public List<T> getmDataList(){
        return mDataList;
    }
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(mItemViewId,parent,false);
        CustomViewHolder holder = new CustomViewHolder(itemView);
        return holder;
    }



    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param holder view holder
     * @param position   the holder position.
     */
    protected abstract void convert(CustomViewHolder holder, int position) throws ParseException;

}
