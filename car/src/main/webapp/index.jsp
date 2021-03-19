<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html class="loginHtml">
<head>
    <meta charset="utf-8">
    <title>汽车租赁·登录</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="icon" href="${pageContext.request.contextPath}/resources/favicon.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index.css" media="all" />
</head>
<body style="background-image: url('./resources/images/car.png'); background-repeat: no-repeat">

<div style="width: 400px;height: 350px;position: absolute;top:50%;left: 50%;margin-top: -125px;margin-left: -200px;border: #dddddd 1px solid;box-shadow: 2px 2px 10px #909090;" >
    <form action="" class="layui-form layui-form-pane" method="post" style="text-align: center">
        <img src="${pageContext.request.contextPath}/resources/images/pc.png" style="width: 100px;height: 100px;border-radius: 50%;margin-top: -50px;box-shadow: 5px 5px 5px #888888;">
        <p style="text-align: center;height: 40px;line-height: 40px;font-size: 25px;font-weight: bold;color: #009688;margin-bottom: 10px;">汽车租赁·登录</p>
        <div class="layui-form-item" style="margin-left: 30px;">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-inline">
                <input type="text" autocomplete="off" value="admin" name="username" lay-verify="required" class="layui-input" placeholder="请输入用户名.....">
            </div>
        </div>
        <div class="layui-form-item" style="margin-left: 30px;">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-inline">
                <input type="text" autocomplete="off" value="admin" name="password"  lay-verify="required" class="layui-input" placeholder="请输入密码.....">
            </div>
        </div>
        <div class="layui-form-item" style="margin-left: 30px;">
            <div class="layui-input-inline" style="width: 300px;">
                <div id="slider"></div>
            </div>
        </div>
        <div class="layui-form-item" style="margin-left: 30px;">
            <div class="layui-input-inline">
                <button class="layui-btn layui-btn" style="width: 300px;" lay-submit id="subBtn" lay-filter="subBtnFilter">登录</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
<script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/index.js"></script>
<script>
    layui.config({
        base:"${pageContext.request.contextPath}/resources/sliderVerify/"
    }).use(['sliderVerify','form','jquery','layer'],function () {
        let sliderVerify = layui.sliderVerify ;
        let form = layui.form ;
        let $ = layui.jquery ;
        let layer = layui.layer ;

        sliderVerify.render({
            elem:"#slider",
            isAutoVerify: true,
            text : '滑动',
            onOk: function(){//当验证通过回调
                layer.msg("滑块验证通过");
            }
        });
      form.on('submit(subBtnFilter)',function (d) {
            let data = d.field ;
            $.post("${pageContext.request.contextPath}/sysuser/login.do",data,function (rs) {
                if (rs.code == 200){
                    layer.msg("登录成功!")
                    location.href = "${pageContext.request.contextPath}/page/main.do";
                    return ;
                }
                layer.msg(rs.msg)
                return false;
            })
          return false ;
      })
    })
</script>