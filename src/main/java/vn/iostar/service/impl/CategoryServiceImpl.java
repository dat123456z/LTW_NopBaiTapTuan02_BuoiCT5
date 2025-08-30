package vn.iostar.service.impl;

import java.io.File;
import java.util.List;

import vn.iostar.dao.CategoryDao;
import vn.iostar.dao.impl.CategoryDaoImpl;
import vn.iostar.models.Category;
import vn.iostar.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	CategoryDao categoryDao = new CategoryDaoImpl();

	@Override
	public void insert(Category category) {
		categoryDao.insert(category);
	}

//	@Override
//	public void edit(Category newCategory) {
//		Category oldCate = categoryDao.get(newCategory.getCateid());
//		oldCate.setName(newCategory.getName());
//		categoryDao.edit(oldCate);
//	}

	@Override
	public void delete(int id) {
		categoryDao.delete(id);
	}

	@Override
	public Category get(int id) {
		return categoryDao.get(id);
	}

	@Override
	public void edit(Category newCategory) {
		Category oldCategory = categoryDao.get(newCategory.getCateid());
		oldCategory.setCatename(newCategory.getCatename());
		if (newCategory.getIcon() != null) {
			// XOA ANH CU DI
			String fileName = oldCategory.getIcon();
			final String dir = "E:\\upload";
			File file = new File(dir + "/category" + fileName);
			if (file.exists()) {
				file.delete();
			}
			oldCategory.setIcon(newCategory.getIcon());
		}
		categoryDao.edit(oldCategory);
	}
	
	@Override
	public Category get(String name) {
	return categoryDao.get(name);
	}
	@Override
	public List<Category> getAll() {
	return categoryDao.getAll();
	}
	@Override
	public List<Category> search(String catename) {
	return categoryDao.search(catename);
	}


}
