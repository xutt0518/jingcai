package com.jingcai.jingcaic.fragment.me;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jingcai.jingcaic.R;
import com.jingcai.jingcaic.activity.me.AddressActicity;
import com.jingcai.jingcaic.activity.me.FavorActivity;
import com.jingcai.jingcaic.activity.me.LoginActivity;
import com.jingcai.jingcaic.fragment.BaseFragment;

public class MeFragment extends BaseFragment implements OnClickListener{
	
    private LinearLayout ly_myorder;
    private LinearLayout ly_attention;
    private LinearLayout ly_address;
    private LinearLayout ly_wallet;
    private LinearLayout ly_coupon;
    private LinearLayout ly_store;
    private LinearLayout ly_vip;
    private LinearLayout ly_recommend;
    private LinearLayout ly_customerservice;
    private LinearLayout ly_service;
    private LinearLayout ly_set;
    private LinearLayout ly_suggest;
    private ImageView imageLogin;
	@Override
	public View initView(LayoutInflater inflater) {
		View view=inflater.inflate(R.layout.fragment_my, null);
		init(view);
		return view;
	}
	public void init(View view){
		imageLogin=(ImageView)view.findViewById(R.id.img_login);
		ly_myorder=(LinearLayout)view.findViewById(R.id.ly_order);
		ly_attention=(LinearLayout)view.findViewById(R.id.ly_favor);
		ly_address=(LinearLayout)view.findViewById(R.id.ly_address);
		ly_wallet=(LinearLayout)view.findViewById(R.id.ly_wallet);
		ly_coupon=(LinearLayout)view.findViewById(R.id.ly_coupon);
		ly_store=(LinearLayout)view.findViewById(R.id.ly_store);
		ly_vip=(LinearLayout)view.findViewById(R.id.ly_vip);
		ly_recommend=(LinearLayout)view.findViewById(R.id.ly_recomment);
		ly_customerservice=(LinearLayout)view.findViewById(R.id.ly_customerservice);
		ly_service=(LinearLayout)view.findViewById(R.id.ly_service);
		ly_set=(LinearLayout)view.findViewById(R.id.ly_set);
		ly_suggest=(LinearLayout)view.findViewById(R.id.ly_suggest);
		imageLogin.setOnClickListener(this);
		ly_attention.setOnClickListener(this);
		ly_address.setOnClickListener(this);
	}
	@Override
	protected void initFindViewById(View view) {
		// TODO Auto-generated method stub		
	}
	@Override
	public void initData() {
		// TODO Auto-generated method stub		
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		//我的登陆界面
		case(R.id.img_login):
			Intent intent=new Intent(getActivity(),LoginActivity.class);
		    startActivity(intent);
			break;
		//我的订单界面
		case(R.id.ly_order):
			break;
		//我的收藏界面
		case(R.id.ly_favor):
			Intent intent1=new Intent(getActivity(),FavorActivity.class);
		    startActivity(intent1);
			break;
		//收货地址界面
		case(R.id.ly_address):
			Intent intent2=new Intent(getActivity(),AddressActicity.class);
		    startActivity(intent2);
		    break;
		}
	}

}
