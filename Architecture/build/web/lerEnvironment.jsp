
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.File" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Informar Diretório</title>
    </head>
    <body>
        <h1>Informar diretório que contenha os logs gerados pelo jenkins</h1>
        <HR width ="100%" align="left" size="5" color="silver">
        <form action="ManterLerBuildController?acao=confirmarOperacao&operacao=Incluir" method="post" name="dados" onSubmit="return enviardados();">
            <table border="1">
                <tr>
                    <td>Build Selecionado:</td>
                    <td> <input type="text" size="100" name="txtdiretorio" value="${diretorio.path}" readonly="true"></td>
                </tr>
                <tr>
                    <td>Arquivo:</td> 
                    <td><textarea name="txtEnvironment" cols="100" rows="3" class="formbutton" id="tx_mensagem" input ></textarea></td>
                </tr>
                <tr>
                    <td>Diretório Testes:</td> 
                    <td><textarea name="txtTest" cols="100" rows="3" class="formbutton" id="tx_test" input ></textarea></td>
                </tr>
                <tr>
                    <td><input name="Submit" type="submit"  class="formobjects" value="Enviar dados"></td>
                    <td><input name="Reset" type="reset" class="formobjects" value="Redefinir"></td>
                </tr>

            </table>

        </form> 
        <SCRIPT language="JavaScript">
            function enviardados() {
                if (document.dados.txtEnvironment.value == "") {
                    alert("Preencha o campo Diretório!");
                    document.dados.tx_mensagem.focus();
                    return false;
                }

                if (document.dados.tx_mensagem.value.length < 10) {
                    alert("É necessario preencher o campo DIRETÓRIO com mais de 10 caracteres!");
                    document.dados.tx_mensagem.focus();
                    return false;
                }

                return true;
            }
        </SCRIPT>        


    </body>  
</html>  




</html>
