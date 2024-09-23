package springboo.jsb_web.lienhe;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DvlLienHe 
{
    @Autowired private KdlLienHe kdl;// kho dữ liệu;

    public List<LienHe> dsLienHe() // getAllThucThe()
    {
  
        // return null;

        // mã bởi lập trình viên:
        return kdl.findAll();
    }

    public List<LienHe>  duyệtLienHe() 
    {
        return kdl.findAll();
    }

    public LienHe  tìmLienHeTheoId(int id)// 
    {
        // TODO Auto-generated method stub
        // return null;

        // return kdl.findById(id);

        LienHe dl = null;

        Optional<LienHe> optional = kdl.findById(id);

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

    public LienHe xemLienHe(int id)// 
    {

        LienHe dl = null;

        Optional<LienHe> optional = kdl.findById(id);

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

    
    public void lưuLienHe(LienHe dl)
    {
        // TODO Auto-generated method stub
        this.kdl.save(dl);
    }

    
    public void xóaLienHe(int id)
    {
        // TODO Auto-generated method stub
        this.kdl.deleteById(id);
    }

}
