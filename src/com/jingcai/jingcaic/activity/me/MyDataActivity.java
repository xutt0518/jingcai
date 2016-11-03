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
 * ������������
 */
public class MyDataActivity  extends BaseActivity implements OnClickListener{
	
	public Context context;
	//����
	private Calendar  calendar;
	//����ѡ���
	private DatePickerDialog dpd;	 
	//�ǳ�
	private RelativeLayout data_name;
	//�绰
    private RelativeLayout data_phone;
    //��ʵ����
  	private RelativeLayout  data_truename;
  	//�Ա�
  	private RelativeLayout data_xb;
  	//��������
  	private RelativeLayout data_rq;
  	//��ͥ��ַ
  	private RelativeLayout data_dz;
  	//ͷ��
  	private RelativeLayout data_image;
  	private static final int PHOTO_CARMERA = 1;
	private static final int PHOTO_PICK = 2;
	private static final int PHOTO_CUT = 3;
	//ͷ���Բ��
	private com.jingcai.jingcaic.circular.imageview.CircularImage data_imge;
	private String[] items={"����","���","ȡ��"};
	private String  title="ѡ����Ƭ";
	//�����
	private TextView text_name,text_phone,text_truename,text_xb,text_rq,text_dz; 
	public String	male,female;
	
	//����һ���Ե�ǰϵͳʱ��Ϊ���Ƶ��ļ�����ֹ�ظ�
	private File tempFile = new File(Environment.getExternalStorageDirectory(),getPhotoFileName());
	
	//ʹ��ϵͳ��ǰ���ڼ��Ե�����Ϊ��Ƭ������
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
		titleView.setText("��������");
		context=MyDataActivity.this;		
		//������Ϣ
		jiazai();
		//�������
		initView();
	}
	
	//�û���¼���ȴ���������õ������Ϣ
	private void jiazai() {
		// TODO Auto-generated method stub
		
	}
	//�������
	private void initView() {
		//����
		calendar=Calendar.getInstance();
		//����	
		data_rq=(RelativeLayout) findViewById(R.id.data_rq);
		data_rq.setOnClickListener(this);
		//ͷ��
		data_image=(RelativeLayout) findViewById(R.id.data_image);
		data_image.setOnClickListener(this);
		//�ǳ�
		data_name=(RelativeLayout) findViewById(R.id.data_name);
		data_name.setOnClickListener(this);
		//�绰
		data_phone=(RelativeLayout) findViewById(R.id.data_phone);
		data_phone.setOnClickListener(this);
		//��ʵ����
		data_truename=(RelativeLayout) findViewById(R.id.data_truename);
		data_truename.setOnClickListener(this);
		//�Ա�
		data_xb=(RelativeLayout) findViewById(R.id.data_xb);
		data_xb.setOnClickListener(this);
		//��ַ
		data_dz=(RelativeLayout) findViewById(R.id.data_dz);
		data_dz.setOnClickListener(this);
		
		//ѡ���Բ��ͷ��
		data_imge=(CircularImage) findViewById(R.id.data_imge);
		//������ǳ��ı�
		text_name=(TextView) findViewById(R.id.text_name);
		//����ĵ绰�ı�
		text_phone=(TextView) findViewById(R.id.text_phone);
		//����ĳ��������ı�
		text_rq=(TextView) findViewById(R.id.text_rq);
		//��ʵ�����ı�
		text_truename=(TextView) findViewById(R.id.text_truename);
	    //������Ա��ı�
		text_xb=(TextView) findViewById(R.id.text_xb);
		//����ĵ�ַ�ı�
		text_dz=(TextView) findViewById(R.id.text_dz);
		
		
	}
	//����ǳƵ���
	private void name(){
		final Dialog dialog=new Dialog(context);
		//���ÿ���ʧ
		dialog.setCancelable(true);
		//���ñ�����
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// �����Զ���ҳ��
		View view = LayoutInflater.from(context).inflate(R.layout.data_name,null);
		// ��ȡȷ����ť
		// �ѵ��õ�ҳ�洫��dialog����ȥ
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
				
				//������������
				
			}
		});
		dialog.show();	
	}
	//�����Ա𵯴�
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
				if(nan.getText().toString().equals("��")){
					
					male="0";
					
				}
				//������������
				
			}
		});
		nv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				alertDialog1.cancel();
				
				
				if(nv.getText().toString().equals("Ů")){
					
					female="1";
					
				}
				//������������
				
			}
		});
		qx.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				alertDialog1.cancel();
				
			}
		});
	}
	
	//�������ڵ���
	private void date(){
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day_of_month = calendar.get(Calendar.DAY_OF_MONTH);
		//�����ť��ʱ��ʵ����һ������ѡ����
		dpd = new DatePickerDialog(context, new OnDateSetListener() {
			
			@Override
			public void onDateSet(DatePicker arg0, final int arg1,
					final int arg2, final int arg3) {
				
				final String rq = (arg1 + "-" + (arg2 + 1) + "-" + arg3).toString();
				
				//������������
				
			}
		}, year, month, day_of_month);	
		dpd.show();
	}
	//���õ�ַ����
	private void address(){
		final Dialog dialog=new Dialog(context);
		//���ÿ���ʧ
		dialog.setCancelable(true);
		//���ر�����
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//�����Զ���ҳ��
		View view=LayoutInflater.from(context).inflate(R.layout.data_address, null);
		//��ȡȷ����ť
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
						
						//������������
						
					}
				});
				dialog.show();	
	}
	
	//������ʵ����
	private void truename(){
		final Dialog dialog=new Dialog(context);
		//���ÿ���ʧ
		dialog.setCancelable(true);
		//���ر�����
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//�����Զ���ҳ��
		View view=LayoutInflater.from(context).inflate(R.layout.data_truename, null);
		//��ȡȷ����ť
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
				
				//������������
				
			}
		});
		dialog.show();	
	}
	
	//���õ绰����
	private void phone(){
		final Dialog dialog=new Dialog(context);
		//���ÿ���ʧ
		dialog.setCancelable(true);
		//���ñ�����
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// �����Զ���ҳ��
		View view = LayoutInflater.from(context).inflate(R.layout.data_phone,null);
		// ��ȡȷ����ť
				// �ѵ��õ�ҳ�洫��dialog����ȥ
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
						
						//������������
						
					}
				});
				dialog.show();	
		
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		//ͷ��
		case R.id.data_image:
			AlertDialog.Builder dialo = AndroidUtil.getListDialogBuilder(
					context, items, title, dialogListener);
			dialo.show();
			break;
		//�ǳ�
		case R.id.data_name:
			name();
			break;
		//�绰
		case R.id.data_phone:
			phone();
			break;
		//��ʵ����
		case R.id.data_truename:
			truename();
			break;
		//�Ա�
		case R.id.data_xb:
			bb_sex();
			break;
		//����
		case R.id.data_rq:
			date();
			break;
		//��ַ
		case R.id.data_dz:
			address();
			break;	
		}
		
	}
	
	
	
	//ͷ��ѡ�񷽷�
		private android.content.DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case 0:
					// ��������
					startCamera(dialog);
					break;
				case 1:
					// �������
					startPick(dialog);
					break;
				case 2:
					// ȡ��
					dialog.cancel();
					break;
				default:
					break;
				}
				
			}};	
		
			/**
			 *��ϵͳ���
			 */
			protected void startPick(DialogInterface dialog) {
				dialog.dismiss();
				Intent intent = new Intent(Intent.ACTION_PICK, null);
				intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
				startActivityForResult(intent, PHOTO_PICK);
			}
			
			

			/**
			 * �������������
			 */
			protected void startCamera(DialogInterface dialog) {
				dialog.dismiss();
				// ����ϵͳ���չ���
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra("camerasensortype", 2); //����ǰ������ͷ
				intent.putExtra("autofocus", true); // �Զ��Խ���
				intent.putExtra("fullScreen", false); // ȫ��
				intent.putExtra("showActionIcons", false);
				
				// ָ����������պ���Ƭ�Ĵ洢·��
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
				startActivityForResult(intent, PHOTO_CARMERA);
		
			}	
		/**
		 * �ص�����
		 */
			protected void onActivityResult(int requestCode, int resultCode, Intent data) {
				switch (requestCode) {
				case PHOTO_CARMERA:
					//����ϵͳ����
					startPhotoZoom(Uri.fromFile(tempFile), 300);
					break;
				case PHOTO_PICK:
					if (null != data) {
						startPhotoZoom(data.getData(), 300);
					}
					break;
				case PHOTO_CUT:
					if (null != data) {//�ϴ��������
						//���ü������Ƭ��ʾ��ImageView��
						setPicToView(data);//������SD����
						/**
						 * �ϴ�ͼƬ
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
	
	
			
			
			// ����ϵͳ����
			private void startPhotoZoom(Uri uri, int size) {
				Intent intent = new Intent("com.android.camera.action.CROP");
				intent.setDataAndType(uri, "image/*");
				// cropδtrue�������ڿ�����intent��������ʾ��view���Լ���
				intent.putExtra("crop", true);
				// aspextx,aspectY�ǿ�ߵı���
				intent.putExtra("aspectX", 1);
				intent.putExtra("aspectY", 1);
				// outputX,outputY�Ǽ���ͼƬ�Ŀ��
				intent.putExtra("outputX", size);
				intent.putExtra("outputY", size);
				// �����Ƿ񷵻�����
				intent.putExtra("return-data", true);
				startActivityForResult(intent, PHOTO_CUT);
			}
			// ���ü����ͼƬ��ʾ��ImageView��
			private void setPicToView(Intent data) {
				Bundle bundle = data.getExtras();
				if (null != bundle) {
					final Bitmap bmp = bundle.getParcelable("data");
					data_imge.setImageBitmap(bmp);
					saveCropPic(bmp);
					//Log.i("", tempFile.getAbsolutePath());
				}
			}
			//���ü����ͼƬ���浽sdcard��
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
