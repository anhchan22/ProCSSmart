# ProCSSmart - Ứng Dụng Quản Lý Chi Tiêu Câu Lạc Bộ  

## 📌 Giới thiệu  
**ProCSSmart** là một ứng dụng hỗ trợ quản lý thu chi cho các câu lạc bộ, giúp theo dõi tình hình tài chính một cách dễ dàng và hiệu quả. Ứng dụng cung cấp các chức năng nhập liệu, thống kê, báo cáo tài chính, giúp ban quản lý có thể quản lý đóng góp của từng thành viên, kiểm soát các khoản chi tiêu và tổng hợp số dư một cách chính xác.

---

## ✨ Tính năng nổi bật  

### 🔹 1. Quản lý thu - chi  
- Nhập các khoản **thu nhập** (tiền đóng góp) và **chi tiêu** (các khoản chi của CLB).  
- Hiển thị thông tin chi tiết: **Số tiền**, **Ngày giao dịch**, **Nội dung**, **Ghi chú**.
  - **Bảng tiền vào:**
  
  <p align="center">
  <img src="https://github.com/user-attachments/assets/9129ea25-33df-4226-98d4-8b87ca89cddc" width="600">
  </p>
  
  - **Bảng tiền ra:**
  
<p align="center">
  <img src="https://github.com/user-attachments/assets/bfb4baa3-ed48-4d54-9e55-2d84839bdc74" width="600">
</p>

- **Tính toán số dư hiện tại của câu lạc bộ** = Tổng thu - Tổng chi.

<p align="center">
  <img src="https://github.com/user-attachments/assets/59a240cf-2c37-49fb-b509-71cfb2166ebf" width="600">
</p>

- Dữ liệu được **tự động lưu** sau mỗi thao tác thêm, sửa, xóa.

### 🔹 2. Quản lý đóng góp của thành viên  
- Theo dõi số tiền **mỗi thành viên đóng góp theo từng tháng**.  
- Dữ liệu hiển thị dưới dạng **bảng**, dễ dàng chỉnh sửa.  
- Khi thành viên chưa đóng tiền, ô tương ứng sẽ để trống.

<p align="center">
  <img src="https://github.com/user-attachments/assets/0f3dd4d5-623c-4cbd-b86d-d1369619af45" width="600">
</p>

### 🔹 3. Thống kê & Báo cáo tài chính  
- **Thống kê tổng số tiền thu - chi** trong tháng.  
- **Hiển thị số dư hiện tại của câu lạc bộ**.  
- Tự động **tính toán tiền dư** sau chi tiêu.  
- Biểu đồ **PieChart trực quan**, giúp dễ dàng nắm bắt tình hình tài chính.
  
<p align="center">
  <img src="https://github.com/user-attachments/assets/bbe290ee-99f0-4b7f-95b2-50da18a5bb2e" width="600">
</p>

### 🔹 4. Hệ thống đăng nhập  
- **Tài khoản quản lý** có quyền truy cập vào hệ thống.  
- Đảm bảo chỉ người có quyền mới được phép chỉnh sửa dữ liệu tài chính.
  
<p align="center">
  <img src="https://github.com/user-attachments/assets/6cd75718-809f-46d4-8e03-749291001c82" width="600">
</p>

### 🔹 5. Lưu trữ dữ liệu bền vững  
- Dữ liệu được lưu vào file bằng **ObjectOutputStream**, đảm bảo không bị mất sau khi đóng ứng dụng.  
- Mỗi khi mở lại ứng dụng, dữ liệu cũ sẽ được **tải lại đầy đủ**.  

---

## 🛠 Công nghệ sử dụng  
| Công nghệ | Mô tả |
|-----------|-------|
| **JavaFX** | Xây dựng giao diện người dùng |
| **FXML** | Thiết kế UI với file FXML |
| **ObjectOutputStream** | Lưu trữ và đọc dữ liệu từ file |
| **Java Collections** | Quản lý dữ liệu thu chi bằng List, HashMap |
| **JavaFX TableView** | Hiển thị dữ liệu thu chi dưới dạng bảng |
| **JavaFX PieChart** | Trực quan hóa dữ liệu bằng biểu đồ |

---

## 🏗 Cách sử dụng  
1. **Mở ứng dụng** và đăng nhập bằng tài khoản  
2. **Nhập các khoản thu/chi** trong tab tương ứng  
3. **Xem thống kê** số tiền thu của từng thành viên theo tháng  
4. **Xem báo cáo tài chính** tổng hợp bằng biểu đồ trực quan  
5. **Đăng xuất** khi hoàn tất
   
## ⚒ Cài đặt  
#### Yêu cầu hệ thống:  
- **Java JDK 11+**  
- IDE hỗ trợ JavaFX (IntelliJ IDEA, Eclipse, NetBeans)  

#### Các bước cài đặt:  
1. **Clone repository** hoặc tải mã nguồn về máy  
   ```bash
   git clone https://github.com/your-repo/ProCSSmart.git

2. Mở bằng IDE (IntelliJ, Eclipse, NetBeans)

3. Chạy Main.java để khởi động ứng dụng
