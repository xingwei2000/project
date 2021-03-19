<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>客户列表</title>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/iconfont.css" media="all"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index.css" media="all"/>
</head>
<body>
<%--搜索--%>
<blockquote class="layui-elem-quote">
    <form class="layui-form layui-form-pane">
        <div class="layui-form-item" style="text-align: center;">
            <div class="layui-inline">
                <label class="layui-form-label">真实姓名</label>
                <div class="layui-input-inline">
                    <input class="layui-input" id="name" placeholder="用户姓名">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">手机号</label>
                <div class="layui-input-inline">
                    <input class="layui-input" id="phone" placeholder="手机号">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">地址</label>
                <div class="layui-input-inline">
                    <input class="layui-input" id="address" placeholder="地址">
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
        <i class="layui-icon layui-icon-add-1"></i>新增客户
    </button>
    <button class="layui-btn  layui-btn-normal" lay-event="export">
        <i class="layui-icon layui-icon-export"></i>导出客户
    </button>
    <button class="layui-btn layui-btn-warm" lay-event="customerImport">
        <i class="layui-icon layui-icon-upload"></i>导入客户
    </button>
</script>

<%--行按钮操作--%>
<script type="text/html" id="rowBtns">
    <button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="edit">
        <i class="layui-icon layui-icon-edit"></i>修改客户
    </button>
</script>

<%--上传文件--%>
<script type="text/html" id="uploadTpl">
    <form class="layui-form layui-form-pane">
        <div class="layui-form-item" style="padding-left: 10px;padding-top: 30px;">
            <label class="layui-form-label">客户文件</label>
            <div class="layui-inline">
                <button type="button" class="layui-btn layui-btn-normal" id="chooseBtn">选择文件</button>
                <button type="button" class="layui-btn" id="uploadBtn">上传文件</button>
            </div>
        </div>
    </form>
</script>

<%--编辑客户模板--%>
<script type="text/html" id="addCustomerTpl" style="margin: 10px auto;">
    <form class="layui-form layui-form-pane" lay-filter="editFormFilter">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">姓名</label>
                <div class="layui-input-inline">
                    <input name="name" lay-verify="required" class="layui-input" lay-reqText="客户姓名不能为空" placeholder="客户姓名">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">手机号</label>
                <div class="layui-input-inline">
                    <input name="phone" lay-verify="required|phone" class="layui-input" lay-reqText="手机号不能为空" placeholder="手机号">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">身份证号</label>
                <div class="layui-input-inline">
                    <input name="idCard" lay-verify="required|idCard" class="layui-input" lay-reqText="身份证号不能为空" placeholder="身份证号">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-inline">
                <input type="radio" name="sex" value="1" title="男" checked>
                <input type="radio" name="sex" value="2" title="女">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">地址</label>
            <div class="layui-input-block">
                <textarea class="layui-textarea" name="address"></textarea>
            </div>
        </div>
        <button style="display: none;" id="subBtn" lay-submit lay-filter="submitBtnFilter"></button>
    </form>
</script>

</body>
</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script>
    layui.use(['table', 'jquery', 'layer', 'form','upload'], function () {

        let cxt = "${pageContext.request.contextPath}";
        let table = layui.table;
        let $ = layui.jquery;
        let layer = layui.layer;
        let form = layui.form;
        let upload = layui.upload;

        // 渲染数据表格
        let tableIns = ({
            id: "dataTable",
            elem: "#dataTable",
            url: cxt + "/customer/page.do",
            page: true,
            toolbar: "#headBtns", // 头工具栏
            cols: [[
                {type: "checkbox"},
                {field: "id", title: "ID"},
                {field: "name", title: "客户姓名"},
                {field: "phone", title: "手机号", width: 130},
                {field: "idCard", title: "身份证号", width: 180},
                {
                    field: "sex", title: "性别", templet: function (d) {
                        let sex = d.sex;
                        if (sex == '1') {
                            return "<font color='green'>男</font>"
                        } else if (sex == '2') {
                            return "<font color='red'>女</font>"
                        } else {
                            return "<font color='gray'>不详</font>"
                        }
                    }
                },
                {field: "address", title: "地址", width: 110},
                {field: "createTime", title: "创建时间", width: 170},
                {field: "updateTime", title: "修改时间", width: 170},
                {fixed: 'right', title: "操作", width: 240, align: 'center', toolbar: '#rowBtns'} // 行工具栏
            ]],
            parseData: function (rs) {
                return {
                    code: rs.code,
                    msg: rs.msg,
                    count: rs.data.total,
                    data: rs.data.list,
                }
            },
            response: {
                statusCode: 200
            },
        })
        let tableOps = table.render(tableIns);
        // 搜索
        $("#searchBtn").click(function () {
            debugger ;
            // 获取搜索框中的数据
            let name = $("#name").val();
            let phone = $("#phone").val();
            let address = $("#address").val();

            tableOps.reload({
                where: {
                    "name": name,
                    "phone": phone,
                    "address": address,
                }
            })
        })

        // 表格头工具栏监听事件
        table.on("toolbar(dataTableFilter)", function (d) {
            let event = d.event;
            if (event == "add") {
                add();
            }else if(event == "export"){
                exportData();
            }else if(event == "customerImport"){
                customerImport() ;
            }
        });

        // 弹出层参数
        let layOps = {
            title: "编辑客户",
            type: 1,
            content: $("#addCustomerTpl").html(),
            area: ['420px', '450px'],
            success: function (layero, index) {
                form.render("radio");
                form.on("submit(submitBtnFilter)", function (d) {
                    let formData = d.field;
                    $.post(cxt + "/customer/add.do", formData, function (rs) {
                        layer.msg(rs.msg); // 展示业务消息
                        if (rs.code != 200) {
                            return false;
                        }
                        layer.close(index);  // 关闭弹层
                        // 刷新表格
                        $("#searchBtn").click();
                    })
                    return false;
                })
            },
            btn: ['确认', '取消'],
            btnAlign:'c',
            yes: function (index, layero) {
                // 点击隐藏的提交按钮 触发表单提交监听
                $("#subBtn").click()
            }
        }
        /**
         * 请求导出客户
         *
         */
        function exportData() {
            // 获取搜索框中的数据
            let name = $("#name").val();
            let phone = $("#phone").val();
            let address = $("#address").val();
            location.href = cxt + "/customer/export.do?name="+name+"&phone="+phone+"&address="+address ;
        }


        //导入客户信息
        function customerImport() {
            let opt = {
                type:1,
                title:"上传文件",
                content: $("#uploadTpl").html(),
                area:['470px','200px'],
                success:function (layero,index) {
                    // 初始化文件上传
                    let uploadOps = {
                        elem:"#chooseBtn",
                        url:cxt+"/customer/import.do", // 处理文件上传的接口
                        auto:false, // 不自动上传
                        bindAction:"#uploadBtn", // 绑定具体上传的按钮
                        field:"customer", // 指定上传的数据域名称
                        accept:"file", // 允许上传文件
                        done:function (rs,FileIndex,upload) {
                            layer.msg(rs.msg)  // 将业务消息进行展示
                            if (rs.code==200){
                                // 关闭弹出层
                                layer.close(index) ;
                                $("#searchBtn").click()
                            }
                        }
                    }
                    upload.render(uploadOps);
                }
            }

            layer.open(opt)
        }


        /**
         * 新增用户方法
         */
        function add() {
            layer.open(layOps)
        }
        // 行工具栏监听事件
        table.on("tool(dataTableFilter)",function (d) {
            let event = d.event ;
            let rowData = d.data ;
            if (event == "edit"){
                edit(rowData);  // 修改客户
            }
        });

        // 具体的修改方法
        function edit(rowData) {
            let ops = new Object();
            //将对象进行复制 原因就是 新增一次 修改一次 再新增一次就使用的是修改的模板
            Object.assign(ops,layOps) ;
            ops.success = function (layero,index) {
                form.render("radio") ;
                form.val("editFormFilter",rowData); // 为表单赋值
                form.on("submit(submitBtnFilter)",function (d) {
                    let formData = d.field ;
                    formData.id = rowData.id ;
                    $.post(cxt + "/customer/update.do",formData,function (rs) {
                        if (rs.code == 200){
                            layer.msg("修改成功!")
                            // 关闭弹层 刷新表格
                            layer.close(index) ;
                            $("#searchBtn").click() ;
                            return ;
                        }
                        layer.msg(rs.msg) ;
                    })
                    return false ;
                })
            }
            layer.open(ops) ;
        }
    })
</script>