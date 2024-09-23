package springboo.jsb_web.nhasanxuat;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Gọi các thư viện xử lý phân trang & sắp xếp
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

/**
 * Các hàm thực sự cần thiết:
 * thêm(), them()
 * sửa(), sua()
 * xóa(), xoa()
 * xem(), xemId()
 * duyệt(), duyet()
 * tìm(), tim(), tìmTheoId(), timTheoId()
 * 
 */
@Service
public class DvlNhaSanXuat
// implements DviNhaSanXuat
{
    @Autowired
    private KdlNhaSanXuat kdl;// kho dữ liệu;

    @Autowired
    private KdlNhaSanXuat KdlNhaSanXuat;

    public Page<NhaSanXuat> getPaged (
        int pageNumber,
        int pageSize,
        String sortField,
        String sortDirection
    ) 
    {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);

        return this.kdl.findAll(pageable);
    }

    public Page<NhaSanXuat> getPaginated(
            int pageNumber,
            int pageSize,
            String sortField,
            String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);

        return this.kdl.findAll(pageable);
    }

    public Page<NhaSanXuat> getPaginatedSearch(
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

    // @Override
    public List<NhaSanXuat> dsNhaSanXuat() {
        return kdl.findAll();
    }

    public List<NhaSanXuat> duyet() {
        return kdl.findAll();
    }

    public List<NhaSanXuat> duyetNhaSanXuat() {
        return duyet();
    }

    // @Override
    public List<NhaSanXuat> duyệtNhaSanXuat() {
        return duyet();
    }

    public NhaSanXuat timNhaSanXuatTheoId(int id) {
        return timTheoId(id);
    }

    // @Override
    public NhaSanXuat timTheoId(int id)//
    {
        // TODO Auto-generated method stub
        // return null;

        // return kdl.findById(id);

        NhaSanXuat dl = null;

        Optional<NhaSanXuat> optional = kdl.findById(id);

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



    public NhaSanXuat xemNhaSanXuat(int id)//
    {
        return xem(id);
    }

    // @Override
    public NhaSanXuat xem(int id) {

        NhaSanXuat dl = null;

        Optional<NhaSanXuat> optional = kdl.findById(id);

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

    // @Override
    public void lưuNhaSanXuat(NhaSanXuat dl) {
        luu(dl);
    }

    public void luu(NhaSanXuat dl) {
        // TODO Auto-generated method stub
        this.kdl.save(dl);
    }

    public void them(NhaSanXuat dl) {
        this.kdl.save(dl);
    }

    public void sua(NhaSanXuat dl) {
        this.kdl.save(dl);
    }

    // @Override
    public void xóaNhaSanXuat(int id) {
        xoa(id);
    }

    public void xoa(int id) {
        this.kdl.deleteById(id);
    }

}

