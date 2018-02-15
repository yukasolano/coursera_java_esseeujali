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
        <h2>Perfil de ${usuario.getNome()}</h2>
        
        <p> Pontuação: ${pontuacao} </p>
        <p> Troféus:</p>
        <ul>
            <c:forEach var="trofeu" items="${trofeus}" >
                <li> ${trofeu}</li>
            </c:forEach> 
        </ul>
            
        <br><br>
        <form action="livro" method="get" id="goto_livros"> 
            <input type="submit" value="Voltar"> 
        </form>
   </body>
       
</html>