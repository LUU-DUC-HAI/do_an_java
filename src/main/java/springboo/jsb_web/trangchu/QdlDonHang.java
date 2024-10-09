package springboo.jsb_web.trangchu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import springboo.jsb_web.quangcao.DvlQuangCao;
import springboo.jsb_web.quangcao.QuangCao;
import springboo.jsb_web.sanpham.DvlSanPham;

public class QdlDonHang {
    @Autowired
    private DvlQuangCao dvlQuangCao;

    @Autowired
    private DvlSanPham dvlSanPham;

    @GetMapping("/donhang")
    public String getCuaHang(Model model) {

        List<QuangCao> list = dvlQuangCao.duyệtQuangCao();
        model.addAttribute("dsQuangcao", list);

        model.addAttribute("title", "Trang Cửa Hàng"); // Cập nhật tiêu đề nếu cần

        model.addAttribute("content", "trangchu/xacnhan-donhang.html");
        model.addAttribute("dsSanPhamNoiBat", dvlSanPham.dsSanPhamNoiBat());

        return "layout/layout-home.html";
    }
}
