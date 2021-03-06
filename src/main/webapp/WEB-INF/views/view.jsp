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

       <!-- Page Content -->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <c:forEach var="post" items="${posts}">
                            <div class="panel panel-default project-list">
                                <div class="panel-heading">
                                    <h3>  <c:out value="${post.title}"/></h3><span class="tag tag-default"><h6><c:out value="${post.status}"/> </h6></span>
                                    <p>Publish Date: <input type="text" id="datepicker"></p>
                                </div>
                                <%--</div>--%>
                                <div class="panel-body">
                                    <p><c:out value="${post.content}"/> </p>
                                    <br>
                                </div>
                                <div class="panel-footer">
                                    <div class="row">
                                        <form class="col-md-2" action="${pageContext.request.contextPath}/post/state" method="post">
                                            <input type="hidden" name="id" value="${post.blog_post_id}" readonly="readonly"/>
                                            <input type="hidden" name="date" id="date" value="${post.publish}">
                                            <button type="submit" name="action" class="btn btn-primary btn-sm" value="change">change status</button>
                                        </form>
                                        <form class="col-md-2" action="${pageContext.request.contextPath}/post/previews" method="post">
                                            <input type="hidden" name="id" value="${post.blog_post_id}" readonly="readonly"/>
                                            <button type="submit" name="action" class="btn btn-primary btn-sm" value="change">Preview</button>
                                        </form>
                                        <form class="col-md-2" action="${pageContext.request.contextPath}/post/edit" method="post">
                                            <input type="hidden" name="id" value="${post.blog_post_id}" readonly="readonly"/>
                                            <button type="submit" name="action" class="btn btn-primary btn-sm" value="change">Edit</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
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

    $( function() {
        var $datepicker = $('#datepicker');
        $datepicker.datepicker();
        $datepicker.datepicker('setDate', new Date());
    } );

    $("#datepicker").datepicker({
        onSelect: function (dateText) {
            $('input[name="date"]').val($("#datepicker").datepicker({ dateFormat: 'yy-mm-dd' }).val());

        }
    });

    function openPreview() {

    }

    function savePost() {
        var data = {};
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
