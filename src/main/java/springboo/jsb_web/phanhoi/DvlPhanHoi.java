package springboo.jsb_web.phanhoi;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DvlPhanHoi 
{
    @Autowired private KdlPhanHoi kdl;// kho dữ liệu;

    public List<PhanHoi> dsPhanHoi() // getAllThucThe()
    {
  
        // return null;

        // mã bởi lập trình viên:
        return kdl.findAll();
    }

    public List<PhanHoi>  duyệtPhanHoi() 
    {
        return kdl.findAll();
    }

    public PhanHoi  tìmPhanHoiTheoId(int id)// 
    {
        // TODO Auto-generated method stub
        // return null;

        // return kdl.findById(id);

        PhanHoi dl = null;

        Optional<PhanHoi> optional = kdl.findById(id);

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

    public PhanHoi xemPhanHoi(int id)// 
    {

        PhanHoi dl = null;

        Optional<PhanHoi> optional = kdl.findById(id);

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

    
    public void lưuPhanHoi(PhanHoi dl)
    {
        // TODO Auto-generated method stub
        this.kdl.save(dl);
    }

    
    public void xóaPhanHoi(int id)
    {
        // TODO Auto-generated method stub
        this.kdl.deleteById(id);
    }

}

