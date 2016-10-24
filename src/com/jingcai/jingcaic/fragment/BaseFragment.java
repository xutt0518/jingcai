package com.jingcai.jingcaic.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment{
	//����FragmentActivity����
	public FragmentActivity mActivity; 
	public Context context;
    /** 
     * �˷������Եõ������Ķ��� 
     */  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        context=getActivity();
    }  
    /** 
     * ����һ����Ҫչʾ��View 
     */  
    @Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
                             Bundle savedInstanceState) {  
        mActivity = getActivity();  
        View view = initView(inflater);  
        initFindViewById(view);  
  
        return view;  
    }  
    /** 
     * ��Activity��ʼ��֮��������������һЩ���ݵĳ�ʼ������ 
     */  
    @Override  
    public void onActivityCreated(Bundle savedInstanceState) {  
        super.onActivityCreated(savedInstanceState);  
        initData();  
        setLinstener();  
    }  
    
    /** 
     * ������Ը�д�˷�����ʼ���¼�(�������¼�) 
     */  
    protected  void setLinstener(){  
  
    } 
    /** 
     * ����ʵ�ִ˳��󷽷�����View����չʾ 
     * 
     * @return 
     */  
    public abstract View initView(LayoutInflater inflater);  
    
    /** 
     * ��ʼ���ؼ� 
     */  
    protected abstract void initFindViewById(View view);  
  
    /** 
     * �����ڴ˷�����ʵ�����ݵĳ�ʼ�� 
     */  
    public  abstract void initData() ;  
}
