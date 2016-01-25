package com.attosoft.mvpdemo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.attosoft.mvpdemo.R;
import com.attosoft.mvpdemo.ui.adapter.DemoListAdapter;
import com.attosoft.mvpdemo.ui.adapter.item.DemoListItem;
import com.attosoft.mvpdemo.ui.view.contact.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListener {

    private FloatingActionButton fab;
    private RecyclerView mList;
    private DemoListAdapter mDemoListAdapter;
    private List<Integer> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        mList = (RecyclerView) findViewById(R.id.recyclerView);

        mDemoListAdapter = new DemoListAdapter();
        list.add(DemoListItem.TYPE_CONTACT);
        list.add(DemoListItem.TYPE_MAIL);
        list.add(DemoListItem.TYPE_SETTING);
        mDemoListAdapter.setData(list);
        mDemoListAdapter.setOnItemClickListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mList.setLayoutManager(linearLayoutManager);
        mList.setAdapter(mDemoListAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


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

    @Override
    public void onItemClick(View v, int position, Object object) {
        switch (position){
            case DemoListItem.TYPE_CONTACT:{
                Intent intent = new Intent();
                intent.setClass(this,ContactActivity.class);
                startActivity(intent);
            }
                break;
            case DemoListItem.TYPE_MAIL:{
                Toast.makeText(this,"邮件列表实例",Toast.LENGTH_SHORT).show();
            }
                break;
            case DemoListItem.TYPE_SETTING:{
                Toast.makeText(this,"设置",Toast.LENGTH_SHORT).show();
            }
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemDelete(int position, Object object) {

    }
}
