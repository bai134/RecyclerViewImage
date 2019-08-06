package com.bai.textrecyclerviewimage;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

/**
 * Cerated by bailing
 * Create date : 2019/8/5 16:12
 * description :
 */

public class TempRecyclerView extends RecyclerView {
    public TempRecyclerView(@NonNull Context context) {
        this(context,null);
    }

    public TempRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    LinearLayoutManager linearLayoutManager;
    ImageView mImageView;
    List<Integer> mIntegerList;

    @Override
    public void setLayoutManager(LayoutManager layout) {
        super.setLayoutManager(layout);
        linearLayoutManager = (LinearLayoutManager) layout;
    }

    /**
     * 设置可移动的背景图
     * @param imageView
     */
    public void setImage(ImageView imageView){
        mImageView = imageView;
    }

    /**
     * 设置透明的item下标集合，两个下标所在的item不能同时显示
     * @param integerList
     */
    public void setListPosition(List<Integer> integerList){
        mIntegerList = integerList;
    }


    int first, last, offsetY,offsetY2, offseta,offseta2;
    View one, lastv;
    boolean flag;

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        first = linearLayoutManager.findFirstVisibleItemPosition();//第一个可见item
        last = linearLayoutManager.findLastVisibleItemPosition();//最后一个可见item
        one = linearLayoutManager.findViewByPosition(first);
        lastv = linearLayoutManager.findViewByPosition(last);

        for (int i = 0; i < mIntegerList.size(); i++) {
            flag = false;
            if (last == mIntegerList.get(i)) {
                offsetY2 = (getHeight() - lastv.getBottom()) * (-1) - offsetY;
                mImageView.offsetTopAndBottom(offsetY2);
                offsetY = (getHeight() - lastv.getBottom()) * (-1);
                offseta = 0;
                offseta2 = 0;
                break;
            } else if (first == mIntegerList.get(i)) {
                offseta2 = one.getTop() - offseta;
                mImageView.offsetTopAndBottom(offseta2);
                offseta = one.getTop();
                offsetY = 0;
                offsetY2 = 0;
                break;
            }else {
                flag=true;
            }

        }

        if ((offsetY2 != 0 || offseta2 != 0) && flag) {
            mImageView.layout(0, 0, mImageView.getWidth(), mImageView.getHeight());
            offsetY = 0;
            offsetY2 = 0;
            offseta = 0;
            offseta2 = 0;
        }

    }

    //抛掷速度
    @Override
    public boolean fling(int velocityX, int velocityY) {
        velocityY = 0;//不可抛掷
        return super.fling(velocityX, velocityY);
    }

}
