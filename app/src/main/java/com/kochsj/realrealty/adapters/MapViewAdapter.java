package com.kochsj.realrealty.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.kochsj.realrealty.R;

public class MapViewAdapter extends PagerAdapter {
    private Context context;

    public MapViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) layoutInflater.inflate(R.layout.activity_maps, container, false);

//        TextView userFirstName = (TextView) layout.findViewById(R.id.first_name);
//        TextView userLastName = (TextView) layout.findViewById(R.id.last_name);

//        userFirstName.setText(user.getFirstName());
//        userLastName.setText(user.getLastName());

        //need to add to ViewGroup to render
        container.addView(layout);
        return layout;
    }

    @Override
    public int getCount(){
        return 1;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
