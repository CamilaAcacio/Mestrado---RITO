
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.File" %>  


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Informar Diretório</title>
    </head>
    <body>
        <h1>Informar diretório</h1>
        <HR width ="100%" align="left" size="5" color="silver">
        <form action="ManterDiretorioController?acao=confirmarOperacao&operacao=Incluir" method="post" name="dados" onSubmit="return enviardados();">
            <table>
                <tr>
                    <td>Diretório:</td> 
                    <td><textarea name="txtdiretorio" cols="80" rows="3" class="formbutton" id="tx_mensagem" input ></textarea></td>
                </tr>
                <tr>

                    <td><input name="Submit" type="submit"  class="formobjects" value="Enviar dados"></td>
                    <td><input name="Reset" type="reset" class="formobjects" value="Redefinir"></td>
                </tr>
            </table>
            
        </form> 
        <SCRIPT language="JavaScript">
            function enviardados() {
            if (document.dados.txtdiretorio.value == ""){
                alert("Preencha o campo Diretório!");
                document.dados.tx_mensagem.focus();
                return false;
            }

            if (document.dados.tx_mensagem.value.length < 10){
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
