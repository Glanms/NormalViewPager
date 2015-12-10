package com.demo.list.viewpager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.demo.list.viewpager.activity.SimpleFragmentActivity;
import com.demo.list.viewpager.activity.ViewFlipperActivity1;
import com.demo.list.viewpager.activity.ViewFlipperActivity2;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView demo_lv;
    private List<String> demo_label = new ArrayList<String>();
    private Activity[] toActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        demo_lv = (ListView)findViewById(R.id.demo_lv);
        initList();

        final ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.main_demo_label, R.layout.main_list_item);
        demo_lv.setAdapter(arrayAdapter);
        demo_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                        startNewActivity(SimpleFragmentActivity.class);
                        break;
                    case 1:
                        startNewActivity(ViewFlipperActivity1.class);
                        break;
                    case 2:
                        startNewActivity(ViewFlipperActivity2.class);
                        break;
                    case 3:
                        startNewActivity(SimpleFragmentActivity.class);
                        break;
                }
            }
        });
    }

    private void initList(){
        String[] itemStr = this.getResources().getStringArray(R.array.main_demo_label);
        for(int i=0;i<itemStr.length;i++)
        {
            demo_label.add(itemStr[i]);
        }
    }

    private void startNewActivity(Class<? extends Activity> activityClass){
        Intent _intent = new Intent(MainActivity.this,activityClass);
        startActivity(_intent);
        overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_slide_out_right);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
