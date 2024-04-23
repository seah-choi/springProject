<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2024-04-17
  Time: 오후 4:29
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<html>
<head>
    <title>회원가입</title>
    <style>
        .card-body {
            width: 450px;
            margin: 0 auto;
            margin-top: 50px;
        }
        .form-floating {
            margin-top: 15px;
        }
        #head {
            text-align: center;
        }
    </style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <%--        <h1>Header</h1>--%>
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
                    <li><a href="/member/view" class="nav-link px-2">MyPage</a></li>
                </ul>

                <div class="col-md-3 text-end">
                    <button type="button" class="btn btn-outline-primary me-2" onclick="location.href='/login/login'">Login</button>
                    <button type="button" class="btn btn-primary" onclick="location.href='/member/join'">Sign-up</button>
                </div>
            </header>
        </div>
    </div>
</div>
<form name="frm" id="frm" method="post" action="/member/join">
    <div class="card-body">
        <div id="head">
        <i class="bi bi-person-fill"></i>
        <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="currentColor" class="bi bi-person-fill" viewBox="0 0 16 16">
            <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6"/>
        </svg>
        <h1>Join</h1>
        </div>
        <br>
        <div class="form-floating mb-3" style="display: flex">
            <input type="text" class="form-control" name="user_id" id="user_id" value="${member.user_id}" maxlength="20" placeholder="id" style="width: 330px;">
            <label for="user_id">id</label>
            <div class="col-auto">
                <button class="btn btn-primary" type="button" id="button-addon2" style="height: 55px;" id="idCheck" onclick="idCheck();">id_check</button>
            </div>
        </div>
        <div id="result_checkId"></div>
        <div id="div_err_user_id" style="display: none "></div>
        <div class="form-floating mb-3">
            <input type="password" class="form-control" name="pwd" id="pwd" value="${member.pwd}" maxlength="100" placeholder="password">
            <label for="pwd">password</label>
            <div id="div_err_pwd" style="display: none "></div>
        </div>
        <div class="form-floating mb-3">
            <input type="text" class="form-control" name="name" id="name" value="${member.name}" maxlength="20" placeholder="name">
            <label for="name">name</label>
            <div id="div_err_name" style="display: none "></div>
        </div>
        <div class="form-floating mb-3">
            <input type="email" class="form-control" name="email" id="email" value="${member.email}" maxlength="100" placeholder="email">
            <label for="email">email</label>
            <div id="div_err_email" style="display: none "></div>
        </div>
        <div class="input-group">
            <span class="input-group-text">주소1/주소2</span>
            <input type="text" aria-label="addr1" name="addr1" id="addr1" class="form-control" value="${member.addr1}">
            <input type="text" aria-label="addr2" name="addr2" id="addr2" class="form-control" value="${member.addr2}">

        </div>
        <div id="div_err_addr1" style="display: none "></div>
        <div id="div_err_addr2" style="display: none "></div>
        <br>
        <div class="form-floating mb-3">
            <input type="date" class="form-control" name="birthday" id="birthday" value="${member.birthday}" maxlength="100" placeholder="birthday(YYYY-MM-DD)">
            <label for="birthday">birthday</label>
            <div id="div_err_birthday" style="display: none "></div>
        </div>
        <br>
        <label>관심사항 : </label>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="checkbox" name="interest" id="interest_0" value="스포츠" <c:out value="${member.interest.contains(\"스포츠\") ? 'checked' :''}"></c:out>>
            <label class="form-check-label" for="interest_0">스포츠</label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="checkbox" name="interest" id="interest_1" value="게임" <c:out value="${member.interest.contains(\"게임\") ? 'checked' :''}"></c:out>>
            <label class="form-check-label" for="interest_1">게임</label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="checkbox" name="interest" id="interest_2" value="여행" <c:out value="${member.interest.contains(\"여행\") ? 'checked' :''}"></c:out>>
            <label class="form-check-label" for="interest_2">여행</label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="checkbox" name="interest" id="interest_3" value="영화" <c:out value="${member.interest.contains(\"영화\") ? 'checked' :''}"></c:out>>
            <label class="form-check-label" for="interest_3">영화</label>
        </div>
        <div id="div_err_interest" style="display: none "></div>
        <br><br>
        <div id="btn">
            <button class="w-100 btn btn-lg btn-primary" type="submit" id="btn_join">Join</button>
        </div>
    </div>
</form>
<%@ include file="../common/footer.jsp"%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script>
    const severValResult = {};
    <c:forEach items="${errors}" var="err">
    if(document.getElementById("div_err_${err.getField()}") != null ) {
        document.getElementById("div_err_${err.getField()}").innerHTML = "<span style='color: red'>${err.getField()}를 다시 입력해주세요. </span>";
        document.getElementById("div_err_${err.getField()}").style.display = "block";
    }
    severValResult['${err.getField()}'] = '${err.defaultMessage}';
    </c:forEach>

    console.log(severValResult);

    //아이디 중복체크
    function idCheck() {
        let user_id = $('#user_id').val();

        if(user_id != null || !(user_id.isEmpty()) || user_id != "") {
            $.ajax({
                type: "post",
                url: "/member/idCheck",
                data: {"user_id": user_id},
                success: function (data) {
                    console.log(user_id);

                    if (data == "N") {
                        let msg = "사용 가능한 아이디입니다.";
                        $("#result_checkId").html(msg).css("color", "green");
                        $("#div_err_user_id").css("display", "none");
                        //alert("사용 가능한 아이디입니다.");
                    } else {
                        let msg = "이미 사용 중인 아이디입니다.";
                        $("#result_checkId").html(msg).css("color", "red");
                        $("#div_err_user_id").css("display", "none");
                        //alert("중복 아이디입니다.");
                    }

                },
                error: function (error) {
                    alert(error);
                }
            });
        }
    }

</script>
</body>
</html>
