package com.bw.a6_8exam;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bw.a6_8exam.mvvm.model.entity.AddGoodEntity;
import com.bw.a6_8exam.mvvm.model.entity.GoodsEntity;
import com.bw.a6_8exam.mvvm.view.base.BaseActivity;
import com.bw.a6_8exam.mvvm.viewModel.api.IMainConstract;
import com.bw.a6_8exam.mvvm.viewModel.presenter.MainPresenter;

@Route(path = "/app/XqActivity")
public class XqActivity extends BaseActivity<IMainConstract.IMainVIew, MainPresenter> implements IMainConstract.IMainVIew {

    private ImageView xqIv;
    private TextView xqDesc;
    private TextView xqPrice;

    @Autowired
    GoodsEntity.DataBean goods;
    private Button add;


    @Override
    protected int bindLayout() {
        ARouter.getInstance().inject(this);
        return R.layout.activity_xq;
    }

    @Override
    protected void initData() {
        presenter=new MainPresenter();
        presenter.addresult(1,goods.getId(),"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsImV4cCI6MTY4Njc5MzUwOSwidXNlcm5hbWUiOiJoeWoifQ.YpOTfYmVO3gXBU3lQRcGtuczug9zaZ_9r_EF02u4yX8");
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        xqIv = (ImageView) findViewById(R.id.xq_iv);
        xqDesc = (TextView) findViewById(R.id.xq_desc);
        xqPrice = (TextView) findViewById(R.id.xq_price);
        add = (Button) findViewById(R.id.add);
        if(goods!=null){
            Glide.with(this).load(goods.getGoods_default_icon()).into(xqIv);
            xqDesc.setText(goods.getGoods_desc());
            xqPrice.setText("￥"+goods.getGoods_default_price());
        }

    }




    @Override
    public void showGoods(GoodsEntity goodsEntity) {

    }

    @Override
    public void showResult(AddGoodEntity addGoodEntity) {

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Toast.makeText(XqActivity.this, "添加成功", Toast.LENGTH_SHORT).show();

            }
        });

    }
}