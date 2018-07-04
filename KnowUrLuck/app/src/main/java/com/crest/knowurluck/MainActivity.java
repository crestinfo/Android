package com.crest.knowurluck;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static NonSwipeableViewPager mViewPager;
    MyCustomPagerAdapter pagerAdapter;
    public static Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNext = (Button) findViewById(R.id.btnNext);
        mViewPager = (NonSwipeableViewPager) findViewById(R.id.viewPagerQue);
        final String que[] = getResources().getStringArray(R.array.Questions);
        pagerAdapter = new MyCustomPagerAdapter(getApplicationContext(), que);
        mViewPager.setAdapter(pagerAdapter); //set adapter to viewpager.
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mViewPager.getCurrentItem() == que.length - 1) {
                    btnNext.setBackgroundColor(Color.RED);
                    btnNext.setText("Test Luck");
                    btnNext.setTextColor(Color.WHITE);
                    MyDialog myDialog = new MyDialog();
                    myDialog.show(getFragmentManager(), "MyDialog");
                } else
                    mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
            }
        });
    }

    public class MyCustomPagerAdapter extends PagerAdapter {
        Context context;
        String[] myQue;
        LayoutInflater layoutInflater;

        public MyCustomPagerAdapter(Context applicationContext, String[] que) {
            this.context = applicationContext;
            this.myQue = que;
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return myQue.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View itemView = layoutInflater.inflate(R.layout.layout_questions, container, false);
            TextView txtQue = (TextView) itemView.findViewById(R.id.txtQuestions);
            txtQue.setText(myQue[position]);
            container.addView(itemView);
            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }
}
