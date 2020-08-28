package pdm.unindubria.examcountdown;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    ListView l;
    DatabaseReference r;
    EsamiListAdapter ll;
    TextView ogg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = (FloatingActionButton)findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddExam.class);
                startActivity(intent);
            }
        });


        r = FirebaseDatabase.getInstance().getReference("Esami");
        ll = new EsamiListAdapter(getApplicationContext(), R.layout.custom_view_esami);

        ogg = (TextView) findViewById(R.id.NomeEsameText);

        if(!ll.isEmpty()) {
        ogg.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       Toast.makeText(getApplicationContext(),"Item selected", Toast.LENGTH_LONG).show();
                                   }
                               });


                ogg.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {

                        final int POSITION = 0;
                        new AlertDialog.Builder(MainActivity.this)
                                .setIcon(android.R.drawable.ic_delete)
                                .setTitle("Are you sure?")
                                .setMessage("Do you want to delete this item")
                                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //ll.remove(POSITION);
                                        ll.notifyDataSetChanged();
                                    }
                                })
                                .setNegativeButton("No", null)
                                .show();
                        return true;
                    }
                });
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater infl = getMenuInflater();
        infl.inflate(R.menu.delete_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        item.getItemId();
        Toast.makeText(this,"Delete all", Toast.LENGTH_SHORT).show();
        ll.clear();
        deleteEsami();
        return true;
    }

    private void deleteEsami() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Esami");
        ref.removeValue();
    }

    @Override
    protected void onStart() {
        super.onStart();

        r.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ll.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    Esami e1 = snap.getValue(Esami.class);
                    ll.add(e1);
                }
                l = (ListView)findViewById(R.id.listaEsami);
                l.setAdapter(ll);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}

