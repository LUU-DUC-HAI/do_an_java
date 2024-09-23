package springboo.jsb_web.loai;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import springboo.jsb_web.nhasanxuat.NhaSanXuat;

public interface KdlLoai extends JpaRepository<Loai, Integer> {
        Page<NhaSanXuat> findByTen(String ten, Pageable pageable);

        Page<Loai> findByTenContaining(String keyword, Pageable pageable);

}