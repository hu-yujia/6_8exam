package com.bw.a6_8exam.mvvm.viewModel.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bw.a6_8exam.R;
import com.bw.a6_8exam.mvvm.model.entity.GoodsEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

public class GoodsAdapter extends BaseQuickAdapter<GoodsEntity.DataBean, BaseViewHolder> {

    public GoodsAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, GoodsEntity.DataBean dataBean) {
        baseViewHolder.setText(R.id.good_tv,dataBean.getGoods_desc());
        baseViewHolder.setText(R.id.good_price,"￥"+dataBean.getGoods_default_price());

        ImageView view = baseViewHolder.getView(R.id.good_iv);

        Glide.with(getContext()).load(dataBean.getGoods_default_icon()).into(view);

    }
}
