package com.bw.a6_8exam.mvvm.viewModel.api;

import com.bw.a6_8exam.mvvm.model.entity.AddGoodEntity;
import com.bw.a6_8exam.mvvm.model.entity.GoodsEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/goods/info?")
    Observable<GoodsEntity> goods(@Query("category_id")int category_id,@Query("currentPage")int currentPage,@Query("pageSize")int pageSize);

    @POST("/goods/addCar?")
    Observable<AddGoodEntity> addresult(@Query("count")int count,@Query("goods_id")int goods_id,@Query("token")String token);
}
