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
<body>
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
                    <a href="${pageContext.request.contextPath}/post/create">Create Post</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/post/view">View Posts</a>
                </li>

            </ul>
        </div>
        <!-- /#sidebar-wrapper -->
        <!-- Page Content -->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <c:forEach var="post" items="${posts}">
                            <h1>  <c:out value="${post.title}"/> </h1>
                            <%--</div>--%>
                            <h3><c:out value="${post.content}"/> </h3>
                            <h3><c:out value="${post.status}"/> </h3>
                            <br>
                            <form action="${pageContext.request.contextPath}/post/state" method="post">
                                <input type="hidden" name="id" value="${post.blog_post_id}" readonly="readonly"/>'
                                <button type="submit" name="action" class="btn btn-primary btn-sm" value="change">change Status</button>
                            </form>


                        </c:forEach>
                            <%--<div id="cover-image" style="background-image: url(${post.image_path})">--%>

                    </div>
                </div>
            </div>
        </div>
        <!-- /#page-content-wrapper -->
    </div>
</div>
<script>
    jQuery(document).ready(function($) {
        $("#drafts-button").click(function(event) {
            savePost();
        });

        $("#preview-button").click(function(event) {
            openPreview();
        });
    });

    function openPreview() {

    }

    function savePost() {
        var data = {}
        data["title"] = $("#post-title").val();
        data["content"] = $("#post-content").val();

        $.ajax({
            type : "POST",
            contentType : "application/json",
            url : "post/add",
            data : JSON.stringify(data),
            dataType : 'json',
            timeout : 100000,
            success : function(data) {
                console.log("SUCCESS: ", data);
                display(data);
            },
            error : function(e) {
                console.log("ERROR: ", e);
                display(e);
            },
            done : function(e) {
                console.log("DONE");
            }
        });
    }
</script>
</body>
</html>
