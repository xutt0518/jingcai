package com.jingcai.jingcaic.activity;
import com.jingcai.jingcaic.R;
import com.jingcai.jingcaic.fragment.BaseFragment;
import com.jingcai.jingcaic.fragment.homepage.HomePageFragemnt;
import com.jingcai.jingcaic.fragment.me.MeFragment;
import com.jingcai.jingcaic.fragment.shoppingcat.ShoppingCatFragment;
import com.jingcai.jingcaic.fragment.type.TypeFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class JingCaiCAcitvity extends BaseFragmentActivity {
	
	//首页
	public  static Button butHomePage;
	//分类	
	public  static Button butType;
	//购物车
	public  static Button butShoppingCat;
	//我的
	public  static Button butMe;
	private FragmentManager fragmentManager;
	private FragmentTransaction transaction;
	
	@Override
	protected void initView() {
		setContentView(R.layout.activity_jingcaic);
		
	}
	@Override
	protected void initFindViewById() {
		//获取FragmentManager实例
		  fragmentManager = getSupportFragmentManager();
		  transaction = fragmentManager.beginTransaction();
		  butHomePage=(Button) findViewById(R.id.btn_jingcai_homepage);
		  butType=(Button) findViewById(R.id.btn_jingcai_type);
		  butShoppingCat=(Button) findViewById(R.id.btn_jingcai_shoppingcat);
		  butMe=(Button) findViewById(R.id.btn_jingcai_me);
		  Fragment fragment = new HomePageFragemnt();
		  transaction.replace(R.id.fragment_container, fragment);
		  transaction.commit();
		  butHomePage.setSelected(true);
		  butType.setSelected(false);
		  butShoppingCat.setSelected(false);
		  butMe.setSelected(false);
		
	}

	@Override
	protected void initDate() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void setLinstener() {

		butHomePage.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View arg0) {
				transaction = fragmentManager.beginTransaction();
				Fragment HoemPageFragemnt=new HomePageFragemnt();
				transaction.replace(R.id.fragment_container, HoemPageFragemnt);
				transaction.commit();
				 butHomePage.setSelected(true);
			     butType.setSelected(false);
			     butShoppingCat.setSelected(false);
			     butMe.setSelected(false);
			}
		});
		butType.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			transaction = fragmentManager.beginTransaction();
			Fragment TypeFragment=new TypeFragment();
			transaction.replace(R.id.fragment_container, TypeFragment);
			transaction.commit();
		    butHomePage.setSelected(false);
		    butType.setSelected(true);
		    butShoppingCat.setSelected(false);
		    butMe.setSelected(false);
		    }
		});
		butShoppingCat.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				transaction = fragmentManager.beginTransaction();
				Fragment ShoppingCatFragment=new ShoppingCatFragment();
				transaction.replace(R.id.fragment_container, ShoppingCatFragment);
				transaction.commit();
			    butHomePage.setSelected(false);
			    butType.setSelected(false);
			    butShoppingCat.setSelected(true);
			    butMe.setSelected(false);
			}
		});
		
		butMe.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				transaction = fragmentManager.beginTransaction();
				Fragment MeFragment=new MeFragment();
				transaction.replace(R.id.fragment_container, MeFragment);
				transaction.commit();
			    butHomePage.setSelected(false);
			    butType.setSelected(false);
			    butShoppingCat.setSelected(false);
			    butMe.setSelected(true);
			}
		});
	}
}
