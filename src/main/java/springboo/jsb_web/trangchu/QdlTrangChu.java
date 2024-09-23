package springboo.jsb_web.trangchu;

import java.util.ArrayList;
import java.util.HashMap;
// Thư viện chuẩn: Java Standard Edition(JavaSE)
import java.util.List;
import java.util.Map;

// Thư viện doanh nghiệp: Java Entprise Edition(JavaEE)
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import springboo.jsb_web.quangcao.DvlQuangCao;
import springboo.jsb_web.quangcao.QuangCao;
import springboo.jsb_web.sanpham.DvlSanPham;
import springboo.jsb_web.sanpham.SanPham;

// Thư viện web: Java SpringBoot
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Thư viện tự định nghĩa

@Controller
public class QdlTrangChu {
    @Autowired
    private DvlQuangCao dvlQuangCao; // cung cấp các dịch vụ thao tác dữ liệu

    @Autowired
    private DvlSanPham dvlSanPham;

    @GetMapping("/trangchu")
    public String getTrangChu(Model model) {

        // Đọc dữ liệu bảng rồi chứa vào biến tạm
        List<QuangCao> list = dvlQuangCao.duyệtQuangCao();

        // Gửi danh sách sang giao diện View HTML
        // Dùng thymeleaf if ở bên view html không hiệu quả
        // bắt buộc phải viết hàm truy vấn bên trong lớp Kdl.java để truy vấn các bản
        // ghi cần thiết
        // model.addAttribute("dsQuangCao", dvlQuangCao.dsQuangCaoChoPhep());

        model.addAttribute("dsQuangcao", list);

        model.addAttribute("title", "Trang Chủ");

        // Nội dung riêng của trang...
        model.addAttribute("content", "trangchu/home.html");
        model.addAttribute("dsSanPhamNoiBat", dvlSanPham.dsSanPhamNoiBat());

        // ...được đặt vào bố cục chung của toàn website
        return "layout/layout-home.html";
    }

    // @GetMapping("/giohang/chitiet")
    // public String getgiohang(Model model) {
    // // Đọc dữ liệu bảng rồi chứa vào biến tạm
    // List<QuangCao> list = dvlQuangCao.duyệtQuangCao();

    // model.addAttribute("dsQuangcao", list);
    // model.addAttribute("title", "Giỏ Hàng");
    // model.addAttribute("content", "trangchu/giohang-chitiet.html");
    // return "layout/layout-home.html";
    // }

    // @GetMapping("/giohang/chitiet")
    // public String getgiohang(Model model, HttpSession session) {
    // // Đọc dữ liệu bảng quảng cáo rồi chứa vào biến tạm
    // List<QuangCao> list = dvlQuangCao.duyệtQuangCao();

    // // Lấy giỏ hàng từ session
    // List<SanPham> cart = (List<SanPham>) session.getAttribute("cart");
    // if (cart == null) {
    // cart = new ArrayList<>();
    // }

    // // Truyền dữ liệu giỏ hàng và quảng cáo vào model
    // model.addAttribute("dsQuangcao", list);
    // model.addAttribute("cartData", cart); // Truyền giỏ hàng vào model
    // model.addAttribute("title", "Giỏ Hàng");
    // model.addAttribute("content", "trangchu/giohang-chitiet.html");

    // return "layout/layout-home.html";
    // }


}
