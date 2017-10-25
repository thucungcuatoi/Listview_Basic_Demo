package com.example.thucu.demo_listview_basic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView _listviewMain;
    ArrayList<String> _arraylistMain;
    EditText _textInput1;
    Button _btnAdd, _btnEdit, _btnDelete;
    int _position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _listviewMain  = (ListView) findViewById(R.id.listviewMain);
        _textInput1 = (EditText) findViewById(R.id.txtText1);
        _btnAdd = (Button) findViewById(R.id.btnAdd);
        _btnEdit = (Button) findViewById(R.id.btnEdit);
        _btnDelete = (Button) findViewById(R.id.btnDelete);

        _arraylistMain = new ArrayList<>();
        _arraylistMain.add("Item 1");
        _arraylistMain.add("Item 2");
        _arraylistMain.add("Item 3");
        _arraylistMain.add("Item 4");
        _arraylistMain.add("Item 5");
//        _arraylistMain.add("Item 6");
//        _arraylistMain.add("Item 7");
//        _arraylistMain.add("Item 8");

        final ArrayAdapter _adapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1, _arraylistMain);
        _listviewMain.setAdapter(_adapter);

        _listviewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // i là vị trí click của item trên list
//                Toast.makeText(MainActivity.this, _arraylistMain.get(i), Toast.LENGTH_SHORT).show();
                _textInput1.setText(_arraylistMain.get(i));
                _position = i;



            }
        });

        _btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textInputAdd = _textInput1.getText().toString().trim();
//                Toast.makeText(MainActivity.this, "Text: ["+textInputAdd+"]" , Toast.LENGTH_SHORT).show();

                if(!textInputAdd.equals("")){
                    if (_arraylistMain.indexOf(textInputAdd) > -1){
                        Toast.makeText(MainActivity.this, "Data is existing in list", Toast.LENGTH_SHORT).show();
                    }else {
                        _arraylistMain.add(textInputAdd);
                        _adapter.notifyDataSetChanged();
                        _textInput1.setText("");
                        Toast.makeText(MainActivity.this, "Add new completed...", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please enter your text", Toast.LENGTH_SHORT).show();
                }
            }
        });

        _btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textInputEdit = _textInput1.getText().toString().trim();

                if (!textInputEdit.equals("")){
                    _arraylistMain.set(_position,textInputEdit);
                    _adapter.notifyDataSetChanged();
                    _textInput1.setText("");
                    Toast.makeText(MainActivity.this, "Update completed...", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Please enter your text", Toast.LENGTH_SHORT).show();
                }


            }
        });

        _btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _arraylistMain.remove(_position);
                _adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Delete completed...", Toast.LENGTH_SHORT).show();
            }
        });

        _listviewMain.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                _arraylistMain.remove(_position);
                _adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Delete completed...", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
