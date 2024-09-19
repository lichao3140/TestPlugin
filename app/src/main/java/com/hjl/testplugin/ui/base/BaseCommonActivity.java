package com.hjl.testplugin.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.hjl.testplugin.ui.dialog.WaitingDialog;
import com.hjl.testplugin.ui.dialog.WaitingDialogImpl;
import com.hjl.testplugin.utils.ToastUtil;

import me.yokeyword.fragmentation.ISupportActivity;

/**
 * 基础Activity，建议继承此类再写一个BaseActivity
 */
public abstract class BaseCommonActivity extends BaseFragmentationActivity implements UiView, ISupportActivity {

    private WaitingDialog mWaitingDialog;

    /**
     * Activity调用过{@link #onStart()}
     */
    protected boolean mActivityStarted;
    /**
     * Activity调用过{@link #onResume()}
     */
    protected boolean mActivityResumed;

    /**
     * 获取布局xml的id, 子类实现，可以为0
     *
     * @return
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeSetContentView();

        afterSetContentView();
    }

    /**
     * 在设置布局前执行,可以用来设置横竖屏、屏幕常亮、修改状态栏操作之类
     */
    protected void beforeSetContentView() {
        // 在设置布局前执行
    }

    /**
     * 在设置布局后执行,用来初始化特殊的逻辑
     */
    private void afterSetContentView() {
        // 空实现
    }

    @Override
    protected void onStart() {
        super.onStart();
        mActivityStarted = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mActivityResumed = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mActivityResumed = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        mActivityStarted = false;
    }

    @Override
    protected void onDestroy() {

        dismissWaitingDialog();

        super.onDestroy();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void startActivity(Class<? extends Activity> cls) {
        startActivity(new Intent(this, cls));
    }

    @Override
    public void startActivityForResult(Class<? extends Activity> cls, int requestCode) {
        startActivityForResult(new Intent(this, cls), requestCode);
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void showToast(int resId) {
        ToastUtil.show(this, resId);
    }

    @Override
    public void showToast(String text) {
        ToastUtil.show(this, text);
    }

    @Override
    public void showOneToast(int resId) {
        showOneToast(getResources().getString(resId));
    }

    @Override
    public void showOneToast(String text) {
        ToastUtil.show(this, text);
    }

    /**
     * 获取等待对话框实例，可以重写这个方法以实现自己的等待对话框
     *
     * @return
     */
    protected WaitingDialog getWaitingDialogInstance() {
        return WaitingDialogImpl.newDialog(this);
    }

    @Override
    public void showWaitingDialog(String text, boolean cancelable) {
        if (mWaitingDialog == null) {
            mWaitingDialog = getWaitingDialogInstance();
        }
        if (mWaitingDialog.isShowing()) {
            mWaitingDialog.setMessage(text);
        } else {
            mWaitingDialog.setMessage(text).setCancelable(cancelable).show();
        }
    }

    @Override
    public void showWaitingDialog(int stringRes, boolean cancelable) {
        showWaitingDialog(getString(stringRes), cancelable);
    }

    @Override
    public void dismissWaitingDialog() {
        if (mWaitingDialog != null && mWaitingDialog.isShowing()) {
            mWaitingDialog.dismiss();
        }
    }

    @Override
    public boolean isActivityStarted() {
        return mActivityStarted;
    }

    @Override
    public boolean isActivityResumed() {
        return mActivityResumed;
    }

}
