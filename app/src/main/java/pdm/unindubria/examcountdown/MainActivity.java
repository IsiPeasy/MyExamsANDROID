package pdm.unindubria.examcountdown;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    LinearLayoutManager mListLayoutManager;
    static RecyclerView.Adapter<EsamiListAdapter.Holder> mAdapter;
    FloatingActionButton floatingActionButton;
    RecyclerView l;
    static ArrayList<Esami> listaEsami = new ArrayList<>();
    DatabaseReference r;

    public static void deleteExam(Esami e)
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Esami");
        ref.child(e.getKey()).removeValue();
        listaEsami.remove(e);
        mAdapter.notifyDataSetChanged();
    }

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
        mListLayoutManager = new LinearLayoutManager(this);

        l = (RecyclerView) findViewById(R.id.listaEsami);
        l.setLayoutManager(mListLayoutManager);
        setmAdapter();
    }
    private void setmAdapter()
    {
        mAdapter = new EsamiListAdapter(this,listaEsami);
        l.setAdapter(mAdapter);
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
        listaEsami.clear();
        deleteEsami();
        return true;
    }

    private void deleteEsami()
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Esami");
        ref.removeValue();
    }

    @Override
    protected void onStart() {
        super.onStart();

        r.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {

                listaEsami.clear();
                for (DataSnapshot snap : snapshot.getChildren())
                {
                    Esami e1 = snap.getValue(Esami.class);
                    e1.setKey(snap.getKey());
                    listaEsami.add(e1);
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

