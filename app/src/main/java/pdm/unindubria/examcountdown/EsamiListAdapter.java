package pdm.unindubria.examcountdown;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public final class EsamiListAdapter extends RecyclerView.Adapter<EsamiListAdapter.Holder>
{
    private Context mContext;
    protected static ArrayList<Esami> mEsami;

    public EsamiListAdapter(Context c, ArrayList<Esami> mEsami)
    {
        this.mEsami = mEsami;
        mContext = c;
    }
    @NonNull
    @Override
    public EsamiListAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_view_esami,parent,false);
        return new Holder(inflatedView,mContext);
    }
    @Override
    public void onBindViewHolder(@NonNull EsamiListAdapter.Holder holder, int position) {
        Esami element = mEsami.get(position);
        holder.bindProd(element);
    }
    @Override
    public int getItemCount() {
        return mEsami.size();
    }

    public static class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView txtNome;
        private TextView txtData;
        private TextView txtCfu;
        private TextView txtCountDown;
        private Context mContext;
        public Holder(@NonNull View itemView,Context c)
        {
            super(itemView);
            txtNome = (TextView) itemView.findViewById(R.id.NomeEsameText);
            txtData = (TextView) itemView.findViewById(R.id.dataText);
            txtCfu = (TextView) itemView.findViewById(R.id.cfuText);
            txtCountDown = (TextView) itemView.findViewById(R.id.countDownText);
            itemView.setOnClickListener(this);
            txtNome.setOnLongClickListener(popupAction);
            mContext = c;
        }
        public View.OnLongClickListener popupAction =  new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                PopupMenu popup = new PopupMenu(v.getContext(), v);

                popup.getMenuInflater().inflate(R.menu.esame_menu, popup.getMenu());
                final MenuItem btnElimina = popup.getMenu().findItem(R.id.menuEsami_btnElimina);
                final MenuItem btnCount = popup.getMenu().findItem(R.id.menuEsami_btnMostra);
                btnElimina.setOnMenuItemClickListener(eliminaAction);
                btnCount.setOnMenuItemClickListener(mostraAction);
                popup.show();
                return false;
            }
        };
        public MenuItem.OnMenuItemClickListener eliminaAction = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                final Esami e = mEsami.get(getAdapterPosition());
                new AlertDialog.Builder(mContext)
                        .setTitle("Elimina")
                        .setMessage("Eliminare l'esame "+e.getEsame()+" ?")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                MainActivity.deleteExam(e);
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;
            }
        };
        public MenuItem.OnMenuItemClickListener mostraAction = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                item.getItemId();
                Intent i = new Intent(mContext,Countdown.class);
                Bundle mBundle = new Bundle();
                mBundle.putString("testo", txtData.getText().toString());
                i.putExtras(mBundle);
                mContext.startActivity(i);
                return true;
            }


        };

        @Override
        public void onClick(View v)
        {

        }

        public void bindProd(Esami element)
        {
                txtNome.setText(element.getEsame());
                txtData.setText(element.getData());
                txtCfu.setText(element.getCfu());
                txtCountDown.setText(element.getCountDown());
        }
    }
}
