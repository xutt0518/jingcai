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
		//���������
		mAutoEdit=(EditText) findViewById(R.id.auto_edit);   
        Timer timer=new Timer();
        //��ֹҳ��δ���������ʱ200���뵯�����
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
		if (id == R.id.search_button) {//������ť
			saveSearchHistory();
			mSearchAutoAdapter.initSearchHistory();
			Toast.makeText(this, "��������ɹ�", Toast.LENGTH_LONG).show();
		} 
	}
	
	/*
	 * ����������¼
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
		//�����ʷ��¼�Ƿ��Ѿ����ڵ�ǰ�����text�����������ɾ��
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
			//�����¼����4�������Ƴ����һ������������ǰ������һ������
			if (history.size() > 4) {
				history.remove(history.size()-1);
			}
			history.add(0, text);
		}

		//���¼ӣ��ύ
		if (history.size() > 0) //history.size()>1��history.size()>0һ��
		{
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < history.size(); i++) 
			{
				sb.append(history.get(i) + ",");//���һ��ʼ���һ������ʱ��������,	why�� ��StringҲ�� ��Ϊ""
			}
			sp.edit().putString(SEARCH_HISTORY, sb.toString()).commit();
		} 
		else 
		{
			sp.edit().putString(SEARCH_HISTORY, text + ",").commit();
		}
	}
	
	
	
}
