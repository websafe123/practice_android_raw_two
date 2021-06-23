package com.example.myapplication.partnerfrag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.utils.AssetUtils;
import com.example.myapplication.utils.LoadDataAysnTask;
import com.example.myapplication.utils.URLContent;
import com.google.gson.Gson;

import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ParnterAnalysisActivity extends AppCompatActivity implements View.OnClickListener ,LoadDataAysnTask.OnGetNetDataListener{
    TextView manTv,womanTv,pdTv,vsTv,pfTv,bzTv,jxTv,zyTv,titleTv;
    CircleImageView manIv,womanIv;
    ImageView backIv;
    private String man_name;
    private String man_logoname;
    private String woman_name;
    private String woman_logoname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parnter_analysis);
        initView();
        getLastData();
        String path = URLContent.getParnterURL(man_name,woman_name);
        LoadDataAysnTask  task = new LoadDataAysnTask(this,this,true);
        task.execute(path);
    }
    @Override
    public void onSuccess(String json) {
        if (!TextUtils.isEmpty(json)) {
            ParnterAnalysisBean analysisBean = new Gson().fromJson(json, ParnterAnalysisBean.class);
            ParnterAnalysisBean.ResultBean resultBean = analysisBean.getResult();

            pfTv.setText("配对评分: "+resultBean.getZhishu()+"  "+resultBean.getJieguo());
            bzTv.setText("星座比重: "+resultBean.getBizhong());
            jxTv.setText("解析:\n\n"+resultBean.getLianai());
            zyTv.setText("注意事项:\n\n"+resultBean.getZhuyi());
        }

    }

    private void getLastData() {
        Intent intent = getIntent();
        man_name = intent.getStringExtra("man_name");
        man_logoname = intent.getStringExtra("man_logoname");
        woman_name = intent.getStringExtra("woman_name");
        woman_logoname = intent.getStringExtra("woman_logoname");
        // 设置能够显示的控件信息
        Map<String, Bitmap> contentlogoImgMap = AssetUtils.getContentlogoImgMap();
        Bitmap bitmap_man = contentlogoImgMap.get(man_logoname);
        manIv.setImageBitmap(bitmap_man);
        Bitmap bitmap_woman = contentlogoImgMap.get(woman_logoname);
        womanIv.setImageBitmap(bitmap_woman);
        pdTv.setText("星座配对-"+man_name+"和"+woman_name+"配对");
        vsTv.setText(man_name+" vs "+woman_name);
    }

    private void initView() {
        manIv = findViewById(R.id.parnteranalysis_iv_man);
        womanIv = findViewById(R.id.parnteranalysis_iv_woman);
        backIv = findViewById(R.id.title_iv_back);
        manTv = findViewById(R.id.parnteranalysis_tv_man);
        womanTv = findViewById(R.id.parnteranalysis_tv_woman);
        pdTv = findViewById(R.id.parnteranalysis_tv_pd);
        vsTv = findViewById(R.id.parnteranalysis_tv_vs);
        pfTv = findViewById(R.id.parnteranalysis_tv_pf);
        bzTv = findViewById(R.id.parnteranalysis_tv_bz);
        jxTv= findViewById(R.id.parnteranalysis_tv_jx);
        zyTv = findViewById(R.id.parnteranalysis_tv_zy);
        titleTv = findViewById(R.id.title_tv);
        backIv.setOnClickListener(this);
        titleTv.setText("配对详情");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_iv_back:
                finish();
                break;
        }
    }
}