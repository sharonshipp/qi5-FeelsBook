package cmput301.qi5_feelsbook;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.Calendar;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // get emotion from intent
        final Emotion emotion = DataHolder.getInstance(this).get(getIntent().getIntExtra("position",0));

        // get views
        final Spinner edit_catalog = findViewById(R.id.edit_catalog);
        final EditText edit_date = findViewById(R.id.edit_date);
        final EditText edit_comment =  findViewById(R.id.edit_comment);

        // set values to views
        edit_catalog.setSelection(emotion.getEmotion());
        edit_date.setText(emotion.getISOTimestamp());
        edit_comment.setText(emotion.getComment());

        // set up datetime picker to modify timestamp
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(emotion.getTimestamp());
        edit_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(EditActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(year, monthOfYear, dayOfMonth);
                        new TimePickerDialog(EditActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                calendar.set(Calendar.MINUTE, minute);
                                edit_date.setText(Emotion.ISO_FORMAT.format(calendar.getTime()));
                            }
                        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)).show();
            }
        });

        // set onclick event for save button, save data modified to emotion
        findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emotion.setTimestamp(calendar.getTime());
                emotion.setEmotion(edit_catalog.getSelectedItemPosition());
                emotion.setComment(edit_comment.getText().toString());
                DataHolder.saveInFile(EditActivity.this);
                finish();
            }
        });
    }
}
