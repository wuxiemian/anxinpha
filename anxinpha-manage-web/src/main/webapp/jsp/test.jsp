<%--
  Created by IntelliJ IDEA.
  User: wumian
  Date: 2020/5/8
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>test</h1>
<div id="test">

</div>
<script type="text/javascript" src="../lib/wangeditor/wangEditor.min.js"></script>
<script type="text/javascript">
    var E = window.wangEditor;
    var editor = new E('#test');
    editor.customConfig.uploadImgServer = 'http://api.anxinpha.com/api/upload/imageUpload';
    editor.create();
</script>
</body>
</html>
