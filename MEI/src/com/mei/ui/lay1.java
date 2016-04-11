package com.mei.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;

public class lay1 extends Activity {

	LinearLayout linear01;
	View view;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lay1);
		linear01 = (LinearLayout)findViewById(R.id.lay01);
		linear01.setOnClickListener((OnClickListener) new onDoubleClick());
	}
	/**
	 * Í¼Æ¬Ë«»÷¼àÌýÆ÷ÄÚ²¿Àà
	 */
	class onDoubleClick implements View.OnTouchListener{

		private int count = 0;
		private long firClick ;
		private long secClick;
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if(MotionEvent.ACTION_DOWN == event.getAction()){
				count++;
				if(count == 1){
					firClick = System.currentTimeMillis();
					
				} else if (count == 2){
					secClick = System.currentTimeMillis();
					if(secClick - firClick < 1000){
						Toast.makeText(lay1.this,"Ë«»÷Í¼Æ¬" ,Toast.LENGTH_SHORT)
							.show();
						
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
