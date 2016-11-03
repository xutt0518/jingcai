package com.jingcai.jingcaic.fragment.homepage;
import java.util.ArrayList;
import java.util.List;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.jingcai.jingcac.rolling.CycleShowView;
import com.jingcai.jingcac.rolling.CycleShowView.MyPageOnClickItemListener;
import com.jingcai.jingcaic.R;
import com.jingcai.jingcaic.activity.me.LoginActivity;
import com.jingcai.jingcaic.activity.me.OrderlistActivity;
import com.jingcai.jingcaic.activity.type.GoodsInfoActvity;
import com.jingcai.jingcaic.adapter.GridItemDecoration;
import com.jingcai.jingcaic.adapter.MyRecyclerAdapter;
import com.jingcai.jingcaic.fragment.BaseFragment;
import com.jingcai.jingcaic.util.UserUtil;
/**
 * 何孝天：首页
 */
public class HomePageFragemnt extends BaseFragment {
	private RecyclerView recyclerview;
	private PtrClassicFrameLayout ptr;
	private MyRecyclerAdapter myrecyclerAdapter;
	private List<String> list;
	public View view;
	public EditText edit;
	private String userId;
	
	String[] imageUrls = new String[]{
			"http://h.hiphotos.baidu.com/image/w%3D1920%3Bcrop%3D0%2C0%2C1920%2C1080/sign=fed1392e952bd40742c7d7f449b9a532/e4dde71190ef76c6501a5c2d9f16fdfaae5167e8.jpg",
            "http://a.hiphotos.baidu.com/image/w%3D1920%3Bcrop%3D0%2C0%2C1920%2C1080/sign=25d477ebe51190ef01fb96d6fc2ba675/503d269759ee3d6df51a20cd41166d224e4adedc.jpg",
            "http://c.hiphotos.baidu.com/image/w%3D1920%3Bcrop%3D0%2C0%2C1920%2C1080/sign=70d2b81e60d0f703e6b291d53aca6a5e/0ff41bd5ad6eddc4ab1b5af23bdbb6fd5266333f.jpg",};
	
	@Override
	public View initView(LayoutInflater inflater) {
		view=inflater.inflate(R.layout.fragment_homepage, null);
		list=new ArrayList<String>();
		for(int i=0;i<100;i++){
			String data="草莓";
			list.add(data);
		}
		init();
		return view;
	}
	public void init(){
	   recyclerview=(RecyclerView)view.findViewById(R.id.recyclerview);
	   ptr=(PtrClassicFrameLayout)view.findViewById(R.id.ptr);
	   myrecyclerAdapter=new MyRecyclerAdapter(list,getActivity());
	   //头布局宽度占满三列
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
	   //设置下拉刷新
	   ptr.setPtrHandler(ptrDefaultHandler);	

	   MyRecyclerAdapter.setmOnItemClickListener(new MyRecyclerAdapter.OnItemClickListener() {		
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
	
	
	//五图滚动广告
		private void rolling(RecyclerView view) {
			View viewHeader=LayoutInflater.from(getActivity()).inflate(R.layout.fragment_homeheader, null);
			CycleShowView cycleView = (CycleShowView) viewHeader.findViewById(R.id.cycle_view);
			cycleView.setData(imageUrls);
			cycleView.startPlay();
			myrecyclerAdapter.setmHeaderView(viewHeader);
			cycleView.setMyPageOnClickItemListener(new MyPageOnClickItemListener() {
				
				@Override
				public void curSelect(int position) {
					Toast.makeText(context, position+"", Toast.LENGTH_SHORT).show();
					
				}
			});
   
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
	@Override
	protected void initData(View view) {
		// TODO Auto-generated method stub
		
	}

}
