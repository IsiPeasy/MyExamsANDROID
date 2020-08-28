package pdm.unindubria.examcountdown;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

    public class Countdown extends AppCompatActivity {

        private TextView txtTimerDay, txtTimerHour, txtTimerMinute, txtTimerSecond, textView1;
        private TextView tvEvent;
        private Handler handler;
        private Runnable runnable;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_countdown);

            txtTimerDay = (TextView) findViewById(R.id.txtTimerDay);
            txtTimerHour = (TextView) findViewById(R.id.txtTimerHour);
            txtTimerMinute = (TextView) findViewById(R.id.txtTimerMinute);
            txtTimerSecond = (TextView) findViewById(R.id.txtTimerSecond);
            textView1 = (TextView) findViewById(R.id.textView1);

            countDownStart();
        }

        public void countDownStart() {
            handler = new Handler();
            runnable = new Runnable() {
                @Override
                public void run() {
                    handler.postDelayed(this, 1000);
                    try {
                        String d = getIntent().getExtras().getString("testo");
                        SimpleDateFormat dateFormat = new SimpleDateFormat(
                                "dd/MM/yyyy");
                        // Please here set your event date//YYYY-MM-DD
                        Date futureDate = dateFormat.parse(d);
                        Date currentDate = new Date();
                        if (!currentDate.after(futureDate)) {
                            long diff = futureDate.getTime()
                                    - currentDate.getTime();
                            long days = diff / (24 * 60 * 60 * 1000);
                            diff -= days * (24 * 60 * 60 * 1000);
                            long hours = diff / (60 * 60 * 1000);
                            diff -= hours * (60 * 60 * 1000);
                            long minutes = diff / (60 * 1000);
                            diff -= minutes * (60 * 1000);
                            long seconds = diff / 1000;
                            txtTimerDay.setText("" + String.format("%02d", days));
                            txtTimerHour.setText("" + String.format("%02d", hours));
                            txtTimerMinute.setText(""
                                    + String.format("%02d", minutes));
                            txtTimerSecond.setText(""
                                    + String.format("%02d", seconds));
                        } else {
                            textViewGone();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            handler.postDelayed(runnable, 1 * 1000);
        }
        public void textViewGone() {
            findViewById(R.id.LinearLayout10).setVisibility(View.GONE);
            findViewById(R.id.LinearLayout11).setVisibility(View.GONE);
            findViewById(R.id.LinearLayout12).setVisibility(View.GONE);
            findViewById(R.id.LinearLayout13).setVisibility(View.GONE);
            textView1.setText("Exam already passed!");
        }
    }