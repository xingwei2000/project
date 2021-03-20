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
           <div class="layui-form-item" style="text-align: center;">
               <div class="layui-inline">
                   <label class="layui-form-label">真实姓名</label>
                   <div class="layui-input-inline">
                       <input class="layui-input" id="realName" placeholder="用户姓名">
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
            <i class="layui-icon layui-icon-add-1"></i>增加
        </button>
        <button class="layui-btn  layui-btn-danger" lay-event="add">
            <i class="layui-icon layui-icon-delete"></i>删除
        </button>
    </script>

    <%--行按钮操作--%>
    <script type="text/html" id="rowBtns">
        <button class="layui-btn layui-btn-sm layui-btn-warm" lay-event="resetPwd">
            <i class="layui-icon layui-icon-refresh-3"></i>重置密码
        </button>
        <button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="setRoles">
            <i class="layui-icon layui-icon-user"></i>设置角色
        </button>
    </script>

    <%--新增用户模板--%>
    <script type="text/html" id="addUserTpl">
        <form action="" class="layui-form layui-form-pane" style="margin: 10px;">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">姓名</label>
                    <div class="layui-input-inline">
                        <input type="text" name="realName" placeholder="用户姓名" lay-reqText="请输入姓名" lay-verify="required" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">登录名</label>
                    <div class="layui-input-inline">
                        <input type="text" name="userName" placeholder="登录名" lay-reqText="请输入登录名" lay-verify="required" autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">手机号</label>
                    <div class="layui-input-inline">
                        <input type="text" name="phone" placeholder="手机号" lay-verify="required|phone" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">身份证</label>
                    <div class="layui-input-inline">
                        <input type="text" name="idCard" placeholder="身份证"  lay-verify="identity" autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">性别</label>
                    <div class="layui-input-inline">
                        <input type="radio" name="sex" value="1" title="男" checked>
                        <input type="radio" name="sex" value="2" title="女">
                    </div>
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">地址</label>
                <div class="layui-input-block">
                    <textarea class="layui-textarea" name="address" placeholder="地址" lay-reqText="请输入地址" lay-verify="required"></textarea>
                </div>
            </div>
            <button class="layui-btn" style="display: none;" id="subBtn" lay-submit lay-filter="submitFilter"></button>
        </form>
    </script>

    <%--设置角色的模板--%>
    <script type="text/html" id="setRoleTpl">
        <form class="layui-form layui-form-pane" style="padding-left: 20px; padding-top: 30px;" id="allRoles">
            <button id="subBtn" style="display: none" lay-submit lay-filter="submitBtnFilter"></button>
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
            url: cxt + "/sysuser/page.do",
            page:true,
            toolbar: "#headBtns", // 头工具栏
            cols:[[
                {type:"checkbox"},
                {field:"userName",title:"用户名"},
                {field:"realName",title:"真实姓名"},
                {field:"idCard",title:"身份证号",width: 180},
                {field:"phone",title:"手机号",width: 130},
                {field:"sex",title:"性别",templet:function (d) {
                    let sex = d.sex ;
                    if (sex == '1'){
                        return "<font color='#adff2f'>男</font>"
                    }else if (sex == '2'){
                        return "<font color='green'>女</font>"
                    }else{
                        return "<font color='red'>不详</font>"
                    }
                    }},
                {field:"address",title:"地址",width: 110},
                {field:"img",title:"图片",templet:function (d) {
                    let img = d.img ;
                    return "<button class='layui-btn layui-btn-sm' onclick=showUserImg(\'"+img+"\')>查看</button>"
                    }},
                {field:"createTime",title:"创建时间",width: 170},
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
            let realName = $("#realName").val();
            let phone = $("#phone").val();
            let address = $("#address").val();

            tableIns.reload({
                where:{
                    "realName":realName,
                    "phone":phone,
                    "address":address,
                }
            })
        })

        // 表格头工具栏监听事件
        table.on("toolbar(dataTableFilter)",function (d) {
          let event = d.event ;
          if (event == "add"){
              add();
          }else if(event == "resetPwd"){
              resetPwd();
          }
        });
        // 行工具栏事件
        table.on("tool(dataTableFilter)",function (d) {
            let event = d.event ;
            let rowData = d.data ;
            if (event=="resetPwd"){
                resetPwd(rowData);
            }else if(event == "setRoles"){
                setRoles(rowData) ;
            }
        })

        /**
         * 新增用户方法
         */
        function add() {
            // 弹出层参数
            layer.open({
                title:"新增用户",
                type:1,
                content:$("#addUserTpl").html(),
                area:['680px','480px'],
                success:function (layero,index) {
                    form.render("radio");
                    form.on("submit(submitFilter)",function (d) {
                        let formData = d.field ;
                        $.post(cxt +"/sysuser/add.do",formData,function (rs) {
                            layer.msg(rs.msg) ; // 展示业务消息
                            if (rs.code!=200){
                                return false ;
                            }
                            layer.close(index) ;  // 关闭弹层
                            // 刷新表格
                            $("#searchBtn").click();
                        })
                        return false ;
                    })
                },
                btn:['确认','取消'],
                yes:function (index,layero) {
                    // 点击隐藏的提交按钮 触发表单提交监听
                    $("#subBtn").click()
                }
            })
        }

        /**
         * 重置密码方法
         * @param rowData
         */
        function resetPwd(rowData) {
            layer.confirm("确定要重置密码吗?",function (index) {
                $.get(cxt + "/sysuser/reset.do",{id:rowData.id},function (rs) {
                    layer.msg(rs.msg) ; // 展示业务消息
                    layer.close(index) ;// 关闭弹出层
                    $("#searchBtn").click()  // 重载表格
                })
            })
        }

        /**
         * 显示用户头像的方法
         * @param url 用户头像地址
         */
        window.showUserImg = function (url) {
            let imgData = {
                "title": "用户头像", //相册标题
                "id": 123, //相册id
                "data": [   //相册包含的图片，数组格式
                    {
                        "alt": "用户头像",
                        "src": cxt +"/" + url, //原图地址
                        "thumb": cxt +"/" + url //缩略图地址
                    }
                ]
            }
            // 展示
            layer.photos({
                photos: imgData
            });
        };


        function setRoles(rowData) {
            let layerIns = {
                title:"用户角色管理",
                type: 1,
                content:$("#setRoleTpl").html(),
                area:['400px','400px'],
                success:function (layero,index) {
                    //1、获取所有的额角色
                    /**
                     *  <div class="layui-form-item">
                            <div class="layui-input-inline" style="width: 250px;">
                                 <input type="checkbox" lay-skin="primary" title="管理员">
                                <input type="checkbox" lay-skin="primary" title="管理员">
                            </div>
                         </div>
                     */
                    $.get(cxt + "/role/all.do",function (rs) { // 2条 3条
                        // 所有的角色
                        let roles = rs.data ;
                        // 角色容器
                        let rolesContent = $("#allRoles") ;
                        let header = '<div class="layui-form-item"> <div class="layui-input-inline" style="width: 250px;">'
                        let footer = '</div> </div>'
                        let content = "" ;
                        // 循环拼接 所有的额角色
                        for (let i=0;i<roles.length;i++){
                            let role = roles[i] ;
                            let id = role.id ;
                            let name = role.name ;
                            let body = '<input id="checkbox'+id+'" type="checkbox" name="roleId" value="'+id+'" title="'+name+'">' ;
                            //  第一次循环拼接头
                            if (i%2 == 0){
                                content = content + header ;
                            }
                            content = content + body ;
                            //  第二次循环拼接尾
                           if (i%2==1){
                               content = content + footer ;
                           }
                        }
                        if (roles.length%2 !=0){
                            content = content + footer ;
                        }
                        rolesContent.append(content) ;
                        form.render("checkbox") ;

                        $.get(cxt +"/role/userRoles.do",{userId:rowData.id},function(rs) {
                            debugger ;
                            // 获取当前用户的所有角色
                            let userRoles = rs.data ;
                            // true 有角色 有角色选中角色
                            if (userRoles){
                                for (roles of userRoles) {
                                    let id = roles.id ;
                                    let checkboxId = "checkbox" + id ;
                                    // 选中这个id 对应的CheckBox 设置为check
                                    // 属性选择器 value=id
                                    $("#"+checkboxId).prop("checked",true) ;
                                }
                            }
                            form.render("checkbox");
                        });
                    })
                    form.on('submit(submitBtnFilter)',function (d) {
                        let checkBox = $("#allRoles :checked") ;

                        let roleIds = new  Array() ;
                        $.each(checkBox,function (index,object) {
                            let roleId = $(object).val() ;
                            roleIds.push("roleId="+roleId) ;
                            console.log(roleIds)
                        })
                        let userId = rowData.id ;
                        let userRoleIds = roleIds.join("&") ;
                        console.log(userRoleIds)
                        $.post(cxt + "/role/setRole.do?userId="+userId+"&"+userRoleIds,function (rs) {
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

    })
</script>