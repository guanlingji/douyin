package com.javaer.jdouyin;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import aweme.custom.CustomLabelTextView;
import aweme.custom.CustomLinearLayout;
import aweme.ui.adapter.InfoAdapter;
import data.Info;
import data.InfoEnum;
import data.VersionEnum;
import util.PackageUtil;
import util.Util;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener{

    public static final String MAIN_PKG = "com.javaer.jdouyin";
    public static final String DOUYIN_PKG = "com.ss.android.ugc.aweme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        root.setLayoutParams(params);
        root.setGravity(Gravity.CENTER_VERTICAL);

        LinearLayout linearLayout1 = new LinearLayout(this);
        LinearLayout linearLayout2 = new LinearLayout(this);
        linearLayout2.setPadding(20, 20, 20, 0);
        linearLayout2.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));

        CustomLinearLayout layout = new CustomLinearLayout(this);

        linearLayout2.addView(layout);


        root.addView(linearLayout1);
        root.addView(linearLayout2);

        setContentView(root);



        init(linearLayout1, layout);
    }

    public void init(LinearLayout linearLayout1, CustomLinearLayout layout){

        ListView listView = new ListView(this);
        listView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        listView.setOnItemClickListener(this);
        InfoAdapter adapter = new InfoAdapter(this);
        adapter.setItems(getList());
        listView.setAdapter(adapter);

        linearLayout1.addView(listView);

        String[] colors = new String[]{"#417BC7", "#6FA13E", "#DB9548", "#C8382E", "#753B94"};
//        for (int i = 0; i < 10; i++){
//            CustomLabelTextView customLabelTextView = new CustomLabelTextView(
//                    this,
//                    "测试文字",
//                    "test"+i,
//                    "#58575c",
//                    CustomLabelTextView.randomColorString(colors));
//            layout.addView(customLabelTextView);
//        }
        initModel(layout);
        for (VersionEnum versionEnum : VersionEnum.values()){
            CustomLabelTextView customLabelTextView = new CustomLabelTextView(
                    this,
                    versionEnum.isSupported() ? "已支持版本" : "不支持版本",
                    versionEnum.getVersion(),
                    "#58575c",
                    CustomLabelTextView.randomColorString(colors));
            layout.addView(customLabelTextView);
        }
    }

    private void initModel(CustomLinearLayout layout){
        CustomLabelTextView customLabelTextView_Title = new CustomLabelTextView(this, "已支持", "去除视频广告");

        layout.addView(customLabelTextView_Title);

        CustomLabelTextView customLabelTextView_Title1 = new CustomLabelTextView(this, "已支持", "下载无水印视频");
        layout.addView(customLabelTextView_Title1);
        CustomLabelTextView customLabelTextView_Title2 = new CustomLabelTextView(this, "已支持", "跳过首页广告");
        layout.addView(customLabelTextView_Title2);
    }

    private List<Info> getList(){
        List<Info> list = new ArrayList<>();
        list.addAll(getModel());
        for (InfoEnum infoEnum : InfoEnum.values()){
            Info info = new Info();
            info.setType(infoEnum.getType());
            info.setTitle(infoEnum.getTitle());
            info.setSubTitle(infoEnum.getSubTitle());
            list.add(info);
        }
        return list;
    }

    private List<Info> getModel(){
        List<Info> list = new ArrayList<>();
        Info info = new Info();
        info.setType(1);
        info.setTitle("插件版本");
        info.setSubTitle("Build_"+ PackageUtil.getAppVersion(MAIN_PKG, this));
        list.add(info);
        info = new Info();
        info.setType(1);
        info.setTitle("抖音版本");
        info.setSubTitle(PackageUtil.getAppVersion(DOUYIN_PKG, this) + "_" + PackageUtil.getAppVersionCode(DOUYIN_PKG, this));
        list.add(info);
        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                String url = "https://github.com/javaeryang/douyin";
                Util.openUrl(url, this);
                break;
            case 4:
                String[] urls = new String[]{"HTTPS://QR.ALIPAY.COM/FKX09291CRJV1DVWMYRF47?t=1532799394783",
                "https://qr.alipay.com/c1x06037wulhdkegmso7rec"};
                int random = new Random().nextInt(2);
                Util.gotoAlipay(this, urls[random]);
                break;
            case 5:
                String key = "024zhiRbVwMHKsVZc1MN3t5Pq8vA99Mb";
                Util.joinGroup(key, this);
                break;
        }
    }
}
