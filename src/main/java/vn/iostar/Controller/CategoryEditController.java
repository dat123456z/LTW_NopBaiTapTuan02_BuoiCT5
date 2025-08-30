package vn.iostar.Controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.core.FileUploadException;
import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload;

import vn.iostar.Constant;
import vn.iostar.models.Category;
import vn.iostar.service.CategoryService;
import vn.iostar.service.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = { "/admin/category/edit" })
public class CategoryEditController extends HttpServlet {
    private final CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("id");
        if (sid == null) { resp.sendError(400, "Missing id"); return; }
        int id = Integer.parseInt(sid);
        Category category = cateService.get(id);
        if (category == null) { resp.sendError(404, "Category not found"); return; }
        req.setAttribute("cate", category); 
        req.getRequestDispatcher("/views/admin/category/edit-category.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (!JakartaServletFileUpload.isMultipartContent(req)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Form must be multipart/form-data");
            return;
        }

        Category incoming = new Category();
        String oldIcon = null;

        // Cấu hình upload
        DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
        JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);

        File uploadDir = new File(Constant.DIR, "category");
        if (!uploadDir.exists()) uploadDir.mkdirs();

        try {
            List<FileItem> items = upload.parseRequest(req);

            for (FileItem item : items) {
                if (item.isFormField()) {
                    String field = item.getFieldName();
                    // ĐỌC UTF-8 để không lỗi rỗng/ký tự
                    String value = item.getString(java.nio.charset.StandardCharsets.UTF_8);

                    switch (field) {
                    case "id":
                        if (value != null && !value.isBlank()) {
                            incoming.setCateid(Integer.parseInt(value.trim()));
                        }
                        break;
                    case "name":                        // ← đây chính là mục b mình nói
                        incoming.setCatename(value);
                        break;
                    case "oldIcon":
                        oldIcon = value;
                        break;
                }

                } else if ("icon".equals(item.getFieldName()) && item.getSize() > 0) {
                    // Tên file sạch (loại bỏ đường dẫn)
                    String original = item.getName();
                    int slash = Math.max(original.lastIndexOf('/'), original.lastIndexOf('\\'));
                    String base = (slash >= 0) ? original.substring(slash + 1) : original;

                    int dot = base.lastIndexOf('.');
                    String ext = (dot >= 0) ? base.substring(dot) : "";
                    String fileName = System.currentTimeMillis() + ext;

                    File saved = new File(uploadDir, fileName);
                    item.write(saved.toPath()); // FileUpload v2: write(Path)
                    incoming.setIcon("category/" + fileName);
                }
            }
            System.out.println("DEBUG >>> incoming id = " + incoming.getCateid());
            System.out.println("DEBUG >>> incoming name = " + incoming.getCatename());

            // Lấy record cũ => cập nhật an toàn
            Category db = cateService.get(incoming.getCateid());
            if (db == null) { resp.sendError(404, "Category not found"); return; }

            // name bắt buộc
            if (incoming.getCatename() == null || incoming.getCatename().isBlank()) {
                resp.sendError(400, "Name is required");
                return;
            }

            db.setCatename(incoming.getCatename());
            // Nếu không upload ảnh mới, giữ icon cũ (ưu tiên oldIcon từ form)
            if (incoming.getIcon() != null && !incoming.getIcon().isBlank()) {
                db.setIcon(incoming.getIcon());
            } else if (oldIcon != null && !oldIcon.isBlank()) {
                db.setIcon(oldIcon);
            } // else: giữ db.getIcon() nguyên

            cateService.edit(db);
            resp.sendRedirect(req.getContextPath() + "/admin/category/list");

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            resp.sendError(400, "Invalid form data: " + e.getMessage());
        } catch (FileUploadException e) {
            e.printStackTrace();
            resp.sendError(400, "Upload failed");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(500, "Server error");
        }
    }
}
