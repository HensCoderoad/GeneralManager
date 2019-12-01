<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
</head>
<body>
<!--上方标题栏-->
<div class="parentTop">
    <a href="#" class="appName">后台管理系统</a>

    <div class="loginNameDiv">
        <!--<img class="loginIcon" src="image/icon.png">-->
        <div class="loginUserInfo">
            <div class="dropdown">
                <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="true">
                ${user.nickname}
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                    <li><a href="#">修改密码</a></li>
                    <li><a href="/authc/logout">退出</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<!-- Single button -->

<!--左侧菜单-->
<div class="leftMenu">
    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
<#list menu as item>
    <#if item.parentId = 0>
        <div class="panel panel-default">
            <div class="panel-heading" role="tab" id="headingOne">
                    <div data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                        ${item.menuName}
                    </div>
            </div>
        <#list menu as child>
            <#if item.mid = child.parentId>
            <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                <div class="list-group">
                    ${child.menuName}
                </div>
            </div>
            </#if>
        </#list>
        </div>
    </#if>
</#list>
    </div>

    <ul class="list-group">
        <#list menu as item>
        <#if item.parentId = 0>
            <ul class="list-group-item">
                <#--${item.menuName}-->
                ${item.menuName}
            </ul>
        </#if>
        </#list>
    </ul>
</div>
<!--主要内容区-->
<div class="parentMain">
    <iframe id="iframeParent"></iframe>
</div>

<!--设置弹框-->
<div class="dialogMenu">
    <div id="parentChangePassword" class="dialogMenu-item">修改密码</div>
    <div id="parentExit" class="dialogMenu-item">退出</div>
</div>
</body>
</html>
<!--修改密码弹框-->
<script type="text/html" id="dialogChangePassword">
    <form class="layui-form" id="dialogChangePasswordForm" style="margin-top:30px;padding-right:30px;">
        <div class="layui-form-item">
            <label class="layui-form-label">原密码</label>
            <div class="layui-input-block">
                <input type="password" name="password"
                       placeholder="请输入原密码" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">新密码</label>
            <div class="layui-input-block">
                <input type="password" name="newPassword"
                       placeholder="请输入新密码" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">确认密码</label>
            <div class="layui-input-block">
                <input type="password" name="okPassword"
                       placeholder="请重复输入密码" class="layui-input">
            </div>
        </div>
    </form>
</script>




</body>
<script>

</script>
<script src="../js/jquery-3.4.1.min.js"></script>
<script src="../js/bootstrap.js"></script>
</html>