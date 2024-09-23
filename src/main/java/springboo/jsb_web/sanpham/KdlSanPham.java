package springboo.jsb_web.sanpham;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KdlSanPham extends JpaRepository<SanPham, Integer>
{
    List<SanPham> findByNoiBat(Boolean noiBat);

    Page<SanPham> findByTenContaining(String keyword, Pageable pageable);

    // Tên biến dl là do lập trình viên tự đặt
    // Viết xong đầy đủ câu JPQL thì phải biên dịch dự án
    // để xem nó có lỗi gì không !
    // Đây là tầng DAL, DAO trình diễn khả năng lọc tìm dữ liệu bảng quan hệ
    // @Query("""
    //     SELECT dl
    //     FROM SanPham dl
    //     WHERE 1 = 1
    //     AND (
    //         :filter_ten IS NULL 
    //         OR 
    //         dl.ten LIKE CONCAT('%',:filter_ten,'%')
    //     )            
    //     AND (
    //         :filter_maNhaSanXuat IS NULL
    //         OR
    //         dl.maNhaSanXuat = :filter_maNhaSanXuat
    //     )
    //     AND (
    //         :filter_maLoai IS NULL
    //         OR
    //         dl.maLoai = :filter_maLoai
    //     )
    //     AND (
    //         :filter_trangThai IS NULL
    //         OR
    //         dl.trangThai = :filter_trangThai
    //     )
    //     AND (
    //         :filter_donGia_from IS NULL
    //         OR
    //         dl.donGia >= :filter_donGia_from
    //     )
    //     AND (
    //         :filter_donGia_to IS NULL
    //         OR
    //         dl.donGia <= :filter_donGia_to
    //     )
    // """)
    // Page<SanPham> filterSanPhamByJPQL(
    //     String   filter_ten,
    //     Integer  filter_maNhaSanXuat, 
    //     Integer  filter_maLoai, 
    //     Boolean  filter_trangThai,
    //     Float    filter_donGia_from,
    //     Float    filter_donGia_to,
    //     Pageable pageable
    // );

}
