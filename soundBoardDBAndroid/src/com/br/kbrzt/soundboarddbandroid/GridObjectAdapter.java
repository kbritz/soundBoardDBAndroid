package com.br.kbrzt.soundboarddbandroid;

import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.br.kbrzt.soundboarddbandroid.enums.CaractersEnum;

public class GridObjectAdapter extends BaseAdapter {
    
    List<CaractersEnum> caracters = Arrays.asList(CaractersEnum.values());
    Context context;
    
    public GridObjectAdapter (final Context context){
	this.context = context;
	
    }

    @Override
    public int getCount() {
	return caracters.size();
    }

    @Override
    public CaractersEnum getItem(int position) {
	return caracters.get(position);
    }

    @Override
    public long getItemId(int position) {
	return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
	ViewHolder holder = null;
	View convertView = view;

	if (convertView == null) {
	    holder = new ViewHolder();

	    final LayoutInflater inflater = (LayoutInflater) context
		    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	    convertView = inflater.inflate(R.layout.grid_item, null);

	    holder.icon = (ImageView) convertView.findViewById(R.id.imb_grid);

	    convertView.setTag(holder);

	} else {
	    holder = (ViewHolder) convertView.getTag();
	}

	holder.icon.setImageResource(caracters.get(position)
		.getImage());

	return convertView;
    }
    
    static class ViewHolder {
	public ImageView icon;
    }


}
