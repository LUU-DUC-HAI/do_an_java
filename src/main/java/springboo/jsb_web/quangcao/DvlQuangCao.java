package springboo.jsb_web.quangcao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DvlQuangCao 
{
    @Autowired private KdlQuangCao kdl;// kho dữ liệu;

    public List<QuangCao> dsQuangCao() // getAllThucThe()
    {
  
        // return null;

        // mã bởi lập trình viên:
        return kdl.findAll();
    }

    public List<QuangCao>  duyệtQuangCao() 
    {
        return kdl.findAll();
    }

    public QuangCao  tìmQuangCaoTheoId(int id)// 
    {
        // TODO Auto-generated method stub
        // return null;

        // return kdl.findById(id);

        QuangCao dl = null;

        Optional<QuangCao> optional = kdl.findById(id);

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

    public QuangCao xemQuangCao(int id)// 
    {

        QuangCao dl = null;

        Optional<QuangCao> optional = kdl.findById(id);

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

   
    public void lưuQuangCao(QuangCao dl)
    {
        // TODO Auto-generated method stub
        this.kdl.save(dl);
    }

   
    public void xóaQuangCao(int id)
    {
        // TODO Auto-generated method stub
        this.kdl.deleteById(id);
    }


}

