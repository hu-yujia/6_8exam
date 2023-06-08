package com.bw.a6_8exam.mvvm.viewModel.api;

import com.bw.a6_8exam.mvvm.model.entity.AddGoodEntity;
import com.bw.a6_8exam.mvvm.model.entity.GoodsEntity;

import io.reactivex.Observable;
import retrofit2.http.Query;

public interface IMainConstract {
    interface IMainModel{
        Observable<GoodsEntity> goods(@Query("category_id")int category_id, @Query("currentPage")int currentPage, @Query("pageSize")int pageSize);

        Observable<AddGoodEntity> addresult(@Query("count")int count, @Query("goods_id")int goods_id, @Query("token")String token);
    }
    interface IMainPresenter{
        void goods(@Query("category_id")int category_id, @Query("currentPage")int currentPage, @Query("pageSize")int pageSize);

        void addresult(@Query("count")int count, @Query("goods_id")int goods_id, @Query("token")String token);
    }
    interface IMainVIew{
        void showGoods(GoodsEntity goodsEntity);

        void showResult(AddGoodEntity addGoodEntity);
    }
}
