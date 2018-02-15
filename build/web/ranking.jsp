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
        <h2>Ranking</h2>
       
        <table id="ranking">
            <thead>
                <tr>
                    <th></th><th>Nome</th><th>Login</th><th>Pontos</th> 
                </tr>
            </thead>
            <tbody> 
                <c:forEach var="usuario" items="${ranking}" varStatus="status" >
                    <tr>
                        <th>${status.count}º</th><th>${usuario.getNome()}</th>
                        <th>${usuario.getLogin()}</th><th>${usuario.getPontos()}</th>      
                    </tr>
                </c:forEach> 
            </tbody> 
        </table> 
        
        <br><br>
        <form action="livro" method="get" id="goto_livros"> 
            <input type="submit" value="Voltar"> 
        </form>
   </body>
       
</html>