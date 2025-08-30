package vn.iostar.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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

@WebServlet(urlPatterns = { "/admin/category/add" })
public class CategoryAddController extends HttpServlet {
	private final CategoryService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/category/add-category.jsp");
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Phải là multipart
		if (!JakartaServletFileUpload.isMultipartContent(req)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Form must be multipart/form-data");
			return;
		}

		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		Category category = new Category();

		// FileUpload v2 (Jakarta)
		DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
		JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);

		// Thư mục lưu
		File uploadDir = new File(Constant.DIR, "category");
		if (!uploadDir.exists())
			uploadDir.mkdirs();

		try {
			List<FileItem> items = upload.parseRequest(req);

			for (FileItem item : items) {
				if (item.isFormField()) {
					if ("name".equals(item.getFieldName())) {
						// đọc UTF-8 để tránh rỗng/ký tự sai
						category.setCatename(item.getString(StandardCharsets.UTF_8));
					}
				} else if ("icon".equals(item.getFieldName())) {
					if (item.getSize() > 0) {
						// lấy tên file sạch (loại bỏ đường dẫn)
						String original = item.getName();
						if (original != null) {
							int slash = Math.max(original.lastIndexOf('/'), original.lastIndexOf('\\'));
							original = (slash >= 0) ? original.substring(slash + 1) : original;
						}
						int dot = (original == null) ? -1 : original.lastIndexOf('.');
						String ext = (dot >= 0) ? original.substring(dot) : "";
						String fileName = System.currentTimeMillis() + ext;

						File saved = new File(uploadDir, fileName);
						// v2: write(Path)
						item.write(saved.toPath());

						category.setIcon("category/" + fileName);
					}
				}
			}

			// debug log
			System.out.println("DEBUG >>> name = " + category.getCatename());
			System.out.println("DEBUG >>> icon = " + category.getIcon());

			cateService.insert(category);
			resp.sendRedirect(req.getContextPath() + "/admin/category/list");

		} catch (FileUploadException e) {
			throw new ServletException("Upload failed", e);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
