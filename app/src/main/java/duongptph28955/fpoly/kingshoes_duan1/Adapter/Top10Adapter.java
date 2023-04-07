package duongptph28955.fpoly.kingshoes_duan1.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import duongptph28955.fpoly.kingshoes_duan1.R;
import duongptph28955.fpoly.kingshoes_duan1.dto.SanPham;


public class Top10Adapter extends RecyclerView.Adapter<Top10Adapter.ViewHoler>{
    private Context context;
    private ArrayList<SanPham> list;

    public Top10Adapter(Context context, ArrayList<SanPham> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_top10, parent, false);

        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        holder.txtTenSanPham.setText(list.get(position).getTenSP());
        holder.txtSoLuong.setText(String.valueOf(list.get(position).getSoluongdaban()));

        SanPham sanPham = list.get(position);
        byte[] hinhAnh = sanPham.getHinhAnh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0 , hinhAnh.length);
        holder.imgHinhTop10.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        TextView txtTenSanPham, txtSoLuong;
        ImageView imgHinhTop10;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            txtTenSanPham = itemView.findViewById(R.id.txtTenSanPham);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuong);
            imgHinhTop10 = itemView.findViewById(R.id.hinhTop10);
        }
    }
}
