package com.example.miniwordbookprovidertest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button bt,add,remove,change;
    EditText eng,chi,exm;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eng=findViewById(R.id.eng);
        chi=findViewById(R.id.chi);
        exm=findViewById(R.id.exm);
        bt=findViewById(R.id.bt);
        tv=findViewById(R.id.show);
        add=findViewById(R.id.add);
        change=findViewById(R.id.change);
        remove=findViewById(R.id.remove);


        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri=Uri.parse("content://com.example.miniwordbook.provider/word");
                ContentValues values=new ContentValues();
                values.put("eng",eng.getText()+"");
                values.put("chi",chi.getText()+"");
                values.put("exm",exm.getText()+"");
                getContentResolver().update(uri,values,"id=? and book=?",new String[]{"999","999"});
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri=Uri.parse("content://com.example.miniwordbook.provider/word");
                ContentValues values=new ContentValues();
                values.put("id",999);
                values.put("book",999);
                values.put("eng",eng.getText()+"");
                values.put("chi",chi.getText()+"");
                values.put("exm",exm.getText()+"");
                getContentResolver().insert(uri,values);
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri=Uri.parse("content://com.example.miniwordbook.provider/word");
                getContentResolver().delete(uri,"id=? and book=?",new String[]{"999","999"});
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp="";
                Uri uri=Uri.parse("content://com.example.miniwordbook.provider/word");
                Cursor cursor=getContentResolver().query(uri,null,null,null,null);
                if(cursor!=null){
                    cursor.moveToFirst();
                    do{
                        int wid=cursor.getInt(cursor.getColumnIndex("id"));
                        int bid=cursor.getInt(cursor.getColumnIndex("book"));
                        String eng=cursor.getString(cursor.getColumnIndex("eng"));
                        String chi=cursor.getString(cursor.getColumnIndex("chi"));
                        String exm=cursor.getString(cursor.getColumnIndex("exm"));
                        temp+="第"+(bid+1)+"号单词本第"+(wid+1)+"个单词：\n";
                        temp+="英文:"+eng+"\n";
                        temp+="中文:"+chi+"\n";
                        temp+="例句:"+exm+"\n";
                        temp+="--------------------------------------------\n";
                    }while (cursor.moveToNext());
                }
                tv.setText(temp);
            }
        });
    }
}
