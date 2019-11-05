<%-- 
    Document   : index
    Created on : 5 nov. 2019, 14:02:13
    Author     : Florian MOULY
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Saisie d'un taux de remise</title>
    </head>
    <body>

            <!-- On montre le formulaire de saisie -->
            <h1>Edition des taux de remise</h1>
            <form action="ControllerServlet" method="GET">
                Code : <input name="code" size="1" maxlength="1" pattern="[A-Z]{1}+" title="Une lettre en MAJUSCULES"><br>
                Taux : <input name="taux" step="0.01" min="0.0" max="99.99" size="5" type="number"><br>
                    <input name="action" value="ADD" type="hidden">
                    <input value="Ajouter" type="submit">

            </form>


            <%String mes = (String) request.getAttribute("mes");%>

            <%if(mes == null){
                mes = " ";
            }%>
            <div><h4><%=mes%></h4></div>
            <div>
                    <table border="1">
                        <tbody><tr><th>Code</th><th>Taux</th><th>Action</th></tr>


                        <%Map<String, Float> HT = (HashMap<String, Float>) request.getAttribute("HT");

                            if(HT != null){
                            Set keys = HT.keySet();

                            Iterator itr = keys.iterator();
                            String key = null;

                            while(itr.hasNext()){
                                key = (String) itr.next();%>

                                <tr>
                                    <td><%=key%></td>
                                    <td><%=HT.get(key)%> %</td>
                                    <td><a href="ControllerServlet?action=DELETE&amp;code=<%=key%>">delete</a></td>
                                </tr>


                            <%}
                            }%>


                                <tr>
                                        <td>H</td>
                                        <td>16,00 %</td>
                                        <td><a href="ControllerServlet?action=DELETE&amp;code=H">delete</a></td>
                                </tr>	  		    

                                <tr>
                                        <td>L</td>
                                        <td>07,00 %</td>
                                        <td><a href="ControllerServlet?action=DELETE&amp;code=L">delete</a></td>
                                </tr>	  		    

                                <tr>
                                        <td>M</td>
                                        <td>11,00 %</td>
                                        <td><a href="ControllerServlet?action=DELETE&amp;code=M">delete</a></td>
                                </tr>	  		    

                                <tr>
                                        <td>N</td>
                                        <td>00,00 %</td>
                                        <td><a href="ControllerServlet?action=DELETE&amp;code=N">delete</a></td>
                                </tr>	    

                        </tbody></table>
            </div>                     
    </body>
</html>
