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
        <button class="layui-btn layui-btn-sm layui-btn-warm" lay-event="edit">
            <i class="layui-icon layui-icon-edit"></i>修改
        </button>
        <button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="">
            <i class="layui-icon layui-icon-user"></i>设置权限
        </button>
    </script>

    <%--新增角色模板--%>
    <script type="text/html" id="addRoleTpl">                                                                                                                                                                                                                     ">
        <form class="layui-form layui-form-pane" lay-filter="editFormFilter" style="margin: 10px;">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">角色名</label>
                    <div class="layui-input-inline">
                        <input type="text" name="name" placeholder="角色名" lay-reqText="请输入角色名" lay-verify="required" class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">角色标识</label>
                    <div class="layui-input-inline">
                        <input type="text" name="tag" placeholder="角色标识" lay-reqText="请输入角色标识" lay-verify="required" class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">描述</label>
                    <div class="layui-input-inline">
                        <input type="text" name="descp" placeholder="描述" autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>
            <button class="layui-btn" style="display: none;" id="subBtn" lay-submit lay-filter="submitFilter"></button>
        </form>
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
                {field:"id",title: "ID"},
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
          if (event == "add"){
              add() ;
          }
        });

        // 表格头工具栏监听事件
        table.on("tool(dataTableFilter)",function (d) {
            let event = d.event ;
            let rowData = d.data ;
            if (event == "edit"){
                edit(rowData) ;
            }
        });

        /**
         * 添加角色的方法
         */
        function add() {
            let layerIns = {
                title:"新增角色",
                type: 1,
                content:$("#addRoleTpl").html(),
                area:['450px','450px'],
                success:function (layero,index) {
                    form.on('submit(submitFilter)',function (d) {
                        let formData = d.field ;
                        $.post(cxt + "/role/add.do",formData,function (rs) {
                            if (rs.code!=200){
                                layer.msg(rs.msg) ;// 展示业务消息
                                return ;
                            }
                            layer.msg(rs.msg) ;
                            layer.close(index) // 关闭弹层
                            $("#searchBtn").click() ;
                        })
                    })
                },
                yes:function (index,layero) {
                    $("#subBtn").click() ;
                },
                btn:["确定","取消"],
                btnAlign:'c'
            }

            layer.open(layerIns) ;
        }

        function edit(rowData) {
            let layerIns = {
                title:"新增角色",
                type: 1,
                content:$("#addRoleTpl").html(),
                area:['450px','450px'],
                success:function (layero,index) {
                    // 表单赋值
                    form.val("editFormFilter",rowData) ;
                    form.on('submit(submitFilter)',function (d) {
                        let formData = d.field ;
                        formData.id = rowData.id ;
                        $.post(cxt + "/role/update.do",formData,function (rs) {
                            if (rs.code != 200){
                                layer.msg(rs.msg) ;// 展示业务消息
                                return ;
                            }
                            layer.msg(rs.msg) ;
                            layer.close(index) // 关闭弹层
                            $("#searchBtn").click() ;
                        })
                        return false ;
                    })
                },
                yes:function (index,layero) {
                    $("#subBtn").click() ;
                },
                btn:["确定","取消"],
                btnAlign:'c'
            }
            layer.open(layerIns) ;
        }


    })
</script>