package itor.topnetwork.com.dxditor.activitys;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import itor.topnetwork.com.dxditor.R;
import itor.topnetwork.com.dxditor.bean.User;
import itor.topnetwork.com.dxditor.databinding.ABinding;
import itor.topnetwork.com.dxditor.databinding.TestitemmBinding;

/**
 * Created by handong on 2017/11/23.
 */

public class LoginPageActivity extends AppCompatActivity {

    private ListView lv;
    private List<User> tl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ABinding a = DataBindingUtil.setContentView(this, R.layout.a);
        User user = new User("23", "User");
        a.setUser(user);
        initView();
    }

    void initView() {
       /* final TextInputLayout usernameWrapper = (TextInputLayout) findViewById(R.id.usernameWrapper);
        final TextInputLayout passwordWrapper = (TextInputLayout) findViewById(R.id.psdWrapper);
        usernameWrapper.setHint("用户名");
        passwordWrapper.setHint("密码");*/

        lv = (ListView)findViewById(R.id.test_lv);
        tl = new ArrayList<User>();
        for (int i = 0; i < 100; i++) {
            tl.add(new User("1", "name", "eat", "white", i + ""));
        }
        mytestAdapter b=new mytestAdapter(LoginPageActivity.this);
        lv.setAdapter(b);
    }

    public class mytestAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private TestitemmBinding binding;
        public mytestAdapter(Context context) {
            inflater=LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return tl.size();
        }

        @Override
        public Object getItem(int position) {
            return tl.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                binding = DataBindingUtil.inflate(inflater, R.layout.testitemm, parent, false);
                convertView = binding.getRoot();
                convertView.setTag(binding);
            } else {
                binding = (TestitemmBinding) convertView.getTag();
            }
            binding.setUser(tl.get(position));
            binding.setAdapter(this);
            return convertView;
        }
    }

}
