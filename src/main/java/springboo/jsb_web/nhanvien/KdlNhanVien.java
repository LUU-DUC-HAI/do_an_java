package springboo.jsb_web.nhanvien;

import org.springframework.data.jpa.repository.JpaRepository;

public interface KdlNhanVien extends JpaRepository<NhanVien, Integer>
{

    NhanVien findOneByTenDangNhap(String tdn);

    Boolean existsByTenDangNhap(String tdn);

}
