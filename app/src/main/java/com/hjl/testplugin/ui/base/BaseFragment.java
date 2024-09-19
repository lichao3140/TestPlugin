package com.hjl.testplugin.ui.base;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.hjl.testplugin.R;
import com.hjl.testplugin.ui.activity.MainActivity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseFragment<T extends ViewBinding> extends BaseCommonFragment {

    protected T binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Type superclass = getClass().getGenericSuperclass();
        Class<?> aClass = (Class<?>) ((ParameterizedType) superclass).getActualTypeArguments()[0];
        try {
            Method method = aClass.getDeclaredMethod("inflate", LayoutInflater.class, ViewGroup.class, boolean.class);
            binding = (T) method.invoke(null, getLayoutInflater(), container, false);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return binding.getRoot();
    }

    public void initBack(TextView tvBack) {
        if (tvBack != null) {
            Drawable drawable = getResources().getDrawable(R.mipmap.ic_back);
            drawable.setBounds(0, 0, getResources().getDimensionPixelSize(R.dimen.normal_9dp), getResources().getDimensionPixelSize(R.dimen.normal_13dp));
            tvBack.setCompoundDrawables(drawable, null, null, null);
            tvBack.setOnClickListener(v -> pop());
        }

    }

    @Override
    public void onViewCreated(View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public MainActivity getMainActivity() {
        return (MainActivity) mActivity;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        binding = null;
        super.onDestroy();
    }

    @Override
    public void onStop() {
        super.onStop();
        onDestroyView();//popTo后不会走onDestroyView
    }
}

