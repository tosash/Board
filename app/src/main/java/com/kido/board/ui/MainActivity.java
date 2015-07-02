package com.kido.board.ui;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.kido.board.R;


public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getName();
    private Context fContext = null;
    private Toolbar mToolbar;
    private TextView mAccount;
    private ImageButton mAddNew;
    private long lastBackPressTime = 0;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fContext = getApplicationContext();

        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
        if (savedInstanceState == null) {
            initDesign();
        } else {
//            mAccount.setText(savedInstanceState.getString("Account"));
        }
    }


    private void initDesign() {
        mAccount = (TextView) findViewById(R.id.textAccount);
        mAddNew = (ImageButton) findViewById(R.id.btnNewAd);
        mAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, new FragmentNewAd())
                            .addToBackStack(FragmentNewAd.class.getName())
                            .commit();
                    Log.i(TAG, "New Ad pressed, visible in LogCat");
                    ;
                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                }
            }
        });
    }

    @Override
    public void onBackPressed() {

//        if (mNavigationDrawerFragment.isDrawerOpen())
//            mNavigationDrawerFragment.closeDrawer();
//        else {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            if (this.lastBackPressTime < System.currentTimeMillis() - 4000) {
                toast = Toast.makeText(this, "Press back again to close this app", 4000);
                toast.show();
                this.lastBackPressTime = System.currentTimeMillis();
            } else {
                if (toast != null) {
                    toast.cancel();
                }
                super.onBackPressed();
            }
        } else {
            getFragmentManager().popBackStack();
        }
    }

    public void clearBackStack() {
        FragmentManager manager = getFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        String s;
//        Gson gson = new Gson();
//        s = gson.toJson(curDevice);
//        outState.putString("Device", s);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        String s;
//        Gson gson = new Gson();
//        s = savedInstanceState.getString("Device");
//        curDevice = gson.fromJson(s, Device.class);
        super.onRestoreInstanceState(savedInstanceState);

    }
}
