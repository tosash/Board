package com.kido.board.adapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kido.board.R;
import com.kido.board.model.Ad;
import com.kido.board.network.VolleySingleton;
import com.kido.board.ui.FragmentNewAd;
import com.kido.board.util.CircularNetworkImageView;

import java.util.List;

import at.markushi.ui.CircleButton;

public class AdsActiveAdapter extends RecyclerView.Adapter<AdsActiveAdapter.AdsActiveViewHolder> {
    public long currID;
    private List<Ad> mItems;
    private FragmentManager fm;

    public AdsActiveAdapter(FragmentManager f, List<Ad> ads) {
        fm = f;
        mItems = ads;
    }

    @Override
    public AdsActiveViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.ads_active_row, viewGroup, false);
        return new AdsActiveViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdsActiveViewHolder adsActiveViewHolder, int i) {
        Ad ad = mItems.get(i);
        adsActiveViewHolder.txtName.setText(ad.getAdName());
        adsActiveViewHolder.txtDesc.setText(ad.getAdDesc());
        adsActiveViewHolder.txtDate.setText(ad.getAdDate().toString());
//        adsActiveViewHolder.imgThumbnail.setCircled(false);
        adsActiveViewHolder.imgThumbnail.setImageUrl(ad.getAdUrl(), VolleySingleton.getInstance(null).getImageLoader());
    }

    @Override
    public int getItemCount() {
        if (mItems == null) {
            return 0;
        } else {
            return mItems.size();
        }
    }

    public Ad getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mItems.get(position).getAdNumer();
    }

    class AdsActiveViewHolder extends RecyclerView.ViewHolder {

        protected CircularNetworkImageView imgThumbnail;
        protected TextView txtName;
        protected TextView txtDesc;
        protected TextView txtDate;
        protected TextView txtPrice;
        protected CircleButton btnOff;
        protected CircleButton btnEdit;
        protected CircleButton btnDelete;

        public AdsActiveViewHolder(final View itemView) {
            super(itemView);
            imgThumbnail = (CircularNetworkImageView) itemView.findViewById(R.id.img_thumbnail);
            txtName = (TextView) itemView.findViewById(R.id.txt_name);
            txtDesc = (TextView) itemView.findViewById(R.id.txt_desc);
            txtDate = (TextView) itemView.findViewById(R.id.txt_date);
            txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);
            btnOff = (CircleButton) itemView.findViewById(R.id.btnOff);
            btnEdit = (CircleButton) itemView.findViewById(R.id.btnEdit);
            btnDelete = (CircleButton) itemView.findViewById(R.id.btnDelete);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {

                        Bundle bundle = new Bundle();
                        int pos = getLayoutPosition();
                        bundle.putLong("AdId", getItem(pos).getAdNumer());
                        Fragment fragAd = new FragmentNewAd();
                        fragAd.setArguments(bundle);
                        fm.beginTransaction()
//                                .add(R.id.container, fragAd)
                                .replace(R.id.container, fragAd)
                                .addToBackStack(FragmentNewAd.class.toString())
                                .commit();
                        ;
                    } catch (Exception e) {
                        Log.e("Ad edit", e.toString());
                    }
                }
            });
        }
    }

}
