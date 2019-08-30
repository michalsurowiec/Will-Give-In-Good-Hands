<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pl">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="../../../resources/css/style.css"/>" />
  </head>
  <body>
    <header>

      <%@ include file="elements/header.jsp"%>

    </header>

    <h2>Panel użytkownika</h2>

    <section class="about-us">
      <div class="about-us--text">
        <h2>Twoje dane</h2>
        <p>Imie: ${currentUser.user.name}<br>
        Nazwisko: ${currentUser.user.surname}<br>
        E-mail: ${currentUser.user.email}<br>
        </p>
        <p>
        <a href="/user/update" class="btn btn--small btn--highlighted">Edytuj</a>
        <a href="/user/updatePassword" class="btn btn--small btn--highlighted">Zmień hasło</a>
        </p>
      </div>
    </section>

    <section class="help">

      <h2>Lista nieodebranych donacji</h2>

      <div class="help--slides active" data-id="1">
        <ul class="help--slides-items">
          <c:forEach items="${notReceived}" var="notReceived">
            <li>
              <div class="col">
                <div class="title">${notReceived.quantity} worki dla
                  <c:forEach items="${institutions}" var="institution">
                    <c:if test="${institution.id == notReceived.institution}">"${institution.name}".</c:if>
                  </c:forEach>
                  <a href="/donation/details/<c:out value="${notReceived.id}"/>" class="btn btn--small btn--highlighted">Szczegóły</a>
                </div>
                <div class="subtitle">
                  Data utworzenia: ${notReceived.creationDate}
                </div>
              </div>
            </li>
          </c:forEach>
        </ul>
      </div>

      <h2>Lista odebranych donacji</h2>

      <div class="help--slides active" data-id="1">
        <ul class="help--slides-items">
          <c:forEach items="${received}" var="received">
            <li>
              <div class="col">
                <div class="title">${received.quantity} worki dla
                  <c:forEach items="${institutions}" var="institution">
                    <c:if test="${institution.id == received.institution}">"${institution.name}".</c:if>
                  </c:forEach>
                  <a href="/donation/details/<c:out value="${received.id}"/>" class="btn btn--small btn--highlighted">Szczegóły</a>
                </div>
                <div class="subtitle">
                  Data utworzenia: ${received.creationDate}<br>
                  Data odebrania: ${received.confirmedPickUpDate}
                </div>
              </div>
            </li>
          </c:forEach>
        </ul>
      </div>
    </section>

    <script src="<c:url value="../../../resources/js/app.js"/>"></script>
  </body>
</html>
