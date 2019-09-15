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
      <h2>
        Podaj nowe hasło
      </h2>
      <form:form method="post" action="/changePassword" modelAttribute="user">
        <div class="form-section form-section--columns">
          <div class="form-group form-group--inline">
            <label><form:input placeholder="Hasło" path="password"/></label>
            <br><form:errors path="password"/><br>
          </div>
          <div class="form-group form-group--inline">
            <label><form:input placeholder="Powtórz hasło" path="secondPassword"/></label>
          </div>
        <div class="form-group form-group--buttons">
          <button class="btn" type="submit">Wyślij</button>
        </div>
      </form:form>
    </section>

    <%@ include file="elements/footer.jsp"%>

  </body>
</html>
