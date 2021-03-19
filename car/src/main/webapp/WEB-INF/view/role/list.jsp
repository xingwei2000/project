<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>角色列表</title>
    <meta charset="utf-8">
    <title>汽车租赁管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="icon" href="${pageContext.request.contextPath}/resources/favicon.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/iconfont.css" media="all" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index.css" media="all" />
</head>
<body>
    <%--搜索--%>
    <blockquote class="layui-elem-quote">
        <form class="layui-form layui-form-pane">
           <div class="layui-form-item" style="text-align: center;">
               <div class="layui-inline">
                   <label class="layui-form-label">角色名</label>
                   <div class="layui-input-inline">
                       <input class="layui-input" id="name" placeholder="角色名">
                   </div>
               </div>
               <div class="layui-inline">
                  <button id="searchBtn" type="button" class="layui-btn">搜索</button>
                  <button type="reset" class="layui-btn layui-btn-warm">重置</button>
               </div>
           </div>
        </form>
    </blockquote>
    <%--数据表格--%>
    <div>
        <table id="dataTable" lay-filter="dataTableFilter"></table>
    </div>

    <%--头标签--%>
    <script type="text/html" id="headBtns">
        <button class="layui-btn" lay-event="add">
            <i class="layui-icon layui-icon-add-1"></i>增加
        </button>
    </script>

    <%--行按钮操作--%>
    <script type="text/html" id="rowBtns">
        <button class="layui-btn layui-btn-sm layui-btn-warm" lay-event="resetPwd">
            <i class="layui-icon layui-icon-edit"></i>修改
        </button>
        <button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="add">
            <i class="layui-icon layui-icon-user"></i>设置权限
        </button>
    </script>

</body>
</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script>
    layui.use(['table','jquery','layer','form'],function () {

        let cxt = "${pageContext.request.contextPath}" ;
        let table = layui.table ;
        let $ = layui.jquery ;
        let layer = layui.layer ;
        let form = layui.form ;

        // 渲染数据表格
        let tableIns = table.render({
            id:"dataTable",
            elem:"#dataTable",
            url: cxt + "/role/page.do",
            page:true,
            toolbar: "#headBtns", // 头工具栏
            cols:[[
                {type:"checkbox"},
                {field:"name",title:"角色名"},
                {field:"tag",title:"角色标识"},
                {field:"descp",title:"描述",minWidth:300},
                {fixed: 'right',title:"操作", width: 240, align:'center', toolbar: '#rowBtns'} // 行工具栏
            ]],
            parseData:function (rs) {
                return {
                    code:rs.code,
                    msg:rs.msg,
                    count:rs.data.total,
                    data:rs.data.list,
                }
            },
            response:{
                statusCode:200
            }
        })
        // 搜索
        $("#searchBtn").click(function () {
            // 获取搜索框中的数据
            let name = $("#name").val();
            tableIns.reload({
                where:{
                    "name":name,
                }
            })
        })

        // 表格头工具栏监听事件
        table.on("toolbar(dataTableFilter)",function (d) {
          let event = d.event ;

        });

    })
</script>