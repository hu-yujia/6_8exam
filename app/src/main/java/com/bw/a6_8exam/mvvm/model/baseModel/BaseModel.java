package com.bw.a6_8exam.mvvm.model.baseModel;

import com.bw.a6_8exam.mvvm.viewModel.api.ApiService;
import com.bw.a6_8exam.mvvm.viewModel.retrofit.RetrofitManager;

public class BaseModel {
    protected ApiService apiService;

    public BaseModel(){
        apiService= RetrofitManager.getRetrofitManager().getRetrofit().create(ApiService.class);
    }
}
