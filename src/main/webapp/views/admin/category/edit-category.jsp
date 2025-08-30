<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Chỉnh sửa danh mục</title>
  <style>
    :root{--bg:#f5f7fb;--card:#fff;--border:#e9e9e9;--muted:#666;--title:#f0142f;--btn:#1777ff}
    *{box-sizing:border-box}
    body{margin:0;font-family:Arial,Helvetica,sans-serif;background:var(--bg);color:#222}
    .wrap{max-width:980px;margin:28px auto;background:var(--card);border:1px solid var(--border);
          border-radius:14px;box-shadow:0 2px 10px rgba(0,0,0,.06);overflow:hidden}
    .hd{padding:14px 18px;background:#f7f7f7;border-bottom:1px solid var(--border);font-weight:700}
    .body{padding:22px}
    h1{margin:0 0 6px;color:var(--title);font-size:28px}
    .hint{margin:0 0 18px;color:var(--muted)}
    .row{display:grid;grid-template-columns:1fr;gap:18px;margin-top:8px}
    .field label{display:block;margin:0 0 8px;font-weight:700}
    .text{width:100%;padding:11px 12px;border:1px solid #ddd;border-radius:10px}
    .avatar{display:flex;align-items:center;gap:16px}
    .thumb{width:120px;height:120px;border-radius:60px;object-fit:cover;border:1px solid var(--border)}
    .file{margin-top:8px}
    .actions{display:flex;gap:10px;margin-top:16px}
    .btn{padding:10px 18px;border:none;border-radius:10px;cursor:pointer}
    .btn-primary{background:var(--btn);color:#fff}
    .btn-secondary{background:#e5e7eb}
    .back{margin-left:auto;text-decoration:none;color:#0a66ff;align-self:center}
  </style>
</head>
<body>

<div class="wrap">
  <div class="hd">Chỉnh sửa danh mục</div>
  <div class="body">
    <h1>Danh mục</h1>
    <p class="hint">Cập nhật tên và ảnh đại diện cho danh mục của bạn.</p>

    <!-- Controller phải setAttribute("cate", category) -->
    <form action="<c:url value='/admin/category/edit'/>" method="post" enctype="multipart/form-data">
      <!-- ID để update -->
      <input type="hidden" name="id" value="${cate.cateid}">
      <!-- Giữ icon cũ nếu không chọn ảnh mới -->
      <input type="hidden" name="oldIcon" value="${cate.icon}">

      <div class="row">
        <div class="field">
          <label for="name">Tên danh sách:</label>
          <input id="name" class="text" type="text" name="name" value="${cate.catename}" placeholder="Nhập tên danh mục">
        </div>

        <div class="field">
          <label>Ảnh đại diện</label>
          <div class="avatar">
            <c:url value="/image?fname=${cate.icon}" var="imgUrl"/>
            <img id="preview" class="thumb" src="${imgUrl}" alt="Ảnh danh mục">
            <div>
              <input class="file" type="file" name="icon" accept="image/*" onchange="previewImg(event)">
              <div style="font-size:12px;color:#777;margin-top:6px">Chọn ảnh mới để thay thế (tùy chọn)</div>
            </div>
          </div>
        </div>
      </div>

      <div class="actions">
        <button type="submit" class="btn btn-primary">Edit</button>
        <button type="reset" class="btn btn-secondary">Reset</button>
        <a class="back" href="<c:url value='/admin/category/list'/>">← Quay lại danh sách</a>
      </div>
    </form>
  </div>
</div>

<script>
  function previewImg(e){
    const file = e.target.files && e.target.files[0];
    if(!file) return;
    const url = URL.crea
