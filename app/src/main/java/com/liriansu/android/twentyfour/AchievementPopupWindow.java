package com.liriansu.android.twentyfour;

import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;

import java.util.Timer;
import java.util.TimerTask;

public class AchievementPopupWindow extends PopupWindow  implements  PopupWindow.OnDismissListener{

    public static final long DEFAULT_DELAY_TIME = 3000;
    private long closeDelayTime;
    private Timer timer;
    private TimerTask delayTask = new TimerTask() {
        @Override
        public void run() {
            dismiss();
        }
    };

    public AchievementPopupWindow(Context context, long closeDelayTime) {
        super(context);
        this.closeDelayTime = closeDelayTime;
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        onShow();
        super.showAtLocation(parent, gravity, x, y);
    }

    private void onShow() {
        if (closeDelayTime > 0) {
            if (null == timer) {
                timer = new Timer();
            }
            timer.schedule(delayTask, closeDelayTime);
        }
    }

    @Override
    public void setOnDismissListener(OnDismissListener onDismissListener) {
        super.setOnDismissListener(onDismissListener);
    }

    @Override
    public void onDismiss() {
        if (null != timer) {
            timer.cancel();
        }
    }
}
