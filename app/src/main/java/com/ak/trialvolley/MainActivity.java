package com.ak.trialvolley;

import android.app.DownloadManager;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText name,branch,semester, sapId,contact,email,time;
    Button btn;
    String server_url= "http://";   ///Fill this portion
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn= (Button)findViewById(R.id.btn);
        name= (EditText)findViewById(R.id.name);
        branch= (EditText)findViewById(R.id.branch);
        semester=(EditText)findViewById(R.id.sem);
        sapId= (EditText)findViewById(R.id.sapId);
        contact= (EditText)findViewById(R.id.contact);
        email= (EditText)findViewById(R.id.email);
        time= (EditText)findViewById(R.id.time);
        builder = new AlertDialog.Builder(MainActivity.this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String n,b,se,s,c,e,t;
                n= name.getText().toString();
                b= branch.getText().toString();
                se= semester.getText().toString();
                s= sapId.getText().toString();
                c= contact.getText().toString();
                e= email.getText().toString();
                t= time.getText().toString();

                Request stringRequest = new StringRequest(Request.Method.POST, server_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                builder.setTitle("Server Response");
                                builder.setMessage("Response: "+response);
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        name.setText("");
                                        branch.setText("");
                                        semester.setText("");
                                        sapId.setText("");
                                        contact.setText("");
                                        email.setText("");
                                        time.setText("");
                                    }
                                });
                                AlertDialog alertDialog= builder.create();
                                alertDialog.show();
                            }
                        },

                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, "Error.......", Toast.LENGTH_LONG).show();
                                error.printStackTrace();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params= new HashMap<String, String>();
                        params.put("name","n");
                        params.put("branch","b");
                        params.put("semester","se");
                        params.put("sapId","s");
                        params.put("contact","c");
                        params.put("email","e");
                        params.put("time","t");
                        return params;
                    }
                };
                MySingleton.getInstance(MainActivity.this).addToRequest(stringRequest);
            }
        });
    }
}
