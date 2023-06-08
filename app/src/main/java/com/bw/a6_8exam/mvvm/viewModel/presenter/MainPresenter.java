package com.bw.a6_8exam.mvvm.viewModel.presenter;

import com.bw.a6_8exam.mvvm.model.baseModel.MainModel;
import com.bw.a6_8exam.mvvm.model.entity.AddGoodEntity;
import com.bw.a6_8exam.mvvm.model.entity.GoodsEntity;
import com.bw.a6_8exam.mvvm.view.base.BasePresenter;
import com.bw.a6_8exam.mvvm.viewModel.api.IMainConstract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<IMainConstract.IMainVIew> implements IMainConstract.IMainPresenter {

    IMainConstract.IMainModel iMainModel;

    public MainPresenter(){
        iMainModel=new MainModel();
    }
    @Override
    public void goods(int category_id, int currentPage, int pageSize) {
        iMainModel.goods(category_id, currentPage, pageSize)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<GoodsEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(GoodsEntity goodsEntity) {
                        if(isAttach()){
                            getView().showGoods(goodsEntity);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void addresult(int count, int goods_id, String token) {
        iMainModel.addresult(count, goods_id, token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<AddGoodEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(AddGoodEntity addGoodEntity) {
                        if (isAttach()){
                            getView().showResult(addGoodEntity);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
