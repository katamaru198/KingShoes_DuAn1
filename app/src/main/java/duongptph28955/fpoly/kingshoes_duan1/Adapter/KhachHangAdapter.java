package duongptph28955.fpoly.kingshoes_duan1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import duongptph28955.fpoly.kingshoes_duan1.DAO.KhachHangDao;
import duongptph28955.fpoly.kingshoes_duan1.R;
import duongptph28955.fpoly.kingshoes_duan1.dto.KhachHang;
import duongptph28955.fpoly.kingshoes_duan1.fragment.FragmentKhachHang;

public class KhachHangAdapter extends ArrayAdapter<KhachHang> {
    private Context context;
    private ArrayList<KhachHang> list;
    FragmentKhachHang fragmentKhachHang;
    private TextView tv_MaKhachHang,tv_TenKhachHang,tv_SoDienThoai,tv_DiaChi;
    ImageView imgDelete;

    public KhachHangAdapter(@NonNull Context context, FragmentKhachHang fragmentKhachHang, ArrayList<KhachHang> list) {
        super(context, 0,list);
        this.context=context;
        this.fragmentKhachHang =fragmentKhachHang;
        this.list=list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v= inflater.inflate(R.layout.item_khachhang,null);

        }
        final KhachHang item = list.get(position);
        if (v!=null ){
            tv_MaKhachHang=v.findViewById(R.id.tv_MKH);
            tv_MaKhachHang.setText("Mã Khách Hàng:"+item.getMaKH());

            tv_TenKhachHang=v.findViewById(R.id.tv_TKH);
            tv_TenKhachHang.setText("Tên Khách Hàng:"+item.getTenKH());

            tv_SoDienThoai=v.findViewById(R.id.tv_SDT);
            tv_SoDienThoai.setText("Số Điện Thoại:"+item.getSoDTKH());

            tv_DiaChi=v.findViewById(R.id.tv_DC);
            tv_DiaChi.setText("Địa chỉ:"+item.getDiaChiKH());
        }

        imgDelete=v.findViewById(R.id.deletekhachhang);

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentKhachHang.xoa(String.valueOf(item.maKH));
            }
        });

        return v;
    }
}
