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
                        <form:form class="well" method="post" modelAttribute="post" action="${pageContext.request.contextPath}/post/create" enctype="multipart/form-data" >
                            <h2 id="rich-text-title">Edit Your Post</h2>
                            <form:hidden path="blog_post_id" />
                            <form:input id="post-title" path="title" placeHolder="Title" name="post-title"></form:input><br/>
                            Please select cover image to upload : <input id="post-file" type="file" name="image" />
                            <textarea name="content" id="post-content">Easy (and free!) You should check out our premium features.</textarea>
                            <div id="rich-text-btn-group" class="btn-group btn-group-sm">
                                <button id="preview-button" disabled="true" type="submit" name="action" value="preview" class="btn btn-primary btn-sm">Preview</button>
                                <button id="drafts-button"  disabled="true" type="submit" name="action" value="draft" class="btn btn-primary btn-sm">Save to Drafts</button>
                                <button id="rp-button" disabled="true" type="submit" name="action" class="btn btn-primary btn-sm" value="publish">Ready to Publish</button>
                            </div>
                        </form:form>
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

        if($('#post-title').val() == ''){
            $('#preview-button').prop("disabled",true);
            $('#drafts-button').prop("disabled",true);
            $('#rp-button').prop("disabled",true);
        } else {
            $('#preview-button').prop("disabled",false);
            $('#drafts-button').prop("disabled",false);
            $('#rp-button').prop("disabled",false);
        }

        $(document.body).on('input', '#post-title' ,function(){
            if($('#post-title').val() == ''){
                $('#preview-button').prop("disabled",true);
                $('#drafts-button').prop("disabled",true);
                $('#rp-button').prop("disabled",true);
            } else {
                $('#preview-button').prop("disabled",false);
                $('#drafts-button').prop("disabled",false);
                $('#rp-button').prop("disabled",false);
            }
        });
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
