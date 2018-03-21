package com.github.goodshelfview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.view.GoodShelfView;
import com.github.view.PointBean;

import java.util.ArrayList;
import java.util.List;

public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        initData();
    }

    private void initData() {
        GoodShelfView gf1 = (GoodShelfView) findViewById(R.id.gf1);//初始化
        GoodShelfView gf2 = (GoodShelfView) findViewById(R.id.gf2);
        List<PointBean> pointList1=new ArrayList<>();//集合
        List<PointBean> pointList2=new ArrayList<>();
        PointBean pointBean1=new PointBean();//设置bean
        PointBean pointBean2=new PointBean();
        pointBean2.setGoodsShelf(1);
        pointBean2.setGoodsAllocation(3);
        pointBean2.setGoodLayer(2);
        pointBean1.setGoodLayer(2);
        pointBean1.setGoodsAllocation(2);
        pointBean1.setGoodsShelf(3);
        pointList1.add(pointBean1);//添加数据到集合
        pointList2.add(pointBean2);
        gf1.setGoodsList(pointList1);//设置数据
        gf2.setGoodsList(pointList2);
    }
}
