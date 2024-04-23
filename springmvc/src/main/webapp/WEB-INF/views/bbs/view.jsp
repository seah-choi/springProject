<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2024-04-17
  Time: 오전 9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<html>
<head>
    <title>View</title>
    <style>
        .card-body {
            width: 600px;
            margin: 0 auto;
            padding-top: 150px;
            padding-bottom: 150px;
        }
        h1 {
            text-align: center;
            margin-bottom: 30px;
        }
        #btn {
            display: flex;
            justify-content: center;
        }
        #btn button {
            font-size: medium;
            width: 100px;
            margin: 5px;
        }
        #interest {
            background-color: #b5e0f1;
            border-radius: 10px;
            padding: 5px;
        }
        #content {
            padding-bottom: 150px;
        }
    </style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<%@ include file="../common/header.jsp"%>
<div id="content">
<form name="frm" id="frm" action="/bbs/delete" method="post">
    <input type="hidden" name="idx" id="idx" value="${bbs.idx}">

    <div class="card-body">
        <h1>View</h1>
        <h2>${bbs.title}</h2>
        <i class="bi bi-person-square"></i>
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-square" viewBox="0 0 16 16">
            <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0"/>
            <path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2zm12 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1v-1c0-1-1-4-6-4s-6 3-6 4v1a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1z"/>
        </svg>
        <span>${bbs.user_id}</span>
        <span id="date">(${bbs.display_date})</span>
        <hr>
        <div>
           <p>${bbs.content}</p>
        </div>
        <div>
            <span id="interest">${bbs.interest}</span>
        </div>
        <br>
        <div id="btn">
            <button type="button" class="btn btn-primary btn-lg" onclick="location.href='/bbs/list'" style="font-size: medium;">목록</button>&nbsp;&nbsp;
            <button type="button" id="btn_modify" class="btn btn-primary btn-lg"  style="font-size: medium;">수정</button>&nbsp;&nbsp;
            <button type="button" class="btn btn-secondary btn-lg" onclick="goDelete()" style="font-size: medium;">삭제</button>
        </div>
    </div>
</form>
</div>
<%@ include file="../common/footer.jsp"%>
<script>
    function goDelete(){
        const frm = document.getElementById("frm");

        let login_id = `${member.user_id}`;
        let post_id = `${bbs.user_id}`;

        if(login_id == post_id) {
            let confirm_flag = confirm("해당 게시글을 삭제하시겠습니까?");
            if(confirm_flag) {
                frm.submit();
            }
        } else {
            alert("작성자만 삭제할 수 있습니다.");
        }
    }

    document.querySelector("#btn_modify").addEventListener("click", function () {
        let login_id = `${member.user_id}`;
        let post_id = `${bbs.user_id}`;

        if(login_id == post_id) {
            confirm("해당 게시글을 수정하시겠습니까?");
            location.href='/bbs/modify?idx=${bbs.idx}';
        } else {
            alert("작성자만 수정할 수 있습니다.");
            return false;
        }
    })
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
