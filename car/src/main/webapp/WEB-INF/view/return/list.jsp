<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>还车列表</title>
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
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label  class="layui-form-label">车牌号</label>
                    <div class="layui-input-inline" style="width: 100px;">
                        <input type="text" class="layui-input" id="carNum" placeholder="车牌号">
                    </div>
                </div>
                <button class="layui-btn layui-btn-sm" type="button" id="searchBtn"> <i class="layui-icon layui-icon-search"></i>提交</button>
                <button class="layui-btn layui-btn-normal layui-btn-sm" type="reset"><i class="layui-icon layui-icon-refresh-1"></i>重置</button>
                </div>
            </div>
        </form>
    </blockquote>

    <%--数据表格--%>
    <div>
        <table id="dataTable" lay-filter="dataTableFilter"></table>
    </div>

</body>
</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script>
    layui.use(['table','jquery','layer','form','laydate'],function () {

        let cxt = "${pageContext.request.contextPath}" ;
        let table = layui.table ;
        let $ = layui.jquery ;

        // 渲染数据表格
        let tableIns = {
            id:"dataTable",
            elem:"#dataTable",
            url: cxt + "/return/page.do",
            page:true,
            toolbar: "#headBtns", // 头工具栏
            cols:[[
                {type: "checkbox",title: "ID"},
                {field: "id", title: "ID",width: 80},
                {field: "carNum", title: "车牌号",width: 150},
                {field: "rentId", title: "出租ID",width: 100},
                {field: "rentPrice", title: "总租金",width: 120},
                {field: "payMoney", title: "赔付金额",width: 120},
                {field: "totalMoney", title: "总金额",width: 120},
                {field: "problem", title: "问题",width: 240},
                {field: "userId", title: "业务员",width: 80},
                {field: "createTime", title: "创建时间"}
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
        } ;
        let tableOps = table.render(tableIns);
        // 搜索
        $("#searchBtn").click(function () {
            // 获取搜索框中的数据
            let carNum = $("#carNum").val();
            tableOps.reload({
                where: {
                    "carNum": carNum
                }
            })
        })
    })
</script>