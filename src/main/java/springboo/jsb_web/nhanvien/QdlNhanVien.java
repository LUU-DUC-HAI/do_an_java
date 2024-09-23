package springboo.jsb_web.nhanvien;

import java.time.LocalDate;

// Thư viện chuẩn: Java Standard (JavaSE)
import java.util.List;

// Thư viện web: Java Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.mindrot.jbcrypt.BCrypt;

import jakarta.servlet.ServletRequest;
// Thue viện Session
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import springboo.jsb_web.qdl.Qdl;

@Controller
public class QdlNhanVien {
    @Autowired
    private DvlNhanVien dvl;

    @Autowired
    private HttpServletRequest request;// cung cấp các dịch vụ thao tác dữ liệu

    @GetMapping({ "/qdl/nhanvien", "/qdl/nhanvien/duyet" })
    public String getDuyet(Model model, HttpSession session, HttpServletRequest request) {
        if (Qdl.NhanVienChuaDangNhap(request))
            return "redirect:/qdl/nhanvien/dangnhap";

        List<NhanVien> list = dvl.duyệtNhanVien();
        model.addAttribute("ds", list);
        model.addAttribute("dl", new NhanVien()); // Ensure 'dl' is added if needed
        model.addAttribute("title", "Quản Lý Nhân Viên");
        model.addAttribute("content", "nhanvien/duyet.html");
        return "layout/layout.html";
    }

    @GetMapping("/qdl/nhanvien/them")
    public String getThem(Model model, HttpServletRequest request) {
        if (Qdl.NhanVienChuaDangNhap(request))
            return "redirect:/qdl/nhanvien/dangnhap";
        NhanVien dl = new NhanVien();
        model.addAttribute("dl", dl);
        model.addAttribute("content", "nhanvien/them.html");
        return "nhanvien/them";
    }

    @GetMapping("/qdl/nhanvien/them/ajax/modal")
    public String getThemAjaxModal(Model model, HttpServletRequest request) {
        if (Qdl.NhanVienChuaDangNhap(request))
            return "redirect:/qdl/nhanvien/dangnhap";

        var dl = new NhanVien();
        model.addAttribute("dl", dl);
        model.addAttribute("action", "/qdl/nhanvien/them");
        model.addAttribute("content", "nhanvien/them.html");
        return "nhanvien/modal-bs4.html";
    }

    @GetMapping("/qdl/nhanvien/sua")
    public String getSua(Model model, @RequestParam("id") int id) {
        NhanVien dl = dvl.xemNhanVien(id); // Consistency in xemNhanVien
        model.addAttribute("dl", dl);
        model.addAttribute("content", "nhanvien/sua.html"); // Ensure correct path
        return "nhanvien/duyet"; // Main layout
    }

    @GetMapping("/qdl/nhanvien/ajax/modal")
    public String getAjaxModale(Model model, HttpServletRequest request) {
        if (Qdl.NhanVienChuaDangNhap(request))
            return "redirect:/qdl/nhanvien/dangnhap";

        // Xác định mã định danh mà máy khách gửi
        String id_str = request.getParameter("id");
        Integer id = 0;

        try {
            id = Integer.parseInt(id_str);
        } catch (Exception e) {
            // TODO: handle exception
            id = 0;
        }

        // Xác định xem Modal này là Add hay Edit (id >=1)
        var dl = (id < 1) ? new NhanVien() : dvl.xemNhanVien(id);

        String action = (id < 1) ? "/qdl/nhanvien/them" : "/qdl/nhanvien/sua";

        // Gửi đối tượng dữ liệu sang giao diện (ui view) html form
        model.addAttribute("dl", dl);
        model.addAttribute("action", action);

        return "nhanvien/them";
    }

    @GetMapping("/qdl/nhanvien/suaajax")
    public String getSuaAjax(Model model, @RequestParam("id") int id, HttpServletRequest request) {
        if (Qdl.NhanVienChuaDangNhap(request)) {
            return "redirect:/qdl/nhanvien/dangnhap";
        }

        NhanVien dl = dvl.xemNhanVien(id); // Consistent with other methods
        model.addAttribute("dl", dl);
        model.addAttribute("action", "/qdl/nhanvien/sua");
        return "nhanvien/sua.html"; // Ensure this is the correct view
    }

    @PostMapping("/qdl/nhanvien/sua")
    public String postSua(@ModelAttribute("NhanVien") NhanVien dl, RedirectAttributes redirectAttributes) {
        dl.setMatKhau(BCrypt.hashpw(dl.getMatKhau(), BCrypt.gensalt(12))); // Password hashing
        dvl.luuNhanVien(dl); // Method name should be 'luuNhanVien' for consistency
        redirectAttributes.addFlashAttribute("THONG_BAO_OK", "Đã sửa thành công!");
        return "redirect:/nhanvien/duyet"; // Ensure this path is correct
    }

    @GetMapping("/qdl/nhanvien/xoa")
    public String getXoa(Model model, @RequestParam(value = "id") int id, HttpServletRequest request) {
        if (Qdl.NhanVienChuaDangNhap(request))
            return "redirect:/qdl/nhanvien/dangnhap";

        NhanVien dl = dvl.tìmNhanVienTheoId(id);
        model.addAttribute("dl", dl);
        model.addAttribute("content", "nhanvien/xoa.html"); // xoa.html
        return "layout/layout.html"; // layout.html
    }

    @GetMapping("/qdl/nhanvien/xoa/ajax/modal")
    public String getXoaAjaxModal(Model model, @RequestParam(value = "id") int id, HttpServletRequest request) {
        if (Qdl.NhanVienChuaDangNhap(request))
            return "redirect:/qdl/nhanvien/dangnhap";

        NhanVien dl = dvl.xemNhanVien(id);
        model.addAttribute("dl", dl);
        return "modal-bs4.html"; // Load modal HTML
    }

    @PostMapping("/qdl/nhanvien/xoa")
    public String postXoa(Model model, @RequestParam("id") int id, HttpServletRequest request, HttpSession session) {
        if (Qdl.NhanVienChuaDangNhap(request))
            return "redirect:/qdl/nhanvien/dangnhap";

        this.dvl.xoa(id);

        session.setAttribute("message", "Đã hoàn tất việc xóa !");
        return "redirect:/qdl/nhanvien";
    }

    @GetMapping("/qdl/nhanvien/xem")
    public String getXem(Model model, @RequestParam(value = "id") int id) {
        NhanVien dl = dvl.xemNhanVien(id);
        model.addAttribute("dl", dl);
        model.addAttribute("content", "nhanvien/xem.html");
        return "layout/layout.html"; // layout.html
    }

    @GetMapping("/qdl/nhanvien/xem-ajax/{id}")
    public String getMethodName(Model model, @RequestParam(value = "id") int id, HttpServletRequest request) {
        if (Qdl.NhanVienChuaDangNhap(request))
            return "redirect:/qdl/nhanvien/dangnhap";
        var dl = dvl.xemNhanVien(id);
        model.addAttribute("dl", dl);
        return "nhanvien/xem.html";
    }

    // form đăng ký tk nhanvien
    @PostMapping("/qdl/nhanvien/them")
    public String postThem(@ModelAttribute("NhanVien") NhanVien dl, RedirectAttributes redirectAttributes) {

        // Cách làm hệ thống: Spring Security
        // mã hóa mật khẩu trước khi lưu
        // var password_encoded = passwordEncoder.encode(dl.getMatKhau());
        // dl.setMatKhau(password_encoded);
        // dl.setMatKhau(passwordEncoder.encode(dl.getMatKhau()));

        // var password = "123abc";
        // Lấy mật khẩu trên HTML Form
        var inputPassword = dl.getMatKhau();
        // Mã hóa
        var hash = BCrypt.hashpw(inputPassword, BCrypt.gensalt(12));
        dl.setMatKhau(hash);

        dl.setNgayTao(LocalDate.now());
        dl.setNgaySua(LocalDate.now());

        // rồi mới lưu vào csdl
        dvl.lưuNhanVien(dl);

        // Gửi thông báo thành công từ view Add/Edit sang view List
        redirectAttributes.addFlashAttribute("THONG_BAO_OK", "Đã thêm mới thành công !");

        return "redirect:/nhanvien/duyet";
    }

    @GetMapping("/qdl/nhanvien/dangnhap")
    public String getDangNhap(Model model, HttpSession session) {

        System.out.println("\n uri before login: " + (String) session.getAttribute("URI_BEFORE_LOGIN"));
        model.addAttribute("dl", new NhanVien());
        // Lấy ra bản ghi theo id
        model.addAttribute("content", "nhanvien/dangnhap.html");

        return "layout/dangnhap.html";
    }
    // @PostMapping("/qdl/nhanvien/dangnhap")
    // public String postDangNhap(@ModelAttribute NhanVien nhanVien, HttpSession
    // session) {
    // // Xác thực nhân viên
    // if (nhanVienHopLe(nhanVien) && nhanVien.getId() != null) {
    // session.setAttribute("NhanVien_Id", nhanVien.getId());
    // String uriBeforeLogin = (String) session.getAttribute("URI_BEFORE_LOGIN");
    // if (uriBeforeLogin != null) {
    // session.removeAttribute("URI_BEFORE_LOGIN");
    // return "redirect:" + uriBeforeLogin;
    // }
    // // return "redirect:/trang-chu";
    // // ... phần còn lại của mã
    // }
    // // Xử lý đăng nhập thất bại
    // return "redirect:/qdl/nhanvien";

    // @PostMapping("/qdl/nhanvien/dangnhap")
    // public String postDangNhap(Model model,
    // RedirectAttributes redirectAttributes,
    // @RequestParam("TenDangNhap") String TenDangNhap,
    // @RequestParam("MatKhau") String MatKhau,
    // HttpServletRequest request,
    // HttpSession session) {

    // // đừng viết là dl.getMatKhau() vì nó sẽ chết !

    // String old_password = null;
    // // var old_dl = dvl.tìmNhanVienTheoTenDangNhap(TenDangNhap);
    // if// Nếu tồn tại tên đăng nhập trong csdl
    // // (old_dl!=null)// chạy OK
    // (dvl.tồnTạiTênĐăngNhập(TenDangNhap)) {
    // var old_dl = dvl.tìmNhanVienTheoTenDangNhap(TenDangNhap);
    // System.out.println(old_dl.getTenDangNhap());
    // old_password = old_dl.getMatKhau();
    // // So sánh mật khẩu trên Form và trong MySQL
    // // xem có khớp không
    // var mật_khẩu_khớp = BCrypt.checkpw(MatKhau, old_password);

    // if// nếu
    // (mật_khẩu_khớp) {
    // System.out.println("\n Đúng tài khoản, đăng nhập thành công");
    // // Gửi thông báo thành công từ view Add/Edit sang view List
    // // redirectAttributes.addFlashAttribute("THONG_BAO_OK", "Đã đăng nhập thành
    // công
    // // ! Xin chào"+old_dl.getTenDayDu());

    // // Lưu danh tính của nhân viên đăng nhập vào session
    // // request.getSession().setAttribute("USER_LOGGED",
    // // old_dl.getId());
    // request.getSession().setAttribute("NhanVien_Id",
    // old_dl.getId());
    // request.getSession().setAttribute("NhanVien_TenDayDu",
    // old_dl.getTenDayDu());

    // } else {
    // System.out.println("\n Sai mật khẩu");
    // // Gửi thông báo thành công từ view Add/Edit sang view List
    // redirectAttributes.addFlashAttribute("THONG_BAO_OK", "Sai mật khẩu !");
    // return "redirect:/qdl/nhanvien/loidangnhap";
    // }
    // } else {
    // System.out.println("\n Không tồn tại tên đăng nhập");
    // // Gửi thông báo thành công từ view Add/Edit sang view List
    // redirectAttributes.addFlashAttribute("THONG_BAO_OK", "Sai tên đăng nhập !");
    // return "redirect:/qdl/nhanvien/loidangnhap";
    // }

    // @todo: Đăng nhập thành công rồi, thì
    // điều hướng sang trang trước đó
    // return response.sendRedirect(request.getHeader("Referer"));
    // return "redirect:"+request.getHeader("Referer"); // không hiệu quả
    // lỗi vì nó là:
    // http://localhost:6868/nhanvien/dangnhap
    // chứ không phải là một...
    // ------------------------------------------------------------------
    @PostMapping("/qdl/nhanvien/dangnhap")
    public String postDangNhap(Model model,
            RedirectAttributes redirectAttributes,
            @RequestParam("TenDangNhap") String TenDangNhap,
            @RequestParam("MatKhau") String MatKhau,
            HttpServletRequest request,
            HttpSession session) {

        if (dvl.tồnTạiTênĐăngNhập(TenDangNhap)) {
            var nhanVien = dvl.tìmNhanVienTheoTenDangNhap(TenDangNhap);
            if (BCrypt.checkpw(MatKhau, nhanVien.getMatKhau())) {
                session.setAttribute("NhanVien_Id", nhanVien.getId());
                session.setAttribute("NhanVien_TenDayDu", nhanVien.getTenDayDu());

                var uriBeforeLogin = (String) session.getAttribute("URI_BEFORE_LOGIN");
                if (uriBeforeLogin != null) {
                    session.removeAttribute("URI_BEFORE_LOGIN");
                    return "redirect:" + uriBeforeLogin;
                }
                return "redirect:/qdl/nhanvien";
            } else {
                redirectAttributes.addFlashAttribute("THONG_BAO_LOI", "Sai mật khẩu!");
            }
        } else {
            redirectAttributes.addFlashAttribute("THONG_BAO_LOI", "Tên đăng nhập không tồn tại!");
        }
        return "redirect:/qdl/nhanvien/dangnhap";

    }
    // var uriBeforeLogin = (String) session.getAttribute("URI_BEFORE_LOGIN");
    // if (uriBeforeLogin == null)
    // uriBeforeLogin = "/qdl/nhanvien";

    // return "redirect:" + uriBeforeLogin;
    // return "redirect:/nhanvien";

    @GetMapping("/qdl/nhanvien/loidangnhap")
    public String loiDangNhap(Model model) {

        model.addAttribute("dl", new NhanVien());
        // Lấy ra bản ghi theo id
        model.addAttribute("content", "nhanvien/loidangnhap.html");

        // ...được đặt vào bố cục chung của toàn website
        return "layout/layout.html"; // layout.html
    }

    @GetMapping("/qdl/nhanvien/dangthoat")
    public String getDangThoat(HttpServletRequest request) {

        request.getSession().invalidate();
        return "redirect:/qdl/nhanvien/dangnhap";
    }

}// end class
