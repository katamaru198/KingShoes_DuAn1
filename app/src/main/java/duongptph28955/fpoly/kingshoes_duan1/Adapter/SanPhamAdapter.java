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

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewHolder>{
    private Context context;
    ArrayList<SanPham> list;
    private SanPhamDAO sanPhamDAO;


    public ItemClickListener itemClickListener;


    public SanPhamAdapter(Context context, ArrayList<SanPham> list, SanPhamDAO sanPhamDAO, ItemClickListener itemClickListener) {
        this.context = context;
        this.list = list;
        this.sanPhamDAO = sanPhamDAO;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_recycler_sanpham, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTenSP.setText(list.get(position).getTenSP());
        holder.txtGiaNhapSP.setText("₫"+list.get(position).getGiaNhap());
        holder.txtTenLoaiSP.setText(list.get(position).getTenLoai());

        // chuyển mảng byte[] sang bitmap
        SanPham sanPham = list.get(position);
        byte[] hinhAnh = sanPham.getHinhAnh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0 , hinhAnh.length);
        holder.imgHinhSP.setImageBitmap(bitmap);
        holder.itemView.setOnClickListener(view -> {
            itemClickListener.onItemClick(list.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface ItemClickListener{
        void onItemClick(SanPham sanPham);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView  txtTenSP, txtGiaNhapSP, txtTenLoaiSP;
        ImageView imgHinhSP;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenSP = itemView.findViewById(R.id.txtTenSP);
            txtGiaNhapSP = itemView.findViewById(R.id.txtGiaNhapSP);
            txtTenLoaiSP = itemView.findViewById(R.id.txtTenLoaiSP);

            imgHinhSP = itemView.findViewById(R.id.imgHinhSP);


        }
    }

}
