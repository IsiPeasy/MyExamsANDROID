package pdm.unindubria.examcountdown;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public final class EsamiListAdapter extends ArrayAdapter<Esami>
{
    private final int newsItemLayoutResource;

    public EsamiListAdapter(final Context context, final int newsItemLayoutResource) {
        super(context, 0);
        this.newsItemLayoutResource = newsItemLayoutResource;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {

        final View view = getWorkingView(convertView);
        final ViewHolder viewHolder = getViewHolder(view);
        final Esami esame = getItem(position);

        viewHolder.nomeEsameText.setText(esame.getEsame());
        viewHolder.dataEsameText.setText("  " + esame.getData());
        viewHolder.cfuEsameText.setText(esame.getCfu() + " CFU  ");
        viewHolder.countdownEsameText.setText(esame.getCountDown());
        return view;
    }

    private View getWorkingView(final View convertView) {

        View workingView = null;

        if(null == convertView) {
            final Context context = getContext();
            final LayoutInflater inflater = (LayoutInflater)context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);

            workingView = inflater.inflate(newsItemLayoutResource, null);
        } else {
            workingView = convertView;
        }

        return workingView;
    }
    private ViewHolder getViewHolder(final View workingView) {

        final Object tag = workingView.getTag();
        ViewHolder viewHolder = null;

        if(null == tag || !(tag instanceof ViewHolder)) {
            viewHolder = new ViewHolder();

            viewHolder.nomeEsameText = (TextView) workingView.findViewById(R.id.NomeEsameText);
            viewHolder.dataEsameText = (TextView) workingView.findViewById(R.id.dataText);
            viewHolder.cfuEsameText = (TextView) workingView.findViewById(R.id.cfuText);
            viewHolder.countdownEsameText = (TextView) workingView.findViewById(R.id.countDownText);
            workingView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) tag;
        }

        return viewHolder;
    }
    private static class ViewHolder {
        //Campi adapter
        public TextView nomeEsameText;
        public TextView dataEsameText;
        public TextView cfuEsameText;
        public TextView countdownEsameText;
    }
}
