package com.jingcai.jingcaic.fragment.homepage;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.Toast;
import com.jingcai.jingcaic.R;
import com.jingcai.jingcaic.activity.me.LoginActivity;
import com.jingcai.jingcaic.activity.me.OrderlistActivity;
import com.jingcai.jingcaic.activity.me.UserUtil;
import com.jingcai.jingcaic.activity.type.GoodsInfoActvity;
import com.jingcai.jingcaic.adapter.GridItemDecoration;
import com.jingcai.jingcaic.adapter.MyRecyclerAdapter;
import com.jingcai.jingcaic.adapter.RecyclerViewClickListener;
import com.jingcai.jingcaic.fragment.BaseFragment;
import com.jingcai.jingcaic.fragment.type.TypeFragment;
import com.jingcai.jingcaic.util.ExceptionUtil;
import com.jingcai.jingcaic.util.KeybordUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
/**
 * 何孝天：首页
 */
public class HomePageFragemnt extends BaseFragment {
	private RecyclerView recyclerview;
	private PtrClassicFrameLayout ptr;
	private MyRecyclerAdapter myrecyclerAdapter;
	private List<String> list;
	//设置五图滚动
	private ViewPager contentPager;
	public View view;
	public EditText edit;
	private String userId;
	@Override
	public View initView(LayoutInflater inflater) {
		view=inflater.inflate(R.layout.fragment_homepage, null);
		list=new ArrayList<String>();
		for(int i=0;i<100;i++){
			String data="老友记";
			list.add(data);
		}
		init();
		//加载五图滚动试图
		//rolling(view);
		return view;
	}
	public void init(){
	   recyclerview=(RecyclerView)view.findViewById(R.id.recyclerview);
	   ptr=(PtrClassicFrameLayout)view.findViewById(R.id.ptr);
	   myrecyclerAdapter=new MyRecyclerAdapter(list,getActivity());
	   final GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
	   manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
			  @Override
			  public int getSpanSize(int position) {
			    return myrecyclerAdapter.isHeader(position) ? manager.getSpanCount() : 1;
			  }
			});
	   recyclerview.setLayoutManager(manager);
	   recyclerview.setItemAnimator(new DefaultItemAnimator());
	   recyclerview.addItemDecoration(new GridItemDecoration(getActivity(), true));
	   recyclerview.setAdapter(myrecyclerAdapter);
	   rolling(recyclerview);
	   ptr.setPtrHandler(ptrDefaultHandler);
	   myrecyclerAdapter.setmOnItemClickListener(new MyRecyclerAdapter.OnItemClickListener() {		
			@Override
			public void onItemClick(View v, int position) {
			 switch(v.getId()){
			 //开始购买
			 case R.id.Search_food:
		      
				 break;
		     //我的订单
			 case R.id.Search_outing:
				 if(isLogin()){
				  	Intent intent=new Intent(getActivity(),OrderlistActivity.class);
				  	startActivity(intent);
				 }else{
					 Intent intent1=new Intent(getActivity(),LoginActivity.class);
					 startActivity(intent1);
				 }
				 break;
		     //精菜检测
			 case R.id.Search_hotel:
				 break;
		     //账户充值
			 case R.id.Search_hotell:
				 break;	 
			//菜品详情
			 case R.id.ly_recycler:
				 Intent intent4=new Intent(getActivity(),GoodsInfoActvity.class);
				 startActivity(intent4);
			 default:
		         break;
			 }
			}
		});
	}
	//下拉刷新
	private PtrDefaultHandler ptrDefaultHandler=new PtrDefaultHandler() {
		@Override
		public void onRefreshBegin(PtrFrameLayout frame) {
			frame.postDelayed(new Runnable() {
				@Override
				public void run() {
					//page = 0;
					//recyclerViewAdapter.getfirst();
					//mAdapter.notifyDataSetChanged();
					ptr.refreshComplete();
				}
			},700);
		}
	};
	
	//五图滚动广告
	private void rolling(RecyclerView view) {
		View viewHeader=LayoutInflater.from(getActivity()).inflate(R.layout.fragment_homeheader, null);
	  try {
		  ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context).build();
	      ImageLoader.getInstance().init(configuration);
	      contentPager = (ViewPager) viewHeader.findViewById(R.id.pager);
	      contentPager.setOffscreenPageLimit(2);
	      contentPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return 3;
			}
			
			@Override
			public Fragment getItem(int i) {
	          if (i == 0) {
	          return new AutoScrollPagerFragment();
	          }
	          return TextFragment.newInstance("Fragment " + i);
			}
		});
	  	myrecyclerAdapter.setmHeaderView(viewHeader);
		} catch (Exception e) {
			
			ExceptionUtil.handleException(e);
		}
	
     
	}

	@Override
	protected void initFindViewById(View view) {
		edit=(EditText) view.findViewById(R.id.edit);
		//使edit失去焦点
		edit.clearFocus();
		edit.setFocusable(false);

	}
    
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		
	}
	
	//判断用户是否在线
	public boolean isLogin(){
		userId=UserUtil.getUsrId(getActivity());
		if(!"no".equals(userId)){
			return true;
		}else {
			return false;
		}
	}

}
