package com.jingcai.jingcaic.adapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.jingcai.jingcaic.R;
import com.jingcai.jingcaic.activity.homepage.SearchActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ������¼������
 * @author ��Т��
 *2016��11��7��
 */
public class SearchAutoAdapter extends BaseAdapter {
	private Context mContext;
	private ArrayList<String> mOriginalValues;// ���е�Item
	private List<String> mObjects;// ���˺��item
	private final Object mLock = new Object();
	private int mMaxMatch = 5;// �����ʾ���ٸ�ѡ��,������ʾȫ��

	public SearchAutoAdapter(Context context, int maxMatch) {
		this.mContext = context;
		this.mMaxMatch = maxMatch;
		initSearchHistory();
		mObjects = mOriginalValues;
	}

	//�õ���������  
	@Override
	public int getCount() {
		//һ��ʼmobject��Ϊnull��
		if (mObjects != null)
		{
			if (mObjects.isEmpty()) 
			{
//				Log.i("getCount", String.valueOf(mObjects.size()));
				return 0;
			}
			else 
			{
				return mObjects.size()+1;
			}
		} 
		else 
		{
			return 0;
		}
		//		return null == mObjects ? 0 : mObjects.size()+1;
	}

	//�õ�ÿһ������  
	@Override
	public Object getItem(int position) {
		if (mObjects != null) 
		{
			if (!mObjects.isEmpty()) 
			{
				if (position == mObjects.size()) 
				{
					return 0;
				}
				else 
				{
					return mObjects.get(position);
				}
			}
			else 
			{
				return null;
			}
		} 
		else 
		{
			return null;
		}
		//		return null == mObjects ? null : mObjects.get(position);
	}

	//�õ���Ŀ��λ��  
	@Override
	public long getItemId(int position) {
		return position;
	}

	/** 
	 * ListView������ʾ��item����ͨ������Adapter�����getView�������õ�һ��View���� 
	 * Ȼ������View����������item�У�������һ�����̣������ListView��Adapter֮��Ĺ�ϵ 
	 */ 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		AutoHolder holder;
		RelativeLayout cleanHistoryLayout;
		final int location = position;

		if (mObjects != null) 
		{
			if (!mObjects.isEmpty()) 
			{
				if (position == mObjects.size()) 
				{
					convertView = LayoutInflater.from(mContext).inflate(
							R.layout.fragment_seach_clean_history_item, parent, false);
					cleanHistoryLayout = (RelativeLayout) convertView.findViewById(R.id.clean_history_layout);
					cleanHistoryLayout.setOnClickListener(new OnClickListener() {

						@SuppressLint("NewApi")
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							SharedPreferences sp = mContext.getSharedPreferences(
									SearchActivity.SEARCH_HISTORY, 0);
							String longhistory = sp.getString(SearchActivity.SEARCH_HISTORY, "");
							if (longhistory.isEmpty()) 
							{
								Toast.makeText(mContext, "�Ѿ����������ʷ", Toast.LENGTH_LONG).show();
							}else 
							{
								cleanHistory();
								mObjects.clear();
								mOriginalValues.clear();
								notifyDataSetChanged();
								Toast.makeText(mContext, "�ɹ����������ʷ", Toast.LENGTH_LONG).show();
							}
						}
					});
				}
				else 
				{

					convertView = LayoutInflater.from(mContext).inflate(
							R.layout.fragment_seach_list_item, parent, false);
					holder = new AutoHolder();
					holder.content = (TextView) convertView
							.findViewById(R.id.auto_content);
					holder.addButton = (ImageView) convertView
							.findViewById(R.id.auto_add);

					holder.content.setText(mObjects.get(position));
					holder.addButton.setTag(mObjects.get(position));

					holder.addButton.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							deleteItem(location);
						}
					});
				}
			}
		} 

		return convertView;
	}

	//���������¼
	public void cleanHistory(){
		SharedPreferences sp = mContext.getSharedPreferences(SearchActivity.SEARCH_HISTORY, 0);
		SharedPreferences.Editor editor=sp.edit();
		editor.clear();
		editor.commit();
	}

	/** 
	 * @Title: deleteItem ɾ��Item
	 * @Description: TODO
	 * @param @param position
	 * @return void
	 * @throws 
	 * @auhor ��Т��
	 * @date 2016.11.5
	 */
	protected void deleteItem(int position) {
		String data = mObjects.get(position);
		mObjects.remove(position);
		SharedPreferences sp = mContext.getSharedPreferences(
				SearchActivity.SEARCH_HISTORY, 0);
		String longhistory = sp.getString(SearchActivity.SEARCH_HISTORY, "");
		String[] tmpHistory = longhistory.split(",");
		ArrayList<String> history = new ArrayList<String>(
				Arrays.asList(tmpHistory));
		//������
		if (history.size() > 0) {
			int i;
			for (i = 0; i < history.size(); i++) {
				if (data.equals(history.get(i))) {
					Log.i("data.equals(history.get(i))", "ok");
					history.remove(i);
					break;
				}
			}

			mOriginalValues = history;

			StringBuilder sb = new StringBuilder();
			for (i = 0; i < history.size(); i++) {
				sb.append(history.get(i) + ",");
			}
			sp.edit().putString(SearchActivity.SEARCH_HISTORY, sb.toString()).commit();
			Log.i("xianhua tag", "sp ok");
		}

		notifyDataSetChanged();
	}

	/**
	 * ��ȡ��ʷ������¼
	 */
	public void initSearchHistory() {
		SharedPreferences sp = mContext.getSharedPreferences(
				SearchActivity.SEARCH_HISTORY, 0);
		String longhistory = sp.getString(SearchActivity.SEARCH_HISTORY, "");
		String[] hisArrays = longhistory.split(",");
		mOriginalValues = new ArrayList<String>();
		if ( !longhistory.contains(",")) {
			return;
		}
		if (hisArrays.length < 1) {
			return;
		}
		if (hisArrays.length > 5) {
			for (int i = 0; i < 5; i++) {
				mOriginalValues.add(hisArrays[i]);
			}
		} else {
			for (int i = 0; i < hisArrays.length; i++) {
				mOriginalValues.add(hisArrays[i]);
			}
		}

	}
	/**
	 * ƥ�������������
	 * 
	 * @param prefix
	 *  ����������������
	 */
	public void performFiltering(CharSequence prefix) {
		if (prefix == null || prefix.length() == 0) {//����������Ϊ�յ�ʱ����ʾ������ʷ��¼
			synchronized (mLock) {
				mObjects = mOriginalValues;
			}
		} else{
			String prefixString = prefix.toString();
			int count = mOriginalValues.size();
			ArrayList<String> newValues = new ArrayList<String>(
					count);
			for (int i = 0; i < count; i++) {
				final String value = mOriginalValues.get(i);
				final String valueText = value;
				if (valueText.contains(prefixString))	//valueText���Ƿ������prefixString�ֶ�
				{
				}
				if (valueText.startsWith(prefixString)) {
					newValues.add(valueText);
				} 
				else 
				{
					final String[] words = valueText.split(" ");
					final int wordCount = words.length;
					for (int k = 0; k < wordCount; k++) 
					{
						if (words[k].startsWith(prefixString)) 
						{
							newValues.add(value);
							break;
						}
					}
				}
				if (mMaxMatch > 0) {
					if (newValues.size() > mMaxMatch - 1) {
						break;
					}
				}
			}
			mObjects = newValues;
		}
		notifyDataSetChanged();
	}

	private class AutoHolder {
		TextView content;
		ImageView addButton;
	}
}
