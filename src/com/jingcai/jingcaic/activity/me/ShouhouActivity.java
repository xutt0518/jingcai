package com.jingcai.jingcaic.activity.me;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.widget.LinearLayout;

import com.jingcai.jingcaic.R;
import com.jingcai.jingcaic.activity.BaseActivity;

public class ShouhouActivity extends BaseActivity implements OnClickListener{
	private WebView shouhou;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		isTemplate=true;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service_shouhou);
		titleView.setText("�ۺ����");	
		shouhou=(WebView)findViewById(R.id.webview_sh);
		WebSettings webSettings=shouhou.getSettings();
		webSettings.setLayoutAlgorithm(LayoutAlgorithm.NORMAL);
		//webSettings.setLoadWithOverviewMode(true); 
		shouhou.setBackgroundColor(Color.TRANSPARENT); // WebView ����͸��Ч������֪��Ϊʲô��xml�������޷����ã�
		shouhou.loadUrl("file:///android_asset/explain.html");
	}
	@Override
	public void onClick(View v) {

	}
}
