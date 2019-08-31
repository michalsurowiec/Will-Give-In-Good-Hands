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

    <h2>Szczegóły donacji</h2>

    <section class="about-us">
      <div class="about-us--text">
        <p>
          ${donation.quantity} worków zawierających
          <c:forEach items="${donation.categoriesNames}" var="category">${category},
          </c:forEach><br>
          dla fundacji<c:forEach items="${institutions}" var="institution">
            <c:if test="${institution.id == donation.institution}">"${institution.name}"</c:if>
          </c:forEach><br>
          Odbiór w ${donation.city} ${donation.zipCode} na ulicy ${donation.street}<br>
          Do odebrania dnia ${donation.pickUpDate} o godzinie ${donation.pickUpTime}<br>
          Komentarz do odbioru: ${donation.pickUpComment}<br>
          Data utworzenia: ${donation.creationDate}<br>
          Status: ${donation.status}<br>
          <c:if test="${donation.status == 'Odebrane'}">
          Data odebrania: ${donation.confirmedPickUpDate}
          </c:if>
        </p>
        <c:if test="${donation.status == 'Nieodebrane'}">
          <p>
            <a href="/donation/confirm/<c:out value="${donation.id}"/>" class="btn btn--small btn--highlighted">Potwierdź odbiór</a>
          </p>
        </c:if>
        <a href="/user/main" class="btn btn--large btn--highlighted">Powrót</a>
      </div>
    </section>

    <script src="<c:url value="../../../resources/js/app.js"/>"></script>
  </body>
</html>
