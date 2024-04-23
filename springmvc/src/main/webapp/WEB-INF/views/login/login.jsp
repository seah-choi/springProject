<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2024-04-17
  Time: 오후 4:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<html>
<head>
    <title>로그인</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.88.1">
    <link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/sign-in/">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="/docs/5.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="apple-touch-icon" href="/docs/5.1/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
    <link rel="icon" href="/docs/5.1/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
    <link rel="icon" href="/docs/5.1/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
    <link rel="manifest" href="/docs/5.1/assets/img/favicons/manifest.json">
    <link rel="mask-icon" href="/docs/5.1/assets/img/favicons/safari-pinned-tab.svg" color="#7952b3">
    <link rel="icon" href="/docs/5.1/assets/img/favicons/favicon.ico">
    <meta name="theme-color" content="#7952b3">
    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
        .form-signin {
            width: 400px;
            margin: 0 auto;
            padding-top: 150px;
            padding-bottom: 150px;
        }
        .form-floating {
            margin-top: 15px;
        }
    </style>
    <link href="signin.css" rel="stylesheet">
</head>
    <input type="hidden" name="acc_url" id="acc_url" value="${acc_url}">

<body class="text-center">
<div class="container">
    <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
        <div class="col-md-3 mb-2 mb-md-0">
            <a href="/" class="d-inline-flex link-body-emphasis text-decoration-none">
                <svg class="bi" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"/></svg>
            </a>
        </div>

        <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
            <li><a href="/index.jsp" class="nav-link px-2">Home</a></li>
            <li><a href="/bbs/list" class="nav-link px-2">List</a></li>
            <li><a href="/bbs/regist" class="nav-link px-2">Regist</a></li>
            <li><a href="/member/view?user_id=${member['user_id']}" class="nav-link px-2">MyPage</a></li>
        </ul>

        <div class="col-md-3 text-end">
            <button type="button" class="btn btn-outline-primary me-2" onclick="location.href='/login/login'">Login</button>
            <button type="button" class="btn btn-primary" onclick="location.href='/member/join'">Sign-up</button>
        </div>
    </header>
</div>
<main class="form-signin">
    <form name="frm" id="frm" action="/login/login" method="post">
        <i class="bi bi-person-fill"></i>
        <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="currentColor" class="bi bi-person-fill" viewBox="0 0 16 16">
            <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6"/>
        </svg>
        <h1 class="h3 mb-3 fw-normal">Please Login</h1>

        <div class="form-floating">
            <input type="text" class="form-control" name="user_id" id="user_id"  maxlength="20" placeholder="id" value="${cookie.save_id.value}">
            <label for="user_id">id</label>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control" name="pwd" id="pwd"  maxlength="20" placeholder="Password">
            <label for="pwd">Password</label>
        </div>
        <div>
            <c:if test="${not empty loginErr}"><span style="color: red">${loginErr}</span></c:if>
        </div>
        <br>
        <div class="checkbox mb-3">
            <label>
                <input type="checkbox" name="save_id" id="save_id" value="Y" <c:if test="${cookie.save_id.value != null}">checked</c:if>> 아이디저장&nbsp;
            </label>
            <label>
                <input type="checkbox" name="auto_login" id="auto_login" value="Y"> 자동로그인
            </label>
        </div>
        <button class="w-100 btn btn-lg btn-primary" type="submit" id="btn_login">Login</button>
    </form>
</main>
<%@ include file="../common/footer.jsp"%>
<script>
    const btn_login = document.getElementById("btn_login");
    const frm = document.getElementById("frm");

    btn_login.addEventListener("click", function (e) {
        e.preventDefault();
        e.stopPropagation();

        frm.method = "post";
        frm.submit();
    }, false)
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
