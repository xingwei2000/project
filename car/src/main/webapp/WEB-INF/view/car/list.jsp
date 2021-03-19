<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>汽车管理</title>
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
        <div class="layui-form-item">
            <div class="layui-inline">
                <label  class="layui-form-label">车牌号</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="carNum" placeholder="车牌号">
                </div>
            </div>
            <div class="layui-inline">
                <div class="layui-inline">
                    <label  class="layui-form-label">租金</label>
                    <div class="layui-input-inline" style="width: 100px;">
                        <input type="text" class="layui-input" id="minRentPrice" placeholder="￥">
                    </div>
                    <div class="layui-form-mid">-</div>
                    <div class="layui-input-inline" style="width: 100px;">
                        <input type="text" class="layui-input" id="maxRentPrice" placeholder="￥">
                    </div>
                </div>
            </div>
            <div class="layui-inline" >
                <label  class="layui-form-label">颜色</label>
                <div class="layui-input-inline" style="width: 120px;">
                    <input type="text" class="layui-input" id="color" placeholder="颜色">
                </div>
            </div>
            <div class="layui-inline" >
                <label  class="layui-form-label">出租状态</label>
                <div class="layui-input-inline" style="width: 150px;">
                    <select id="isRent" >
                        <option value="">出租状态</option>
                        <option value="1">未出租</option>
                        <option value="2">已出租</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label  class="layui-form-label">描述</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="descp" placeholder="描述">
                </div>
            </div>
            <div class="layui-inline">
                <label  class="layui-form-label">价格</label>
                <div class="layui-input-inline" style="width: 100px;">
                    <input type="text" class="layui-input" id="minPrice" placeholder="￥">
                </div>
                <div class="layui-form-mid">-</div>
                <div class="layui-input-inline" style="width: 100px;">
                    <input type="text" class="layui-input" id="maxPrice" placeholder="￥">
                </div>
            </div>
            <div class="layui-inline">
                <label  class="layui-form-label">车型</label>
                <div class="layui-input-inline" style="width: 120px;">
                    <select id="type" name="type">
                        <option value="">车型</option>
                        <option value="1">轿车</option>
                        <option value="2">SUV</option>
                        <option value="3">跑车</option>
                        <option value="4">房车</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <button class="layui-btn" type="button" id="searchBtn"> <i class="layui-icon layui-icon-search"></i>提交</button>
                <button class="layui-btn layui-btn-normal" type="reset"><i class="layui-icon layui-icon-refresh-1"></i>重置</button>
            </div>
        </div>

    </form>
</blockquote>

<%--添加模板--%>
<script type="text/html" id="addCarTpl">
    <form class="layui-form layui-form-pane" style="padding-left: 10px;padding-top: 5px;">
        <div class="layui-form-item">
            <div class="layui-inline">
                <div class="layui-form-item">
                    <label class="layui-form-label">车牌号</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="carNum" lay-verify="required" lay-reqText="请输入车牌号" placeholder="车牌号">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">车型</label>
                    <div class="layui-input-inline">
                        <select name="type" lay-verify="required" lay-reqText="请输入车型">
                            <option value="">车型</option>
                            <option value="1">轿车</option>
                            <option value="2">SUV</option>
                            <option value="3">跑车</option>
                            <option value="4">房车</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">颜色</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="color" lay-verify="required" lay-reqText="请输入颜色" placeholder="颜色">
                    </div>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">图片</label>
                <div class="layui-input-inline">
                    <img id="uploadImg" src="${pageContext.request.contextPath}/resources/images/sc.png" alt="选择图片" style="height: 160px;border: 1px solid #c0c4cc;width: 160px;">
                    <%--隐藏的input 用于保存文件上传的URL地址--%>
                    <input type="hidden" id="img" name="img" lay-verify="required" lay-reqText="请上传车辆文件">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <div class="layui-form-item">
                    <label class="layui-form-label">价格</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="price" lay-verify="required|number" lay-reqText="请输入价格" placeholder="价格">
                    </div>
                </div>
            </div>
            <div class="layui-inline">
                <div class="layui-form-item">
                    <label class="layui-form-label">租金</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="rentPrice" lay-verify="required|number" lay-reqText="请输入租金" placeholder="租金">
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <div class="layui-form-item">
                    <label class="layui-form-label">押金</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="deposity" lay-verify="required|number" lay-reqText="请输入押金" placeholder="押金">
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">描述</label>
            <div class="layui-input-block">
                <textarea class="layui-textarea" name="descp"></textarea>
            </div>
        </div>
        <button type="button"  style="display: none;" id="submitBtn" lay-submit lay-filter="submitBtnFilter"></button>
    </form>
</script>

<%--车辆出租--%>
<script type="text/html" id="rentTpl">
    <form class="layui-form layui-form-pane" style="padding-left: 10px;padding-top: 5px;" lay-filter="rentFormFilter">
        <div class="layui-form-item">
            <div class="layui-inline">
                <div class="layui-form-item">
                    <label class="layui-form-label">车牌号</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" readonly name="carNum" lay-verify="required" lay-reqText="请输入车牌号" placeholder="车牌号">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">车型</label>
                    <div class="layui-input-inline">
                        <select name="type" disabled lay-verify="required" lay-reqText="请输入车型">
                            <option value="">车型</option>
                            <option value="1">轿车</option>
                            <option value="2">SUV</option>
                            <option value="3">跑车</option>
                            <option value="4">房车</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">颜色</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" readonly name="color" lay-verify="required" lay-reqText="请输入颜色" placeholder="颜色">
                    </div>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">图片</label>
                <div class="layui-input-inline">
                    <img id="carImg" style="height: 160px;border: 1px solid #c0c4cc;width: 160px;">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <div class="layui-form-item">
                    <label class="layui-form-label">价格</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="price" lay-verify="required|number" lay-reqText="请输入价格" placeholder="价格">
                    </div>
                </div>
            </div>
            <div class="layui-inline">
                <div class="layui-form-item">
                    <label class="layui-form-label">租金</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="rentPrice" lay-verify="required|number" lay-reqText="请输入租金" placeholder="租金">
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <div class="layui-form-item">
                    <label class="layui-form-label">押金</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="deposity" lay-verify="required|number" lay-reqText="请输入押金" placeholder="押金">
                    </div>
                </div>
            </div>
            <div class="layui-inline">
                <div class="layui-form-item">
                    <label class="layui-form-label">身份证</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="idCard" lay-verify="required|idCard" lay-reqText="请输入客户身份证" placeholder="身份证">
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">出租时间</label>
            <div class="layui-input-inline" style="width: 510px;">
                <input type="text" id="rentTime" name="rentTime" class="layui-input">
            </div>
        </div>
        <button type="button"  style="display: none;" id="subBtn" lay-submit lay-filter="submitBtnFilter"></button>
    </form>
</script>


<%--数据表格--%>
<div>
    <table id="dataTable" lay-filter="dataTableFilter"></table>
</div>

<%--头标签--%>
<script type="text/html" id="headBtns">
    <button class="layui-btn" lay-event="add">
        <i class="layui-icon layui-icon-add-1"></i>
    </button>
</script>

<%--行按钮操作--%>
<script type="text/html" id="rowBtns">
    <button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="rent">
        <i class="layui-icon layui-icon-release"></i>出租
    </button>
</script>
<script>
    let cxt = '${pageContext.request.contextPath}' ;
</script>
</body>
</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script>
    layui.use(['table', 'jquery', 'layer', 'form','upload','laydate'], function () {

        let cxt = "${pageContext.request.contextPath}";
        let table = layui.table;
        let $ = layui.jquery;
        let layer = layui.layer;
        let form = layui.form;
        let upload = layui.upload;
        let laydate = layui.laydate;

        // 渲染数据表格
        let tableIns = {
            id: "dataTable",
            elem: "#dataTable",
            url: cxt + "/car/page.do",
            page: true,
            toolbar: "#headBtns", // 头工具栏
            cols: [[
                {type: "checkbox"},
                {field: "id", title: "ID",width: 50},
                {field: "carNum", title: "车牌号",width: 100},
                {field: "type", title: "类型", width: 100,templet:function (d) {
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
                {field: "color", title: "颜色",},
                {field: "price", title: "价格"},
                {field: "rentPrice", title: "出租价格",width: 130},
                {field: "deposity", title: "押金"},
                {
                    field: "isRent", title: "出租状态", templet: function (d) {
                        let isRent = d.isRent;
                        if (isRent == "1") {
                            return "<font color='green'>未出租</font>"
                        } else {
                            return "<font color='gray'>已出租</font>"
                        }
                    }
                },
                {field: "descp", title: "描述",width: 150},
                {field: "img", title: "图片",templet:function (d) {
                        let img =  d.img ;
                        return "<button class='layui-btn layui-btn-sm' onclick=showCarImg(\'"+img+"'\)>查看</button>"
                    }},
                {field: "createTime", title: "创建时间", width: 170},
                {fixed: 'right', title: "操作", width: 100, align: 'center', toolbar: '#rowBtns'} // 行工具栏
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
        }
        let tableOps = table.render(tableIns);
        // 搜索
        $("#searchBtn").click(function () {
            // 获取搜索框中的数据
            let carNum = $("#carNum").val();
            let phone = $("#phone").val();
            let color = $("#color").val();
            let minPrice = $("#minPrice").val();
            let maxPrice = $("#maxPrice").val();
            let minRentPrice = $("#minRentPrice").val();
            let maxRentPrice = $("#maxRentPrice").val();
            let isRent = $("#isRent").val();

            tableOps.reload({
                where: {
                    "carNum": carNum,
                    "phone": phone,
                    "color": color,
                    "minPrice": minPrice,
                    "maxPrice": maxPrice,
                    "minRentPrice": minRentPrice,
                    "maxRentPrice": maxRentPrice,
                    "isRent": isRent,
                }
            })
        })

        // 表格头工具栏监听事件
        table.on("toolbar(dataTableFilter)", function (d) {
            let event = d.event;
            if (event == "add") {
                add();
            }
        });

        // 弹出层参数
        let layOps = {
            title: "新增车辆",
            type: 1,
            content: $("#addCarTpl").html(),
            area: ['700px', '580px'],
            success: function (layero, index) {
                form.render();
                // 初始化上传组件
                let uploadOps = {
                    elem:"#uploadImg",
                    url:cxt + "/file/uploadImg.do",
                    auto:true,
                    bindAction:"#img",
                    field:"image",  // 此属性是后台接口接收参数的值
                    accept:'file',
                    choose:function(obj){   // 实现预览
                        obj.preview(function (index,file,result) {
                            $("#uploadImg").attr("src",result);
                        })
                    },
                    done:function (rs,fileIndex,upload) {
                        layer.msg(rs.msg);  // 展示业务消息
                        if (rs.code == 200){
                            // 给隐藏框赋值
                            $("#img").val(rs.data) ;
                        }
                    }
                }
                upload.render(uploadOps);
                // 表单监听提交
                form.on("submit(submitBtnFilter)", function (d) {
                    let formData = d.field;
                    $.post(cxt + "/car/add.do", formData, function (rs) {
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
                $("#submitBtn").click()
            }
        }

        /**
         * 展示图片
         * @param url
         */
        window.showCarImg = function (url) {
            let imgData = {
                "title": "车辆图片", //相册标题
                "data": [   //相册包含的图片，数组格式
                    {
                        "src":  cxt + "/" + url, //原图地址
                        "thumb": cxt + "/" + url //缩略图地址
                    }
                ]
            }
            layer.photos({
                photos:imgData
            }) ;
        }

        function add() {
            layer.open(layOps) ;
        }

        // 行工具栏监听事件
        table.on("tool(dataTableFilter)",function (d) {
            let event = d.event ;
            let rowData = d.data ;
            if (event == "rent"){
                rent(rowData); // 具体的出租方法
            }
        });

        function rent(rowData) {
            layer.open({
                title: "车辆出租",
                type: 1,
                content: $("#rentTpl").html(),
                area: ['700px', '580px'],
                success: function (layero, index) {
                    form.render();
                    // 为表单赋值
                    form.val("rentFormFilter",rowData);
                    $("#carImg").attr("src",cxt + "/" + rowData.img)
                    // 渲染时间组件
                    laydate.render({
                        elem:"#rentTime",
                        type:"date",
                        range:"~",
                        trigger:"click",
                        theme: '#393D49'
                    })
                    // 表单监听提交
                    form.on("submit(submitBtnFilter)", function (d) {
                        let formData = d.field;
                        $.post(cxt + "/rent/add.do", formData, function (rs) {
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
            })
        }

    })
</script>