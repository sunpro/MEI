package com.mei.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteOpenHelper extends SQLiteOpenHelper{

	//数据库的名称
	private static final String DATABASE_NAME = "meiDatebase.db";
	//数据库的版本
	private static final int DATABASE_VERSION = 1;
	//数据表的名称
	public static final String DATABASE_TABLE_NAME = "name";
	public static final String DATABASE_TABLE_ALL = "all1";
	public static final String DATABASE_TABLE_LABEL = "label1";
	//数据表字段名
	public static final String TABLE_1_0 = "_id";
	public static final String TABLE_1_1 = "LABEL";
	public static final String TABLE_1_2 = "DATE";
	public static final String TABLE_1_3 = "HIGH";
	public static final String TABLE_1_4 = "X";
	public static final String TABLE_1_5 = "Y";	
	
	public static final String TABLE_2_0 = "_id";
	public static final String TABLE_2_1 = "LABEL";
	public static final String TABLE_2_2 = "DATE";
	public static final String TABLE_2_3 = "";
	public static final String TABLE_2_4 = "";
	public static final String TABLE_2_5 = "";
	//创建数据库的SQL语句
	private static final String DATABASE_CREATE_NAME = "create table IF NOT EXISTS " + 
			DATABASE_TABLE_NAME + " ( " + 
			TABLE_1_0 + " integer primary key autoincrement, " + 
			TABLE_1_1 + " text not null, " + 
			TABLE_1_2 + " text not null);" ;
			
	
	private static final String DATABASE_CREATE_ALL = "create table IF NOT EXISTS " + 
			DATABASE_TABLE_ALL + " ( " +   
			TABLE_1_0 + " integer primary key autoincrement, " + 
			TABLE_1_1 + " text not null, " + 
			TABLE_1_2 + " text not null, " + 
			TABLE_1_3 + " text not null, " + 
			TABLE_1_4 + " integer, " + 
			TABLE_1_5 + " integer);";
	private static final String DATABASE_CREATE_LABEL = "create table IF NOT EXISTS " + 
			DATABASE_TABLE_LABEL + " ( " +   
			"_id  integer primary key autoincrement, " + 
			"LABEL text not null, " + 
			"DATE text not null, " + 
			"l1 text , " + 
			"l2 text , " + 
			"l3 text , " + 
			"l4 text , " + 
			"l5 text , " + 
			"jilu text );";
	//构造函数1
	public MySQLiteOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		Log.i("MySQLiteOpenHelper","contructer1");
	}
	public MySQLiteOpenHelper(Context context,String name) {
		super(context, name, null, DATABASE_VERSION);
		Log.i("MySQLiteOpenHelper","contructer2");
	}
	public MySQLiteOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		Log.i("MySQLiteOpenHelper","contructer3");
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE_NAME);
		db.execSQL(DATABASE_CREATE_LABEL);
		db.execSQL(DATABASE_CREATE_ALL);
		Log.i("MySQLiteOpenHelper","onCreat");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//检测到新版本，删除旧表，生成新表
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_ALL);
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_LABEL);
		//创建新表
		onCreate(db);
		Log.i("MySQLiteOpenHelper","onUpgrade");
	}

}
