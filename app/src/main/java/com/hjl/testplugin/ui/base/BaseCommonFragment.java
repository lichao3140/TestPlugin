package com.hjl.testplugin.ui.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;


/**
 * 基础Fragment，建议继承此类再写一个BaseFragment
 */
public abstract class BaseCommonFragment extends BaseFragmentationFragment implements UiView {

    protected Activity mActivity;


    /**
     * 设置视图资源id
     *
     * @return
     */


    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        super.onAttach(context);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void startActivity(Class<? extends Activity> cls) {
        startActivity(new Intent(getActivity(), cls));
    }

    @Override
    public void startActivityForResult(Class<? extends Activity> cls, int requestCode) {
        startActivityForResult(new Intent(getActivity(), cls), requestCode);
    }

    @Override
    public void finishActivity() {
        try {
            getActivityView().finishActivity();
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    @Override
    public void showToast(int resId) {
        try {
            getActivityView().showToast(resId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showToast(String text) {
        try {
            getActivityView().showToast(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showOneToast(int resId) {
        try {
            getActivityView().showOneToast(resId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showOneToast(String text) {
        try {
            getActivityView().showOneToast(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showWaitingDialog(String text, boolean cancelable) {
        try {
            getActivityView().showWaitingDialog(text, cancelable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showWaitingDialog(int stringRes, boolean cancelable) {
        try {
            getActivityView().showWaitingDialog(stringRes, cancelable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public void dismissWaitingDialog() {
        try {
            getActivityView().dismissWaitingDialog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isActivityStarted() {
        try {
            return getActivityView().isActivityStarted();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isActivityResumed() {
        try {
            return getActivityView().isActivityResumed();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private UiView getActivityView() {
        return (UiView) mActivity;
    }
}
