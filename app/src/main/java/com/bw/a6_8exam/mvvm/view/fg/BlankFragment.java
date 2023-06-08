package com.bw.a6_8exam.mvvm.view.fg;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bw.a6_8exam.R;
import com.bw.a6_8exam.mvvm.model.entity.AddGoodEntity;
import com.bw.a6_8exam.mvvm.model.entity.GoodsEntity;
import com.bw.a6_8exam.mvvm.view.base.BaseFragment;
import com.bw.a6_8exam.mvvm.viewModel.adapter.GoodsAdapter;
import com.bw.a6_8exam.mvvm.viewModel.api.IMainConstract;
import com.bw.a6_8exam.mvvm.viewModel.presenter.MainPresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;


public class BlankFragment extends BaseFragment<IMainConstract.IMainVIew, MainPresenter> implements IMainConstract.IMainVIew {

    private RecyclerView rv;

    GoodsAdapter goodsAdapter;
    @Override
    protected void initData() {
        presenter=new MainPresenter();
        presenter.goods(0,1,10);
        goodsAdapter=new GoodsAdapter(R.layout.item_goods);
        rv.setAdapter(goodsAdapter);
        rv.setLayoutManager(new GridLayoutManager(getContext(),2));


        goodsAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                ARouter.getInstance().build("/app/XqActivity").withSerializable("goods",goodsAdapter.getData().get(position)).navigation();
            }
        });
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        rv = (RecyclerView) findViewById(R.id.rv);
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_blank;
    }

    @Override
    public void showGoods(GoodsEntity goodsEntity) {
        goodsAdapter.addData(goodsEntity.getData());
        goodsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showResult(AddGoodEntity addGoodEntity) {

    }
}