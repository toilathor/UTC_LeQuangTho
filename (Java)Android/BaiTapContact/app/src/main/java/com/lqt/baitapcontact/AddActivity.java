package com.lqt.baitapcontact;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    private Database database;
    private static final String NAME_DATABASE = "Contact.sqlite";
    private static final String NAME_TABLE = "Contact";
    private static final String COLUMN_NAME_1 = "id";
    private static final String COLUMN_NAME_2 = "name";
    private static final String COLUMN_NAME_3 = "phonenumber";
    private static final String COLUMN_NAME_4 = "status";

    private EditText editTextId, editTextName, editTextNumber;
    private Button buttonAdd, buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initView();
        
        SetListener();
    }

    private void SetListener() {
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkEmpty()) {
                    int id = Integer.parseInt(editTextId.getText().toString());
                    String name = editTextName.getText().toString();
                    String phonenumber = editTextNumber.getText().toString();
                    if (checkId(id)) {
                        InsertData(id, name, phonenumber, false);
                        finish();
                    } else {
                        editTextId.setText("");
                        Toast.makeText(AddActivity.this, "Da co Id nay roi!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddActivity.this, "Chua diuen du", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean checkEmpty() {
        String id = editTextId.getText().toString();
        String name = editTextName.getText().toString();
        String phonenumber = editTextNumber.getText().toString();
        if (id.isEmpty() || name.isEmpty() || phonenumber.isEmpty()){
            return false;
        }
        return true;
    }

    private void InsertData(int id, String name, String phonenumber, boolean status) {
        database = new Database(this, NAME_DATABASE, null, 1);
        database.Query(String.format("INSERT INTO %s VALUES ('%d','%s', '%s', '%d')"
                , NAME_TABLE
                , id
                , name
                , phonenumber
                , status ? 1 : 0));
    }


    private boolean checkId(int id) {
        database = new Database(this, NAME_DATABASE, null, 1);
        Cursor cursor = database.GetData(String.format("select * from %s where %s = %d", NAME_TABLE, COLUMN_NAME_1, id));
        return !cursor.moveToNext();
    }

    private void initView() {
        editTextId = (EditText) findViewById(R.id.editText_ID);
        editTextName = (EditText) findViewById(R.id.editText_Name);
        editTextNumber = (EditText) findViewById(R.id.editText_PhoneNumber);
        buttonAdd = (Button) findViewById(R.id.button_Add);
        buttonBack = (Button) findViewById(R.id.button_Back);
    }
}