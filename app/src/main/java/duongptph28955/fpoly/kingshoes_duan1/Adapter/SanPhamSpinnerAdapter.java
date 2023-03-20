package duongptph28955.fpoly.kingshoes_duan1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import duongptph28955.fpoly.kingshoes_duan1.R;
import duongptph28955.fpoly.kingshoes_duan1.dto.SanPham;

public class SanPhamSpinnerAdapter extends ArrayAdapter<SanPham> {
    Context context;
    ArrayList<SanPham> list;

    public SanPhamSpinnerAdapter(@NonNull Context context, ArrayList<SanPham> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v= inflater.inflate(R.layout.item_giayspinner,null);
        }
        final SanPham item = list.get(position);
        if(item != null){
            TextView tvitem = v.findViewById(R.id.itemGiay);
            tvitem.setText(item.getTenSP());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v= inflater.inflate(R.layout.item_giayspinner,null);
        }
        final SanPham item = list.get(position);
        if(item != null){
            TextView tvitem = v.findViewById(R.id.itemGiay);
            tvitem.setText(item.getTenSP());
        }
        return v;
    }
}
