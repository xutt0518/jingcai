package com.jingcai.jingcaic.activity.me;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jingcai.jingcaic.R;
import com.jingcai.jingcaic.activity.BaseActivity;

public class FavorActivity extends BaseActivity implements OnClickListener{
	private TextView qgwc;
    private LinearLayout ly_bj;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		isTemplate=true;
		isTemplate=true;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myfavor);
		titleView.setText("�ҵ��ղ�");
		templateTextViewRight.setVisibility(View.VISIBLE);
		templateTextViewRight.setText("�༭");		
		qgwc=(TextView)findViewById(R.id.text_qgwc);
		ly_bj=(LinearLayout)findViewById(R.id.ly_bj);
		templateTextViewRight.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
	switch(v.getId()){
	case R.id.title_tv_right:
        ly_bj.setVisibility(View.VISIBLE);
        qgwc.setText("ɾ��");
        templateTextViewRight.setText("���");
		break;
	default:
		break;
	}			
	}

}
