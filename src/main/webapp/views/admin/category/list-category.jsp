<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${empty requestScope.__fromController}">
  <c:redirect url="/admin/category/list"/>
</c:if>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Quản lý danh mục</title>
<style> :root { --blue: #0aa3ff; --blueDark: #0486d6; --red: #ff4d4f; --text: #222 } * { box-sizing: border-box } body { margin: 0; font-family: Arial, Helvetica, sans-serif; background: #f5f7fb; color: var(--text) } .layout { display: grid; grid-template-columns: 260px 1fr; min-height: 100vh } /* SIDEBAR */ .sidebar { background: linear-gradient(180deg, var(--blue), var(--blueDark)); color: #fff; padding: 20px 16px } .sb-title { font-size: 28px; font-weight: 700; margin: 4px 0 18px } .profile { display: flex; align-items: center; gap: 12px; margin: 6px 0 18px } .avatar { width: 64px; height: 64px; border-radius: 50%; object-fit: cover; border: 3px solid #fff; box-shadow: 0 2px 6px rgba(0, 0, 0, .2) } .role { opacity: .9 } .menu { margin-top: 8px } .menu a { display: flex; align-items: center; gap: 10px; padding: 12px 14px; margin: 6px 0; border-radius: 12px; color: #fff; text-decoration: none } .menu a.active { background: #ff2d2f } .submenu { margin-left: 14px; border-left: 2px solid rgba(255, 255, 255, .3); padding-left: 10px } .submenu a { background: transparent } .submenu a:hover { background: rgba(255, 255, 255, .15) } /* TOPBAR */ .topbar { display: flex; justify-content: flex-end; align-items: center; background: #fff; padding: 12px 18px; box-shadow: 0 2px 6px rgba(0, 0, 0, .06) } .hello { margin-right: auto; font-weight: 600; color: #1363df } .btn-logout { background: #ff5b57; color: #fff; border: none; padding: 10px 16px; border-radius: 10px; cursor: pointer } /* CONTENT */ .content { padding: 22px } .page { max-width: 980px; margin: 0 auto; background: #fff; border-radius: 14px; box-shadow: 0 2px 10px rgba(0, 0, 0, .06) } .page-header { padding: 20px 22px; border-bottom: 1px solid #eee } .page-header h1 { margin: 0; font-size: 28px; color: #f0142f } .page-header p { margin: 6px 0 0; color: #666 } .page-body { padding: 22px } .panel { border: 1px solid #e9e9e9; border-radius: 10px; overflow: hidden } .panel-hd { background: #f7f7f7; padding: 12px 14px; color: #555; display: flex; align-items: center; gap: 10px } .panel-body { padding: 0 } table { width: 100%; border-collapse: collapse } th, td { padding: 14px 12px; border-top: 1px solid #eee; vertical-align: middle } th { background: #fafafa; text-align: left; color: #555 } .img-td img { width: 90px; height: 90px; border-radius: 50%; object-fit: cover; border: 1px solid #eee } .actions a { color: #0a66ff; text-decoration: none; margin-right: 10px } .actions a.delete { color: #ff4d4f } .toolbar { display: flex; justify-content: space-between; align-items: center; padding: 10px 12px; border-top: 1px solid #eee; border-bottom: 1px solid #eee; background: #fff } select, input[type="text"] { padding: 8px 10px; border: 1px solid #ddd; border-radius: 8px } </style>
</head>
<body>

<div class="layout">
  <!-- SIDEBAR -->
  <aside class="sidebar">
    <div class="sb-title">Dashboard</div>
    <div class="profile">
      <img class="avatar" src="https://i.pravatar.cc/100?img=3" alt="">
      <div>
        <div>Bạn là Admin</div>
        <div class="role">Quản trị</div>
      </div>
    </div>

    <nav class="menu">
      <a href="<c:url value='/admin/home'/>">Dashboard</a>
      <a href="<c:url value='/admin/category/list'/>">Danh sách danh mục</a>
      <div class="submenu">
        <a href="<c:url value='/admin/category/list'/>">Danh sách danh mục</a>
<a href="<c:url value='/admin/category/add'/>">– Thêm danh mục mới</a>

      </div>
      <a href="<c:url value='/admin/product/list'/>">Quản lý sản phẩm</a>
      <a href="<c:url value='/admin/user/list'/>">Quản lý tài khoản</a>
    </nav>
  </aside>

  <!-- MAIN -->
  <main>
    <div class="topbar">
      <div class="hello">Xin chào <strong>Nguyễn Quốc Đạt</strong></div>
      <form action="<c:url value='/logout'/>" method="post">
        <button class="btn-logout" type="submit">Đăng xuất</button>
      </form>
    </div>

    <div class="content">
      <section class="page">
        <div class="page-header">
          <h1>Quản lý danh mục</h1>
          <p>Nơi bạn có thể quản lý danh mục của mình</p>
        </div>

        <div class="page-body">
          <div class="panel">
            <div class="panel-hd">Danh sách danh mục</div>

            <div class="toolbar">
              <div>
                <label>
                  <select>
                    <option>10</option><option>25</option><option>50</option>
                  </select> records per page
                </label>
              </div>
              <div>
                <label>Search:
                  <input type="text" placeholder="Tìm theo tên...">
                </label>
              </div>
            </div>

            <div class="panel-body">
              <table>
                <thead>
                  <tr>
                    <th style="width:70px">STT</th>
                    <th style="width:180px">Hình ảnh</th>
                    <th>Tên danh mục</th>
                    <th style="width:160px">Hành động</th>
                  </tr>
                </thead>
                <tbody>
                  <c:if test="${empty cateList}">
                    <tr>
                      <td colspan="4" style="text-align:center;color:#888;padding:20px">
                        Chưa có danh mục nào.
                      </td>
                    </tr>
                  </c:if>

                  <c:forEach items="${cateList}" var="cate" varStatus="st">
                    <tr>
                      <td>${st.index + 1}</td>
                      <td class="img-td">
                        <c:url value="/image?fname=${cate.icon}" var="imgUrl"/>
                        <img src="${imgUrl}" alt="${cate.catename}">
                      </td>
                      <td>${cate.catename}</td>
                      <td class="actions">
                        <a href="<c:url value='/admin/category/edit?id=${cate.cateid}'/>">Sửa</a>
                        <a class="delete"
                           href="<c:url value='/admin/category/delete?id=${cate.cateid}'/>"
                           onclick="return confirm('Xóa danh mục này?');">Xóa</a>
                      </td>
                    </tr>
                  </c:forEach>
                </tbody>
              </table>
            </div>

          </div>
        </div>
      </section>
    </div>

  </main>
</div>

</body>
</html>
