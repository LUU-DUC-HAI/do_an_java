package springboo.jsb_web.donhang;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import springboo.jsb_web.qdl.Qdl;

@Controller
public class QdlDonHang {

    @Autowired
    private DvlDonHang dvl; // Đổi tên biến cho dễ hiểu

    @GetMapping("/qdl/donhang")
    public String getDuyet(Model model) {
        List<DonHang> list = dvl.duyệtDonHang(); // Kiểm tra phương thức này

        model.addAttribute("ds", list);
        System.out.println("Danh sách đơn hàng: " + list);
        model.addAttribute("dl", new DonHang()); // Nếu cần sử dụng trong form

        model.addAttribute("content", "donhang/duyet.html"); // Tệp HTML bạn muốn hiển thị
        return "layout/layout.html";
    }

    @GetMapping("/qdl/donhang/them")
    public String getThemForm(Model model) {
        model.addAttribute("dl", new DonHang());
        return "donhang/them"; // Tên view hiển thị form thêm sản phẩm
    }

    @GetMapping("/qdl/donhang/them/modal")
    public String getThemModal(Model model, HttpServletRequest request) {
        if (Qdl.NhanVienChuaDangNhap(request))
            return "redirect:/qdl/donhang/dangnhap";

        var dl = new DonHang();
        model.addAttribute("dl", dl);
        model.addAttribute("action", "/qdl/donhang/them");
        model.addAttribute("content", "donhang/them.html");
        return "modal-bs4.html";
    }

    @PostMapping("/qdl/donhang/them")
    public String postThem(@ModelAttribute DonHang dl, RedirectAttributes redirectAttributes) {
        System.out.println("Gọi phương thức thêm đơn hàng với dữ liệu: " + dl);
        dvl.them(dl);
        redirectAttributes.addFlashAttribute("THONG_BAO_OK", "Đã thêm mới thành công!");
        return "redirect:/qdl/donhang";
    }

    // @PostMapping("/luu")
    // public String luuDonHang(@ModelAttribute("donHang") DonHang donHang, Model model) {
    //     // Lưu thông tin đơn hàng vào cơ sở dữ liệu
    //     DonHang donHangMoi = dvl.luuDonHang(donHang);

    //     // Thêm đơn hàng vào model để hiển thị sau khi lưu thành công
    //     model.addAttribute("donHang", donHangMoi); // Đảm bảo rằng donHangMoi được thêm vào model

    //     // Chuyển hướng sang trang xác nhận đơn hàng
    //     return "trangchu/xacnhan-donhang"; // Đảm bảo đường dẫn đúng đến trang xác nhận
    // }

}
