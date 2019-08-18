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

    <section class="steps">
      <h2>Panel Admina</h2>

      <div class="steps--container">
        <div class="steps--item">
          <a href="/admin/adminCRUD/main" class="btn btn--small btn--highlighted">Zarządzanie administratorami</a>
        </div>
        <div class="steps--item">
          <a href="/admin/userCRUD/main" class="btn btn--small btn--highlighted">Zarządzanie użytkownikami</a>
        </div>
        <div class="steps--item">
          <a href="/admin/institutionCRUD/main" class="btn btn--small btn--highlighted">Zarządzanie instytucjami</a>
        </div>
      </div>
    </section>

    <script src="<c:url value="../../../resources/js/app.js"/>"></script>
  </body>
</html>
