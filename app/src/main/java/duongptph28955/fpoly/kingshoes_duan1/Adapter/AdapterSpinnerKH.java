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
import duongptph28955.fpoly.kingshoes_duan1.dto.KhachHang;
import duongptph28955.fpoly.kingshoes_duan1.dto.MauSac;

public class AdapterSpinnerKH extends ArrayAdapter<KhachHang> {
    Context context;
    ArrayList<KhachHang> list;

    public AdapterSpinnerKH(@NonNull Context context, ArrayList<KhachHang> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v= inflater.inflate(R.layout.item_spnkhachhang,null);
        }
        final KhachHang item = list.get(position);
        if(item != null){
            TextView tvitem = v.findViewById(R.id.tv_itemkh);
            tvitem.setText(item.getTenKH());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v= inflater.inflate(R.layout.item_spnkhachhang,null);
        }
        final KhachHang item = list.get(position);
        if(item != null){
            TextView tvitem = v.findViewById(R.id.tv_itemkh);
            tvitem.setText(item.getTenKH());
        }
        return v;
    }
}
