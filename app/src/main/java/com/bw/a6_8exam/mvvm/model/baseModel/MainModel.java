package com.bw.a6_8exam.mvvm.model.baseModel;

import com.bw.a6_8exam.mvvm.model.entity.AddGoodEntity;
import com.bw.a6_8exam.mvvm.model.entity.GoodsEntity;
import com.bw.a6_8exam.mvvm.viewModel.api.IMainConstract;

import io.reactivex.Observable;

public class MainModel extends BaseModel implements IMainConstract.IMainModel {
    @Override
    public Observable<GoodsEntity> goods(int category_id, int currentPage, int pageSize) {
        return apiService.goods(category_id, currentPage, pageSize);
    }

    @Override
    public Observable<AddGoodEntity> addresult(int count, int goods_id, String token) {
        return apiService.addresult(count, goods_id, token);
    }
}
