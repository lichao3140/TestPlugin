package com.hjl.testplugin.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatDialog;

import com.hjl.testplugin.R;

/**
 * 等待对话框
 */
public class WaitingDialogImpl implements WaitingDialog<Dialog> {

    private final TextView mTvMessage;
    private final Dialog mDialog;

    public WaitingDialogImpl(@NonNull Context context) {
        mDialog = new AppCompatDialog(context, R.style.WaitingDialog);
        mDialog.setContentView(R.layout.dialog_waiting);
        mTvMessage = mDialog.findViewById(R.id.tv_message);
        DialogUtil.setGravity(mDialog, Gravity.CENTER);
    }

    public static WaitingDialogImpl newDialog(Context context) {
        return new WaitingDialogImpl(context);
    }

    /**
     * 设置对话框文本
     *
     * @param resId
     * @return
     */
    public WaitingDialogImpl setMessage(@StringRes int resId) {
        mTvMessage.setText(resId);
        return this;
    }

    /**
     * 设置对话框文本
     *
     * @param message
     * @return
     */
    public WaitingDialogImpl setMessage(CharSequence message) {
        mTvMessage.setText(message);
        return this;
    }

    @Override
    public WaitingDialog setCancelable(boolean flag) {
        mDialog.setCancelable(flag);
        return this;
    }

    @Override
    public void show() {
        mDialog.show();
    }

    @Override
    public boolean isShowing() {
        return mDialog.isShowing();
    }

    @Override
    public void dismiss() {
        mDialog.dismiss();
    }

    @Override
    public Dialog getDialog() {
        return mDialog;
    }
}
