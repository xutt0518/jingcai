package com.jingcai.jingcac.rolling;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.jingcai.jingcaic.R;

import net.tsz.afinal.FinalBitmap;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

public class CycleShowView extends FrameLayout {

	/**������*/
	private Context context;
	/**ͼƬ���ض���*/
	private FinalBitmap fb;
	/**���ӵ�ַ����*/
	private String[] imageUrls;
	/**�ֲ���ͼƬ�ؼ�����*/
	private List<ImageView> imageViewsList;
	/**ָʾ��Բ�㼯��*/
	private List<View> dotViewsList;
	/**�Զ��ֲ����ÿ���*/
    private final static boolean isAutoPlay = true;
    /**��ʱ����*/
	private ScheduledExecutorService scheduledExecutorService;
	/***/
	private ViewPager viewPager; 
	/**��ǰ�ֲ�ҳ*/
    private int currentItem  = 0;
    /**��ǰҳ��ı����*/
    private MyPageOnClickItemListener mMyPageOnClickItemListener;
	
	
	
	public CycleShowView(Context context) {
		this(context, null);
	}

	public CycleShowView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CycleShowView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
		
		//��ʼ��ͼƬ���ع���
		initImageLoader(context);
		
		initData();
		
		if(isAutoPlay){
//			startPlay();
		}
		
	}
	
	private Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			viewPager.setCurrentItem(currentItem);
		}
	};

	//��ʼ���������
	private void initData() {
		imageViewsList = new ArrayList<ImageView>();
		dotViewsList = new ArrayList<View>();
		
//		initUI();
	}
	

	private void initUI() {
		if(imageUrls == null || imageUrls.length == 0)
			return;
		LayoutInflater.from(context).inflate(R.layout.pic_cycle_layout, this, true);
		LinearLayout dotLayout = (LinearLayout) findViewById(R.id.dot_layout);
		dotLayout.removeAllViews();
		
		//��̬���ͼƬ�͵��ؼ�
		for (int i = 0; i < imageUrls.length; i++) {
			ImageView mImageView = new ImageView(context);
			mImageView.setTag(imageUrls[i]);
			
			if(i== 0)
				mImageView.setBackgroundResource(R.drawable.banner2);
			
			mImageView.setScaleType(ScaleType.FIT_XY);
			imageViewsList.add(mImageView);
			
			ImageView mDotImageView = new ImageView(context);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtil.dip2px(context, 7),DensityUtil.dip2px(context, 7));
			params.leftMargin = 4;
			params.rightMargin = 4;
			mDotImageView.setLayoutParams(params);
			mDotImageView.setScaleType(ScaleType.FIT_XY);
			mDotImageView.setBackgroundResource(R.drawable.ic_focus);
			dotViewsList.add(mDotImageView);
			dotLayout.addView(mDotImageView);
		}
		viewPager = (ViewPager) findViewById(R.id.cycle_viewPager);
		viewPager.setFocusable(true);
		
		viewPager.setAdapter(new MyPagerAdapter());
		viewPager.setOnPageChangeListener(new MyPagerChangeListener());
		
	}

	private void initImageLoader(Context context) {
		fb = FinalBitmap.create(context);
	}
	
	/**����ͼƬ����*/
	public void setData(String[] imageUrls){
		this.imageUrls = imageUrls;
		initUI();
	}
	
	/**��ʼ�ֲ�*/
	public void startPlay() {
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(), 1, 4, TimeUnit.SECONDS);
	}
	
	/**ֹͣ�ֲ�*/
	private void stopPlay(){
		scheduledExecutorService.shutdown();
	}
	
    /**
     *ִ���ֲ�ͼ�л�����
     */
    private class SlideShowTask implements Runnable{

        @Override
        public void run() {
            synchronized (viewPager) {
                currentItem = (currentItem+1)%imageViewsList.size();
                handler.obtainMessage().sendToTarget();
            }
        }
        
    }
    
    private class MyPagerAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return imageViewsList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager)container).removeView(imageViewsList.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView imageView = imageViewsList.get(position);
			String url = imageView.getTag()+"";
			fb.display(imageView, url);
			((ViewPager)container).addView(imageViewsList.get(position));
			
			imageView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					mMyPageOnClickItemListener.curSelect(currentItem);
				}
			});
			return imageViewsList.get(position);
		}
		
		
    }
    
    /**
     * ViewPager������
     * ҳ��״̬���͸ı�ʱ����
     */
    private class MyPagerChangeListener implements OnPageChangeListener{

    	boolean isAutoPlay = false;
		@Override
		public void onPageScrollStateChanged(int arg0) {
			switch (arg0) {
			case 1://���ƻ�����������
				isAutoPlay = false;
				break;
			case 2://�����л���
				isAutoPlay = true;
				break;
			case 0://�����������л����߼������
				//��ǰΪ���һ�ž��л�����һ��
				if(viewPager.getCurrentItem() == viewPager.getAdapter().getCount() -1 && !isAutoPlay){
					viewPager.setCurrentItem(0);
				}
				//��ǰΪ��һ�ţ����󻬶����л������һ��
				else if(viewPager.getCurrentItem() == 0 && !isAutoPlay){
					viewPager.setCurrentItem(viewPager.getAdapter().getCount() -1);
				}
				break;
			default:
				break;
			}
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {}

		@Override
		public void onPageSelected(int arg0) {
			currentItem = arg0;
			for (int i = 0; i < dotViewsList.size(); i++) {
				if(i == arg0){
					//��ǰ���ı���
					((View)dotViewsList.get(arg0)).setBackgroundResource(R.drawable.ic_focus_select);
				}else{
					((View)dotViewsList.get(i)).setBackgroundResource(R.drawable.ic_focus);
				}
			}
		}
    	
    }
    
    /**
     * ����ImageView��Դ�������ڴ�
     * 
     */
    private void destoryBitmaps() {

        for (int i = 0; i < imageViewsList.size(); i++) {
            ImageView imageView = imageViewsList.get(i);
            Drawable drawable = imageView.getDrawable();
            if (drawable != null) {
                //���drawable��view������
                drawable.setCallback(null);
            }
        }
    }
    
    public interface MyPageOnClickItemListener {
    	public void curSelect(int position);
    }
    
    public void setMyPageOnClickItemListener(MyPageOnClickItemListener mMyPageOnClickItemListener){
    	this.mMyPageOnClickItemListener = mMyPageOnClickItemListener;
    }
    
}
