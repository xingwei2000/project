<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户列表</title>
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
                <div class="layui-inline" >
                    <label  class="layui-form-label">出租状态</label>
                    <div class="layui-input-inline" style="width: 150px;">
                        <select id="flag" name="flag">
                            <option value="">出租状态</option>
                            <option value="1">未还车</option>
                            <option value="2">已还车</option>
                        </select>
                    </div>
                </div>

                <div class="layui-inline">
                    <label  class="layui-form-label">出租时间</label>
                    <div class="layui-input-inline">
                        <input readonly name="beginTime" id="beginTime" placeholder="出租时间" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label  class="layui-form-label">客户名称</label>
                    <div class="layui-input-inline" style="width: 100px;">
                        <input type="text" class="layui-input" name="name" id="name" placeholder="客户名称">
                    </div>
                </div>
                <button class="layui-btn layui-btn-sm" type="button" id="searchBtn"> <i class="layui-icon layui-icon-search"></i>提交</button>
                <button class="layui-btn layui-btn-normal layui-btn-sm" type="reset"><i class="layui-icon layui-icon-refresh-1"></i>重置</button>
                </div>
            </div>
        </form>
    </blockquote>


    <%--新增还车记录模板--%>
    <script type="text/html" id="returnTpl">
        <form class="layui-form layui-form-pane" lay-filter="returnFormFilter"  style="padding-left: 10px;padding-top: 10px;">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">车牌号</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="carNum" placeholder="车牌号"  readonly>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">还车时间</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="returnTime" id="returnTime" placeholder="还车时间" lay-verify="required" lay-reqText="请输入还车时间" >
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">赔付金额</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="payMoney" placeholder="赔付金额" value="0" lay-verify="required" lay-reqText="请输入赔付金额" >
                    </div>
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">问题</label>
                <div class="layui-input-block">
                    <textarea class="layui-textarea" name="problem" id="problem" placeholder="问题"></textarea>
                </div>
            </div>
            <button style="display: none" id="submitBtn" lay-submit lay-filter="submitBtnFilter"></button >
        </form>
    </script>

    <%--数据表格--%>
    <div>
        <table id="dataTable" lay-filter="dataTableFilter"></table>
    </div>

    <%--行按钮操作--%>
    <script type="text/html" id="rowBtns">
        <button class="layui-btn layui-btn-sm layui-btn-warm" lay-event="returnCar">
            <i class="layui-icon layui-icon-return"></i>h还车
        </button>
    </script>

</body>
</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script>
    layui.use(['table','jquery','layer','form','laydate'],function () {

        let cxt = "${pageContext.request.contextPath}" ;
        let table = layui.table ;
        let $ = layui.jquery ;
        let layer = layui.layer ;
        let form = layui.form ;
        let laydate = layui.laydate ;

        laydate.render({
            elem:"#beginTime",
            range:"~",
            trigger:"click",
            theme: '#393D49'
        })

        // 渲染数据表格
        let tableIns = {
            id:"dataTable",
            elem:"#dataTable",
            url: cxt + "/rent/page.do",
            page:true,
            toolbar: "#headBtns", // 头工具栏
            cols:[[
                {type: "checkbox",title: "ID"},
                {field: "id", title: "ID",width: 50},
                {field: "carNum", title: "车牌号",width: 100},
                {field: "type", title: "类型",templet:function (d) {
                        let type = d.type ;
                        if (type == 1){
                            return "轿车" ;
                        }else if (type == 2){
                            return "SUV" ;
                        }else if (type == 3){
                            return "跑车" ;
                        }else{
                            return "房车";
                        }
                    }},
                {field: "rentPrice", title: "出租价格"},
                {field: "deposity", title: "押金"},
                {field: "name", title: "客户姓名"},
                {field: "idCard", title: "身份证号",width: 150},
                {field: "beginTime", title: "开始时间",width: 130},
                {field: "endTime", title: "结束时间",width: 130},
                {field: "flag", title: "状态",width: 80,templet:function (d) {
                        let flag = d.flag ;
                        if (flag == "1"){
                            return "<font color='red'>未还车</font>"
                        }else {
                            return "<font color='green'>已还车</font>"
                        }
                    }},
                {field: "descp", title: "描述"},
                {field: "createTime", title: "创建时间", width: 140},
                {fixed: 'right', title: "操作", width: 100, align: 'center', toolbar: '#rowBtns'} // 行工具栏
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
            debugger ;
            let name = $("#name").val();
            let flag = $("#flag").val();
            let beginTime = $("#beginTime").val();

            tableOps.reload({
                where: {
                    "carNum": carNum,
                    "name": name,
                    "flag": flag,
                    "beginTime": beginTime
                }
            })
        });
        // 行工具栏事件
        table.on("tool(dataTableFilter)",function (d) {
            let event = d.event ;
            let rowData = d.data ;
            if (event == "returnCar"){
                returnCar(rowData);
            }
        });
        /**
         * 还车方法
         * @param rowData
         */
        function returnCar(rowData) {
            let layerIns = {
                title:"还车",
                type: 1,
                content:$("#returnTpl").html(),
                success:function (layero,index) {
                    // 渲染还车时间
                    laydate.render({
                        elem:"#returnTime"
                    })
                    form.render() ;
                    // 为表单赋值
                    form.val("returnFormFilter",rowData) ;
                    form.on('submit(submitBtnFilter)',function (d) {
                        let formData = d.field ;
                        formData.rentId = rowData.id ; // 出租记录ID
                      $.post(cxt + "/return/add.do",formData,function (rs) {
                          layer.msg(rs.msg) ;
                          if (rs.code !=200){
                              return  ;
                          }
                          layer.msg(rs.msg) ;
                          layer.close(index) ;
                          $("#searchBtn").click() // 刷新表单
                      })
                        return false ;
                    })
                },
                yes:function (index,layero) {
                    $("#submitBtn").click() ;
                },
                btn:['确定','取消'],
                btnAlign:'c',
            }
            layer.open(layerIns) ;
        }
    })
</script>