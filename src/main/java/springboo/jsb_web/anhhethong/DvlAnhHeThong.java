package springboo.jsb_web.anhhethong;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DvlAnhHeThong 
//implements DviAnhHeThong
{
    @Autowired private KdlAnhHeThong kdl;// kho dữ liệu;

    // @Override 
    public List<AnhHeThong> dsAnhHeThong() 
    {
        return kdl.findAll();
    }

    public List<AnhHeThong>  duyet() 
    {
        return kdl.findAll();
    }

    // @Override 
    public List<AnhHeThong>  duyệtAnhHeThong() 
    {
        return duyet();
    }

    // @Override 
    public AnhHeThong  timTheoId(int id)
    {
        // TODO Auto-generated method stub
        // return null;

        // return kdl.findById(id);

        AnhHeThong dl = null;

        Optional<AnhHeThong> optional = kdl.findById(id);

        if// nếu
        (optional.isPresent()) // tìm thấy bản ghi trong kho
        {
            dl = optional.get();
        } else// ngược lại
        {
            //throw new RuntimeException("Không tìm thấy thú cưng ! Ko tim thay thu cung !");
        }

        return dl;

    }

    public AnhHeThong  tìmAnhHeThongTheoId(int id){
        return timTheoId(id);
    }

    // @Override 
    public AnhHeThong xem(int id)// 
    {

        AnhHeThong dl = null;

        Optional<AnhHeThong> optional = kdl.findById(id);

        if// nếu
        (optional.isPresent()) // tìm thấy bản ghi trong kho
        {
            dl = optional.get();
        } else// ngược lại
        {
            //throw new RuntimeException("Không tìm thấy thú cưng ! Ko tim thay thu cung !");
        }

        return dl;

    }

    public AnhHeThong xemAnhHeThong(int id)// 
    {
        return xem(id);
    }

    // @Override
    public void lưuAnhHeThong(AnhHeThong dl)
    {
        luu(dl);
    }

    public void luu(AnhHeThong dl)
    {
        MultipartFile file = dl.getMtFile();

        if (file != null && !file.isEmpty()) 
        {
            try {
                // được sử dụng để lấy tên gốc của file mà người dùng đã tải lên
                String fileName = file.getOriginalFilename();
                String uploadDir = "src/main/resources/static/anhhethong/";
       
                // kiểm tra thu mục tồn tại hay không nếu chưa có thì nó sẽ tạo thư mục đó
                if (!Files.exists(Paths.get(uploadDir))) 
                {
                    Files.createDirectories(Paths.get(uploadDir));
                }

                String filePath = uploadDir + UUID.randomUUID().toString() + "_" + fileName;
               
                //  sao chép nội dung của file ảnh từ  MultipartFile  vào đường dẫn được chỉ định trong biến  filePath
                Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
       
               
                // được sử dụng để lấy tên file sau khi đã được lưu vào thư mục đích. Cụ thể:
                String savedFileName = filePath.substring(filePath.lastIndexOf("/") + 1);
                // anh san pham được lưu vào đây src/main/resoures/static/images/sanpham/
                dl.setDuongDan("/anhhethong/" + savedFileName);

                // ví dụ:
                // sanpham\65063fe8-cccc-42be-92da-5b54c944e086_tải xuống.jpg
                // C:\Users\Public\Code\Java\java_spring_qlbh_k35dl_2024.04.09_16h00_OK_upload_Ảnh\java_spring_qlbh_k35dl\src\main\resources\static\images\sanpham\65063fe8-cccc-42be-92da-5b54c944e086_tải xuống.jpg
                // http://localhost:6868/anhhethong/03a512b6-4252-46df-9d6d-683f3232ca71_iphone15promax-vidu.png
            } catch (IOException e) {
                // Xử lý lỗi nếu có
            }
        }

        // Lưu Ảnh Hệ Thống vào csdl
        this.kdl.save(dl);
    }

    // @Override
    public void xóaAnhHeThong(int id)
    {
        // TODO Auto-generated method stub
        this.kdl.deleteById(id);
    }

    public void xoaId(int id)
    {
        this.kdl.deleteById(id);
    }

}


