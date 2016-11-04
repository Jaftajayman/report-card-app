        package com.example.ekasilab.reportcard;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

        public class MainActivity extends AppCompatActivity {
        Button  ButtonAdmin;
            EditText editTextUsername,editTextPassword;

           // databaseHelper helper = new databaseHelper(this);
            @Override


            protected void onCreate(Bundle savedInstanceState) {


                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                //casting variables
                editTextUsername = (EditText) findViewById(R.id.etUsername);
                editTextPassword = (EditText) findViewById(R.id.etPassword);
                //buttonLogin = (Button) findViewById(R.id.bLogin);


                // creating a register link to open register page
                final TextView registerLink = (TextView) findViewById(R.id.tvRegister);
                registerLink.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //creating intent that will open register activity
                        Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                        //to make intent to actually work
                        MainActivity.this.startActivity(registerIntent);
                    }
                });

                final TextView loginLink = (TextView) findViewById(R.id.EditDetails);
                loginLink.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //creating intent that will open register activity
                        Intent registerIntent = new Intent(MainActivity.this,adminActivity.class);
                        //to make intent to actually work
                        MainActivity.this.startActivity(registerIntent);
                    }
                });
            }



          //  public void StartAdmin(View view){
         //     Intent objIntent=new Intent (MainActivity.this,adminActivity.class);
        //       startActivity(objIntent);


           // }

        }
