
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/layout/head.jsp" %>
<%@include file="/layout/navigation.jsp" %>

<div class="main container">
    <div class="row">
        <div class="col-lg-3">
            <!-- AVATAR -->
            
                <c:choose>
                    <c:when test="${empty user.avatar_name}">
                        <img src="${pageContext.request.contextPath}/res/images/default_avatar.png" class="avatar" />
                    </c:when>
                    <c:otherwise>
                        <img src="${pageContext.request.contextPath}/resource/avatar/${user.id}_${user.avatar_name}" class="avatar" />
                    </c:otherwise>
                </c:choose>
            
        </div>
        <div class="col-lg-9">
            <c:choose>
                <c:when test="${empty user.last_login_date}">
                    <h1>Benvenuto, ${user.username}! </h1>
                </c:when>
                <c:otherwise>
                    <h1>Bentornato, ${user.username}! </h1><p>È da ${user.last_login_date} che non ci vediamo.<p />
                </c:otherwise>
            </c:choose>

        </div>
        <div>
            <c:forEach var="p" items="${posts}">
                        Post di: <c:out value="${p.writer.username}" /><br />
                        Testo: <c:out value="${p.text}" /> <br />
                        <br />
             </c:forEach>
        </div>
    </div>
</div>
<%@include file="/layout/foot.jsp" %>
