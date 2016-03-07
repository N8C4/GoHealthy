package se.bolzyk.gohealthy;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingActivity extends ActionBarActivity {

    private EditText goal_walking, goal_running, goal_weight_training, goal_group_workout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        goal_walking =(EditText) findViewById(R.id.goal_walking);
        goal_running =(EditText) findViewById(R.id.goal_running);
        goal_weight_training =(EditText) findViewById(R.id.goal_weight_training);
        goal_group_workout=(EditText) findViewById(R.id.goal_group_workout);

        final Button button1 = (Button) findViewById(R.id.button_save_setting);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                saveData();

                CharSequence text = "Settings are saved!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(getBaseContext(), text, duration);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });

        final Button button2 = (Button) findViewById(R.id.button_delete_all);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                deleteAllData();

                CharSequence text = "All data is deleted!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(getBaseContext(), text, duration);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            }
        });
    }


    private void saveData() {

        SharedPreferences ss = getSharedPreferences("dbSetting", MODE_PRIVATE);
        SharedPreferences.Editor editor = ss.edit();

        editor.putInt("goal_walking", Integer.parseInt(goal_walking.getText().toString()));
        editor.putInt("goal_running", Integer.parseInt(goal_running.getText().toString()));
        editor.putInt("goal_weight_training", Integer.parseInt(goal_weight_training.getText().toString()));
        editor.putInt("goal_group_workout", Integer.parseInt(goal_group_workout.getText().toString()));

        editor.commit();
    }


    private void deleteAllData() {

        SharedPreferences sp = getSharedPreferences("dbActivity", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();

        SharedPreferences sd = getSharedPreferences("dbSort", MODE_PRIVATE);
        SharedPreferences.Editor editorsd = sd.edit();
        editorsd.clear();
        editorsd.commit();
    }
}
