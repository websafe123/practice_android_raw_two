package com.example.myapplication.starfrag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.bean.StarBean;
import com.example.myapplication.utils.AssetUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class StarAnalysisActivity extends AppCompatActivity implements View.OnClickListener{
    TextView titleTv;
    ImageView backIv;
    CircleImageView iconIv;
    TextView nameTv,dateTv;
    ListView analysisLv;
    StarBean.StarinfoBean bean;
    private Map<String, Bitmap> contentlogoImgMap;
    private TextView footerTv;  //ListView底部布局当中需要改变的TextView
    List<StarAnalysisBean> mDatas;
    private AnalysisBaseAdpter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_analysis);
        Intent intent = getIntent();
        bean = (StarBean.StarinfoBean) intent.getSerializableExtra("star");
        initView();
        mDatas = new ArrayList<>();   //初始化显示在ListView上的数据源
        adapter = new AnalysisBaseAdpter(this, mDatas);
        analysisLv.setAdapter(adapter);
        addDataToList();

    }
    private void initView() {
        titleTv = findViewById(R.id.title_tv);
        backIv = findViewById(R.id.title_iv_back);
        iconIv = findViewById(R.id.staranalysis_iv);
        nameTv = findViewById(R.id.starnanlysis_tv_name);
        dateTv = findViewById(R.id.starnanlysis_tv_date);
        analysisLv = findViewById(R.id.staranalysis_lv);
//        为ListView添加底部布局
        View footerView = LayoutInflater.from(this).inflate(R.layout.footer_star_analysis, null);
        analysisLv.addFooterView(footerView);
        footerTv = footerView.findViewById(R.id.footerstar_tv_info);
//      将数据进行显示
        titleTv.setText("星座详情");
        backIv.setOnClickListener(this);
        nameTv.setText(bean.getName());
        dateTv.setText(bean.getDate());
        contentlogoImgMap = AssetUtils.getContentlogoImgMap();
        Bitmap bitmap = contentlogoImgMap.get(bean.getLogoname());
        iconIv.setImageBitmap(bitmap);
        footerTv.setText(bean.getInfo());
    }

    @Override
    public void onClick(View v) {
        finish();
    }
    private void addDataToList() {
        StarAnalysisBean sab1 = new StarAnalysisBean("性格特点 :", bean.getTd(), R.color.lightblue);
        StarAnalysisBean sab2 = new StarAnalysisBean("掌管宫位 :", bean.getGw(), R.color.lightpink);
        StarAnalysisBean sab3 = new StarAnalysisBean("显阴阳性 :", bean.getYy(), R.color.lightgreen);
        StarAnalysisBean sab4 = new StarAnalysisBean("最大特征 :", bean.getTz(), R.color.purple);
        StarAnalysisBean sab5 = new StarAnalysisBean("主管星球 :", bean.getZg(), R.color.orange);
        StarAnalysisBean sab6 = new StarAnalysisBean("幸运颜色 :", bean.getYs(), R.color.colorAccent);
        StarAnalysisBean sab7 = new StarAnalysisBean("搭配珠宝 :", bean.getZb(), R.color.colorPrimary);
        StarAnalysisBean sab8 = new StarAnalysisBean("幸运号码 :", bean.getHm(), R.color.grey);
        StarAnalysisBean sab9 = new StarAnalysisBean("相配金属 :", bean.getJs(), R.color.darkblue);
        mDatas.add(sab1);
        mDatas.add(sab2);
        mDatas.add(sab3);
        mDatas.add(sab4);
        mDatas.add(sab5);
        mDatas.add(sab6);
        mDatas.add(sab7);
        mDatas.add(sab8);
        mDatas.add(sab9);

//        数据源发生变化，提示适配器更新
        adapter.notifyDataSetChanged();
    }
}