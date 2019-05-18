package com.maheshgupta.analyticssdkdemo;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.maheshgupta.analyticssdk.AnalyticsMp;
import com.maheshgupta.analyticssdk.Events;
import com.maheshgupta.analyticssdk.Identify;
import com.maheshgupta.analyticssdk.dao.OtherEvent;
import com.maheshgupta.analyticssdkdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        context = this;
        binding.btnStringDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = binding.edtStringKey.getText().toString().trim();
                String value = binding.edtStringValue.getText().toString().trim();

                if (key.equals(""))
                    Toast.makeText(context, getString(R.string.string_key_error), Toast.LENGTH_LONG).show();
                else if (value.equals(""))
                    Toast.makeText(context, getString(R.string.string_value_error), Toast.LENGTH_LONG).show();
                else {
                    Identify identify = new Identify().set(key, value).set("age", 12).set("age2", 142.32);
                    AnalyticsMp.getInstance().identify(identify);

                    binding.edtStringKey.setText("");
                    binding.edtStringValue.setText("");

                    Toast.makeText(context, getString(R.string.values_inserted), Toast.LENGTH_LONG).show();

                }
            }
        });

        binding.btnIntDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = binding.edtNumberKey.getText().toString().trim();
                String value = binding.edtNumberValue.getText().toString().trim();

                if (key.equals(""))
                    Toast.makeText(context, getString(R.string.number_key_error), Toast.LENGTH_LONG).show();
                else if (value.equals(""))
                    Toast.makeText(context, getString(R.string.number_value_error), Toast.LENGTH_LONG).show();
                else {
                    Events events;
                    if (value.contains("."))
                        events = new Events(key, Float.parseFloat(value));
                    else events = new Events(key, Integer.parseInt(value));

                    AnalyticsMp.getInstance().saveEvents(events);

                    binding.edtNumberKey.setText("");
                    binding.edtNumberValue.setText("");

                    Toast.makeText(context, getString(R.string.values_inserted), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
