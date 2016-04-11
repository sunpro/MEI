package com.mei.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.mei.util.DatabaseManager;
import com.mei.util.Labels;

@SuppressWarnings("unused")
public class ActiChoose extends Activity {

	private Context context;
	private int chooseNum = 5;
	private int ABCNum = 4;
	// private TextView[] textv = new TextView[5];
	private TextView text1, text2, text3, text4, text5;
	private RadioGroup rg1, rg2, rg3, rg4, rg5;
	private RadioButton rb10, rb11, rb12, rb13;
	private RadioButton rb20, rb21, rb22, rb23;
	private RadioButton rb30, rb31, rb32, rb33;
	private RadioButton rb40, rb41, rb42, rb43;
	private RadioButton rb50, rb51, rb52, rb53;
	private Button button1, button2;
	// private RadioButton[] rb = new RadioButton[20];
	private String labelStr;
	private String dateStr;
	private String ans = "";
	private List<Labels> labels = new ArrayList<Labels>();
	private Labels label = new Labels();
	// private Map<String,Object> map = new HashMap<String,Object>();
	private DatabaseManager dbManager;

	private Button button3;
	private Button button4;
	private EditText edittext;
	private String strTemp;
	private String strAll = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题//必须在setContentView之前
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏

		setContentView(R.layout.activity_choose);
		context = ActiChoose.this;
		Intent intent = getIntent();
		labelStr = intent.getStringExtra("label");
		dateStr = intent.getStringExtra("date");
		dbManager = new DatabaseManager(context);
		initLabel();
		initView();
		rg1.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
		rg2.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
		rg3.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
		rg4.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
		rg5.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
		//
		button1.setOnClickListener(new MyButtonOnClickListener());
		button2.setOnClickListener(new MyButtonOnClickListener());
		button3.setOnClickListener(new MyButtonOnClickListener());
		button4.setOnClickListener(new MyButtonOnClickListener());
	}

	/**
	 * Label初始化默认情况的选择
	 */
	private void initLabel() {
		label.setLabel(labelStr);
		label.setDate(dateStr);
		label.setL1("双发球接球");
		label.setL2("电机驱动");
		label.setL3("一个拍面");
		label.setL4("网前居多");
		label.setL5("撩球");
		label.setJilu("");
	}

	/**
	 * 
	 */
	private void initView() {
		// button
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		button4 = (Button) findViewById(R.id.button4);

		// text
		text1 = (TextView) findViewById(R.id.textView1);
		text2 = (TextView) findViewById(R.id.textView2);
		text3 = (TextView) findViewById(R.id.textView3);
		text4 = (TextView) findViewById(R.id.textView4);
		text5 = (TextView) findViewById(R.id.textView5);
		// g
		rg1 = (RadioGroup) findViewById(R.id.radioGroup1);
		rg2 = (RadioGroup) findViewById(R.id.radioGroup2);
		rg3 = (RadioGroup) findViewById(R.id.radioGroup3);
		rg4 = (RadioGroup) findViewById(R.id.radioGroup4);
		rg5 = (RadioGroup) findViewById(R.id.radioGroup5);
		// 1
		rb10 = (RadioButton) findViewById(R.id.radio10);
		rb11 = (RadioButton) findViewById(R.id.radio11);
		rb12 = (RadioButton) findViewById(R.id.radio12);
		rb13 = (RadioButton) findViewById(R.id.radio13);
		// 2
		rb20 = (RadioButton) findViewById(R.id.radio20);
		rb21 = (RadioButton) findViewById(R.id.radio21);
		rb22 = (RadioButton) findViewById(R.id.radio22);
		rb23 = (RadioButton) findViewById(R.id.radio23);
		// 3
		rb30 = (RadioButton) findViewById(R.id.radio30);
		rb31 = (RadioButton) findViewById(R.id.radio31);
		rb32 = (RadioButton) findViewById(R.id.radio32);
		rb33 = (RadioButton) findViewById(R.id.radio33);
		// 4
		rb40 = (RadioButton) findViewById(R.id.radio40);
		rb41 = (RadioButton) findViewById(R.id.radio41);
		rb42 = (RadioButton) findViewById(R.id.radio42);
		rb43 = (RadioButton) findViewById(R.id.radio43);
		// 5
		rb50 = (RadioButton) findViewById(R.id.radio50);
		rb51 = (RadioButton) findViewById(R.id.radio51);
		rb52 = (RadioButton) findViewById(R.id.radio52);
		rb53 = (RadioButton) findViewById(R.id.radio53);
	}

	/**
	 * 
	 */
	private class MyOnCheckedChangeListener implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			RadioButton rb = (RadioButton) findViewById(checkedId);
			ans = rb.getText().toString();
			switch (group.getId()) {
			case R.id.radioGroup1:
				label.setL1(ans);
				break;
			case R.id.radioGroup2:
				label.setL2(ans);
				break;
			case R.id.radioGroup3:
				label.setL3(ans);
				break;
			case R.id.radioGroup4:
				label.setL4(ans);
				break;
			case R.id.radioGroup5:
				label.setL5(ans);
				break;
			}
//			labels.add(label);
		}
	}

	/**
	 * 
	 */
	private class MyButtonOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.button1:
			case R.id.button2:
//				labels.add(label);
				if (!labels.isEmpty()) {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							context);
					// builder.setIcon(R.drawable.ic_launcher);
					builder.setTitle("系统提示");
					builder.setMessage("有数据未保存！");
					builder.setPositiveButton("保存",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									// TODO

									dbManager.add2label(labels);
									labels.clear();
									strAll = "";
									Intent intent = new Intent(context,
											ActiResultCurt.class);
									intent.putExtra("activity", 1);
									startActivity(intent);
								}
							});
					builder.setNegativeButton("不保存",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									labels.clear();
									strAll = "";
									Intent intent = new Intent(context,
											ActiResultCurt.class);
									intent.putExtra("activity", 1);
									startActivity(intent);
								}
							});
					builder.create().show();
				}else{
					Intent intent = new Intent(context, ActiResultCurt.class);
					intent.putExtra("activity", 1);
					startActivity(intent);
				}
				break;
			case R.id.button3:
				edittext = new EditText(context);
				// edittext.setWidth(pixels);
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				// builder.setIcon(R.drawable.ic_launcher);
				builder.setTitle("MEI随记");
				builder.setView(edittext);
				builder.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								// TODO
								strTemp = edittext.getText().toString() + "\n";
								strAll += strTemp;
								label.setJilu(strAll);
							}
						});
				builder.setNegativeButton("取消",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {

							}
						});
				builder.create().show();
				break;
			case R.id.button4:
				labels.add(label);
				if (labels.isEmpty()) {
					Toast.makeText(context, "没有可保存的数据", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				dbManager.add2label(labels);
				labels.clear();
				strAll = "";
				Toast.makeText(context, "数据保存成功！", Toast.LENGTH_SHORT).show();
				break;
			}

		}

	}

}
