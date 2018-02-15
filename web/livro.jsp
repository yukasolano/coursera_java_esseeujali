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
        <h2> Informações do livro </h2>
        
        <c:if test="${not empty msgpontos}">
            <p style="color:blue;"> ${msgpontos} </p>
        </c:if>
        
        <p> Livro: ${livro.getTitulo()} </p>
        <p> Escritor: ${livro.getEscritor()} </p>
        <p> Estilo: ${livro.getEstilo()} </p>
        <p> Quantidade de páginas: ${livro.getPaginas()} </p>
        
        <br>
        <form action="livro" method="post" id="goto_livro"> 
            <input type="hidden" name="paginas" value="${livro.getPaginas()}">
            <input type="hidden" name="livro_id" value="${livro.getId()}">
            <input type="hidden" name="estilo" value="${livro.getEstilo()}">
            <input type="submit" ${ lido  ? 'disabled="disabled"' : ''} value="Marcar como lido"> 
        </form> 
        <br><br>
        <form action="livro" method="get" id="goto_livros"> 
            <input type="submit" value="Voltar"> 
        </form> 
   </body>
       
</html>