package com.jingcai.jingcaic.activity.homepage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.jingcai.jingcaic.R;
import com.jingcai.jingcaic.activity.BaseActivity;
import com.jingcai.jingcaic.adapter.SearchAutoAdapter;

public class SearchActivity extends BaseActivity implements OnClickListener{
	public static final String SEARCH_HISTORY = "search_history";
	private ListView mAutoListView;
	public EditText mAutoEdit;
	public Button mSearchButtoon;
	public Context context;
	private SearchAutoAdapter mSearchAutoAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_serach);
		context=getApplicationContext();
		//弹出软键盘
		mAutoEdit=(EditText) findViewById(R.id.auto_edit);   
        Timer timer=new Timer();
        //防止页面未加载完成延时200毫秒弹软键盘
        timer.schedule(new TimerTask() {
			@Override
			public void run() {
				  InputMethodManager inputManager =    

		                    (InputMethodManager)mAutoEdit.getContext().getSystemService(context.INPUT_METHOD_SERVICE);    

		                inputManager.showSoftInput(mAutoEdit, 0);    
			}
		}, 200);  
        init();
	}
	
	private void init() {
		mSearchAutoAdapter = new SearchAutoAdapter(this, 5);
		mAutoListView = (ListView) findViewById(R.id.auto_listview);
		mAutoListView.setAdapter(mSearchAutoAdapter);
		mAutoListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long arg3) {
				String data = (String) mSearchAutoAdapter.getItem(position);
				mAutoEdit.setText(data);
				mSearchButtoon.performClick();
			}
		});

		mSearchButtoon=(Button) findViewById(R.id.search_button);
		mSearchButtoon.setOnClickListener(this);
		mAutoEdit = (EditText) findViewById(R.id.auto_edit);
		mAutoEdit.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				mSearchAutoAdapter.performFiltering(s);
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.search_button) {//搜索按钮
			saveSearchHistory();
			mSearchAutoAdapter.initSearchHistory();
			Toast.makeText(this, "点击搜索成功", Toast.LENGTH_LONG).show();
		} 
	}
	
	/*
	 * 保存搜索记录
	 */
	private void saveSearchHistory() {
		String text = mAutoEdit.getText().toString().trim();
		if (text.length() < 1) 
		{
			return;
		}
		SharedPreferences sp = getSharedPreferences(SEARCH_HISTORY, 0);
		String longhistory = sp.getString(SEARCH_HISTORY, "");
		String[] tmpHistory  = longhistory.split(",");
		ArrayList<String> history = new ArrayList<String>(
				Arrays.asList(tmpHistory));
		//检查历史记录是否已经存在当前输入的text，如果存在则删除
		if (history.size() > 0) 
		{
			int i;
			for (i = 0; i < history.size(); i++) 
			{
				if (text.equals(history.get(i))) 
				{
					history.remove(i);
					break;
				}
			}
			//如果记录大于4个，则移除最后一个数据再在最前面增加一个数据
			if (history.size() > 4) {
				history.remove(history.size()-1);
			}
			history.add(0, text);
		}

		//重新加，提交
		if (history.size() > 0) //history.size()>1和history.size()>0一样
		{
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < history.size(); i++) 
			{
				sb.append(history.get(i) + ",");//这句一开始添加一个数据时加了两个,	why？ 用String也是 因为""
			}
			sp.edit().putString(SEARCH_HISTORY, sb.toString()).commit();
		} 
		else 
		{
			sp.edit().putString(SEARCH_HISTORY, text + ",").commit();
		}
	}
	
	
	
}
