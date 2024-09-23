package springboo.jsb_web.sanpham;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class DvlSanPham
// implements DviSanPham
{
    @Autowired
    private KdlSanPham kdl;// kho dữ liệu;

    public Page<SanPham> getPaged(
            int pageNumber,
            int pageSize,
            String sortField,
            String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);

        return this.kdl.findAll(pageable);
    }

    public Page<SanPham> getPaginated(
            int pageNumber,
            int pageSize,
            String sortField,
            String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        return this.kdl.findAll(pageable);
    }

    public Page<SanPham> getPaginatedSearch(
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

    // private CrudRepository<SanPham, Integer> sanPhamRepository;

    
    // @Override
    public List<SanPham> dsSanPham() {
        return kdl.findAll();
    }


    public List<SanPham> dsSanPhamNoiBat() {
        return kdl.findByNoiBat(true);
    }


    public List<SanPham> duyet() {
        return kdl.findAll();
    }

    // @Override
    public List<SanPham> duyệtSảnPhẩm() {
        return duyet();
    }

    // @Override
    public SanPham tìmSảnPhẩmTheoId(int id) {
        return timTheoId(id);
    }

    public SanPham timTheoId(int id)//
    {

        SanPham dl = null;

        Optional<SanPham> optional = kdl.findById(id);

        if// nếu
        (optional.isPresent()) // tìm thấy bản ghi trong kho
        {
            dl = optional.get();
        } else// ngược lại
        {
            // throw new RuntimeException("Không tìm thấy thú cưng ! Ko tim thay thu cung
            // !");
        }

        return dl;

    }

    public SanPham xemSanPham(int id) {
        return xem(id);
    }

    public SanPham xem(int id) {
        return kdl.findById(id).orElse(null);
    }

    // @Override
    // public SanPham xem(int id) {

    //     SanPham dl = null;

    //     Optional<SanPham> optional = kdl.findById(id);

    //     if// nếu
    //     (optional.isPresent()) // tìm thấy bản ghi trong kho
    //     {
    //         dl = optional.get();
    //     } else// ngược lại
    //     {
    //         // throw new RuntimeException("Không tìm thấy thú cưng ! Ko tim thay thu cung
    //         // !");
    //     }

    //     return dl;

    // }

    // @Override
    public void lưuSảnPhẩm(SanPham dl) {
        luu(dl);
    }

    public void luu(SanPham dl) {
        // TODO Auto-generated method stub
        this.kdl.save(dl);
    }

    public void thêm(SanPham dl) {
        them(dl);
    }

    public void them(SanPham dl) {
        if (dl.getMaLoai() == null) {
            throw new IllegalArgumentException("Mã loại không được để trống");
        }
        this.kdl.save(dl);
    }

    public void sửa(SanPham dl) {
        sua(dl);
    }

    public void sua(SanPham dl) {
        kdl.save(dl);
    }

    // public void sua(SanPham dl) {
    //     this.kdl.save(dl);
    // }

    // @Override
    public void xóaSảnPhẩm(int id) {
        xoa(id);
    }

    public void xoa(int id) {
        kdl.deleteById(id);
    }

    public static SanPham timTheoId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'timTheoId'");
    }

    // public void xoa(int id) {
    //     // TODO Auto-generated method stub
    //     this.kdl.deleteById(id);
    // }


}
