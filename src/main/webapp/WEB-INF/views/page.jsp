<%@ include file="/common/taglibs.jsp" %>
<head>
    <title>Preview</title>
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
            <a href="/post/create">Create Post</a>
        </li>
        <li>
            <a href="/post/view">View Posts</a>
        </li>

    </ul>
</div>

<div align="center">
    <br>
        <h1>${post.title}</h1>
    <h3>${post.content}</h3>
    <img id="ItemPreview" src="data:image/png;base64,<c:out value='${post.imageSrc}'/>" />


</div>

</div>
</div>
</body>

