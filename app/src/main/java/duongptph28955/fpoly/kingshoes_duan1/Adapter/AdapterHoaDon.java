package duongptph28955.fpoly.kingshoes_duan1.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import duongptph28955.fpoly.kingshoes_duan1.DAO.HoaDonDAO;
import duongptph28955.fpoly.kingshoes_duan1.DAO.KhachHangDao;
import duongptph28955.fpoly.kingshoes_duan1.DAO.MauSacDAO;
import duongptph28955.fpoly.kingshoes_duan1.DAO.SanPhamDAO;
import duongptph28955.fpoly.kingshoes_duan1.R;
import duongptph28955.fpoly.kingshoes_duan1.dto.HoaDon;
import duongptph28955.fpoly.kingshoes_duan1.dto.KhachHang;
import duongptph28955.fpoly.kingshoes_duan1.dto.SanPham;
import duongptph28955.fpoly.kingshoes_duan1.fragment.fragment_HoaDon;

public class AdapterHoaDon extends ArrayAdapter<HoaDon> {
    Context context;
    List<HoaDon> list;
    fragment_HoaDon fragment;

    TextView tvSP, tvKH, tvMau, tvSize, tvSoLuong, tvGiaXuat, tvNgayXuat, tvTrangThai;

    TextInputLayout edGia, edNgay, edSoLuong;
    EditText edMau, edSize;

    Spinner spnKH, spnSP;

    CheckBox chkTrangThai;

    SanPhamSpinnerAdapter SPAdapterSPN;
    ArrayList<SanPham> listSP;
    AdapterSpinnerKH adapterSpinnerKH;
    ArrayList<KhachHang> listKH;
    int maSP, maKH;

    String itemSelectedColor, itemSelectedSize;



    public AdapterHoaDon(@NonNull Context context, List<HoaDon> list, fragment_HoaDon fragment) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        final int vitri = position;
        if (v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_hoadon, null);
        }

        final HoaDon item = list.get(position);

        if (item != null){
            tvSP = v.findViewById(R.id.tvSPHoaDon);
            tvKH =v.findViewById(R.id.tvTenKH);
            tvMau = v.findViewById(R.id.tvMau);
            tvSize = v.findViewById(R.id.tvSize);
            tvNgayXuat = v.findViewById(R.id.tvNgayXuat);
            tvGiaXuat = v.findViewById(R.id.tvGiaXuat);
            tvSoLuong =v.findViewById(R.id.tvSoLuongXuat);
            tvTrangThai = v.findViewById(R.id.tvTrangThai);

            SanPhamDAO sanPhamDAO = new SanPhamDAO(context);
            SanPham sanPham = sanPhamDAO.getID(String.valueOf(item.maSP));
            tvSP.setText("Sản phẩm: " + sanPham.tenSP);
            KhachHangDao khachHangDao = new KhachHangDao(context);
            KhachHang khachHang = khachHangDao.getID(String.valueOf(item.maKH));
            tvKH.setText("Khách hàng: " + khachHang.tenKH);
            tvMau.setText("Màu: " + item.tenMau);
            tvSize.setText("Size: " + item.size);
            tvGiaXuat.setText("Giá: " + item.giaXuat);
            tvSoLuong.setText("Số lượng: " + item.soLuongXuat);
            tvNgayXuat.setText("Ngày xuất: " + item.ngayXuat);
            if (item.trangThai == 1) {
                tvTrangThai.setText("Đã thanh toán");
            } else {
                tvTrangThai.setText("Chưa thanh toán");
            }
            v.findViewById(R.id.btnDeleteHD).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Xóa hóa đơn "+ item.getMaHD());
                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            HoaDonDAO dao = new HoaDonDAO(context);
                            dao.deleteHoaDon(item);
                            list.remove(item);
                            notifyDataSetChanged();
                            dialog.dismiss();
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                }
            });
            v.findViewById(R.id.btnFixHD).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                    View view = inflater.inflate(R.layout.dialog_fixhd, null);
                    builder.setView(view);
                    edGia = view.findViewById(R.id.edGiaXuatNew);
                    edNgay = view.findViewById(R.id.edNgayXuatNew);
                    edSoLuong = view.findViewById(R.id.edSoLuongXuatNew);
                    edMau = view.findViewById(R.id.edMauNew);
                    edSize = view.findViewById(R.id.edSizeNew);
                    edGia.getEditText().setText(item.giaXuat + "");
                    edNgay.getEditText().setText(item.ngayXuat);
                    edSoLuong.getEditText().setText(item.soLuongXuat +"");
                    chkTrangThai = view.findViewById(R.id.chkTrangThaiNew);
                    edMau.setText(item.tenMau);
                    edSize.setText(item.size);
                    if (item.trangThai == 1){
                        chkTrangThai.setSelected(true);
                    } else {
                        chkTrangThai.setSelected(false);
                    }
                    spnSP = view.findViewById(R.id.spnSanPhamNew);
                    spnKH = view.findViewById(R.id.spnKhachHangNew);
                    SanPhamDAO sanPhamDAO = new SanPhamDAO(context);
                    listSP = sanPhamDAO.getDSSanPham();
                    SPAdapterSPN = new SanPhamSpinnerAdapter(context, listSP);
                    spnSP.setAdapter(SPAdapterSPN);
                    spnSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            maSP = listSP.get(position).getMaSP();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    view.findViewById(R.id.btn_chkMauNew).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SanPham sanPham = sanPhamDAO.getID(String.valueOf(maSP));
                            String tenMau = sanPham.getTenMau();
                            String[] arrTenMau = tenMau.split("\t");
                            arrTenMau = Arrays.copyOfRange(arrTenMau, 1, arrTenMau.length);
                            itemSelectedColor = arrTenMau[0];
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                            String[] finalArrTenMau = arrTenMau;
                            builder1.setTitle("Chọn màu").setSingleChoiceItems(arrTenMau, 0, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    itemSelectedColor = finalArrTenMau[which];
                                }
                            });
                            builder1.setPositiveButton("Chọn", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    edMau.setText(itemSelectedColor);
                                }
                            });
                            builder1.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            builder1.show();
                        }
                    });

                    view.findViewById(R.id.btn_chkSizeNew).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SanPham sanPham = sanPhamDAO.getID(String.valueOf(maSP));
                            String size = sanPham.getTenSize();
                            String[] arrTenSize = size.split("\t");
                            arrTenSize = Arrays.copyOfRange(arrTenSize, 1, arrTenSize.length);
                            itemSelectedSize = arrTenSize[0];
                            AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
                            String[] finalArrTenSize = arrTenSize;
                            builder2.setTitle("Chọn màu").setSingleChoiceItems(arrTenSize, 0, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    itemSelectedSize = finalArrTenSize[which];
                                }
                            });
                            builder2.setPositiveButton("Chọn", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    edSize.setText(itemSelectedSize);
                                }
                            });
                            builder2.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            builder2.show();
                        }
                    });
                    KhachHangDao khachHangDao = new KhachHangDao(context);
                    listKH = (ArrayList<KhachHang>) khachHangDao.getAll();
                    adapterSpinnerKH = new AdapterSpinnerKH(context, listKH);
                    spnKH.setAdapter(adapterSpinnerKH);
                    spnKH.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            maKH = listKH.get(position).maKH;
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            item.setGiaXuat(Integer.parseInt(edGia.getEditText().getText().toString()));
                            item.setNgayXuat(edNgay.getEditText().getText().toString());
                            item.setSoLuongXuat(Integer.parseInt(edSoLuong.getEditText().getText().toString()));
                            item.setTenMau(edMau.getText().toString());
                            item.size = edSize.getText().toString();
                            item.maSP = maSP;
                            item.maKH = maKH;
                            if (!chkTrangThai.isChecked()){
                                item.trangThai = 0;
                            } else {
                                item.trangThai = 1;
                            }
                            HoaDonDAO dao = new HoaDonDAO(context);
                            if (dao.updateHoaDon(item)>0){
                                list.set(vitri, item);
                                notifyDataSetChanged();
                                dialog.dismiss();
                                Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    builder.show();
                }
            });
        }
        return v;
    }
}
