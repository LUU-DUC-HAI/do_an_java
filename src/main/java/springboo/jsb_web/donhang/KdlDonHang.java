package springboo.jsb_web.donhang;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KdlDonHang extends JpaRepository<DonHang, Integer>
{

}
