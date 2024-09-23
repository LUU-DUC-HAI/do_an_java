package springboo.jsb_web.sanpham;

import java.util.List;

// Thư viện web: Java Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpSession;
import lombok.var;
import springboo.jsb_web.loai.DvlLoai;
import springboo.jsb_web.loai.Loai;
import springboo.jsb_web.nhasanxuat.DvlNhaSanXuat;
import springboo.jsb_web.nhasanxuat.NhaSanXuat;
// import springboo.jsb_web.sanpham.sanpham;
import springboo.jsb_web.qdl.Qdl;

@Controller
public class QdlSanPham {

    // private static final Object request = null;
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private DvlSanPham dvl; // cung cấp các dịch vụ thao tác dữ liệu

    @Autowired
    private DvlNhaSanXuat DvlNhaSanXuat;

    @Autowired
    private DvlLoai dvlLoai;

    @GetMapping("/qdl/sanpham")
    public String getPhanTrang(Model model, HttpServletRequest request) {
        if (Qdl.NhanVienChuaDangNhap(request))
            return "redirect:/qdl/nhanvien/dangnhap";
        String sortField; // tên cột sắp xếp
        String sortDir; // sort direction, chiều sắp xếp: asc, desc
        String sortRev; // sort reversion, đảo chiều sắp xếp
        int pageNumber; // current page number, no: số thứ tự của trang hiện tại
        int pageSize; // kích thước của mỗi trang (số phần tử (tối đa)trên mỗi trang).

        try {
            pageNumber = request.getParameter("pageNumber") == null ? 1
                    : Integer.parseInt(request.getParameter("pageNumber"));
            pageSize = request.getParameter("pageSize") == null ? 5
                    : Integer.parseInt(request.getParameter("pageSize"));
        } catch (NumberFormatException e) {
            pageNumber = 1;
            pageSize = 5;
        }

        if (pageNumber < 1)
            pageNumber = 1;
        if (pageSize < 1)
            pageSize = 5;

        sortField = request.getParameter("sortField");
        sortDir = request.getParameter("sortDir");

        if (sortField == null || sortField.trim().isEmpty()) {
            sortField = "ten";
        }

        if (sortDir == null || sortDir.trim().isEmpty()) {
            sortDir = "asc";
            sortRev = "desc";
        } else if (sortDir.equals("asc")) {
            sortRev = "desc";
        } else if (sortDir.equals("desc")) {
            sortRev = "asc";
        } else {
            sortDir = "asc";
            sortRev = "desc";
        }

        String linkSortTen = "/qdl/sanpham/phantrang?pageNumber=" + pageNumber + "&pageSize=" + pageSize
                + "&sortField=ten&sortDir=" + sortRev;
        String linkPage = "/qdl/sanpham/phantrang?sortField=" + sortField + "&sortDir=" + sortDir + "&pageSize="
                + pageSize + "&pageNumber=";

        Page<SanPham> page = dvl.getPaged(pageNumber, pageSize, sortField, sortDir);
        List<SanPham> list = page.getContent();
        List<Loai> dsLoai = dvlLoai.dsLoai();
        model.addAttribute("dsLoai", dsLoai);

        // List<Loai> dsLoai = dvlLoai.dsLoai();
        // System.out.println("Ma loai: " + dsLoai);
        List<NhaSanXuat> dsNhaSanXuat = DvlNhaSanXuat.dsNhaSanXuat();

        // model.addAttribute("dsLoai", dsLoai);
        model.addAttribute("dsNhaSanXuat", dsNhaSanXuat);

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageItems", list);
        model.addAttribute("pageCount", list.size());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("totalElements", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("sortRev", sortRev);
        model.addAttribute("linkSortTen", linkSortTen);
        model.addAttribute("linkPage", linkPage);
        model.addAttribute("ds", list);
        model.addAttribute("dl", new SanPham());
        model.addAttribute("title", "Phân Trang Sản Phẩm");
        model.addAttribute("content", "sanpham/duyet.html");

        return "layout/layout.html";
    }

    @GetMapping("/qdl/sanpham/them")
    public String getThemForm(Model model) {
        model.addAttribute("dl", new SanPham());
        return "sanpham/them"; // Tên view hiển thị form thêm sản phẩm
    }

    @GetMapping("/qdl/sanpham/them/modal")
    public String getThemModal(Model model, HttpServletRequest request) {
        if (Qdl.NhanVienChuaDangNhap(request))
            return "redirect:/qdl/sanpham/dangnhap";

        var dl = new SanPham();
        model.addAttribute("dl", dl);
        model.addAttribute("action", "/qdl/sanpham/them");
        model.addAttribute("content", "sanpham/them.html");
        return "modal-bs4.html";
    }

    @PostMapping("/qdl/sanpham/them")
    public String postThem(@ModelAttribute SanPham dl, RedirectAttributes redirectAttributes) {
        // System.out.print("save action...");
        dvl.them(dl);
        // Gửi thông báo thành công từ view Add/Edit sang view List
        redirectAttributes.addFlashAttribute("THONG_BAO_OK", "Đã thêm mới thành công!");
        return "redirect:/qdl/sanpham";
    }

    @GetMapping("/qdl/sanpham/sua/{id}")
    public String getSua(@PathVariable int id, Model model, HttpServletRequest request) {
        if (Qdl.NhanVienChuaDangNhap(request)) {
            return "redirect:/qdl/nhanvien/dangnhap";
        }

        SanPham dl = dvl.xem(id);
        if (dl == null) {
            return "redirect:/qdl/sanpham/duyet?error=khong_tim_thay_san_pham";
        }

        model.addAttribute("dl", dl);
        model.addAttribute("dsLoai", dvlLoai.dsLoai());
        model.addAttribute("dsNhaSanXuat", DvlNhaSanXuat.dsNhaSanXuat());
        model.addAttribute("title", "Sửa Sản Phẩm");
        model.addAttribute("content", "sanpham/sua.html");

        return "layout/layout";
    }

    @PostMapping("/qdl/sanpham/sua")
    public String postSua(@ModelAttribute("dl") SanPham dl, RedirectAttributes redirectAttributes) {
        try {
            dvl.sua(dl);
            redirectAttributes.addFlashAttribute("message", "Sửa sản phẩm thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi sửa sản phẩm: " + e.getMessage());
        }
        return "redirect:/qdl/sanpham";
    }

    
    @GetMapping("/qdl/sanpham/xem/{id}")
    public String getXem(@PathVariable int id, Model model, HttpServletRequest request) {
        if (Qdl.NhanVienChuaDangNhap(request)) {
            return "redirect:/qdl/nhanvien/dangnhap";
        }

        SanPham dl = dvl.xem(id);
        if (dl == null) {
            return "redirect:/qdl/sanpham/duyet?error=khong_tim_thay_san_pham";
        }

        model.addAttribute("dl", dl);
        model.addAttribute("title", "Chi tiết Sản Phẩm");
        model.addAttribute("content", "sanpham/xem.html");

        return "layout/layout";
    }

    @GetMapping("/qdl/sanpham/xem-ajax/{id}")
    public String getMethodName(Model model, @RequestParam(value = "id") int id, HttpServletRequest request) {
        if (Qdl.NhanVienChuaDangNhap(request))
            return "redirect:/qdl/sanpham/dangnhap";
        var dl = dvl.xem(id);
        model.addAttribute("dl", dl);
        return "sanpham/xem.html";
    }
   
    @GetMapping("/qdl/sanpham/xoa/{id}")
    public String getXoa(@PathVariable int id, Model model, HttpServletRequest request) {
        if (Qdl.NhanVienChuaDangNhap(request)) {
            return "redirect:/qdl/nhanvien/dangnhap";
        }

        SanPham dl = dvl.xem(id);
        model.addAttribute("dl", dl);
        model.addAttribute("title", "Xác nhận xóa Sản Phẩm");
        model.addAttribute("content", "sanpham/xoa.html");

        return "layout/layout";
    }

    @PostMapping("/qdl/sanpham/xoa")
    public String postXoa(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        try {
            dvl.xoa(id);
            redirectAttributes.addFlashAttribute("message", "Xóa sản phẩm thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi xóa sản phẩm: " + e.getMessage());
        }
        return "redirect:/qdl/sanpham";
    }

}// end class
