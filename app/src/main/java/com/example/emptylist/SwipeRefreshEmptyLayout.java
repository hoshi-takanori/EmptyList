package com.example.emptylist;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Based on Android SwipeRefreshMultipleViews Sample at
 * https://github.com/googlesamples/android-SwipeRefreshMultipleViews
 */
public class SwipeRefreshEmptyLayout extends SwipeRefreshLayout {
    private View mTargetView;
    private View mEmptyView;

    public SwipeRefreshEmptyLayout(Context context) {
        super(context);
    }

    public SwipeRefreshEmptyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setViews(View targetView, View emptyView) {
        mTargetView = targetView;
        mEmptyView = emptyView;
    }

    @Override
    public boolean canChildScrollUp() {
        View view = mEmptyView != null && mEmptyView.isShown() ? mEmptyView : mTargetView;
        return ViewCompat.canScrollVertically(view, -1);
    }
}
