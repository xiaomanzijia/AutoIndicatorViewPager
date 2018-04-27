package me.licheng.autoindicatorviewpager;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class AutoViewPager extends CustomViewPager implements View.OnTouchListener {

    private boolean interceptAndMeasureChild = true; //是否拦截父控件点击事件 控件适应child最大高度
    private boolean stopAuto = false;
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable autoRunnable = new Runnable() {
        @Override
        public void run() {
            if (!stopAuto) {
                if (getPageCount() < 4) {
                    stopAuto = true;
                    handler.removeCallbacks(this);
                }
                int currentItem = getCurrentItem();
                setCurrentItem(currentItem + 1, true);
                handler.postDelayed(autoRunnable, autoTime);
            }
        }
    };

    private boolean isAuto = false;//是否设置滚动
    private long autoTime = 5000; //滚动间隔时间

    public void setAutoTime(long autoTime) {
        this.autoTime = autoTime;
    }

    public void setAuto(boolean auto) {
        isAuto = auto;
    }

    public void setInterceptAndMeasureChild(boolean interceptAndMeasureChild) {
        this.interceptAndMeasureChild = interceptAndMeasureChild;
    }

    public AutoViewPager(Context context) {
        this(context, null);
    }

    public AutoViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(this);
    }

    public void startAuto() {
        if (isAuto && handler != null) {
            handler.removeCallbacks(autoRunnable);
            handler.postDelayed(autoRunnable, autoTime);
        }
    }

    public void removeCallbacks() {
        if (handler != null) {
            handler.removeCallbacks(autoRunnable);
        }
    }


    @Override
    public void addOnPageChangeListener(PageIndicator listener) {
        super.addOnPageChangeListener(listener);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (interceptAndMeasureChild) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (!isAuto) return false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                stopAuto = true;
                handler.removeCallbacks(autoRunnable);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                stopAuto = false;
                handler.removeCallbacks(autoRunnable);
                handler.postDelayed(autoRunnable, autoTime);
                break;
            default:
                stopAuto = false;
                handler.removeCallbacks(autoRunnable);
                handler.postDelayed(autoRunnable, autoTime);
                break;
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!isAuto) return false;
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (interceptAndMeasureChild) {
            int height = 0;
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                int h = child.getMeasuredHeight();
                if (h > height) {
                    height = h;
                }
            }
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height,
                    MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
