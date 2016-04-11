package com.mei.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText edit1;
	private Button button1;
	private Button button2;
	private Button button3;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = MainActivity.this;
		edit1 = (EditText) findViewById(R.id.editText1);
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		button1.setOnClickListener(new MyButtonOnClickListener());
		button2.setOnClickListener(new MyButtonOnClickListener());
		button3.setOnClickListener(new MyButtonOnClickListener());
	
	}
	
	/**
	 * 
	 * @author dell
	 *
	 */
	@SuppressLint({ "SimpleDateFormat", "ShowToast" })
	private class MyButtonOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			SimpleDateFormat formatter = new SimpleDateFormat(
					"MM月dd日 HH:mm:ss ");
			Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
			String str = formatter.format(curDate);
			String editStr = edit1.getText().toString();
			switch (v.getId()) {
			case R.id.button1:
				if ("".equals(editStr)) {
					Toast.makeText(context, "请输入标签内容！", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				Intent intent1 = new Intent(context, ActiLocation.class);
				intent1.putExtra("label", editStr);
				intent1.putExtra("date", str);
				startActivity(intent1);
				break;

			case R.id.button2:
				if ("".equals(editStr)) {
					Toast.makeText(context, "请输入标签内容！", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				Intent intent2 = new Intent(context, ActiChoose.class);
				intent2.putExtra("label", editStr);
				intent2.putExtra("date", str);
				startActivity(intent2);
				break;
			case R.id.button3:
				Intent intent = new Intent(context, ActiResultCurt.class);
				intent.putExtra("activity", 2);
				startActivity(intent);
				break;
			default:
				break;
			}
		}

	}

}
