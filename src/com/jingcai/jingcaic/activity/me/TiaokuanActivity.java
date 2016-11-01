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

public class TiaokuanActivity extends BaseActivity implements OnClickListener{
	private WebView tk;
	@Override
	protected void onCreate(Bundle savedInstanceState) {	
			isTemplate=true;
			super.onCreate(savedInstanceState);
			setContentView(R.layout.service_tiaokuan);
			titleView.setText("服务条款");	
			tk=(WebView)findViewById(R.id.webview_tk);
			WebSettings webSettings=tk.getSettings();
			webSettings.setLayoutAlgorithm(LayoutAlgorithm.NORMAL);
			//webSettings.setLoadWithOverviewMode(true); 
			tk.setBackgroundColor(Color.TRANSPARENT); // WebView 背景透明效果，不知道为什么在xml配置中无法设置？
			tk.loadUrl("file:///android_asset/explain.html");
	}
	@Override
	public void onClick(View v) {
		
	}
}
