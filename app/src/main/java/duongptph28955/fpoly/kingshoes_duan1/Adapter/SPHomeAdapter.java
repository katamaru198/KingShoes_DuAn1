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

import duongptph28955.fpoly.kingshoes_duan1.DAO.SanPhamDAO;
import duongptph28955.fpoly.kingshoes_duan1.R;
import duongptph28955.fpoly.kingshoes_duan1.dto.SanPham;

public class SPHomeAdapter  extends RecyclerView.Adapter<SPHomeAdapter.ViewHolder>{
    private Context context;
    ArrayList<SanPham> list;

    public SPHomeAdapter(Context context, ArrayList<SanPham> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_sphome, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTenSP.setText(list.get(position).getTenSP());

        // chuyển mảng byte[] sang bitmap
        SanPham sanPham = list.get(position);
        byte[] hinhAnh = sanPham.getHinhAnh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0 , hinhAnh.length);
        holder.imgHinhSP.setImageBitmap(bitmap);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTenSP;
        ImageView imgHinhSP;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTenSP = itemView.findViewById(R.id.txtTenSP);
            imgHinhSP = itemView.findViewById(R.id.imgHinhSP);
        }
    }
}
