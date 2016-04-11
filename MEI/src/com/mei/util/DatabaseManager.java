package com.mei.util;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class DatabaseManager {

	private MySQLiteOpenHelper helper;
	private SQLiteDatabase db;

	public DatabaseManager(Context context) {
		helper = new MySQLiteOpenHelper(context);
		db = helper.getWritableDatabase();
	}

	public void add2Name(List<Position> positions) {
		db.beginTransaction();
		try {
			for (Position position : positions) {
				ContentValues cv = new ContentValues();
				cv.put("LABEL", position.getLabel());
				cv.put("DATE", position.getDate());
				db.insert("name", null, cv);
			}

			db.setTransactionSuccessful(); // 设置事务成功完成
		} finally {
			db.endTransaction(); // 结束事务
		}
	}

	public void add2All1(List<Position> positions) {
		db.beginTransaction(); // 开始事务
		try {
			for (Position position : positions) {
				ContentValues cv = new ContentValues();
				cv.put("LABEL", position.getLabel());
				cv.put("DATE", position.getDate());
				cv.put("HIGH", position.getHigh());
				cv.put("X", position.getXmm());
				cv.put("Y", position.getYmm());
				db.insert("all1", null, cv);
				// db.execSQL("INSERT INTO all1 VALUES(null, ?, ?, ?,?)",
				// new Object[]{position.getLabel(),
				// position.getDate(),
				// position.getXmm(),
				// position.getYmm()});
			}

			db.setTransactionSuccessful(); // 设置事务成功完成
		} finally {
			db.endTransaction(); // 结束事务
		}
	}

	public void add2label(List<Labels> labels) {
		db.beginTransaction(); // 开始事务
		try {
			for (Labels label : labels) {
				ContentValues cv = new ContentValues();
				cv.put("LABEL", label.getLabel());
				cv.put("DATE", label.getDate());
				cv.put("l1", label.getL1());
				cv.put("l2", label.getL2());
				cv.put("l3", label.getL3());
				cv.put("l4", label.getL4());
				cv.put("l5", label.getL5());
				cv.put("jilu", label.getJilu());
				db.insert("label1", null, cv);
			}

			db.setTransactionSuccessful(); // 设置事务成功完成
		} finally {
			db.endTransaction(); // 结束事务
		}
	}

	/**
	 * query all persons, return list
	 * 
	 * @return List<Person>
	 */
	public List<Position> queryAll() {
		ArrayList<Position> positions = new ArrayList<Position>();
		Cursor c = queryTheCursor("all1");
		while (c.moveToNext()) {
			Position position = new Position();
			position.set_id(c.getInt(c.getColumnIndex("_id")));
			position.setLabel(c.getString(c.getColumnIndex("LABEL")));
			position.setDate(c.getString(c.getColumnIndex("DATE")));
			position.setHigh(c.getString(c.getColumnIndex("HIGH")));
			position.setXmm(c.getInt(c.getColumnIndex("X")));
			position.setYmm(c.getInt(c.getColumnIndex("Y")));
			positions.add(position);
		}
		c.close();
		return positions;
	}

	/**
	 * 
	 */
	public List<Labels> queryLabel() {
		List<Labels> labels = new ArrayList<Labels>();
		Cursor c = queryTheCursor("label1");
		while (c.moveToNext()) {
			Labels label = new Labels();
			label.set_id(c.getInt(c.getColumnIndex("_id")));
			label.setLabel(c.getString(c.getColumnIndex("LABEL")));
			label.setDate(c.getString(c.getColumnIndex("DATE")));
			label.setL1(c.getString(c.getColumnIndex("l1")));
			label.setL2(c.getString(c.getColumnIndex("l2")));
			label.setL3(c.getString(c.getColumnIndex("l3")));
			label.setL4(c.getString(c.getColumnIndex("l4")));
			label.setL5(c.getString(c.getColumnIndex("l5")));
			label.setJilu(c.getString(c.getColumnIndex("jilu")));
			labels.add(label);
		}
		c.close();
		return labels;

	}

	public List<AllUtil> query2All() {
		List<AllUtil> allUtils = new ArrayList<AllUtil>();
		Cursor c;
		try {
			c = db.rawQuery("SELECT "
					+ "all1._id,all1.LABEL,all1.DATE,all1.HIGH,all1.X,all1.Y, "
					+ "label1.l1,label1.l2,label1.l3,label1.l4,label1.l5,label1.jilu "
					+ "FROM all1,label1 "
					+ "WHERE all1.LABEL=label1.LABEL AND all1.DATE=label1.DATE", null);
			while (c.moveToNext()) {
				AllUtil allutil = new AllUtil();
				allutil.set_id(c.getInt(c.getColumnIndex("all1._id")));
				allutil.setLabel(c.getString(c.getColumnIndex("all1.LABEL")));
				allutil.setDate(c.getString(c.getColumnIndex("all1.DATE")));
				allutil.setHigh(c.getString(c.getColumnIndex("all1.HIGH")));
				allutil.setXmm(c.getInt(c.getColumnIndex("all1.X")));
				allutil.setYmm(c.getInt(c.getColumnIndex("Y")));
				allutil.setL1(c.getString(c.getColumnIndex("label1.l1")));
				allutil.setL2(c.getString(c.getColumnIndex("label1.l2")));
				allutil.setL3(c.getString(c.getColumnIndex("label1.l3")));
				allutil.setL4(c.getString(c.getColumnIndex("label1.l4")));
				allutil.setL5(c.getString(c.getColumnIndex("l5")));
				allutil.setJilu(c.getString(c.getColumnIndex("label1.jilu")));
				allUtils.add(allutil);
			}
			c.close();
		} catch (SQLiteException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
//			c.close();
		}
		return allUtils;

	}

	/**
	 * 查询某个表，表名为name, return cursor
	 * 
	 * @return Cursor
	 */
	public Cursor queryTheCursor(String name) {
		Cursor c = db.rawQuery("SELECT * FROM " + name, null);
		return c;
	}

	/**
	 * 
	 */
	// public Cursor queryTheCursor2() {
	// // Cursor c = db.
	// }

}
