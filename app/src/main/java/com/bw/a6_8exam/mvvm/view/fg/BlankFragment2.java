package com.bw.a6_8exam.mvvm.view.fg;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.a6_8exam.R;
import com.bw.a6_8exam.mvvm.model.entity.AddGoodEntity;
import com.bw.a6_8exam.mvvm.model.entity.GoodsEntity;
import com.bw.a6_8exam.mvvm.view.base.BaseFragment;
import com.bw.a6_8exam.mvvm.viewModel.adapter.GoodsAdapter;
import com.bw.a6_8exam.mvvm.viewModel.adapter.GoodsAdapter2;
import com.bw.a6_8exam.mvvm.viewModel.api.IMainConstract;
import com.bw.a6_8exam.mvvm.viewModel.presenter.MainPresenter;
import com.example.mypay.MyPay;


public class BlankFragment2 extends BaseFragment<IMainConstract.IMainVIew, MainPresenter> implements IMainConstract.IMainVIew {
    private RecyclerView tvF2;
    GoodsAdapter2 goodsAdapter2;
    @Override
    protected void initData() {
        presenter=new MainPresenter();
        presenter.goods(0,1,10);
        goodsAdapter2=new GoodsAdapter2(R.layout.item_goods_two);
        tvF2.setAdapter(goodsAdapter2);
        tvF2.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        tvF2 = (RecyclerView) findViewById(R.id.tv_f2);
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_blank2;
    }

    @Override
    public void showGoods(GoodsEntity goodsEntity) {
        goodsAdapter2.addData(goodsEntity.getData());
    }

    @Override
    public void showResult(AddGoodEntity addGoodEntity) {

    }


    public void gogo(View view) {
        MyPay myPay=new MyPay(getContext());
        myPay.pay(view,"13299");
    }
}