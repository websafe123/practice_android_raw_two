package com.example.myapplication.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class LoadDataAysnTask extends AsyncTask<String,Void,String> {
    Context context;
    OnGetNetDataListener listener;
    ProgressDialog dialog;
    private boolean isDialog;

    private void initDialog(){
        dialog = new ProgressDialog(context);
        dialog.setTitle("提示信息");
        dialog.setMessage("正在加载中.....");

    }

    public LoadDataAysnTask(Context context,OnGetNetDataListener listener,boolean isDialog){
        this.context = context;
        this.listener = listener;
        this.isDialog = isDialog;
        initDialog();
    }
    public interface OnGetNetDataListener{
        void onSuccess(String json);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.e("tag","访问后----------");
        if(isDialog){
            dialog.show();
        }
        listener.onSuccess(s);
    }

    @Override
    protected String doInBackground(String ... strings) {
        String json = HttpUtils.getJSONFromNet(strings[0]);
        Log.d("tag","访问中------------------");
        return json;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("tag","访问前---------------------");
        if(isDialog){
            Log.d("tag","dialog不显示");
            dialog.show();
        }
    }
}
