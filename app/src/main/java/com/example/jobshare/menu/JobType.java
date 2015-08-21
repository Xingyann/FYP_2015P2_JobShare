package com.example.jobshare.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class JobType extends Activity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);

        // Defined Array values to show in ListView
        String[] values = new String[]{
                "Information Security Analyst",
                "Software Engineer",
                "Programmer",
                "System Support Manager",
                "System Analyst",
                "Network Administrator",
                "IT Support (Software Licensing Compliance)",
                "Oracle Forms and Reports Application Analyst",
                "Database Administrator",
                "Java Developer",
                "Backup & Systems Engineer",
                "IT Security Engineer"
        };

       ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        // Assign adapter to ListView
        listView.setAdapter(adapter);

        //ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);
                Intent intent = null;

                if (itemValue.equals("Information Security Analyst")) {
                    intent = new Intent(JobType.this, ShareLineActivity.class);
                    startActivity(intent);
                }

                else if (itemValue.equals("Software Engineer")){
                    Toast.makeText(getApplicationContext(), "No Data found!", Toast.LENGTH_LONG).show();
                }

                else if (itemValue.equals("Programmer")){
                    Toast.makeText(getApplicationContext(), "No Data found!", Toast.LENGTH_LONG).show();
                }


                else if (itemValue.equals("System Support Manager")){
                    Toast.makeText(getApplicationContext(), "No Data found!", Toast.LENGTH_LONG).show();
                }

                else if (itemValue.equals("System Analyst")){
                    Toast.makeText(getApplicationContext(), "No Data found!", Toast.LENGTH_LONG).show();
                }

               else if (itemValue.equals("Network Administrator")){
                    Toast.makeText(getApplicationContext(), "No Data found!", Toast.LENGTH_LONG).show();
                }

                else if (itemValue.equals("IT Support (Software Licensing Compliance)")){
                    Toast.makeText(getApplicationContext(), "No Data found!", Toast.LENGTH_LONG).show();
                }

                else if (itemValue.equals("Oracle Forms and Reports Application Analyst")){
                    Toast.makeText(getApplicationContext(), "No Data found!", Toast.LENGTH_LONG).show();
                }

                else if (itemValue.equals("Database Administrator")){
                    Toast.makeText(getApplicationContext(), "No Data found!", Toast.LENGTH_LONG).show();
                }

                else if (itemValue.equals("Java Developer")){
                    Toast.makeText(getApplicationContext(), "No Data found!", Toast.LENGTH_LONG).show();
                }

                else if (itemValue.equals("Backup & Systems Engineer")){
                    Toast.makeText(getApplicationContext(), "No Data found!", Toast.LENGTH_LONG).show();
                }

                else if (itemValue.equals("IT Security Engineer")){
                    Toast.makeText(getApplicationContext(), "No Data found!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void NewPost(View view) {
        Intent intent = new Intent(this, NewPost.class);
        startActivity(intent);
    }
}

