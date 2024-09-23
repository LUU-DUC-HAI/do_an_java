package springboo.jsb_web.chitietdonhang;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DvlChiTietDonHang 
//implements DviChiTietDonHang
{
    @Autowired private KdlChiTietDonHang kdl;// kho dữ liệu;

    // @Override 
    public List<ChiTietDonHang> dsChiTietDonHang() // getAllThucThe()
    {
  
        // return null;

        // mã bởi lập trình viên:
        return kdl.findAll();
    }

    // @Override 
    public List<ChiTietDonHang>  duyệtChiTietDonHang() 
    {
        return duyet();
    }
    public List<ChiTietDonHang>  duyet() 
    {
        return kdl.findAll();
    }

    // @Override 
    public ChiTietDonHang  tìmChiTietDonHangTheoId(int id)// 
    {
        // TODO Auto-generated method stub
        // return null;

        // return kdl.findById(id);

        ChiTietDonHang dl = null;

        Optional<ChiTietDonHang> optional = kdl.findById(id);

        if// nếu
        (optional.isPresent()) // tìm thấy bản ghi trong kho
        {
            dl = optional.get();
        } else// ngược lại
        {
            //throw new RuntimeException("Không tìm thấy thú cưng ! Ko tim thay thu cung !");
        }

        return dl;

    }

    // @Override 
    public ChiTietDonHang xemChiTietDonHang(int id)// 
    {

        ChiTietDonHang dl = null;

        Optional<ChiTietDonHang> optional = kdl.findById(id);

        if// nếu
        (optional.isPresent()) // tìm thấy bản ghi trong kho
        {
            dl = optional.get();
        } else// ngược lại
        {
            //throw new RuntimeException("Không tìm thấy thú cưng ! Ko tim thay thu cung !");
        }

        return dl;

    }

    // @Override
    public void lưuChiTietDonHang(ChiTietDonHang dl)
    {
        // TODO Auto-generated method stub
        this.kdl.save(dl);
    }

    // @Override
    public void xóaChiTietDonHang(int id)
    {
        // TODO Auto-generated method stub
        this.kdl.deleteById(id);
    }


}


