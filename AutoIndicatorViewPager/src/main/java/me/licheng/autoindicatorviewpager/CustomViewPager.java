package me.licheng.autoindicatorviewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

public class CustomViewPager extends ViewPager implements IViewPager {

    public CustomViewPager(Context context) {
        this(context, null);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public int getPageCount() {
        return getAdapter().getCount();
    }

    @Override
    public void addOnPageChangeListener(PageIndicator listener) {
        super.addOnPageChangeListener(listener);
    }

    @Override
    public void removeOnPageChangeListener(PageIndicator listener) {
        super.removeOnPageChangeListener(listener);
    }

    @Override
    public void notifyDataChanged(Object data) {

    }

    @Override
    public View getViewByPosition(int pageIndex, int itemIndex) {
        return null;
    }

    @Override
    public int getCurrentPageIndex() {
        return 0;
    }


}
