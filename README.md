# RecyclerViewImage
仿猫眼首页，recyclerview透明item展示背景图


首先来个低配版的效果图

![在这里插入图片描述](https://github.com/bai134/RecyclerViewImage/blob/master/RecyclerViewImage.gif)


逻辑并不复杂，就是继承RecyclerView重写onScrolled(int dx, int dy)方法就可，Adapter用两种itemView，一种是透明背景，一种是普通布局View。

```
@Override
    public int getItemViewType(int position) {
        if (position==10||position==0){
            return 0;
        }else
            return 1;
    }
```
这里我是使用的固定了的item下标。
然后在onCreateViewHolder方法中做itemType处理。

主要逻辑在这里

```
/**
     * 通过滑动，判断当前是否有显示透明item，如果有，且透明item位于第一个或者最后一个，那么就移动imageview，让imageview随该透明item移动
     * @param dx
     * @param dy
     */
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
```
通过linearLayoutManager的findFirstVisibleItemPosition()方法和findLastVisibleItemPosition()方法来判断透明item是否已经显示在屏幕上来对背景imagView进行移动或则回归原位。

RecyclerView布局是这样

```
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <ImageView
        android:id="@+id/imageView"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:scaleType="fitXY"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:srcCompat="@mipmap/ad_bg"
        tools:ignore="VectorDrawableCompat" />

    <com.bai.textrecyclerviewimage.TempRecyclerView
        android:id="@+id/recyclerview"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</android.support.constraint.ConstraintLayout>
```

因为imageView在底层，所以当recyclerView的item是透明时，则会显示出imageView的一部分。

这个我没封装在一起，还有一些细节没有写，例如动态换图片什么的，预加载图片什么的，所以很简陋，低配版。

记录一下
