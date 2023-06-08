package com.bw.a6_8exam.mvvm.view.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<V,P extends BasePresenter<V>> extends Fragment {
    View inflate;
    protected P presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        inflate=inflater.inflate(bindLayout(),null);
        initView(savedInstanceState);
        initData();
        if(presenter!=null){
            presenter.attachView((V) this);
        }
        return inflate;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.distach();
        }

    }

    public <V extends View> V findViewById(int id){
        return inflate.findViewById(id);
    }

    protected abstract void initData();

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract int bindLayout();
}
