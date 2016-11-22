package com.jingcai.jingcaic.fragment.me;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jingcai.jingcaic.R;
import com.jingcai.jingcaic.activity.me.AddressActicity;
import com.jingcai.jingcaic.activity.me.CouponActivity;
import com.jingcai.jingcaic.activity.me.FavorActivity;
import com.jingcai.jingcaic.activity.me.LoginActivity;
import com.jingcai.jingcaic.activity.me.MyDataActivity;
import com.jingcai.jingcaic.activity.me.ServiceActivity;
import com.jingcai.jingcaic.activity.me.SetActivity;
import com.jingcai.jingcaic.activity.me.SuggestActivity;
import com.jingcai.jingcaic.activity.me.WalletActivity;
import com.jingcai.jingcaic.fragment.BaseFragment;
import com.jingcai.jingcaic.util.UserUtil;

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
    private String userId;
    private TextView tvLogin;
    //ǩ��
    private TextView text_signin;
	@Override
	public View initView(LayoutInflater inflater) {
		View view=inflater.inflate(R.layout.fragment_my, null);
		init(view);
		return view;
	}
	public void init(View view){
		tvLogin=(TextView) view.findViewById(R.id.text_login);
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
		//ǩ��
		text_signin=(TextView) view.findViewById(R.id.text_signin);
		
		tvLogin.setOnClickListener(this);
		imageLogin.setOnClickListener(this);
		ly_attention.setOnClickListener(this);
		ly_address.setOnClickListener(this);
		ly_wallet.setOnClickListener(this);
		ly_store.setOnClickListener(this);
		ly_vip.setOnClickListener(this);
		ly_recommend.setOnClickListener(this);
		ly_customerservice.setOnClickListener(this);
		ly_service.setOnClickListener(this);
		ly_set.setOnClickListener(this);
		ly_suggest.setOnClickListener(this);
		text_signin.setOnClickListener(this);
		
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
		//ǩ��
		case (R.id.text_signin):
			Intent intent6=new Intent(getActivity(),LoginActivity.class);
	        startActivity(intent6);
	        break;		
		//������¼ҳ��
		case (R.id.text_login):
			if(isLogin()){
				Intent intent=new Intent(getActivity(),MyDataActivity.class);
			    startActivity(intent);
			}else{
				Intent intent=new Intent(getActivity(),LoginActivity.class);
			    startActivity(intent);
			}
			break;
		//�������Ͻ���
		case(R.id.img_login):
			if(isLogin()){
				Intent intent=new Intent(getActivity(),MyDataActivity.class);
			    startActivity(intent);
			}else{
				Intent intent5=new Intent(getActivity(),LoginActivity.class);
				startActivity(intent5);
			}
			
			break;
		//�ҵĶ�������
		case(R.id.ly_order):
			if(isLogin()){
				
			}else{
				Intent intent0=new Intent(getActivity(),LoginActivity.class);
				startActivity(intent0);
			}
			break;
		//�ҵ��ղؽ���
		case(R.id.ly_favor):
			Intent intent1=new Intent(getActivity(),FavorActivity.class);
		    startActivity(intent1);
			break;
		//�ջ���ַ����
		case(R.id.ly_address):
			Intent intent2=new Intent(getActivity(),AddressActicity.class);
		    startActivity(intent2);
		    break;
		//�ҵ�Ǯ��
		case(R.id.ly_wallet):
			Intent intent3=new Intent(getActivity(),WalletActivity.class);
		    startActivity(intent3);
			break;
		//�ҵ��Ż�ȯ
		case(R.id.ly_coupon):
			Intent intent4=new Intent(getActivity(),CouponActivity.class);
		    startActivity(intent4);
			break;
		//�����̳�
		case(R.id.ly_store):
			break;
		//��Ա����
		case(R.id.ly_vip):
			break;
		//�Ƽ��н�
		case(R.id.ly_recomment):
			break;
		//��ϵ�ͷ�
		case(R.id.ly_customerservice):
			AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
		    builder.setMessage("����"+getString(R.string.phone)+"?");
		    builder.setPositiveButton("����", new DialogInterface.OnClickListener() {				
				@Override
				public void onClick(DialogInterface dialog, int which) {
                  
				}
			});
		    builder.setNegativeButton("ȡ��", null);
		    builder.show();
		    break;
		//������֪
		case(R.id.ly_service):
			Intent service=new Intent(getActivity(),ServiceActivity.class);
		    startActivity(service);
			break;
		//����
		case(R.id.ly_set):
			Intent intentset=new Intent(getActivity(),SetActivity.class);
		    startActivity(intentset);
			break;
		//�������
		case(R.id.ly_suggest):
			Intent insuggest=new Intent(getActivity(),SuggestActivity.class);
		    startActivity(insuggest);
			break;
		 default:
			 break;
		}
	}
	public boolean isLogin(){
		userId=UserUtil.getUsrId(getActivity());
		if(!"no".equals(userId)){
			return true;
		}else{
			return false;
		}
	}
	@Override
	protected void initData(View view) {
		// TODO Auto-generated method stub
		
	}

}
