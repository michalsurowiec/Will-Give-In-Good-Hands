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
      <h2>Lista donacji</h2>

      <div class="help--slides active" data-id="1">
        <ul class="help--slides-items">
          <c:forEach items="${donations}" var="donation">
            <li>
              <div class="col">
                <div class="title">${donation.id}. ${donation.quantity} worków zawierających
                  <c:forEach items="${donation.categoriesNames}" var="category">${category}, </c:forEach>
                  dla fundacji <c:forEach items="${institutions}" var="institution">
                    <c:if test="${institution.id == donation.institution}">"${institution.name}".</c:if></c:forEach>
<%--                  <a href="/admin/adminCRUD/update/<c:out value="${admin.id}"/>" class="btn btn--small btn--highlighted">Edytuj</a>--%>
<%--                  <a href="/admin/adminCRUD/delete/<c:out value="${admin.id}"/>" class="btn btn--small btn--highlighted">Usuń</a>--%>
                </div>
                <div class="subtitle">
                  Odbiór w ${donation.city} ${donation.zipCode} na ulicy ${donation.street}.
                  Do odebrania dnia ${donation.pickUpDate} o godzinie ${donation.pickUpTime}.
                  Komentarz do odbioru: ${donation.pickUpComment}
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
