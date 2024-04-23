<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<html>
<head>
    <title>List</title>
    <style>
        #content a:link, #content a:visited {
            color : black;
            text-decoration: none ;
        }
        #content a:hover, #content a:focus {
            color : #0472f6;
            font-weight: bold;
        }
        .card-body {
            width: 1100px;
            margin: 0 auto;
        }
        h1 {
            text-align: center;
        }
        .card-body ul {
            list-style: none;
        }
    </style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
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
            <li><a href="/member/view?user_id=${member.user_id}" class="nav-link px-2">MyPage</a></li>
        </ul>

        <div class="col-md-3 text-end">
            <span style="font-weight: bold">${member.name}</span> 님&nbsp;
            <button type="button" class="btn btn-primary" onclick="location.href='/login/logout'">Logout</button>
        </div>
    </header>
</div>
</div>
<div class="card-body">
    <h1 style="margin-bottom: 50px;">List</h1>
    <div id="content">
        <form id="frm" name="frm" action="/bbs/list" method="get">
        <div class="form-check form-check-inline">
            <c:set value="${fn:join(responseDTO.search_type,'')}" var="search_type"/>
            <input class="form-check-input" type="checkbox" id="search_type_1" name="search_type" value="t" <c:if test='${fn:contains(search_type, "t")}'>checked</c:if>>
            <label class="form-check-label" for="search_type_1">제목</label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="checkbox" id="search_type_2" name="search_type" value="u" <c:if test='${fn:contains(search_type, "u")}'>checked</c:if>>
            <label class="form-check-label" for="search_type_2">작성자</label>
        </div>
        <br>
        <input class="form-control" type="text" placeholder="검색어" aria-label="default input example" name="search_word" id="search_word" value='<c:out value="${pageRequestDTO.search_word}"/>' style="width: 805px; margin-bottom: 5px;">
        <div class="form-floating mb-3" style="display: flex;">
            <input type="date" class="form-control" name="search_date1" id="search_date1" maxlength="100" placeholder="등록일 시작" value='<c:out value="${pageRequestDTO.search_date1}"/>' style="width: 400px; margin-right: 5px;">
            <label for="search_date1">등록일 시작</label>
            <input type="date" class="form-control" name="search_date2" id="search_date2" maxlength="100" placeholder="등록일 끝" value='<c:out value="${pageRequestDTO.search_date2}"/>' style="width: 400px">
            <label for="search_date2">등록일 끝</label>
            <div id="btn" style="margin-left: 20px; margin-top: 5px;">
                <button type="submit" class="btn btn-primary btn-lg" style="width: 100px;">Search</button>
                <button type="reset" class="btn btn-secondary btn-lg" style="width: 100px;">Clear</button>
            </div>
        </div>
        <br>
            <p>총 게시글 수 : ${requestDTO.total_count}</p>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">제목</th>
                <th scope="col">작성자</th>
                <th scope="col">등록일</th>
                <th scope="col">조회수</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${responseDTO.dtoList}" var="list">
                <tr>
                    <th scope="row">${list.idx}</th>
                    <td>
                        <a href="/bbs/view?idx=${list.idx}">
                        <c:out value="${fn:substring(list.title,0,10)}"/>
                        <c:if test="${fn:length(list.title) > 10}"> ...</c:if></a></td>
                    <td>${list.user_id}</td>
                    <td>${list.display_date}</td>
                    <td>${list.read_cnt}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </form>
    </div>
    <br><br>
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <li class="page-item" <c:if test="${responseDTO.prev_page_flag ne true}">disabled</c:if>">
                <a class="page-link" data-num="
                <c:choose>
                    <c:when test="${responseDTO.prev_page_flag}">
                        ${responseDTO.page_block_start-1}
                    </c:when>
                    <c:otherwise>1</c:otherwise>
                </c:choose>" href="<c:choose>
                    <c:when test="${responseDTO.prev_page_flag}">
                        ${responseDTO.linkParams}&page=${responseDTO.page_block_start-1}
                    </c:when>
                    <c:otherwise>#</c:otherwise>
                </c:choose>">&laquo;</a>
            </li>
            <c:forEach begin="${responseDTO.page_block_start}"
                       end="${responseDTO.page_block_end}"
                       var="page_num">
            <li class="page-item"
                <c:if test="${responseDTO.page == page_num}">active</c:if>">
                <a class="page-link" data-num="${page_num}"
                   href="<c:choose>
                                <c:when test="${responseDTO.page == page_num}">#</c:when>
                                <c:otherwise>
                                    ${responseDTO.linkParams}&page=${page_num}
                                </c:otherwise>
                             </c:choose>">${page_num}</a>
            </li>
            </c:forEach>
            <li class="page-item
                    <c:if test="${responseDTO.next_page_flag ne true}">disabled</c:if>">
                <a class="page-link"
                   data-num="
                    <c:choose>
                        <c:when test="${responseDTO.next_page_flag}">
                            ${responseDTO.page_block_end+1}
                        </c:when>
                        <c:otherwise>
                            ${responseDTO.page_block_end}
                        </c:otherwise>
                    </c:choose>"
                   href="<c:choose>
                        <c:when test="${responseDTO.next_page_flag}">
                            ${responseDTO.linkParams}&page=${responseDTO.page_block_end+1}</c:when>
                        <c:otherwise>#</c:otherwise>
                    </c:choose>">&raquo;</a>

            </li>
        </ul>
    </nav>
</div>
<%@ include file="../common/footer.jsp"%>
</body>
</html>