package duongptph28955.fpoly.kingshoes_duan1.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import duongptph28955.fpoly.kingshoes_duan1.DAO.DoanhThuDao;
import duongptph28955.fpoly.kingshoes_duan1.R;
import duongptph28955.fpoly.kingshoes_duan1.dto.HoaDon;

public class fragmentDoanhThu extends Fragment {
    EditText ed_tuNgay ,ed_denNgay;
    Button btn_tuNgay,btn_denNgay,btn_thongKe;
    TextView tv_DoanhThu;
    HoaDon item ;
    ArrayList<HoaDon> list;
    int TT;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    int mYear,mMonth ,mDay;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doanhthu,container,false);
        ed_denNgay =view.findViewById(R.id.ed_denNgay);
        ed_tuNgay =view.findViewById(R.id.ed_tuNgay);
        btn_tuNgay =view.findViewById(R.id.btn_tuNgay);
        btn_denNgay =view.findViewById(R.id.btn_denNgay);
        tv_DoanhThu =view.findViewById(R.id.tv_DoanhThu);

        btn_thongKe= view.findViewById(R.id.btn_ThongKe);

        btn_tuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear =c.get(Calendar.YEAR);
                mMonth =c.get(Calendar.MONTH);
                mDay =c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        int nam = year;
                        int thang = month;
                        int ngay = dayOfMonth;
                        ed_tuNgay.setText(ngay+ "/" + (thang +1) +"/" + nam);

                    }
                },c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DATE)

                );
                datePickerDialog.show();
            }
        });
        btn_denNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        int nam = year;
                        int thang = month;
                        int ngay = dayOfMonth;
                        ed_denNgay.setText(ngay+ "/" + (thang +1) +"/" + nam);

                    }
                },c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DATE)

                );
                datePickerDialog.show();
            }
        });

    btn_thongKe.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String tuNgay =ed_tuNgay.getText().toString().trim();
            String denNgay = ed_denNgay.getText().toString().trim();
            if (tuNgay.isEmpty() && denNgay.isEmpty()){
                Toast.makeText(getActivity(),"không được để trống ngày",Toast.LENGTH_SHORT).show();
                return;
            }


        DoanhThuDao doanhThuDao = new DoanhThuDao(getActivity());

        int doanhtthu =0;
        list= (ArrayList<HoaDon>) doanhThuDao.GetHoaDonTheoNgay(tuNgay,denNgay);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getTrangThai()==1){

                    doanhtthu+=list.get(i).getGiaXuat();

                }
            }
             tv_DoanhThu.setText("Doanh thu: " +doanhtthu+" VND");

        }
    });


        return view;
    }
//    DatePickerDialog.OnDateSetListener mDateTuNgay =(new DatePickerDialog.OnDateSetListener() {
//        @Override
//        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//            mYear = year;
//            mMonth = month;
//            mDay = dayOfMonth;
//            GregorianCalendar c = new GregorianCalendar(mYear,mMonth,mDay);
//            ed_tuNgay.setText(sdf.format(c.getTime()));
//        }
//    });
//    DatePickerDialog.OnDateSetListener mDateDenNgay = (new DatePickerDialog.OnDateSetListener() {
//        @Override
//        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//            mYear= year;
//            mMonth = month;
//            mDay = dayOfMonth;
//
//            GregorianCalendar c = new GregorianCalendar(mYear,mMonth,mDay);
//            ed_denNgay.setText(sdf.format(c.getTime()));
//
//        }
//    });
}
