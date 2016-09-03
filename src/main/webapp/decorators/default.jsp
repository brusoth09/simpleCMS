<!DOCTYPE html>
<%@include file="/common/taglibs.jsp" %>
<html lang="en">
<head>
    <meta http-equiv="Cache-Control" content="no-store"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="<c:url value="/images/favicon.ico"/>"/>
    <title><sitemesh:write property='title'/> | <fmt:message key="web.app.title"/></title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/iCheck/square/blue.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/simplecms/simplecms.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/simplecms/simpleSideBar.css">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script src="${pageContext.request.contextPath}/plugins/jQuery/jQuery-2.2.0.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/iCheck/icheck.min.js"></script>
    <sitemesh:write property='head'/>
</head>
<body <sitemesh:write property="body.id" writeEntireProperty="true"/>
<sitemesh:write property="body.class" writeEntireProperty="true"/>

<div class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="row">
        <!-- Sidebar -->
        <div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <li class="sidebar-brand">
                    <a href="#">
                        <div class="login-logo">
                            <a href="${pageContext.request.contextPath}/home"><b>Simple</b>CMS</a>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/home">Create Post</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/post/view">View Posts</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/admin">Admin View</a>
                </li>
                <li>
                    <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                </li>
            </ul>
        </div>
        <!-- /#sidebar-wrapper -->
        <sitemesh:write property='body'/>
    </div>
</div>
</body>
</html>