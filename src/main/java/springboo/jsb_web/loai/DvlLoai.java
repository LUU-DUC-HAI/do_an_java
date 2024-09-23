package springboo.jsb_web.loai;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class DvlLoai
// implements DviLoai
{   
    @Autowired 
    private KdlLoai kdl;// kho dữ liệu;

    public Page<Loai> getPaged(
            int pageNumber,
            int pageSize,
            String sortField,
            String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);

        return this.kdl.findAll(pageable);
    }

    public Page<Loai> getPaginated(
            int pageNumber,
            int pageSize,
            String sortField,
            String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        return this.kdl.findAll(pageable);
    }

    public Page<Loai> getPaginatedSearch(
            int pageNumber,
            int pageSize,
            String sortField,
            String sortDirection,
            String keyword) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        return this.kdl.findByTenContaining(keyword, pageable);
    }

    // public DvlLoai(KdlLoai kdl) {
    //     this.kdl = kdl;
    //     System.out.println("KdlLoai injected: " + (kdl != null));
    // }


    public List<Loai> dsLoai() {
        return kdl.findAll();
    }

    public List<Loai>  duyet() 
    {
        return kdl.findAll();
    }

    public List<Loai>  duyetLoai() 
    {
        return duyet();
    }

    // @Override 
    public List<Loai>  duyệtLoai() 
    {
        return duyet();
    }

    public Loai timLoaiTheoId(int id)
    {
        return timTheoId(id);
    }

    // @Override 
    public Loai timTheoId(int id)// 
    {
        // TODO Auto-generated method stub
        // return null;

        // return kdl.findById(id);

        Loai dl = null;

        Optional<Loai> optional = kdl.findById(id);

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
    public Loai xemLoai(int id)// 
    {
        return xem(id);
    }
    // @Override 
    public Loai xem(int id)
    {

        Loai dl = null;

        Optional<Loai> optional = kdl.findById(id);

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
    public void lưuNhaSanXuat(Loai dl)
    {
        luu(dl);
    }

    public void luu(Loai dl)
    {
        // TODO Auto-generated method stub
        this.kdl.save(dl);
    }

    public void them(Loai dl)
    {
        this.kdl.save(dl);
    }

    public void sua(Loai dl)
    {
        this.kdl.save(dl);
    }

    // @Override
    public void xóaNhaSanXuat(int id){
        xoa(id);
    }

    public void xoa(int id)
    {
        this.kdl.deleteById(id);
    }

    public static Page<Loai> layTatCa(PageRequest of) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'layTatCa'");
    }


}
