package com.hjl.testplugin.ui.wigets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.hjl.testplugin.R;

/**
 * @author：Chao
 * @date：2023/7/17
 */
public class TitleHeader extends FrameLayout {
    private TextView mTitleText;
    private TextView tvDate;
    private ImageView ivSignal, ivCharge,ivNetType;
    private OnClickListener dateClickListener, ivSignalClickLister, ivNetTypeClickLister,mTitleTextClickLister;

    public TitleHeader(Context context) {
        this(context, null);
    }

    public TitleHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public TitleHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
        setOnEvents();
        setAttrs(context, attrs);
    }

    private void initViews() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_title_header, this);
        mTitleText = view.findViewById(R.id.title);
        tvDate = view.findViewById(R.id.tvDate);
        ivSignal = view.findViewById(R.id.ivSignal);
        ivCharge = view.findViewById(R.id.ivCharge);
        ivNetType = view.findViewById(R.id.ivNetType);
    }

    private void setOnEvents() {
        tvDate.setOnClickListener(v -> {
            if (dateClickListener != null) {
                dateClickListener.onClick(v);
            }
        });

        ivSignal.setOnClickListener(view -> {
            if (ivSignalClickLister != null) {
                ivSignalClickLister.onClick(view);
            }
        });

        ivNetType.setOnClickListener(view -> {
            if (ivNetTypeClickLister != null) {
                ivNetTypeClickLister.onClick(view);
            }
        });

        mTitleText.setOnClickListener(view -> {
            if (mTitleTextClickLister != null) {
                mTitleTextClickLister.onClick(view);
            }
        });
    }

    private void setAttrs(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyHeader);
        String title = array.getString(R.styleable.MyHeader_header_title);
        if (title != null) {
            setTitle(title);
        }
        ;
    }

    public TextView getTitle() {
        return mTitleText;
    }

    public void setTitle(String title) {
        mTitleText.setText(title);
    }

    public TextView getTvDate() {
        return tvDate;
    }

    public void setTvDate(TextView tvDate) {
        this.tvDate = tvDate;
    }

    public ImageView getIvCharge() {
        return ivCharge;
    }

    public void setIvCharge(ImageView ivCharge) {
        this.ivCharge = ivCharge;
    }

    public ImageView getIvSignal() {
        return ivSignal;
    }

    public void setIvSignal(ImageView ivSignal) {
        this.ivSignal = ivSignal;
    }

    public ImageView getIvNetType() {
        return ivNetType;
    }

    public void setIvNetType(ImageView ivNetType) {
        this.ivNetType = ivNetType;
    }

    public void setOnDateClickListener(OnClickListener listener) {
        dateClickListener = listener;
    }

    public void setOnIvSignalClickLister(OnClickListener listener) {
        ivSignalClickLister = listener;
    }

    public void setOnIvNetTypeClickLister(OnClickListener listener) {
        ivNetTypeClickLister = listener;
    }

    public void setOnTitleTextClickLister(OnClickListener listener) {
        mTitleTextClickLister = listener;
    }

}
