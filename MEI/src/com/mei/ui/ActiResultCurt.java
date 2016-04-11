package com.mei.ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.mei.util.AllUtil;
import com.mei.util.DatabaseManager;
import com.mei.util.Labels;
import com.mei.util.Position;

@SuppressLint("ShowToast")
public class ActiResultCurt extends Activity {

	private Context context;
	private DatabaseManager dbManager;
	private List<Position> positions = new ArrayList<Position>();
	private Position position = new Position();
	private List<Labels> labels = new ArrayList<Labels>();
	private Labels label = new Labels();
	private List<AllUtil> allutils = new ArrayList<AllUtil>();
	private AllUtil allutil = new AllUtil();
	private String listStr = "";
	private TextView textv;
	private int actiInt;
	private WritableWorkbook workbook;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result_current);
		context = ActiResultCurt.this;
		textv = (TextView) findViewById(R.id.textView1);
		dbManager = new DatabaseManager(context);
		Intent intent = getIntent();
		actiInt = intent.getIntExtra("activity", 0);

		if (actiInt == 0) {
			positions = dbManager.queryAll();
			if (!positions.isEmpty()) {
				for (Position position : positions) {
					listStr += position.toString() + "\n";
				}
				textv.setText(listStr);
			} else {
				Toast.makeText(context, "未获取数据库中内容或者数据库为空！", Toast.LENGTH_SHORT)
						.show();
			}
		} else if (actiInt == 1) {
			labels = dbManager.queryLabel();
			if (!labels.isEmpty()) {
				for (Labels label : labels) {
					listStr += label.toString();
				}
				textv.setText(listStr);
			} else {
				textv.setText("数据为空");
				Toast.makeText(context, "未获取数据库中内容或者数据库为空！", Toast.LENGTH_SHORT)
						.show();
			}
		} else if (actiInt == 2) {
			positions = dbManager.queryAll();
			labels = dbManager.queryLabel();
			if (!labels.isEmpty()) {
				for (Labels label : labels) {
					listStr += label.toString();
				}
//				textv.setText(listStr);
			} else {
				listStr = "没有记录机器人类型数据！\n";
//				textv.setText("数据为空");
				Toast.makeText(context, "没有记录机器人类型数据！", Toast.LENGTH_SHORT)
						.show();
			}
			if (!positions.isEmpty()) {
				for (Position position : positions) {
					listStr += position.toString() + "\n";
				}
//				textv.setText(listStr);
			} else {
				listStr += "没有记录落点数据！\n";
				Toast.makeText(context, "没有", Toast.LENGTH_SHORT)
						.show();
			}
			textv.setText(listStr);
			/*
			try {
				allutils = dbManager.query2All();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			if (!allutils.isEmpty()) {
				for (AllUtil allutil : allutils) {
					listStr += allutil.toString() + "\n";
				}
				textv.setText(listStr);
			} else {
				Toast.makeText(context, "未获取数据库中内容或者数据库为空！", Toast.LENGTH_SHORT)
						.show();
			}
			 */
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = new MenuInflater(context);
		menuInflater.inflate(R.menu.menu_excel, menu);
		return super.onCreateOptionsMenu(menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_excel:
			Toast.makeText(context, "生成Excel表到" + "手机存储器的mei.xls", Toast.LENGTH_LONG)
					.show();
			String path = Environment.getExternalStorageDirectory().toString();
			File file = new File(path + "/mei.xls");
			WorkbookSettings ws = new WorkbookSettings();
			ws.setLocale(new Locale("en", "EN"));
			try {
				workbook = Workbook.createWorkbook(file, ws);
				// Modify the colour palette to bright red for the lime colour
				workbook.setColourRGB(Colour.LIME, 0xff, 0, 0);
			} catch (IOException e) {
				e.printStackTrace();
				Toast.makeText(context, "创建文件出错", Toast.LENGTH_SHORT).show();
				break;
			}

			WritableSheet wsheet1 = workbook.createSheet("落点", 0);
			WritableSheet wsheet2 = workbook.createSheet("类型", 1);
			String[] labelStr1 = { "序号", "标签", "时间", "轨迹", "X", "Y" };
			String[] labelStr2 = {"序号","标签","时间","机器人类型","发球方式","接球区域大小","回球情况","回球方式","随记"};
			for (int i = 0; i < labelStr1.length; i++) {

				Label label = new Label(i, 0, labelStr1[i]);
				try {
					wsheet1.addCell(label);
				} catch (RowsExceededException e) {
					e.printStackTrace();
				} catch (WriteException e) {
					e.printStackTrace();
				}
			}
			for (int i = 0; i < labelStr2.length; i++) {
				
				Label label = new Label(i, 0, labelStr2[i]);
				try {
					wsheet2.addCell(label);
				} catch (RowsExceededException e) {
					e.printStackTrace();
				} catch (WriteException e) {
					e.printStackTrace();
				}
			}
			
			positions = dbManager.queryAll();
			int i = 1;
			for (Position position : positions) {
				Number numberid = new Number(0, i, position.get_id());
				Label labellabel1 = new Label(1, i, position.getLabel());
				Label labellabel2 = new Label(2, i, position.getDate());
				Label labellabel3 = new Label(3, i, position.getHigh());
				Number numberx = new Number(4, i, position.getXmm());
				Number numbery = new Number(5, i, position.getYmm());
				try {
					wsheet1.addCell(numberid);
					wsheet1.addCell(labellabel1);
					wsheet1.addCell(labellabel2);
					wsheet1.addCell(labellabel3);
					wsheet1.addCell(numberx);
					wsheet1.addCell(numbery);
				} catch (RowsExceededException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
			}
			labels = dbManager.queryLabel();
			int j = 1;
			for(Labels label : labels) {
				Number numberid = new Number(0, j, label.get_id());
				Label labellabel1 = new Label(1, j, label.getLabel());
				Label labellabel2 = new Label(2, j, label.getDate());
				Label labellabel3 = new Label(3, j, label.getL1());
				Label labellabel4 = new Label(4, j, label.getL2());
				Label labellabel5 = new Label(5, j, label.getL3());
				Label labellabel6 = new Label(6, j, label.getL4());
				Label labellabel7 = new Label(7, j, label.getL5());
				Label labellabel8 = new Label(8, j, label.getJilu());
				try {
					wsheet2.addCell(numberid);
					wsheet2.addCell(labellabel1);
					wsheet2.addCell(labellabel2);
					wsheet2.addCell(labellabel3);
					wsheet2.addCell(labellabel4);
					wsheet2.addCell(labellabel5);
					wsheet2.addCell(labellabel6);
					wsheet2.addCell(labellabel7);
					wsheet2.addCell(labellabel8);
				} catch (Exception e) {
					// TODO: handle exception
				}
				j++;
			}
			try {
				workbook.write();
				workbook.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
