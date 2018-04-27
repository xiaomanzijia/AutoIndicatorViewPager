package me.licheng.autoindicatorviewpager;

import android.content.Context;
import android.util.AttributeSet;

public class AutoCirclePageIndicator extends CirclePageIndicator {

    public AutoCirclePageIndicator(Context context) {
        super(context);
    }

    public AutoCirclePageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoCirclePageIndicator(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
        if (getViewPager() == null) return;
        if (positionOffset == 0) {
            if (getViewPager() instanceof AutoViewPager) {
                if (getViewPager().getPageCount() < 3) return;
                if (position == 0) {
                    ((AutoViewPager) getViewPager()).setCurrentItem(getViewPager().getPageCount() - 2, false);
                } else if (position == getViewPager().getPageCount() - 1) {
                    ((AutoViewPager) getViewPager()).setCurrentItem(1, false);
                }
            }
        }

    }

    @Override
    public void onPageSelected(int position) {
        super.onPageSelected(position);
    }
}
