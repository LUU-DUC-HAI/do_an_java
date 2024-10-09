var cart = {
    add: function (id_sanpham, soluong, ten) {
        console.log("Đang thêm sản phẩm:", id_sanpham, soluong, ten);
        $.ajax({
            url: '/giohang/them/ajax',
            type: 'POST',
            data: {
                id_sanpham: id_sanpham,
                soluong: (typeof (soluong) != 'undefined' ? soluong : 1),
                ten: ten
            },
            dataType: 'json',
            success: function (json) {
                console.log("Phản hồi từ server:", json);
                if (json && json.success) {
                    alert(json.success);
                    $('#cart-total').text(json.total);
                    this.loadCart(); // Tải lại giỏ hàng
                    toastr.success(json.success, 'Thêm Sản Phẩm vào Giỏ OK');
                } else {
                    console.error("Phản hồi không hợp lệ từ server:", json);
                    alert("Có lỗi xảy ra khi thêm sản phẩm vào giỏ hàng.");
                }
            }.bind(this), // Bind this để sử dụng trong success
            error: function (xhr, status, error) {
                console.error("Lỗi AJAX:", status, error);
                alert("Lỗi khi gửi yêu cầu: " + error);
            }
        });
    },
    update: function (id_sanpham, so_luong, ten) {
        $.ajax({
            url: '/giohang/sua/ajax',
            type: 'POST',
            data: {
                id_sanpham: id_sanpham,
                ten: ten,
                so_luong: (typeof (so_luong) != 'undefined' ? so_luong : 1)
            },
            dataType: 'json',
            beforeSend: function () {
                $('#cart > button').button('loading');
            },
            success: function (json) {
                $('#cart-total').text(json['total']);
                $('#cart').load('/giohang/ajax/get-html'); // Tải lại giỏ hàng
                
                // Cập nhật tổng giá trị giỏ hàng
                $('#total-value').text(json['tongGiaTri']); // Cập nhật tổng giá trị hiển thị
                
                // Cập nhật tổng tiền cho sản phẩm
                var newSubtotal = (json['tongGiaTri'] / json['total']) * so_luong; // Tính tổng tiền mới
                $('#subtotal-' + id_sanpham).text(newSubtotal + ' đ'); // Cập nhật tổng tiền cho sản phẩm
            },
            error: function () {
                console.error("Lỗi AJAX:", status, error);
                alert("Lỗi khi gửi yêu cầu: " + error);
            }
        });
    },
    remove: function (id_sanpham, ten) {
        console.log("ID sản phẩm:", id_sanpham);
        console.log("Tên sản phẩm:", ten);
        $.ajax({
            url: '/giohang/xoa/ajax',
            type: 'POST',
            data: {
                id_sanpham: id_sanpham, // Đảm bảo id_sanpham là số
                ten: ten
            },
            dataType: 'json',
            beforeSend: function () {
                $('#cart > button').button('loading');
            },
            success: function (json) {
                $('#cart-total').text(json['total']);
                $('#cart').load('/giohang/ajax/get-html'); // Tải lại giỏ hàng
                toastr.success(json['success'], 'Xóa sản phẩm thành công');
            },
            error: function () {
                alert('Có lỗi xảy ra khi xóa sản phẩm.');
            }
        });
    },
    loadCart: function () {
        $.ajax({
            url: '/giohang/ajax/get-html',
            type: 'GET',
            dataType: 'html',
            success: function (data) {
                // console.log("Dữ liệu giỏ hàng:", data);
                $('#cart').html(data);
                $('#cart-total').text(data.total); // Cập nhật nội dung giỏ hàng
            },
            error: function (xhr, status, error) {
                console.error("Lỗi khi lấy giỏ hàng:", status, error);
                // alert("Có lỗi xảy ra khi tải giỏ hàng.");
            }
        });
    },
    loadCart:function(){
    $.ajax({
        // ... existing AJAX code ...
        success: function(json) {
            // ... existing success code ...
            cart.loadCart(); // Tải lại giỏ hàng
        },
        // ... existing error code ...
    });
    }
};

