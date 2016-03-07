package se.bolzyk.gohealthy.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import se.bolzyk.gohealthy.R;

/**
 * Created by Andreas Bolzyk on 2016-02-26.
 */
public class ActivityFragment extends Fragment implements View.OnClickListener {

    private Spinner spinner;
    private Button btnDatePicker;
    private EditText txtDate;
    private int mYear, mMonth, mDay;
    private EditText dateText, timeText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_activity, container, false);

        //
        spinner = (Spinner) rootView.findViewById(R.id.activity_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.spinner_activity_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        //
        dateText = (EditText) rootView.findViewById(R.id.in_date);
        timeText = (EditText) rootView.findViewById(R.id.time_of_activity);

        btnDatePicker = (Button) rootView.findViewById(R.id.btn_date);
        txtDate = (EditText) rootView.findViewById(R.id.in_date);
        btnDatePicker.setOnClickListener(this);

        //
        final Button button1 = (Button) rootView.findViewById(R.id.button_save_activity);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveData();

                CharSequence text = "Activity is saved!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(getContext(), text, duration);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            }
        });
        return rootView;
    }


    private void saveData() {

        // Add all to a string
        SharedPreferences ss = getContext().getSharedPreferences("dbActivity", Context.MODE_PRIVATE);
        Set<String> hs = ss.getStringSet("set", new HashSet<String>());
        Set<String> in = new HashSet<String>(hs);

        in.add(dateText.getText().toString() +","+ timeText.getText().toString() +","+ spinner.getSelectedItem().toString());

        ss.edit().putStringSet("set", in).commit();


        // Sort and add to
        SharedPreferences sd = getContext().getSharedPreferences("dbSort", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sd.edit();

        // get int and find where to add
        int calc = 0;

        switch (spinner.getSelectedItem().toString()) {
            case "Walking":

                calc = sd.getInt("walkingCount", Context.MODE_PRIVATE);
                calc = calc + Integer.parseInt(timeText.getText().toString());
                editor.putInt("walkingCount", calc);
                break;

            case "Running":

                calc = sd.getInt("runningCount",Context.MODE_PRIVATE);
                calc = calc + Integer.parseInt(timeText.getText().toString());
                editor.putInt("runningCount", calc);
                break;

            case "Weight Training":

                calc = sd.getInt("weightCount",Context.MODE_PRIVATE);
                calc = calc + Integer.parseInt(timeText.getText().toString());
                editor.putInt("weightCount", calc);
                break;

            case "Group Workout":

                calc = sd.getInt("groupCount",Context.MODE_PRIVATE);
                calc = calc + Integer.parseInt(timeText.getText().toString());
                editor.putInt("groupCount", calc);
                break;

            default:
                break;
        }
        editor.commit();
    }


    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }
}
