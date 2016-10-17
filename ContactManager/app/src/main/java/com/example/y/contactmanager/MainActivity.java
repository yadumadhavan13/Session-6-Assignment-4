package com.example.y.contactmanager;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
     ListView lv_contactmanager;
     ArrayList <String>al_name;
     ArrayList <String>al_number;
     Myadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("MainActivity","oncreate");
        lv_contactmanager = (ListView) findViewById(R.id.list_view);


        al_name = new ArrayList<>();
        al_name.add("Pushpa");
        al_name.add("Latha");
        al_name.add("Arjun");
        al_name.add("Kiran");
        al_name.add("Arnav");

        al_number = new ArrayList<>();
        al_number.add("9988778877");
        al_number.add("9988778874");
        al_number.add("9988778844");
        al_number.add("7988778877");
        al_number.add("9968778877");

        adapter = new Myadapter();
        lv_contactmanager.setAdapter(adapter);
        this.registerForContextMenu(lv_contactmanager);


    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {


        if (v.getId() == R.id.list_view) {
            this.getMenuInflater().inflate(R.menu.context_menu, menu);
            menu.setHeaderTitle("Select The Action");


        }
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int listposition = info.position;
        int selecteditemid = item.getItemId();
        switch(selecteditemid){
            case R.id.item_call:{
                Toast.makeText(this, "call option selected", Toast.LENGTH_SHORT).show();
                Log.e("MainActivity","call option invoked");
                Intent callintent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" +al_number.get(listposition)));
                startActivity(callintent);
                return true;
            }
            case R.id.item_sms:{
                Toast.makeText(this, "sms option selected", Toast.LENGTH_SHORT).show();
                Log.e("MainActivity","sms option invoked");
                Intent smsintent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" +al_number.get(listposition)));
                startActivity(smsintent);
                return true;
            }
            default:return super.onContextItemSelected(item);

        }

    }


    public class Myadapter extends BaseAdapter{

        @Override
        public int getCount() {
            return al_name.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            viewholder holder;
            if (convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.row_view,parent,false);
                holder = new viewholder();
                holder.bindview(convertView);
                convertView.setTag(holder);
            }
            else{
                holder = (viewholder) convertView.getTag();

            }
            holder.tv_name.setText(al_name.get(position));
            holder.tv_number.setText(al_number.get(position));
            return convertView;
        }
    }

    public class viewholder{
        TextView tv_name;
        TextView tv_number;

        void bindview(View convertView){
            tv_name = (TextView)convertView.findViewById(R.id.tv_name);
            tv_number = (TextView) convertView.findViewById(R.id.tv_number);
        }
    }
}


