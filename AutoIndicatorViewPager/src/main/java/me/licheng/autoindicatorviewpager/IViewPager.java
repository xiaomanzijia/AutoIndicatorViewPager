package me.licheng.autoindicatorviewpager;

import android.view.View;

public interface IViewPager<T> {

    void setCurrentItem(int item);

    int getPageCount();

    void addOnPageChangeListener(PageIndicator listener);

    void removeOnPageChangeListener(PageIndicator listener);

    void notifyDataChanged(T data);

    View getViewByPosition(int pageIndex, int itemIndex);

    View getChildAt(int index);

    int getCurrentPageIndex();
}
