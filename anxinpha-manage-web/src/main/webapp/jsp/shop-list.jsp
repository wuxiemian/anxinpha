<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="../lib/html5shiv.js"></script>
    <script type="text/javascript" src="../lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="../static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="../static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="../lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="../static/h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="../static/h-ui.admin/css/style.css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="../lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>店铺管理</title>
</head>
<style>
    .table>tbody>tr>td{
        text-align:center;
    }
</style>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 店铺中心 <span class="c-gray en">&gt;</span> 店铺管理 <a id="btn-refresh" class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <form id="form-search" class="text-c"> 日期范围：
        <input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'maxDate\')||\'%y-%M-%d\'}' })" id="minDate" name="minDate" class="input-text Wdate" style="width:120px;">
        -
        <input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'minDate\')}',maxDate:'%y-%M-%d' })" id="maxDate" name="maxDate" class="input-text Wdate" style="width:120px;">
        <input type="text" class="input-text" style="width:250px" placeholder="输入店铺名称" id="searchKey" name="searchKey">
        <button id="searchButton" type="submit" class="btn btn-success radius" id="" name=""><i class="Hui-iconfont">&#xe665;</i> 搜店铺</button>
    </form>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l">
            <a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
            <a href="javascript:;" onclick="shop_add('添加店铺','shop-add')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加店铺</a>
        </span>
        <span class="r">共有数据：<strong id="shopListCount">0</strong> 条</span>
    </div>
    <div class="mt-20" style="margin-bottom: 70px">
        <table class="table table-border table-bordered table-hover table-bg table-sort" width="100%">
            <thead>
            <tr class="text-c">
                <th width="25"><input type="checkbox" value="" name=""></th>
                <th width="40">ID</th>
                <th width="200">店铺名称</th>
                <th width="300">店铺图标</th>
                <th width="600">店铺描述</th>
                <th width="200">创建时间</th>
                <th width="70">操作</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="../lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="../lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="../static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="../lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="../lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="../lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="../lib/datatables/dataTables.colReorder.min.js"></script>
<script type="text/javascript" src="../lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="../lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="../lib/common.js"></script>
<script type="text/javascript">

    $(document).ready(function () {
        $('.table').DataTable({
            "processing": true,//加载显示提示
            "ajax": {
                url:baseurl+"/item/shop/list",
                type: 'GET',
            },
            "columns": [
                { "data": null,
                    render : function(data,type, row, meta) {
                        return "<input name=\"checkbox\" value=\""+row.id+"\" type=\"checkbox\" value=\"\">";
                    }
                },
                { "data": "id"},
                { "data": "shopName",
                    render: function(data,type, row, meta){
                        return "<u style=\"cursor:pointer\" class=\"text-primary\" onclick=\"shop_show('店铺商品列表','shop-show',"+row.id+")\">"+data+"</a>";
                    }
                },
                { "data": "shopIcon",
                    render: function (data, type, row, meta) {
                        return "<img width='80' height='80' src='"+data+"' />";
                    }
                },
                { "data": "shopDesc"},
                {"data":"created",
                    render: function (data, type, row, meta) {
                        return date(data);
                    }
                },
                {"data": null,
                    render: function (data, type, row, meta) {
                        return "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"shop_edit('店铺编辑','shop-edit',"+row.id+")\" href=\"javascript:;\" title=\"编辑\"><i class=\"Hui-iconfont\">&#xe6df;</i></a> <a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"shop_del(this,"+row.id+")\" href=\"javascript:;\" title=\"删除\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>";
                    }
                }
            ],
            "aaSorting": [[ 1, "asc" ]],//默认第几个排序
            "bStateSave": false,//状态保存
            "aoColumnDefs": [
                {"orderable":false,"aTargets":[0,3,5]}// 制定列不参与排序
            ],
            language: {
                url: '/../lib/datatables/Chinese.json'
            },
            colReorder: true
        });
    });

        shop_count();

    /*统计店铺数*/
    function shop_count(){
        $.ajax({
            url:baseurl+"/item/shop/count",
            type:"GET",
            success:function (data) {
                $("#shopListCount").html(data.recordsTotal);
            },
            error:function(XMLHttpRequest){
                layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
            }
        });
    }

    var Id="",shopName="",shopIcon="",shopDesc="",created="";

    /*用户-添加*/
    function shop_add(title,url,w,h){
        layer_show(title,url,w,h);
    }
    /*用户-查看*/
    function shop_show(title,url,id,w,h){
        Id = id
        var table = $('.table').DataTable();
        $('.table tbody').on( 'click', 'tr', function () {
            shopName = table.row(this).data().shopName;
            shopIcon = table.row(this).data().shopIcon;
        });
        layer_show(title,url,w,h);
    }

    /*用户-编辑*/
    function shop_edit(title,url,id,w,h){
        Id=id;
        var table = $('.table').DataTable();
        $('.table tbody').on( 'click', 'tr', function () {
            shopName = table.row(this).data().shopName;
            shopIcon = table.row(this).data().shopIcon;
            shopDesc = table.row(this).data().shopDesc;
            created = table.row(this).data().created;
        });
        layer_show(title,url,w,h);
    }

    /*店铺-删除*/
    function shop_del(obj,id){
        layer.confirm('确认要删除ID为\''+id+'\'的店铺吗？',{icon:0},function(index){
            var index = layer.load(3);
            $.ajax({
                type: 'DELETE',
                url: baseurl+'/item/shop/'+id,
                success: function(data){
                    layer.close(index);
                    shop_count();
                    refresh();
                    layer.msg('已删除!',{icon:1,time:1000});
                },
                error:function(XMLHttpRequest){
                    layer.close(index);
                    layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
                }
            });
        });
    }

    /*批量删除*/
    function datadel() {
        var cks=document.getElementsByName("checkbox");
        var count=0,ids="";
        for(var i=0;i<cks.length;i++){
            if(cks[i].checked){
                count++;
                ids+=cks[i].value+",";
            }
        }
        if(count==0){
            layer.msg('您还未勾选任何数据!',{icon:5,time:3000});
            return;
        }
        /*去除末尾逗号*/
        if(ids.length>0){
            ids=ids.substring(0,ids.length-1);
        }
        layer.confirm('确认要删除所选的'+count+'条数据吗？',{icon:0},function(index){
            var index = layer.load(3);
            $.ajax({
                type: 'DELETE',
                url: baseurl+'/item/shop/remove/'+ids,
                success:function(data){
                    layer.close(index);
                    layer.msg('已删除!',{icon:1,time:1000});
                    shop_count();
                    refresh();
                },
                error:function(XMLHttpRequest){
                    layer.close(index);
                    layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
                }
            });
        });
    }

    function msgSuccess(content){
        layer.msg(content, {icon: 1,time:3000});
    }
    /*多条件查询*/
    $("#form-search").validate({
        rules:{
            minDate:{
                required:false,
            },
            maxDate:{
                required:false,
            },
            searchKey:{
                required:false,
            },
        },
        onkeyup:false,
        focusCleanup:false,
        success:"valid",
        submitHandler:function(form){
            var searchKey= $('#searchKey').val();
            var minDate= $('#minDate').val();
            var maxDate= $('#maxDate').val();
            var param = {
                "searchKey": searchKey,
                "minDate": minDate,
                "maxDate":maxDate
            };
            var table = $('.table').DataTable();
            table.settings()[0].ajax.data = param;
            table.ajax.reload();
        }
    });
</script>
</body>
</html>
