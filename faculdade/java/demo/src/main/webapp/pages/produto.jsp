<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aplicação | Clientes</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
    <link rel="stylesheet" href="<c:url value='../bootstrap-5.3.3-dist/css/bootstrap.css'/>">
</head>

<body>
    <nav class="navbar navbar-expand-lg bg-body-tertiary" data-bs-theme="dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="<c:url value='../index.jsp'/>">Nicks Store</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
                aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="<c:url value='../index.jsp'/>">Pedidos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="<c:url value='../pages/produtos'/>">Produtos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value='../pages/clientes'/>">Clientes</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <br>
    <div class="container">
        <div class="table-nav-bar">
            <h1>Produto</h1>
            <button id="openModalBtn" type="button" class="btn btn-dark">Novo Produto</button>
        </div>
        <table class="table table-dark table-hover">
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>Valor</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="produto" items="${produtos}">
                    <tr>
                        <td>${produto.descricao}</td>
                        <td>R$ ${produto.valor}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <div id="myModal" class="modal1">
        <div class="modal-content1">
            <span class="close">&times;</span>
            <h1>Cadastro de Produto</h1>
            <form id="myForm" action="produtos" method="post">
                <div class="mb-3">
                    <label for="name" class="form-label">Nome</label>
                    <input type="text" class="form-control" id="name" name="name">
                </div>
                <div class="mb-3">
                    <label for="value" class="form-label">Valor</label>
                    <input type="text" class="form-control" id="value" name="value">
                </div>
                <button type="submit" class="btn btn-dark">Cadastrar</button>
            </form>
        </div>
    </div>
    <script src="<c:url value='../js/script.js'/>"></script>
</body>

</html>
