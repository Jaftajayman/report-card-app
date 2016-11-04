package com.example.ekasilab.reportcard;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class adminActivity extends AppCompatActivity {
    databaseHelper MyDb;
    EditText editName, editSurname, editMarks,editTextId, editTextUsername, editTextPassword;
    Button buttonRegister, ButtonViewAll;
    Button buttonUpdate, buttonDelete;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        //calling the constructor class
        MyDb = new databaseHelper(this);

        // casting our variables
        editTextId=(EditText)findViewById(R.id.etId);
        editName = (EditText) findViewById(R.id.etName);
        editSurname = (EditText) findViewById(R.id.etSurname);
        editMarks = (EditText) findViewById(R.id.etMarks);
        editTextUsername =(EditText)findViewById(R.id.etUsername);
        editTextPassword=(EditText)findViewById(R.id.etPassword);
        buttonRegister = (Button) findViewById(R.id.bRegister);
        ButtonViewAll=(Button)findViewById(R.id.bView);
        buttonUpdate=(Button)findViewById(R.id.bUdate) ;
        buttonDelete=(Button)findViewById(R.id.bDelete);
        //variables declaration
        //  final EditText etMarks = (EditText) findViewById(R.id.etMarks);
        // final EditText etName= (EditText) findViewById(R.id.etName);
        // final EditText etSurName = (EditText) findViewById(R.id.etSurname);

        // final Button bRegister =(Button) findViewById(R.id.bRegister);

//        viewAll();
//        update();
//        dataDelete();
    }

    //function for updating data

    public  void update( View v){
        buttonUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdated =MyDb.updateData(editTextId.getText().toString(),
                                editName.getText().toString(),editSurname.getText().toString(), editMarks.getText().toString(),editTextUsername.getText().toString(),editTextPassword.getText().toString());
                        //checking if the data is updated

                        if (isUpdated == true)
                            Toast.makeText(adminActivity.this, "Successfully updated!!!", Toast.LENGTH_SHORT).show();

                        else
                            Toast.makeText(adminActivity.this, "Not updated!!!", Toast.LENGTH_SHORT).show();



                    }
                }

        );

    }
    //viewing all data
    public  void viewAll(View v){
        ButtonViewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res=MyDb.getAlldata();
                        if (res.getCount()==0){

                            //show message
                            showMessage("Sorry!!!", "Nothing found");
                            return;

                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0) + "\n" );
                            buffer.append("Name :"+ res.getString(1) + "\n" );
                            buffer.append("Surname :"+ res.getString(2) + "\n" );
                            buffer.append("Marks :"+ res.getString(3) + "\n\n------------------------\n" );
                            buffer.append("Course :"+ res.getString(4) + "\n" );
                            buffer.append("Year :"+ res.getString(5) + "\n\n" );

                        }
                        //show all data
                        showMessage("Data ", buffer.toString());
                    }
                }
        );
    }

    //delete function

    public void  dataDelete(View v){
        buttonDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows =MyDb.deleteData(editTextId.getText().toString());

                        //checking number of rows that are affected
                        if (deletedRows != 0)
                            Toast.makeText(adminActivity.this, "You data is deleted!!!", Toast.LENGTH_SHORT).show();

                        else
                            Toast.makeText(adminActivity.this, "Not DELETED", Toast.LENGTH_SHORT).show();



                    }
                }

        );
    }

    public  void  showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }
}
