package com.jingcai.jingcaic.activity;

import com.jingcai.jingcaic.R;
import com.jingcai.jingcaic.fragment.homepage.AutoScrollPagerFragment;
import com.jingcai.jingcaic.fragment.homepage.TextFragment;
import com.jingcai.jingcaic.util.IntentUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;

public class MainActivity extends FragmentActivity {
	Handler handler = new Handler();	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
       setContentView(R.layout.activity_main);
       
       try {
			handler.postDelayed(new Runnable() {

				@Override
				public void run() {
					try {

						IntentUtil.gotoActivity(MainActivity.this, JingCaiCAcitvity.class, true, null);
						
					} catch (Throwable e) {

					}

				}
			}, 3000);
		} catch (Exception e) {
			// TODO: handle exception
		}
    }


}