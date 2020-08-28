package pdm.unindubria.examcountdown;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class AddExam extends AppCompatActivity {

    private static final String TAG = "AddExam";
    private TextView mDisplayDate;
    private EditText mExam, mCfu;
    private Button mAddEx;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private DatabaseReference reff;
    private Esami esami;
    private long maxId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exam);

        mDisplayDate = (TextView) findViewById(R.id.textDate);
        mExam = (EditText)findViewById(R.id.editTextTextPersonName);
        mCfu = (EditText)findViewById(R.id.editTextTextPersonName2);
        mAddEx = (Button)findViewById(R.id.addButton);

        esami = new Esami();
        reff = FirebaseDatabase.getInstance().getReference().child("Esami");

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                    maxId=(snapshot.getChildrenCount());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mAddEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TEs = mExam.getText().toString().trim();
                String TCfu = mCfu.getText().toString().trim();
                String TDate = mDisplayDate.getText().toString().trim();

                esami.setEsame(TEs);
                esami.setCfu(TCfu);
                esami.setData(TDate);

                reff.child(String.valueOf(maxId + 1)).setValue(esami);

                Toast.makeText(AddExam.this, "Exam added", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddExam.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                String date = day + "/" + month + "/" + year;
                mDisplayDate.setText(date);
            }
        };
    }
}
