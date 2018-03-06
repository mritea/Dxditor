package itor.topnetwork.com.dxditor.activitys;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.ViewGroup;
import android.widget.Toast;

import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import itor.topnetwork.com.dxditor.R;
import itor.topnetwork.com.dxditor.fragment.BaseFragmentActivity;
import itor.topnetwork.com.dxditor.fragment.xj.XJGjfxFragment;
import itor.topnetwork.com.dxditor.fragment.xj.XJGjglFragment;
import itor.topnetwork.com.dxditor.fragment.xj.XJWdqsFragment;
import itor.topnetwork.com.dxditor.fragment.xj.XjWdpmFragment;
import itor.topnetwork.com.dxditor.fragment.xj.XjXjjcFragment;

/**
 * 线夹管理
 * Created by D.Han on 2017/12/5.
 */

public class XJManageActivity extends BaseFragmentActivity {
    /**
     * Tab标题
     */
    private static final String[] TITLE = new String[]{"告警管理", "线夹监测", "温度排名", "温度趋势",
            "告警分析"};
    private List<Class> fList;
    private ViewPager pager;

    @Override
    public int getLayout() {
        return R.layout.viewpagetlayout;
    }

    @Override
    public void initView() {
        pager = (ViewPager) findViewById(R.id.pager);
    }

    @Override
    public void onPrepare() {
        fList = new ArrayList<Class>();
        fList.add(XJGjglFragment.class);
        fList.add(XjXjjcFragment.class);
        fList.add(XjWdpmFragment.class);
        fList.add(XJWdqsFragment.class);
        fList.add(XJGjfxFragment.class);

        //ViewPager的adapter
        FragmentPagerAdapter adapter = new TabPageIndicatorAdapter(XJManageActivity.this, getSupportFragmentManager());
        pager.setAdapter(adapter);

        //实例化TabPageIndicator然后设置ViewPager与之关联
        TabPageIndicator indicator = (TabPageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(pager);

        //如果我们要对ViewPager设置监听，用indicator设置就行了
        indicator.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                Toast.makeText(getApplicationContext(), TITLE[arg0], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }


    /**
     * ViewPager适配器
     *
     * @author
     */
    class TabPageIndicatorAdapter extends FragmentPagerAdapter {
        private static final int COUNT = 5;

        private Fragment[] mFragments;
        private Context mContext;

        public TabPageIndicatorAdapter(Context context, FragmentManager fm) {
            super(fm);
            this.mContext = context;
            this.mFragments = new Fragment[COUNT];
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            bundle.putString("param_key_text", TITLE[position % TITLE.length]);
            return Fragment.instantiate(mContext, fList.get(position).getName(), bundle);
        }

        @Override
        public int getCount() {
            return COUNT;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment fragment = (Fragment) super.instantiateItem(container, position);
            mFragments[position] = fragment;
            return fragment;
        }

        public Fragment[] getFragments() {
            return mFragments;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLE[position % TITLE.length];
        }


    }
}
