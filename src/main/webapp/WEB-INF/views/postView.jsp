<%--
  Created by IntelliJ IDEA.
  User: brusoth
  Date: 4/23/16
  Time: 12:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
    <script>tinymce.init({ selector:'textarea' });</script>

</head>
<body >
<div class="container">
    <div id="wrapper">

        <!-- Sidebar -->
        <div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <li class="sidebar-brand">
                    <a href="#">
                        <div class="login-logo">
                            <a href="/login"><b>Simple</b>CMS</a>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="/post/create">Create Post</a>
                </li>
                <li>
                    <a href="/post/view">View Posts</a>
                </li>

            </ul>
        </div>
        <!-- /#sidebar-wrapper -->

        <!-- Page Content -->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                            <br>
                            <%--<div id="cover-image" style="background-image: url(${post.image_path})">--%>
                            <h1>${post.title}</h1>
                            <%--</div>--%>
                            <h3>${post.content}</h3>
                            <h3>${post.status}</h3>
                        <img id="ItemPreview" src="data:image/png;base64,<c:out value='${post.imageSrc}'/>" />

                    </div>
                </div>
            </div>
        </div>
        <!-- /#page-content-wrapper -->
    </div>
</div><

<script>
    <%--var image = "<c:out value='${post.imageSrc}'/>";--%>
//    document.getElementById("ItemPreview").src = "data:image/png;base64," +image;

</script>
</body>
</html>
