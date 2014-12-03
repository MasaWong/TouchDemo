package me.chunyu.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import me.chunyu.demo.DragEvent.DragEventActivity;
import me.chunyu.demo.SelectEvent.SelectEventActivity;
import me.chunyu.demo.SortEvent.SortEventActivity;
import me.chunyu.demo.TouchEvent.TouchEventActivity;


public class MyActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        findViewById(R.id.my_tv_touch_event).setOnClickListener(
                getOnClickListener(TouchEventActivity.class));

        findViewById(R.id.my_tv_drag_event).setOnClickListener(
                getOnClickListener(DragEventActivity.class));

        findViewById(R.id.my_tv_sort_event).setOnClickListener(
                getOnClickListener(SortEventActivity.class));

        findViewById(R.id.my_tv_select_event).setOnClickListener(
                getOnClickListener(SelectEventActivity.class));
    }

    private View.OnClickListener getOnClickListener(final Class nextClass) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyActivity.this, nextClass);
                startActivity(intent);
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
