package com.bw.a6_8exam.mvvm.view.base;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter<V> {
    private WeakReference<V> weakReference;
    protected CompositeDisposable disposable=new CompositeDisposable();

    public void attachView(V view){
        weakReference=new WeakReference<V>(view);
    }

    public void distach(){
        if(weakReference!=null){
            weakReference.clear();
            weakReference=null;
        }
        if(disposable!=null){
            disposable.clear();
            disposable=null;
        }
    }

    public boolean isAttach(){
        return weakReference!=null && weakReference.get()!=null;
    }

    public V getView(){
        return weakReference!=null && weakReference.get()!=null ? weakReference.get() : null;
    }
}
