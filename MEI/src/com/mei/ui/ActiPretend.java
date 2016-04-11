package com.mei.ui;

import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActiPretend extends Activity{

	@SuppressWarnings("unused")
	private String packageName = "sunpro518.study_viewpager";
	private ViewPager mPager;//ҳ������
    private List<View> listViews; // Tabҳ���б�
    private ImageView cursor;// ����ͼƬ
    private TextView t1, t2, t3;// ҳ��ͷ��
    private int offset = 0;// ����ͼƬƫ����
    private int currIndex = 0;// ��ǰҳ�����
    private int bmpW;// ����ͼƬ���
    private LinearLayout linear01;//�������֣������趨������ɫ
    private ImageView imgLay01,imgLay02,imgLay03;//ViewPager�ڲ��Ĳ��ֵ�ImageView;
    private View view1,view2,view3;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view_pager);
        //��ΪInitViewPager������Ҫ�õ�����ļ�������������Ҫ��ǰΪ�丳ֵ��
        view1 = View.inflate(this, R.layout.fragment1, null);
	    view2 = View.inflate(this, R.layout.fragment2, null);
	    view3 = View.inflate(this, R.layout.fragment3, null);
        imgLay01 = (ImageView)view1.findViewById(R.id.imag_lay01);
	    imgLay02 = (ImageView)view2.findViewById(R.id.imag_lay02);
	    imgLay03 = (ImageView)view3.findViewById(R.id.imag_lay03);
	     /**
         * Ϊʲô�⼸������Ҫ����onCreat�������menu�ļ�������ȴ���ã�
         */
        InitTextView();
        InitImageView();
        InitViewPager();
        //��˫���¼�
        
        imgLay01.setOnTouchListener(new onDoubleClick(imgLay01));
        imgLay02.setOnTouchListener(new onDoubleClick(imgLay02));
        imgLay03.setOnTouchListener(new onDoubleClick(imgLay03));
       
    }
    
    /**
	 * ��ʼ��ͷ��
	 */
	private void InitTextView() {
	    t1 = (TextView) findViewById(R.id.text1);
	    t2 = (TextView) findViewById(R.id.text2);
	    t3 = (TextView) findViewById(R.id.text3);
	    t1.setText(R.string.toptext_1);
	    t2.setText(R.string.toptext_2);
	    t3.setText(R.string.toptext_3);
	    t1.setOnClickListener(new MyOnClickListener(0));
	    t2.setOnClickListener(new MyOnClickListener(1));
	    t3.setOnClickListener(new MyOnClickListener(2));
	}

	/**
     * ͷ��������
     */
    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

		@Override
        public void onClick(View v) {
            mPager.setCurrentItem(index);  
        }
    };
    
    /**
	 * ��ʼ��ViewPager
	 */
	private void InitViewPager() {
		//int num = index;
	    mPager = (ViewPager) findViewById(R.id.vPager);
	    
	    listViews = new ArrayList<View>();
	    listViews.add(view1);
	    listViews.add(view2);
	    listViews.add(view3);
	    
	    imgLay01.setImageResource(R.drawable.tw1);
	    imgLay02.setImageResource(R.drawable.tangwei2);
	    imgLay03.setImageResource(R.drawable.tangwei3);
	    imgLay01.setScaleType(ScaleType.FIT_XY);
	    imgLay02.setScaleType(ScaleType.FIT_XY);
	    imgLay03.setScaleType(ScaleType.FIT_XY);
	    
	    mPager.setAdapter(new MyPagerAdapter(listViews));
	    mPager.setCurrentItem(0);
	    mPager.setOnPageChangeListener(new MyOnPageChangeListener());
	}

	/**
     * ViewPager������
     */
    public class MyPagerAdapter extends PagerAdapter {
        
    	public List<View> mListViews;
        public MyPagerAdapter(List<View> mListViews) {
            this.mListViews = mListViews;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(mListViews.get(arg1));
        }

        @Override
        public void finishUpdate(View arg0) {
        }

        @Override
        public int getCount() {
            return mListViews.size();
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(mListViews.get(arg1), 0);
            return mListViews.get(arg1);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == (arg1);
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
        }
    }

    /**
     * ��ʼ������
     */
    private void InitImageView() {
        cursor = (ImageView) findViewById(R.id.cursor);
        bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.a)
                .getWidth();// ��ȡͼƬ���
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// ��ȡ�ֱ��ʿ��
        offset = (screenW / 3 - bmpW) / 2;// ����ƫ����
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        cursor.setImageMatrix(matrix);// ���ö�����ʼλ��
    }
    
    /**
     * ҳ���л�����
     */
    public class MyOnPageChangeListener implements OnPageChangeListener {

        int one = offset * 2 + bmpW;// ҳ��1 -> ҳ��2 ƫ����
        int two = one * 2;// ҳ��1 -> ҳ��3 ƫ����

		@Override
        public void onPageSelected(int arg0) {
            Animation animation = null;
            switch (arg0) {
            case 0:
                if (currIndex == 1) {
                    animation = new TranslateAnimation(one, 0, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, 0, 0, 0);
                }
                t1.setTextColor(getResources().getColor(R.color.red));
            	t2.setTextColor(getResources().getColor(R.color.black));
            	t3.setTextColor(getResources().getColor(R.color.black));
                break;
            case 1:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, one, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, one, 0, 0);
                }
                t2.setTextColor(getResources().getColor(R.color.red));
            	t1.setTextColor(getResources().getColor(R.color.black));
            	t3.setTextColor(getResources().getColor(R.color.black));
                break;
            case 2:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, two, 0, 0);
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation(one, two, 0, 0);
                }
                t3.setTextColor(getResources().getColor(R.color.red));
            	t2.setTextColor(getResources().getColor(R.color.black));
            	t1.setTextColor(getResources().getColor(R.color.black));
            	System.out.println(t3.getCurrentTextColor());
                break;
            }
            currIndex = arg0;
            animation.setFillAfter(true);// True:ͼƬͣ�ڶ�������λ��
            animation.setDuration(300);
            cursor.startAnimation(animation);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }

    /**
     * menu����
     */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = new MenuInflater(this);
		inflater.inflate(R.menu.menu_pretend, menu);
		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * menuѡ�������
	 */
	@SuppressLint("NewApi")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//�����趨������ɫ
        linear01 = (LinearLayout)findViewById(R.id.linearlayout0);
		if(item.getGroupId() == R.id.menugroup1){
			if(item.isChecked()){
				item.setChecked(false);
			}else{
				item.setChecked(true);
			}
		}
		if(item.getGroupId() == R.id.menugroup2){
			if(item.isChecked()){
				item.setChecked(false);
			}else{
				item.setChecked(true);
			}
		}
		/*
		//ע��˴���
		if(item.getItemId() != R.id.menu2 && item.getItemId() != R.id.menu1){
			Toast.makeText(MainViewPager.this, item.getTitle(), Toast.LENGTH_SHORT).show();
		}
		*/
		switch (item.getItemId()) {
		case R.id.menu6:
			linear01.setBackground(getResources().getDrawable(R.color.deepskyblue));
			break;
		case R.id.menu7:
			linear01.setBackground(getResources().getDrawable(R.color.purple));
			break;
		case R.id.menu8:
			linear01.setBackground(getResources().getDrawable(R.color.white));
			break;
		case R.id.menu3:
			imgLay01.setImageResource(R.drawable.tw1);
		    imgLay02.setImageResource(R.drawable.tangwei2);
		    imgLay03.setImageResource(R.drawable.tangwei3);
		    t1.setText(R.string.toptext_1);
	        t2.setText(R.string.toptext_2);
	        t3.setText(R.string.toptext_3);
	        int redId1 = R.color.red;
	        int redId2 = getResources().getColor(R.color.red);
	        System.out.println("redId1" + redId1);
	        System.out.println("redId2" + redId2);
			break;
		case R.id.menu4:
			imgLay01.setImageResource(R.drawable.fj1);
		    imgLay02.setImageResource(R.drawable.fj2);
		    imgLay03.setImageResource(R.drawable.fj3);
		    t1.setText(R.string.toptext_2_1);
	        t2.setText(R.string.toptext_2_2);
	        t3.setText(R.string.toptext_2_3);
			break;
		case R.id.menu5:
			imgLay01.setImageResource(R.drawable.pic_3_1);
		    imgLay02.setImageResource(R.drawable.pic_3_2);
		    imgLay03.setImageResource(R.drawable.pic_3_3);
		    t1.setText(R.string.toptext_3_1);
	        t2.setText(R.string.toptext_3_2);
	        t3.setText(R.string.toptext_3_3);
			break;
		case R.id.menu_search:
			//Intent intentSearch = new Intent(MainViewPager.this, Search.class);
		//	intentSearch.setClass;
			//MainViewPager.this.startActivity(intentSearch)
			
			break;
		
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * ͼƬ˫���������ڲ���
	 */
	class onDoubleClick implements View.OnTouchListener{

		private int count = 0;
		private long firClick;
		private long secClick;
		private ImageView imageView;
		public onDoubleClick(View view){
			imageView = (ImageView) view;
		}
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			
			if(MotionEvent.ACTION_DOWN == event.getAction()){
				count++;
				if(count == 1){
					firClick = System.currentTimeMillis();
					
				} else if (count == 2){
					secClick = System.currentTimeMillis();
					if(secClick - firClick < 300){
						//TODO ˫������
						if (imageView.getScaleType() == ScaleType.FIT_XY){
							imageView.setScaleType(ScaleType.FIT_CENTER);
						}else{
							imageView.setScaleType(ScaleType.FIT_XY);
						}
						
					}
					count = 0;
					firClick = 0;
					secClick = 0;
					
				}
			}
			return false;
		}
		
	} 
}


