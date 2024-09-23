package springboo.jsb_web.nhasanxuat;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KdlNhaSanXuat extends JpaRepository<NhaSanXuat, Integer>
{

       // Tìm các bản ghi mà Tên khớp 100% với search keyword
       Page<NhaSanXuat> findByTen(String ten, Pageable pageable);

       // Tìm các bản ghi mà Tên có chứa, giống với search keyword
       Page<NhaSanXuat> findByTenContaining(String ten, Pageable pageable);
}
