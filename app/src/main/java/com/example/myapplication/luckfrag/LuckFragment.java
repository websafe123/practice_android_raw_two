package com.example.myapplication.luckfrag;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.myapplication.R;
import com.example.myapplication.bean.StarBean;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LuckFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LuckFragment extends Fragment {
    GridView luckGv;
    List<StarBean.StarinfoBean> mDatas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_luck,container,false);
        luckGv = view.findViewById(R.id.luckfrag_gv);
        Bundle bundle = getArguments();
        StarBean infoBean = (StarBean) bundle.getSerializable("info");
        mDatas = infoBean.getStarinfo();
        LuckBaseAdpter adpter = new LuckBaseAdpter(getContext(),mDatas);
        luckGv.setAdapter(adpter);
//        luckGv.setOnItemSelectedListener(this);
        setListener();
        return view;
    }
    private void setListener() {
        luckGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StarBean.StarinfoBean bean = mDatas.get(position);
                String name = bean.getName();
                Intent intent = new Intent(getContext(), LuckActivity.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
    }
}