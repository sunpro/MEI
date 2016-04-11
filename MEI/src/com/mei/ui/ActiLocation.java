package com.mei.ui;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mei.util.DatabaseManager;
import com.mei.util.Position;

public class ActiLocation extends Activity {

	private Context context;
	private TextView info=null;
	private TextView textv1;
	private ImageView imgLocation;
	private ImageView imgPoint;
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private Button button5;
	private RadioGroup radioGroup; 
	private RadioButton radio1;
	private RadioButton radio2;
	private RadioButton radio3;
	
//	private int width;
//	private int height;
	private int width2;
	private int height2;
	@SuppressWarnings("unused")
	private LayoutParams layoutParams;
	private String label;
	private String date;
	private String high = "-1";
	//换算后的实际尺寸，单位mm
	private int xmm;
	private int ymm;
	private List<Position> positions = new ArrayList<Position>();
	private DatabaseManager dbManager ;
	
	private RelativeLayout relaPa;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题//必须在setContentView之前
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
		
		setContentView(R.layout.activity_location);
		context = ActiLocation.this;
		Intent intentPa = getIntent();
		label = intentPa.getStringExtra("label");
		date = intentPa.getStringExtra("date");
		dbManager = new DatabaseManager(context);
		this.info=(TextView) super.findViewById(R.id.info);
		this.textv1=(TextView) super.findViewById(R.id.textView1);
		button1 = (Button)findViewById(R.id.button1);
		button2 = (Button)findViewById(R.id.button2);
		button3 = (Button)findViewById(R.id.button3);
		button4 = (Button)findViewById(R.id.button4);
		button5 = (Button)findViewById(R.id.button5);
		button1.setOnClickListener(new MyButtonOnClickListener());
		button2.setOnClickListener(new MyButtonOnClickListener());
		button3.setOnClickListener(new MyButtonOnClickListener());
		button4.setOnClickListener(new MyButtonOnClickListener());
		button5.setOnClickListener(new MyButtonOnClickListener());
		
		radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
		radio1 = (RadioButton)findViewById(R.id.radio1);
		radio2 = (RadioButton)findViewById(R.id.radio2);
		radio3 = (RadioButton)findViewById(R.id.radio3);
		radioGroup.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
		imgLocation = (ImageView)findViewById(R.id.imageView1);
		imgPoint = (ImageView)findViewById(R.id.imageView2);
		imgPoint.setVisibility(android.view.View.INVISIBLE);
		//获取图片的高度，需要在初始化完成后，故需使用handler
		layoutParams = imgLocation.getLayoutParams();
		handler.sendEmptyMessage(0);
		
		//添加触摸事件
		this.imgLocation.setOnTouchListener(new TouchListenerImp());
		relaPa = (RelativeLayout) findViewById(R.id.location);
		relaPa.setOnTouchListener(new OnTouchListener() {
			
			private float x1,y1,x2,y2;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN) {  
		            //当手指按下的时候  
		            x1= event.getX();  
		            y1 = event.getY();  
		        }  
		        if(event.getAction() == MotionEvent.ACTION_UP) {  
		            //当手指离开的时候  
		            x2 = event.getX();  
		            y2 = event.getY();  
		            if(y1 - y2 > 50) {  
		                Toast.makeText(ActiLocation.this, "向上滑", Toast.LENGTH_SHORT).show();  
		            } else if(y2 - y1 > 50) {  
		                Toast.makeText(ActiLocation.this, "向下滑", Toast.LENGTH_SHORT).show();  
		            } else if(x1 - x2 > 50) {  
		                Toast.makeText(ActiLocation.this, "向左滑", Toast.LENGTH_SHORT).show();  
		            } else if(x2 - x1 > 50) {  
		                Toast.makeText(ActiLocation.this, "向右滑", Toast.LENGTH_SHORT).show();  
		            }  
		        }  
		        return false;  
			}
		});
	
	}
	
	//onCreat 之后获取图片大小
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			width2 = imgLocation.getWidth();
			height2 = imgLocation.getHeight();
			textv1.setText(width2 + "\n" + height2);
		}
	};
	private class TouchListenerImp implements OnTouchListener{

		private float x1,y1,x2,y2;

		public boolean onTouch(View v, MotionEvent event) {
			
			if(event.getAction() == MotionEvent.ACTION_DOWN) {  
	            //当手指按下的时候  
	            x1= event.getX();  
	            y1 = event.getY();  
	        }  
	        if(event.getAction() == MotionEvent.ACTION_UP) {  
	            //当手指离开的时候  
	            x2 = event.getX();  
	            y2 = event.getY();  
	            if(y1 - y2 > 50) {  
//	                Toast.makeText(ActiLocation.this, "向上滑", Toast.LENGTH_SHORT).show();
//	                Intent intent = new Intent(context,ActiChoose.class);
//	                startActivity(intent);
	            } else if(y2 - y1 > 50) {  
//	                Toast.makeText(ActiLocation.this, "向下滑", Toast.LENGTH_SHORT).show();  
	            } else if(x1 - x2 > 50) {  
//	                Toast.makeText(ActiLocation.this, "向左滑", Toast.LENGTH_SHORT).show();  
	            } else if(x2 - x1 > 50) {  
//	                Toast.makeText(ActiLocation.this, "向右滑", Toast.LENGTH_SHORT).show();  
	            } else{
	            	if("-1".equals(high)) {
	    				Toast.makeText(context, "请先选择轨迹样式！", 1000).show();
	    				return false;
	    			}
	    			float x = event.getX();
	    			float y = event.getY();
	    			xmm = (int) x * 2000 / width2;
	    			ymm = (int) y * 1250 / height2;
	    			ActiLocation.this.info.setText("x= " + xmm +"\ny= " + ymm);
	    			imgPoint.setX(x);
	    			imgPoint.setY(y);
	    			imgPoint.setVisibility(android.view.View.VISIBLE);
	    			Position position = new Position();
	    			//添加到数据库
	    			position .setLabel(label);
	    			position.setDate(date);
	    			position.setHigh(high);
	    			position.setXmm(xmm);
	    			position.setYmm(ymm);
	    			positions.add(position);
//	    			dbManager.add2All1(positions);
	            }
	        } 
			
		return true;
		}
	}

	/**
	 * 
	 */
	@SuppressLint("ShowToast")
	private class MyButtonOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch(v.getId()) {
			case R.id.button3:
				int sizeInt = positions.size();
				if(sizeInt > 0) {
					positions.remove(sizeInt - 1);
					info.setText("该组数据已移除");
				}else {
					Toast.makeText(context, "请先选择坐标！", Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.button2:
				if(!positions.isEmpty()) {
					//把保存在List里面的数据保存到数据库后，清除List里面的内容
					dbManager.add2All1(positions);
					positions.clear();
					Toast.makeText(context, "数据应经保存到数据库！", Toast.LENGTH_SHORT).show();
				}else {
					Toast.makeText(context, "请先选择坐标点！", Toast.LENGTH_SHORT).show();
					break;
				}
				break;
			case R.id.button1:
				if(!positions.isEmpty()) {
					dbManager.add2All1(positions);
					positions.clear();
				}
				Intent intent = new Intent(context,ActiResultCurt.class);
				intent.putExtra("activity", 0);
				startActivity(intent);
				break;
			case R.id.button4:
				if(!positions.isEmpty()) {
					dbManager.add2All1(positions);
					positions.clear();
				}
				Intent intent2 = new Intent(context,ActiPretend.class);
				startActivity(intent2);
				break;
				
			case R.id.button5:
				if(!positions.isEmpty()) {
					dbManager.add2All1(positions);
					positions.clear();
				}
				Intent intent3 = new Intent(context,ActiChoose.class);
				intent3.putExtra("label", label);
				intent3.putExtra("date", date);
				startActivity(intent3);
				break;
				
				default:
					break;
					
			}
		}
		
	}
	
	/**
	 * 
	 */
	private class MyOnCheckedChangeListener implements OnCheckedChangeListener{

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			switch(checkedId) {
			case R.id.radio1:
				high = "高";
				break;
			case R.id.radio2:
				high = "中";
				break;
			case R.id.radio3:
				high = "低";
				break;
				default:
					break;
			}
		}
	}
		
	
}