
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
    </div>

    <c:choose>
        <c:when test="${empty posts}">
            <div class="row box"><p>Non vi sono novità! <a href="/SecondoProgettoWeb/HomeServlet?next=groups">Inizia una discussione</a>.</p></div>
        </c:when>
        <c:otherwise>
            <c:forEach var="p" items="${posts}">
                <c:if test="${p.writer.email != user.email}">
                    <div class="row box">
                        <div class="col-md-2">
                            <!-- AVATAR -->        
                            <c:choose>
                                <c:when test="${empty p.writer.avatar_name}">
                                    <img src="${pageContext.request.contextPath}/res/images/default_avatar.png" class="avatar" />
                                </c:when>
                                <c:otherwise>
                                    <img src="${pageContext.request.contextPath}/resource/avatar/${p.writer.id}_${p.writer.avatar_name}" class="avatar" />
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="col-md-10">

                            <div class="row">
                                <div class="col-md-12">
                                    <p><c:out value="${p.text}" escapeXml="false" /></p>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-5">
                                    <p>
                                        Scritto da ${p.writer.username} alle ${p.creation_date} in <a href="ShowGroupServlet?id_crew=${p.crew.id}">${p.crew.name}</a>
                                    </p>
                                </div>
                                <div class="col-md-5">
                                    <c:if test="${not empty p.files}">
                                        File allegati <ul class="files_list">
                                            <c:forEach var="f" items="${p.files}">
                                                <li><a href="${pageContext.request.contextPath}/resource/files/${p.crew.id}/${f.id}-${f.name}" target="_blank" >${f.id}-${f.name}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </c:forEach>

        </c:otherwise>
    </c:choose>

</div>
<%@include file="/layout/foot.jsp" %>
