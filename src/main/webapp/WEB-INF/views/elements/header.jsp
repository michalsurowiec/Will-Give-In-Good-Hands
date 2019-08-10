<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="container container--70">
    <ul class="nav--actions">
        <sec:authorize access="isAnonymous()">
        <li><a href="/login" class="btn btn--small btn--without-border">Zaloguj</a></li>
        <li><a href="/register" class="btn btn--small btn--highlighted">Załóż konto</a></li>
        </sec:authorize>
<%--        TODO Po dodaniu funkcji logowania tworzyć if, który w zależności od tego czy osoba jest zalogowana czy nie będzie pokazywał odpowiedni element headera--%>
        <sec:authorize access="isAuthenticated()">
        <li class="logged-user">
            <sec:authentication property="name"/>
            <ul class="dropdown">
                <li><a href="#">Profil</a></li>
                <li><a href="#">Ustawienia</a></li>
                <li><a href="#">Moje zbiórki</a></li>
                <li><a href="/logout">Wyloguj</a></li>
            </ul>
        </li>
        </sec:authorize>
    </ul>

    <ul>
        <li><a href="/" class="btn btn--without-border active">Start</a></li>
        <li><a href="#" class="btn btn--without-border">O co chodzi?</a></li>
        <li><a href="#" class="btn btn--without-border">O nas</a></li>
        <li><a href="#" class="btn btn--without-border">Fundacje i organizacje</a></li>
        <li><a href="/donation/form" class="btn btn--without-border">Przekaż dary</a></li>
        <li><a href="#" class="btn btn--without-border">Kontakt</a></li>
    </ul>
</nav>