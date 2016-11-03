package com.jingcai.jingcaic.activity.me;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.net.Uri;
import android.os.Environment;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.jingcai.jingcaic.R;
import com.jingcai.jingcaic.activity.BaseActivity;
import com.jingcai.jingcaic.circular.imageview.CircularImage;
import com.jingcai.jingcaic.util.AndroidUtil;

import java.util.Date;
/**
 * 个人资料设置
 */
public class MyDataActivity  extends BaseActivity implements OnClickListener{
	
	public Context context;
	//日期
	private Calendar  calendar;
	//日期选择框
	private DatePickerDialog dpd;	 
	//昵称
	private RelativeLayout data_name;
	//电话
    private RelativeLayout data_phone;
    //真实姓名
  	private RelativeLayout  data_truename;
  	//性别
  	private RelativeLayout data_xb;
  	//出生日期
  	private RelativeLayout data_rq;
  	//家庭地址
  	private RelativeLayout data_dz;
  	//头像
  	private RelativeLayout data_image;
  	private static final int PHOTO_CARMERA = 1;
	private static final int PHOTO_PICK = 2;
	private static final int PHOTO_CUT = 3;
	//头像变圆形
	private com.jingcai.jingcaic.circular.imageview.CircularImage data_imge;
	private String[] items={"拍照","相册","取消"};
	private String  title="选择照片";
	//输入框
	private TextView text_name,text_phone,text_truename,text_xb,text_rq,text_dz; 
	public String	male,female;
	
	//创建一个以当前系统时间为名称的文件，防止重复
	private File tempFile = new File(Environment.getExternalStorageDirectory(),getPhotoFileName());
	
	//使用系统当前日期加以调正作为照片的名称
	private String getPhotoFileName() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("'PNG'_yyyyMMdd_HHmmss");
		return sdf.format(date) + ".png";
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		isTemplate=true;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mydata);
		titleView.setText("资料设置");
		context=MyDataActivity.this;		
		//加载信息
		jiazai();
		//点击弹窗
		initView();
	}
	
	//用户登录后先从网络加载用的相关信息
	private void jiazai() {
		// TODO Auto-generated method stub
		
	}
	//点击设置
	private void initView() {
		//日期
		calendar=Calendar.getInstance();
		//日期	
		data_rq=(RelativeLayout) findViewById(R.id.data_rq);
		data_rq.setOnClickListener(this);
		//头像
		data_image=(RelativeLayout) findViewById(R.id.data_image);
		data_image.setOnClickListener(this);
		//昵称
		data_name=(RelativeLayout) findViewById(R.id.data_name);
		data_name.setOnClickListener(this);
		//电话
		data_phone=(RelativeLayout) findViewById(R.id.data_phone);
		data_phone.setOnClickListener(this);
		//真实姓名
		data_truename=(RelativeLayout) findViewById(R.id.data_truename);
		data_truename.setOnClickListener(this);
		//性别
		data_xb=(RelativeLayout) findViewById(R.id.data_xb);
		data_xb.setOnClickListener(this);
		//地址
		data_dz=(RelativeLayout) findViewById(R.id.data_dz);
		data_dz.setOnClickListener(this);
		
		//选择的圆形头像
		data_imge=(CircularImage) findViewById(R.id.data_imge);
		//输入的昵称文本
		text_name=(TextView) findViewById(R.id.text_name);
		//输入的电话文本
		text_phone=(TextView) findViewById(R.id.text_phone);
		//输入的出生日期文本
		text_rq=(TextView) findViewById(R.id.text_rq);
		//真实姓名文本
		text_truename=(TextView) findViewById(R.id.text_truename);
	    //输入的性别文本
		text_xb=(TextView) findViewById(R.id.text_xb);
		//输入的地址文本
		text_dz=(TextView) findViewById(R.id.text_dz);
		
		
	}
	//点击昵称弹窗
	private void name(){
		final Dialog dialog=new Dialog(context);
		//设置可消失
		dialog.setCancelable(true);
		//设置标题栏
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 调用自定义页面
		View view = LayoutInflater.from(context).inflate(R.layout.data_name,null);
		// 获取确定按钮
		// 把调用的页面传到dialog里面去
		dialog.setContentView(view);
		Button button_qx = (Button) view.findViewById(R.id.button_qx);
		button_qx.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				dialog.cancel();	
			}
		});
		final EditText editext = (EditText)view.findViewById(R.id.editext);
		Button bu_qd = (Button) view.findViewById(R.id.bu_qd);
		dialog.show();
		bu_qd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				dialog.cancel();
				
				//发送网络请求
				
			}
		});
		dialog.show();	
	}
	//设置性别弹窗
	private void bb_sex(){
		final AlertDialog alertDialog1=new AlertDialog.Builder(this).create();
		alertDialog1.show();
		alertDialog1.setCancelable(true);
		Window win = alertDialog1.getWindow();
		WindowManager.LayoutParams lp = win.getAttributes();
		win.setGravity(Gravity.FILL | Gravity.BOTTOM);
		win.setAttributes(lp);
		win.setContentView(R.layout.data_xb_dialog);
		final Button  nan = (Button)win.findViewById(R.id.bu_nan);
		final Button  nv = (Button)win.findViewById(R.id.bu_nv);
		final Button  qx = (Button)win.findViewById(R.id.bu_qx);
		nan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				alertDialog1.cancel();
				if(nan.getText().toString().equals("男")){
					
					male="0";
					
				}
				//发送网络请求
				
			}
		});
		nv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				alertDialog1.cancel();
				
				
				if(nv.getText().toString().equals("女")){
					
					female="1";
					
				}
				//发送网络请求
				
			}
		});
		qx.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				alertDialog1.cancel();
				
			}
		});
	}
	
	//设置日期弹窗
	private void date(){
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day_of_month = calendar.get(Calendar.DAY_OF_MONTH);
		//点击按钮的时候实例化一个日期选择器
		dpd = new DatePickerDialog(context, new OnDateSetListener() {
			
			@Override
			public void onDateSet(DatePicker arg0, final int arg1,
					final int arg2, final int arg3) {
				
				final String rq = (arg1 + "-" + (arg2 + 1) + "-" + arg3).toString();
				
				//发送网络请求
				
			}
		}, year, month, day_of_month);	
		dpd.show();
	}
	//设置地址弹窗
	private void address(){
		final Dialog dialog=new Dialog(context);
		//设置可消失
		dialog.setCancelable(true);
		//隐藏标题栏
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//调用自定义页面
		View view=LayoutInflater.from(context).inflate(R.layout.data_address, null);
		//获取确定按钮
				dialog.setContentView(view);
				Button button_qx = (Button) view.findViewById(R.id.button_qx);
				button_qx.setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						
						dialog.cancel();	
					}
				});
				final EditText editext = (EditText)view.findViewById(R.id.editext);
				Button bu_qd = (Button) view.findViewById(R.id.bu_qd);
				dialog.show();
				bu_qd.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						
						dialog.cancel();
						
						//发送网络请求
						
					}
				});
				dialog.show();	
	}
	
	//设置真实姓名
	private void truename(){
		final Dialog dialog=new Dialog(context);
		//设置可消失
		dialog.setCancelable(true);
		//隐藏标题栏
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//调用自定义页面
		View view=LayoutInflater.from(context).inflate(R.layout.data_truename, null);
		//获取确定按钮
		dialog.setContentView(view);
		Button button_qx = (Button) view.findViewById(R.id.button_qx);
		button_qx.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				dialog.cancel();	
			}
		});
		final EditText editext = (EditText)view.findViewById(R.id.editext);
		Button bu_qd = (Button) view.findViewById(R.id.bu_qd);
		dialog.show();
		bu_qd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				dialog.cancel();
				
				//发送网络请求
				
			}
		});
		dialog.show();	
	}
	
	//设置电话弹窗
	private void phone(){
		final Dialog dialog=new Dialog(context);
		//设置可消失
		dialog.setCancelable(true);
		//设置标题栏
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 调用自定义页面
		View view = LayoutInflater.from(context).inflate(R.layout.data_phone,null);
		// 获取确定按钮
				// 把调用的页面传到dialog里面去
				dialog.setContentView(view);
				Button button_qx = (Button) view.findViewById(R.id.button_qx);
				button_qx.setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						
						dialog.cancel();	
					}
				});
				final EditText editext = (EditText)view.findViewById(R.id.editext);
				Button bu_qd = (Button) view.findViewById(R.id.bu_qd);
				dialog.show();
				bu_qd.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						
						dialog.cancel();
						
						//发送网络请求
						
					}
				});
				dialog.show();	
		
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		//头像
		case R.id.data_image:
			AlertDialog.Builder dialo = AndroidUtil.getListDialogBuilder(
					context, items, title, dialogListener);
			dialo.show();
			break;
		//昵称
		case R.id.data_name:
			name();
			break;
		//电话
		case R.id.data_phone:
			phone();
			break;
		//真实姓名
		case R.id.data_truename:
			truename();
			break;
		//性别
		case R.id.data_xb:
			bb_sex();
			break;
		//日期
		case R.id.data_rq:
			date();
			break;
		//地址
		case R.id.data_dz:
			address();
			break;	
		}
		
	}
	
	
	
	//头像选择方法
		private android.content.DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case 0:
					// 调用拍照
					startCamera(dialog);
					break;
				case 1:
					// 调用相册
					startPick(dialog);
					break;
				case 2:
					// 取消
					dialog.cancel();
					break;
				default:
					break;
				}
				
			}};	
		
			/**
			 *打开系统相册
			 */
			protected void startPick(DialogInterface dialog) {
				dialog.dismiss();
				Intent intent = new Intent(Intent.ACTION_PICK, null);
				intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
				startActivityForResult(intent, PHOTO_PICK);
			}
			
			

			/**
			 * 调用照相机拍照
			 */
			protected void startCamera(DialogInterface dialog) {
				dialog.dismiss();
				// 调用系统拍照功能
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra("camerasensortype", 2); //调用前置摄像头
				intent.putExtra("autofocus", true); // 自动对焦距
				intent.putExtra("fullScreen", false); // 全屏
				intent.putExtra("showActionIcons", false);
				
				// 指定照相机拍照后照片的存储路径
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
				startActivityForResult(intent, PHOTO_CARMERA);
		
			}	
		/**
		 * 回调函数
		 */
			protected void onActivityResult(int requestCode, int resultCode, Intent data) {
				switch (requestCode) {
				case PHOTO_CARMERA:
					//调用系统剪裁
					startPhotoZoom(Uri.fromFile(tempFile), 300);
					break;
				case PHOTO_PICK:
					if (null != data) {
						startPhotoZoom(data.getData(), 300);
					}
					break;
				case PHOTO_CUT:
					if (null != data) {//上传服务代码
						//将裁剪后的题片显示在ImageView上
						setPicToView(data);//保存在SD卡中
						/**
						 * 上传图片
						 * 
						 */
						 //zhaopian();
					}
					break;

				default:
					break;
				}
				super.onActivityResult(requestCode, resultCode, data);
			}
	
	
			
			
			// 调用系统剪裁
			private void startPhotoZoom(Uri uri, int size) {
				Intent intent = new Intent("com.android.camera.action.CROP");
				intent.setDataAndType(uri, "image/*");
				// crop未true是设置在开启的intent中设置显示的view可以剪裁
				intent.putExtra("crop", true);
				// aspextx,aspectY是宽高的比例
				intent.putExtra("aspectX", 1);
				intent.putExtra("aspectY", 1);
				// outputX,outputY是剪裁图片的宽高
				intent.putExtra("outputX", size);
				intent.putExtra("outputY", size);
				// 设置是否返回数据
				intent.putExtra("return-data", true);
				startActivityForResult(intent, PHOTO_CUT);
			}
			// 将裁剪后的图片显示在ImageView上
			private void setPicToView(Intent data) {
				Bundle bundle = data.getExtras();
				if (null != bundle) {
					final Bitmap bmp = bundle.getParcelable("data");
					data_imge.setImageBitmap(bmp);
					saveCropPic(bmp);
					//Log.i("", tempFile.getAbsolutePath());
				}
			}
			//将裁剪后的图片保存到sdcard上
			private void saveCropPic(Bitmap bmp) {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				FileOutputStream fis = null;
				bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
				try {
					fis = new FileOutputStream(tempFile);
					fis.write(baos.toByteArray());
					fis.flush();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (null != baos) {
							baos.close();
						}
						if (null != fis) {
							fis.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
}
