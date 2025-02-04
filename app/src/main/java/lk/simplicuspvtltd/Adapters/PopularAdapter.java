package lk.simplicuspvtltd.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;

import java.text.DecimalFormat;
import java.util.ArrayList;

import lk.simplicuspvtltd.Domains.PopularDomain;
import lk.simplicuspvtltd.R;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

    ArrayList<PopularDomain> items;
    DecimalFormat formatter;

    public PopularAdapter(ArrayList<PopularDomain> items) {
        this.items = items;
        formatter=new DecimalFormat("###,###,###,###");
    }

    @NonNull
    @Override
    public PopularAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.ViewHolder holder, int position) {
        holder.titleTxt.setText(items.get(position).getTitle());

        int drawableRessId=holder.itemView.getResources().getIdentifier(items.get(position).getPic(),
                "drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableRessId)
                .transform(new CenterCrop(),new GranularRoundedCorners(40,40,40,40))
                .into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView titleTxt;
        ImageView pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTxt=itemView.findViewById(R.id.titleText);
            pic=itemView.findViewById(R.id.picImg);
        }
    }
}
