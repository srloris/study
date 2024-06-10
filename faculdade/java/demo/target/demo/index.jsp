<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aplicação | Pedido</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
    <link rel="stylesheet" href="<c:url value='/bootstrap-5.3.3-dist/css/bootstrap.css'/>">
</head>

<body>
    <nav class="navbar navbar-expand-lg bg-body-tertiary" data-bs-theme="dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="<c:url value='/index'/>">Nicks Store</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
                aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="<c:url value='/index'/>">Pedidos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="<c:url value='/pages/produtos'/>">Produtos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value='/pages/clientes'/>">Clientes</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <br>
    <div class="container">
        <div class="table-nav-bar">
            <h1>Pedidos</h1>
            <button id="openModalBtn" type="button" class="btn btn-dark">Novo Pedido</button>
        </div>
        <table class="table table-dark table-hover">
            <thead>
                <tr>
                    <th>Número</th>
                    <th>Cliente</th>
                    <%-- <th>Produto</th> --%>
                    <%-- <th>Quantidade</th> --%>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody id="tbody">
                <c:forEach var="pedido" items="${pedidos}">
                    <tr>
                        <td>${pedido.numero}</td>
                        <td>${pedido.cliente.nome}</td>
                        <%-- <td>${pedido.produto.descricao}</td> --%>
                        <%-- <td>${pedido.itensPedido.quantidade}</td> --%>
                        <td>${pedido.total}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <div id="myModal" class="modal1">
        <div class="modal-content1">
            <span class="close">&times;</span>
            <h1>Cadastro de Pedido</h1>
            <form id="myForm" action="cadastrarPedido" method="post">
                <div class="mb-3">
                    <label for="client" class="form-label">Cliente</label>
                    <input type="text" class="form-control" id="client" name="client">
                </div>
                <div class="mb-3">
                    <label for="product" class="form-label">Produto</label>
                    <input type="text" class="form-control" id="product" name="product">
                </div>
                <div class="mb-3">
                    <label for="quantity" class="form-label">Quantidade</label>
                    <input type="text" class="form-control" id="quantity" name="quantity">
                </div>
                <div class="mb-3">
                    <label for="total" class="form-label">Total</label>
                    <input type="text" class="form-control" id="total" name="total">
                </div>
                <button type="submit" class="btn btn-dark" id="cadastrar">Cadastrar</button>
            </form>
        </div>
    </div>
    <script src="<c:url value='/js/script.js'/>"></script>
</body>

</html>
