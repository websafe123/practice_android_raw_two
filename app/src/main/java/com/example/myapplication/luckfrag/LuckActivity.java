package com.example.myapplication.luckfrag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.utils.HttpUtils;
import com.example.myapplication.utils.LoadDataAysnTask;
import com.example.myapplication.utils.URLContent;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class LuckActivity extends AppCompatActivity implements View.OnClickListener,LoadDataAysnTask.OnGetNetDataListener{
    ListView luckLv;
    TextView nameTv;
    ImageView backIv;
    List<LuckItemBean> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luck);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String luckURL = URLContent.getLuckURL(name);
        initView(name);
        mDatas = new ArrayList<>();
//        获取网络请求
        LoadDataAysnTask task = new LoadDataAysnTask(this, this, true);
        task.execute(luckURL);
    }
    private void initView(String name) {
        // 查找控件
        luckLv = findViewById(R.id.luckanalysis_lv);
        nameTv = findViewById(R.id.title_tv);
        backIv = findViewById(R.id.title_iv_back);
        nameTv.setText(name);
        backIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.title_iv_back:
                finish();
                break;
        }
    }
    private void addDataToList(LuckBean luckBean) {
        LuckItemBean lib1 = new LuckItemBean("综合运势",luckBean.getMima().getText().get(0), Color.BLUE);
        LuckItemBean lib2 = new LuckItemBean("爱情运势",luckBean.getLove().get(0),Color.GREEN);
        LuckItemBean lib3 = new LuckItemBean("事业学业",luckBean.getCareer().get(0),Color.GRAY);
        LuckItemBean lib4 = new LuckItemBean("健康运势",luckBean.getHealth().get(0),Color.RED);
        LuckItemBean lib5 = new LuckItemBean("财富运势",luckBean.getFinance().get(0),Color.BLACK);
        mDatas.add(lib1);
        mDatas.add(lib2);
        mDatas.add(lib3);
        mDatas.add(lib4);
        mDatas.add(lib5);
    }

    @Override
    public void onSuccess(String json) {
        if (!TextUtils.isEmpty(json)) {
//            数据的解析
            LuckBean luckBean = new Gson().fromJson(json, LuckBean.class);
//          为了显示在ListView上，重新整理数据，整理成集合的形式
            addDataToList(luckBean);
//            设置适配器
            LuckAnalysisAdapter adapter = new LuckAnalysisAdapter(this, mDatas);
            luckLv.setAdapter(adapter);
        }
    }
}