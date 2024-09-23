package springboo.jsb_web.quangcao;

import java.time.LocalDate;

//package Tên_Công_Ty.Tên_Dự_Án.qdl;

// Thư viện chuẩn: Java Standard (JavaSE)
import java.util.List;

// Thư viện web: Java Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Thư viện lập trình Java Web do lập trình viên tự phát triển:
// import Tên_Công_Ty.Tên_Dự_Án.dvl.DvlQuangCao;
// import Tên_Công_Ty.Tên_Dự_Án.tdl.QuangCao;


/**
 * User performs Actions on UI/View
 * Client Computer sends Http Requets -> Server Computer
 * (Http Requets: GET, POST, PUT, DELETE)
 * Server uses Url Routes Mapping Table to forward
 * requests to Spring Controllers
 * Each Controller itself forward requets to its Actions
 * Client có nhiều yêu cầu & truy vấn thông qua giao thức Http Get/Post
 * Server có nhiều Controller phục vụ, đáp ứng
 * Lập trình viên cần định tuyến cho rõ ràng:
 * Controller (Action) nào phục vụ Request(Get/Post) nào ?
 * Đừng thay đổi @Mapping, mà chỉ nên thay đổi Action Name
 * 
 * View Actions: List, Add, Delete, Edit, Details (LADE)
 * Data Actions: List, Create, Delete, Update, Read (CRUD)
 * SQL Actions: Select, Insert, Delete, Update (SIDU)
 */

@Controller
public class QdlQuangCao 
{
    @Autowired
    private DvlQuangCao dvl; // cung cấp các dịch vụ thao tác dữ liệu

    //@Autowired
    //private DvlBảngNgoại dvlBangNgoai; // cung cấp các dịch vụ thao tác dữ liệu

    
    @GetMapping({
            "/qdl/quangcao",
            "/qdl/quangcao/duyet"
    })
    public String getDuyet(Model model) 
    {
        // Đọc dữ liệu bảng rồi chứa vào biến tạm
        List<QuangCao> list = dvl.duyệtQuangCao();

        // Gửi danh sách sang giao diện View HTML
        model.addAttribute("ds", list);

        // Nội dung riêng của trang...
        model.addAttribute("content", "quangcao/duyet.html"); // duyet.html

        // ...được đặt vào bố cục chung của toàn website
         return "layout/layout-admin.html";
    }

    @GetMapping("/qdl/quangcao/them")
    public String getThem(Model model) {
        QuangCao dl = new QuangCao();

        // Gửi đối tượng dữ liệu sang bên view
        // để còn ràng buộc vào html form
        model.addAttribute("dl", dl);

        // Nội dung riêng của trang...
        model.addAttribute("content", "quangcao/them.html"); 
//        model.addAttribute("dsBangNgoai", this.dvlBangNgoai.dsBangNgoai());

        // ...được đặt vào bố cục chung của toàn website
         return "layout/layout-admin.html";
    }

    // @GetMapping("/qdl/quangcao/sua/{id}")
    @GetMapping("/qdl/quangcao/sua")
    public String getSua(Model model, @RequestParam("id") int id) {
        // trangSua(Model model, @PathVariable(value = "id") int id) {
        // Lấy ra bản ghi theo id
        QuangCao dl = dvl.xemQuangCao(id);

        // Gửi đối tượng dữ liệu sang bên view
        model.addAttribute("dl", dl);
//        model.addAttribute("dsBangNgoai", this.dvlBangNgoai.dsBangNgoai());

        // Hiển thị giao diện view html
        // Nội dung riêng của trang...
        model.addAttribute("content", "quangcao/sua.html"); // sua.html

        // ...được đặt vào bố cục chung của toàn website
         return "layout/layout-admin.html";// layout.html
    }

    // @GetMapping("/qdl/quangcao/xoa/{id}")
    // public String // Giao diện xác nhận xoá
    // trangXoa(Model model, @PathVariable(value = "id") int id) {
    @GetMapping("/qdl/quangcao/xoa")
    public String getXoa(Model model, @RequestParam(value = "id") int id) {
        // Lấy ra bản ghi theo id
        QuangCao dl = dvl.tìmQuangCaoTheoId(id);

        // Gửi đối tượng dữ liệu sang bên view
        model.addAttribute("dl", dl);

        // Hiển thị view giao diện
        // Nội dung riêng của trang...
        model.addAttribute("content", "quangcao/xoa.html"); // xoa.html

        // ...được đặt vào bố cục chung của toàn website
         return "layout/layout-admin.html";// layout.html
    }

    @GetMapping("/qdl/quangcao/xem/{id}")
    public String getXem(Model model, @PathVariable(value = "id") int id) 
    {
        // Lấy ra bản ghi theo id
        QuangCao dl = dvl.xemQuangCao(id);

        // Gửi đối tượng dữ liệu sang bên view
        model.addAttribute("dl", dl);

        // Hiển thị view giao diện
        // Nội dung riêng của trang...
        model.addAttribute("content", "quangcao/xem.html"); 

        // ...được đặt vào bố cục chung của toàn website
         return "layout/layout-admin.html";   // layout.html
    }

    // nên là createAction()
    // https://javarevisited.blogspot.com/2022/04/how-to-use-session-attributes-in-spring.html#axzz89tnfpu78
    // https://stackoverflow.com/questions/55882706/how-to-remove-attribute-from-session-using-thymeleaf
    // https://stackoverflow.com/questions/46744586/thymeleaf-show-a-success-message-after-clicking-on-submit-button
    // @PostMapping("/qdl/quangcao/add") why not ??? it's okay right ?
    @PostMapping("/qdl/quangcao/them")
    public String postThem(@ModelAttribute("QuangCao") QuangCao dl, RedirectAttributes redirectAttributes) {
        // System.out.print("save action...");
        dl.setNgayTao(LocalDate.now());
        dl.setNgaySua(LocalDate.now());
        dvl.lưuQuangCao(dl);

        // Gửi thông báo thành công từ view Add/Edit sang view List
        redirectAttributes.addFlashAttribute("THONG_BAO_OK", "Đã thêm mới thành công !");

        return "redirect:/qdl/quangcao/duyet";
    }

    // How to send success message to List View
    // https://www.appsloveworld.com/springmvc/100/17/how-to-add-success-notification-after-form-submit
  
    @PostMapping("/qdl/quangcao/sua")
    public String postSua(@ModelAttribute("QuangCao") QuangCao dl, RedirectAttributes redirectAttributes) {
        
        dl.setNgaySua(LocalDate.now());
        dvl.lưuQuangCao(dl);

        // Gửi thông báo thành công từ view Add/Edit sang view List
        redirectAttributes.addFlashAttribute("THONG_BAO_OK", "Đã sửa thành công !");

        return "redirect:/qdl/quangcao/duyet";
    }

    // @PostMapping("/qdl/quangcao/xoabo/{id}")
    // public String // Hàm xử lý yêu cầu xoá 1 bản ghi
    //         xoabo(Model model, @PathVariable(value = "id") int id) 
    
    @PostMapping("/qdl/quangcao/xoa")
    public String postXoa(Model model, @RequestParam("id") int id) // request param phải khớp với name="id" của thẻ html input
    {
        // Xoá dữ liệu
        this.dvl.xóaQuangCao(id);

        // Điều hướng sang trang giao diện
        return "redirect:/qdl/quangcao/duyet";
    }



}// end class
