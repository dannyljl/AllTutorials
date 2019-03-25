<%-- 
    Document   : beerSelectieKleur
    Created on : Feb 1, 2013, 2:04:07 PM
    Author     : frankcoenen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bier op Kleur</title>
    </head>
    <body>
        <h1>Bier op Kleur</h1>

        <form action="bierOpKleur" method="POST">
            <select name="kleur">
                <option value="blond">Blond</option>
                <option value="donker">Donker</option>
                <option value="amber">Amber</option>
            </select>
            <input type="submit" value="Advies">
        </form>
        
    </body>
</html>
