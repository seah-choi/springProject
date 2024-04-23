<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2024-04-17
  Time: 오전 9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Modify</title>
    <style>
        .card-body {
            width: 800px;
            margin: 0 auto;
            padding-top: 250px;
        }
        h1 {
            text-align: center;
        }
        #btn {
            display: flex;
            justify-content: center;
        }
        #btn button {
            font-size: medium;
            width: 200px;
        }
    </style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<%@ include file="../common/header.jsp"%>
<form name="frm" id="frm" method="post" action="/bbs/modify">
    <input type="hidden" name="idx" id="idx" value="${bbs.idx}">
    <div class="card-body">
        <h1>Modify</h1>
        <br>
        <div class="form-floating mb-3">
            <input type="text" class="form-control" name="user_id" id="user_id" value="${bbs.user_id}" maxlength="20" placeholder="id">
            <label for="title">id</label>
            <div id="div_err_user_id" style="display: none "></div>
        </div>
        <div class="form-floating">
            <input type="text" class="form-control" name="title" id="title" value="${bbs.title}" maxlength="100" placeholder="title">
            <label for="title">title</label>
            <div id="div_err_title" style="display: none "></div>
        </div>
        <br>
        <div class="form-floating">
            <textarea class="form-control" placeholder="Leave a comment here" name="content" id="content" style="height: 100px">${bbs.content}</textarea>
            <label for="content">Comments</label>
            <div id="div_err_content" style="display: none "></div>
        </div>
        <br>
        <div>
            <span>등록일 : <input type="date" name="display_date" id="display_date" value="${bbs.display_date}" ></span>
            <div id="div_err_display_date" style="display: none "></div>
        </div>
        <br>
        <label>관심사항 : </label>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="checkbox" name="interest" id="interest_0" value="스포츠" <c:out value="${bbs.interest.contains(\"스포츠\") ? 'checked' :''}"></c:out>>
            <label class="form-check-label" for="interest_0">스포츠</label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="checkbox" name="interest" id="interest_1" value="게임" <c:out value="${bbs.interest.contains(\"게임\") ? 'checked' :''}"></c:out>>
            <label class="form-check-label" for="interest_1">게임</label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="checkbox" name="interest" id="interest_2" value="여행" <c:out value="${bbs.interest.contains(\"여행\") ? 'checked' :''}"></c:out>>
            <label class="form-check-label" for="interest_2">여행</label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="checkbox" name="interest" id="interest_3" value="영화" <c:out value="${bbs.interest.contains(\"영화\") ? 'checked' :''}"></c:out>>
            <label class="form-check-label" for="interest_3">영화</label>
            <div id="div_err_interest" style="display: none "></div>
        </div>
        <br><br>
        <div id="btn">
            <button type="submit" class="btn btn-primary btn-lg">수정</button>&nbsp;
            <button type="reset" class="btn btn-secondary btn-lg">취소</button>
        </div>
    </div>
</form>
<%@ include file="../common/footer.jsp"%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
