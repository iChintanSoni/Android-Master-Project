package com.chintansoni.android.masterproject.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerAdapter<T, VH extends BaseViewHolder> extends RecyclerView.Adapter<VH> {

    private int mModelLayout;
    private Class<VH> mViewHolderClass;
    private List<T> mList = new ArrayList<>();

    public BaseRecyclerAdapter(Class<VH> viewHolderClass, int modelLayout) {
        mModelLayout = modelLayout;
        mViewHolderClass = viewHolderClass;
    }

    public void setmList(ArrayList<T> mList) {
        this.mList = mList;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup view = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        try {
            Constructor<VH> constructor = mViewHolderClass.getConstructor(View.class);
            return constructor.newInstance(view);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onBindViewHolder(VH viewHolder, int position) {
        T model = getItem(position);
        populateViewHolder(viewHolder, model, position);
    }

    @Override
    public int getItemViewType(int position) {
        return mModelLayout;
    }

    abstract protected void populateViewHolder(VH viewHolder, T model, int position);

    public void addItem(T t) {
        int index = mList.size();
        mList.add(index, t);
        notifyItemInserted(index);
    }

    public void updateItem(int position, T t) {
        mList.set(position, t);
        notifyItemChanged(position);
    }

    public void removeItem(int position) {
        mList.remove(position);
//        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public void setItems(List<T> items) {
        mList = items;
        notifyDataSetChanged();
    }
}