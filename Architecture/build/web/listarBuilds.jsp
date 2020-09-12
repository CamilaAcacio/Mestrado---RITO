<%-- 
    Document   : listarDiretorio
    Created on : 30/05/2017, 16:49:19
    Author     : Camila
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Builds</title>
    </head>
    <body>
         <h1>Selecionar o Build</h1>
        <HR width ="100%" align="left" size="5" color="silver">
        
        <table border="1">
            <tr>
                <td>Jobs</td>
                <td>Ação</td>
            </tr>
             <c:forEach items="${builds}" var="build">
                <tr>
                    <td> <c:out value="${build.path}"/></td>
                    <td><a href="ManterDiretorioEnvironmentController?acao=confirmarOperacao&operacao=Incluir&path=<c:out value="${build.path}"/>">Selecionar</a></td>
                    
                </tr>
            </c:forEach>
         

        </table>
        
        
    </body>
</html>
