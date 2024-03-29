<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="pl">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="../../../resources/css/style.css"/>"/>
  </head>
  <body>
    <header>

      <%@ include file="elements/header.jsp"%>

    </header>

    <section class="login-page">
      <h2>Załóż konto</h2>
      <form:form method="post" action="/register" modelAttribute="user">
        <div class="form-group">
          <form:input placeholder="Imię" path="name"/>
        </div>
        <div class="form-group">
          <form:input placeholder="Nazwisko" path="surname"/>
        </div>
        <div class="form-group">
          <form:input placeholder="E-mail" path="email"/>
        </div>
        <div class="form-group">
          <form:password placeholder="Hasło" path="password"/>
          <br><form:errors path="password"/><br>
        </div>
<%--        TODO Add a double check of password during registration--%>
<%--        <div class="form-group">--%>
<%--          <input type="password" name="password2" placeholder="Powtórz hasło" />--%>
<%--        </div>--%>

        <div class="form-group form-group--buttons">
          <button class="btn" type="submit">Załóż konto</button>
        </div>
      </form:form>
    </section>

    <%@ include file="elements/footer.jsp"%>

  </body>
</html>
