<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Portal</title>
    </head>
    <body>
        <h1>Esse eu já li!</h1>  
        <h2>Livros disponíveis</h2>
        
        <ul>
            <c:forEach var="livro" items="${livros}">
                <li><a href="/EsseEuJaLi/livro?id=${livro.getId()}">${livro.getTitulo()}</a></li>
            </c:forEach> 
        </ul>

        <br><br>
        <form action="perfil" method="get" id="goto_perfil"> 
            <input type="submit" value="Perfil"> 
        </form>
        <br>
        <form action="ranking" method="get" id="goto_ranking"> 
            <input type="submit" value="Ranking"> 
        </form>
   </body>
       
</html>