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

    <section class="help">
      <h2>Lista użytkowników</h2>

      <div class="help--slides active" data-id="1">
        <ul class="help--slides-items">
          <c:forEach items="${users}" var="user">
            <li>
              <div class="col">
                <div class="title">${user.id}. ${user.name} ${user.surname} ${user.email}
                  <a href="/admin/adminCRUD/update/<c:out value="${user.id}"/>" class="btn btn--small btn--highlighted">Edytuj</a>
                  <a href="/admin/adminCRUD/delete/<c:out value="${user.id}"/>" class="btn btn--small btn--highlighted">Usuń</a>
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
