package springboo.jsb_web.qdl;


import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import springboo.jsb_web.caidat.DvlCaiDat;




// Lớp này dùng để khởi tạo các dữ liệu toàn cục
// được chia sẻ chung bởi các trang *.html
// ví dụ: email, phone, web name, ...
@ControllerAdvice
public class Qdl
{

    /**
     * Kiểm tra xem nhân viên đăng nhập hay chưa ?
     * (Id của NhanVien không tồn tại trong Session)
     * Đánh dấu địa chỉ đường dẫn URI mà người dùng đang cố truy cập.
     * 
     * @param session
     * @param request
     * @return
     */
    // public static Boolean 
    // NhanVienChuaDangNhap(Object request) 
    // {
    //     if// nếu danh tính không xác định
    //     (((HttpServletRequest) request).getSession().getAttribute("NhanVien_Id") == null) 
    //     {
    //         // thì Đánh dấu nội trang mà người dùng muốn truy cập nhưng bị chặn
    //         ((HttpServletRequest) request).getSession().setAttribute("URI_BEFORE_LOGIN",
    //          ((HttpServletRequest) request).getRequestURI());

    //         // rồi điều hướng sang trang đăng nhập
    //         return true;

    //     }
        

    //     return false;
    // }

    public static boolean NhanVienChuaDangNhap(HttpServletRequest request) {
        if (request == null) {
            return true; // hoặc xử lý phù hợp
        }
        HttpSession session = request.getSession();
        if (session.getAttribute("NhanVien_Id") == null) {
            session.setAttribute("URI_BEFORE_LOGIN", request.getRequestURI());
            return true;
        }
        return false;
    }

}

