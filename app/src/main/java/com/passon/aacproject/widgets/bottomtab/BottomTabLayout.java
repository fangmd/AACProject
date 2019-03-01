package com.passon.aacproject.widgets.bottomtab;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.passon.aacproject.R;
import com.passon.aacproject.utils.ResGetUtils;
import com.passon.aacproject.widgets.NoScrollViewPager;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;

/**
 * Created by cfp on 16-7-12.
 */
public class BottomTabLayout extends LinearLayout implements View.OnClickListener {

    /** tab页图标drawable名称前缀 */
    private static final String ICON_TAB_DRAWABLE_PREFIX = "icon_main_tab_";

    /** tabs个数 */
    private static final int TAB_COUNT = 4;

    private ArrayList<Tab> tabs;
    private OnTabClickListener onTabListener;
    private int tabCount;
    private View selectView;
    private NoScrollViewPager mViewPager;

    public BottomTabLayout(Context context) {
        super(context);
        setUpView();
    }

    public BottomTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUpView();
    }

    public BottomTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUpView();
    }

    public void setUpView() {
        setOrientation(HORIZONTAL);
    }

    public void setUpData(ArrayList<Tab> tabs, OnTabClickListener listener) {
        this.tabs = tabs;
        this.onTabListener = listener;
        removeAllViews();
        if (tabs != null && tabs.size() > 0) {
            tabCount = tabs.size();
            TabView mTabView;
            LayoutParams params = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;
            params.weight = 1;
            for (int i = 0; i < tabs.size(); i++) {
                mTabView = new TabView(getContext());
                mTabView.setTag(i);
                mTabView.setUpData(tabs.get(i));
                mTabView.setOnClickListener(this);
                addView(mTabView, params);
            }
        } else {
            throw new IllegalArgumentException("tabs can't be empty");
        }
    }

    public void setLocalData(int cnt) {
        setLocalData(cnt, null);
    }

    /**
     * 设置本地数据
     */
    public void setLocalData(int cnt, OnTabClickListener listener) {
        if (cnt == 0) {
            throw new IllegalArgumentException("classes 参数不能为空");
        }

        ArrayList<Tab> tabs = new ArrayList<>();
        for (int i = 0; i < cnt; i++) {

            int selectDrawableId = ResGetUtils.getDrawableId(getContext(), ICON_TAB_DRAWABLE_PREFIX + i * 2);
            int normalDrawableId = ResGetUtils.getDrawableId(getContext(), ICON_TAB_DRAWABLE_PREFIX + String.valueOf(i * 2 + 1));

            StateListDrawable stateDrawable = new StateListDrawable();
            stateDrawable.addState(new int[]{-android.R.attr.state_selected, -android.R.attr.state_focused, -android.R.attr.state_pressed},
                    getResources().getDrawable(normalDrawableId));
            stateDrawable.addState(new int[]{android.R.attr.state_selected, -android.R.attr.state_focused, -android.R.attr.state_pressed},
                    getResources().getDrawable(selectDrawableId));
            stateDrawable.addState(new int[]{-android.R.attr.state_selected, android.R.attr.state_focused, -android.R.attr.state_pressed},
                    getResources().getDrawable(selectDrawableId));
            stateDrawable.addState(new int[]{-android.R.attr.state_selected, -android.R.attr.state_focused, android.R.attr.state_pressed},
                    getResources().getDrawable(selectDrawableId));
            tabs.add(new Tab(stateDrawable, R.string.tab_home));
        }
        setUpData(tabs, listener);
    }

    /**
     * 设置线上的数据
     */
    public void setOnLineData(final ArrayList<Fragment> classes, ArrayList<String> urls) {
//        if (TAB_COUNT * 2 != urls.size()) {
//            return;
//        }
//        final HashSet<String> urlHashset = new HashSet<>();
//        final StateListDrawable[] stateDrawable = new StateListDrawable[TAB_COUNT];
//        for (int i = 0; i < TAB_COUNT * 2; i++) {
//            final int finalI = i;
//            ImageLoader.getInstance().loadImage(urls.get(i), new SimpleImageLoadingListener() {
//                @Override
//                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
//                    super.onLoadingFailed(imageUri, view, failReason);
//                }
//
//                @Override
//                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//                    super.onLoadingComplete(imageUri, view, loadedImage);
//
//                    Drawable drawable = ImageUtils.bitmapToDrawable(loadedImage);
//                    if (drawable == null) {
//                        return;
//                    }
//                    if (stateDrawable[finalI / 2] == null) {
//                        stateDrawable[finalI / 2] = new StateListDrawable();
//                    }
//                    LogUtil.D("finalId = " + finalI / 2);
//                    if (finalI % 2 == 0) {
//                        stateDrawable[finalI / 2].addState(new int[]{android.R.attr.state_selected, -android.R.attr.state_focused, -android.R.attr.state_pressed},
//                                drawable);
//                        stateDrawable[finalI / 2].addState(new int[]{-android.R.attr.state_selected, android.R.attr.state_focused, -android.R.attr.state_pressed},
//                                drawable);
//                        stateDrawable[finalI / 2].addState(new int[]{-android.R.attr.state_selected, -android.R.attr.state_focused, android.R.attr.state_pressed},
//                                drawable);
//                    } else {
//                        stateDrawable[finalI / 2].addState(new int[]{-android.R.attr.state_selected, -android.R.attr.state_focused, -android.R.attr.state_pressed},
//                                drawable);
//                    }
//                    urlHashset.add(imageUri);
//                    if (urlHashset.size() == TAB_COUNT * 2) {
//                        ArrayList<Tab> tabs = new ArrayList<>();
//                        for (int j = 0; j < classes.size(); j++) {
//                            tabs.add(new Tab(stateDrawable[j], R.string.tab_home, classes.get(j)));
//                        }
//                        updateData(tabs);
//                    }
//                }
//            });
//        }
    }

    /**
     * 更新tab中数据
     */
    public void updateData(ArrayList<Tab> tabs) {

        if (tabs != null && tabs.size() > 0) {
            for (int i = 0; i < tabs.size(); i++) {
                TabView tabView = (TabView) getChildAt(i);
                tabView.setUpData(tabs.get(i));
            }
            invalidate();
        }
    }

    public void setCurrentTab(int i) {
        if (i < tabCount && i >= 0) {
            View view = getChildAt(i);
            onClick(view);
        }
    }

    public void onDataChanged(int i, int badgeCount) {
        if (i < tabCount && i >= 0) {
            TabView view = (TabView) getChildAt(i);
            view.onDataChanged(badgeCount);
        }
    }

    /**
     * 获取当前选中的view
     */
    public int getCurrentSelectViewIndex() {
        for (int i = 0; i < tabCount; i++) {
            View view = getChildAt(i);
            if (selectView == view) {
                return i;
            }
        }
        return 0;
    }

    public void setUp(NoScrollViewPager viewPager) {
        mViewPager = viewPager;
    }


    public interface OnTabClickListener {
        void onTabClick(int index);
    }

    @Override
    public void onClick(View v) {
        if (selectView != v) {
            if (isLoginInterrupt(v)) {
                onTabClick(v);
                return;
            }
            onTabClick(v);
            v.setSelected(true);
            if (selectView != null) {
                selectView.setSelected(false);
            }
            selectView = v;
        }
    }

    private void onTabClick(View v) {
        int index = (int) v.getTag();
        mViewPager.setCurrentItem(index, false);
        if (onTabListener != null) {
            onTabListener.onTabClick(index);
        }
    }

    /**
     * 拦截跳转去登录
     */
    public boolean isLoginInterrupt(View v) {
//        if (!Tools.getInstance().hasLogin() && WCGMemberCenterFragment.class.getSimpleName().equals(((Tab) v.getTag()).fragmentClz.getSimpleName())) {
//            return true;
//        } else {
        return false;
//        }
    }

    public class TabView extends LinearLayout {

        private ImageView mTabImg;
        private TextView mTabText;

        public TabView(Context context) {
            super(context);
            setUpTabView();
        }

        public TabView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public TabView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        private void setUpTabView() {
            LayoutInflater.from(getContext()).inflate(R.layout.tab_view_layout, this, true);
            setOrientation(VERTICAL);
            setGravity(Gravity.CENTER);
            mTabImg = findViewById(R.id.iv_tab_view);
            mTabText = findViewById(R.id.tv_tab_view);
        }

        public void setUpData(Tab tab) {
            mTabImg.setImageDrawable(tab.imgDrawable);
            mTabText.setText(tab.textResId);
        }

        public void onDataChanged(int badgeCount) {
            //  TODO notify new message, change the badgeView
        }
    }

    public static class Tab {

        public Drawable imgDrawable;
        public int textResId;

        public Tab(Drawable imgDrawable, int textResId) {
            this.imgDrawable = imgDrawable;
            this.textResId = textResId;
        }
    }
}
