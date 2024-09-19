package com.hjl.testplugin.ui.base;

import androidx.viewbinding.ViewBinding;

import me.jessyan.autosize.internal.CustomAdapt;

public abstract class BaseLandActivity<T extends ViewBinding> extends BaseActivity<T>
        implements CustomAdapt {
    @Override
    public boolean isBaseOnWidth() {
        return true;
    }

    @Override
    public float getSizeInDp() {
        return 640;
    }
}
