package com.jingcai.jingcaic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

public class RecyclerViewClickListener implements RecyclerView.OnItemTouchListener{

	private int mLastDownX,mLastDownY; //��ֵ��¼����С�������� 
	private int touchSlop ; 
	private OnItemClickListener mListener; //�Ƿ��ǵ����¼�
	private boolean isSingleTapUp = false; //�Ƿ��ǳ����¼� 
	private boolean isLongPressUp = false; 
	private boolean isMove = false;
	private long mDownTime;
	//�ڲ��ӿڣ������������Լ��������� 
	public interface OnItemClickListener { 
	void onItemClick(View view, int position);  
	void onItemLongClick(View view, int position); 
	}
	public RecyclerViewClickListener(Context context,OnItemClickListener listener){ 
		touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
		mListener = listener; } 
		@Override 
		public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) { 
		int x = (int) e.getX(); 
		int y = (int) e.getY(); 
		switch (e.getAction()){
		 /** * �����ACTION_DOWN�¼�����ô��¼��ǰ���µ�λ�ã� * ��¼��ǰ��ϵͳʱ�䡣 */ 
		case MotionEvent.ACTION_DOWN: 
		mLastDownX = x; 
		mLastDownY = y;
		mDownTime = System.currentTimeMillis(); 
		isMove = false; 
		break; 
		/** * �����ACTION_MOVE�¼�����ʱ����TouchSlop�ж��û��ڰ��µ�ʱ���Ƿ񻬶��ˣ� * ��������ˣ���ô�����������������¼� */ 
		case MotionEvent.ACTION_MOVE: 
		if(Math.abs(x - mLastDownX)>touchSlop || Math.abs(y - mLastDownY)>touchSlop){ 
		isMove = true; } 
		break;
		/** * �����ACTION_UP�¼�����ô����isMove��־λ���ж��Ƿ���Ҫ�������¼��� * ����ϵͳʱ��Ĳ�ֵ���ж��������¼�����������¼�����1ms������Ϊ�ǳ����¼��� * �����ǵ����¼��� */ 
		case MotionEvent.ACTION_UP: 
		if(isMove){ 
		break; 
		}
		if(System.currentTimeMillis()-mDownTime > 1000){ 
		isLongPressUp = true; 
		}else 
		{ 
		isSingleTapUp = true; 
		} break; 
		} 
		if(isSingleTapUp ){ 
		//���ݴ�����������ȡchildView 
		View childView = rv.findChildViewUnder(e.getX(),e.getY()); 
		isSingleTapUp = false; if(childView != null){ 
		//�ص�mListener#onItemClick����
		 mListener.onItemClick(childView,rv.getChildPosition(childView)); 
		return true; } 
		return false; 
		} 
		if (isLongPressUp ){ 
		View childView = rv.findChildViewUnder(e.getX(),e.getY()); 
		isLongPressUp = false; 
		if(childView != null){ 
		mListener.onItemLongClick(childView, rv.getChildPosition(childView)); 
		return true; 
		}
		return false; 
		} 
		return false; 
		}  
		@Override 
		public void onTouchEvent(RecyclerView rv, MotionEvent e)
		 { 
		 }  
		
}
