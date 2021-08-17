<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
        <html>

        <head>
            <title>CRUD JSP Test</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">


        </head>

        <body style="background-color: #24292d">
            <header>
                <nav class="navbar navbar-dark bg-dark" style="background-color: #e3f2fd">
                    <div>
                        <a href="https://github.com/ThePernalonga" class="navbar-brand"> CRUD JSP Test </a>
                    </div>
                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Users</a></li>
                    </ul>
                </nav>
            </header>
            <br>
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                        <c:if test="${user != null}">
                            <form action="update" method="post">
                        </c:if>
                        <c:if test="${user == null}">
                            <form action="insert" method="post">
                        </c:if>
                        <caption>
                            <h2>
                                <c:if test="${user != null}"> Edit User </c:if>
                                <c:if test="${user == null}"> Novo Usuário </c:if>
                            </h2>
                        </caption>

                        <c:if test="${user != null}">
                            <input type="hidden" name="id" value="<c:out value='${user.id}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>Nome Completo</label> <input type="text" id="name" placeholder="Nome Sobrenome" value="<c:out value='${user.name}' />" class="form-control" name="name" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Data de Nascimento</label> <input type="date" required id="birth" name="birth" value="<c:out value='${user.birth}' />" class="form-control" name="birth">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>E-mail</label> <input type="email" required placeholder="nome@email.com" value="<c:out value='${user.email}' />" class="form-control" name="email">
                        </fieldset>

                        <fieldset class="form-group"> <label>Cargo</label>
                            <select name="work" id="work" value="<c:out value='${user.work}' />" class="form-control" name="work">
							  <option value="Desenvolvedor">Desenvolvedor</option>
							  <option value="BDA">BDA</option>
							  <option value="Gerente de Sistemas">Gerencia de Sistemas</option>
							  <option value="Arquiteto de Software">Arquiteto de Software</option>
							</select>
                        </fieldset>

                        <button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>