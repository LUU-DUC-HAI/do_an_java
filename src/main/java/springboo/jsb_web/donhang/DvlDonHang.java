package springboo.jsb_web.donhang;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboo.jsb_web.donhang.DonHang.trangThai;

@Service
public class DvlDonHang 
//implements DviDonHang
{
    @Autowired private KdlDonHang kdl;// kho dữ liệu;

    // @Override 
    public List<DonHang> dsDonHang() // getAllThucThe()
    {
  
        // return null;

        // mã bởi lập trình viên:
        return kdl.findAll();
    }

    // @Override 
    public List<DonHang>  duyệtDonHang() 
    {
        return kdl.findAll();
    }

    // @Override 
    public DonHang  tìmDonHangTheoId(int id)// 
    {
        // TODO Auto-generated method stub
        // return null;

        // return kdl.findById(id);

        DonHang dl = null;

        Optional<DonHang> optional = kdl.findById(id);

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
    public DonHang xemDonHang(int id)// 
    {

        DonHang dl = null;

        Optional<DonHang> optional = kdl.findById(id);

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

    public void them(DonHang dl){
        this.kdl.save(dl);
    }

    // @Override
    public void lưuDonHang(DonHang dl)
    {
        // TODO Auto-generated method stub
        this.kdl.save(dl);
    }


    public DonHang luuDonHang(DonHang dl)
    {
        // TODO Auto-generated method stub
        return this.kdl.save(dl);
    }

    // @Override
    public void xóaDonHang(int id)
    {
        // TODO Auto-generated method stub
        this.kdl.deleteById(id);
    }

    

}

