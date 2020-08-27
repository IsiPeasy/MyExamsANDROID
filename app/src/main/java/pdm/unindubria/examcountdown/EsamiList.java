package pdm.unindubria.examcountdown;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class EsamiList extends ArrayAdapter<Esami>{

    private Activity context;
    private List<Esami> exLi;

    public EsamiList(Activity context, List<Esami> exLi){
        super(context,R.layout.activity_main, exLi);
        this.context = context;
        this.exLi = exLi;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem  = inflater.inflate(R.layout.activity_main,null,true);

        TextView textViewEsame = (TextView)listViewItem.findViewById(R.id.editTextTextPersonName);
        TextView textViewCfu = (TextView)listViewItem.findViewById(R.id.editTextTextPersonName2);
        TextView textViewData = (TextView)listViewItem.findViewById(R.id.textDate);

        Esami e = exLi.get(position);

        /*textViewEsame.setText(e.getEsame());
        textViewCfu.setText(e.getCfu());
        textViewData.setText(e.getData());*/

        return listViewItem;
    }
}
