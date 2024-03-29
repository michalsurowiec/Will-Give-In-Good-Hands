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
      <h2>Formularz admina</h2>
      <form:form method="post" action="/admin/adminCRUD/create" modelAttribute="admin">

        <div class="form-section form-section--columns">
          <div class="form-group form-group--inline">
            <label>Imię:<form:input placeholder="${admin.name}" path="name"/></label>
          </div>
          <div class="form-group form-group--inline">
            <label>Nazwisko:<form:input placeholder="${admin.surname}" path="surname"/></label>
          </div>
          <div class="form-group form-group--inline">
            <label>E-mail:<form:input placeholder="${admin.email}" path="email"/></label>
          </div>
          <div class="form-group form-group--inline">
            <label>Hasło:<form:password path="password"/></label>
          </div>
        </div>
<%--        TODO Add a double check of password during registration--%>
<%--        <div class="form-group">--%>
<%--          <input type="password" name="password2" placeholder="Powtórz hasło" />--%>
<%--        </div>--%>

<%--        <form:hidden path="id" value="${admin.id}"/>--%>
        <div class="form-group form-group--buttons">
          <button class="btn" type="submit">Zapisz</button>
        </div>
      </form:form>
    </section>

  </body>
</html>
