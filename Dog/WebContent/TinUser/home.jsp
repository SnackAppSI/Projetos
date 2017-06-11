<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css\dog.css">
<link rel="stylesheet" type="text/css" href="css\bootstrap.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/3/w3.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="js\dog.js"></script>
<title>Insert title here</title>
</head>
<body>
	<label>Bem vindo <c:out value="${usuarioLogado.nome}" /></label>
	<input type="button" id="logout" value="Logout" class="btn btn-danger "
		onClick="window.location.href='LoginController.do?action=logout'" />
	<div>

		<h2>Meus cachorros</h2>
		<br> <br>
		<table id="tabeladogs" cellspacing="20">
			<tr>
				<td class="primeiraLinha colunas">Nome</td>
				<td class="primeiraLinha colunas">Ra�a</td>
				<td class="primeiraLinha colunas">Sexo</td>
				<td class="primeiraLinha colunas">Idade</td>
			</tr>

			<c:forEach items="${myDogs}" var="dog">
				<tr>
					<td class="colunas"><c:out value="${dog.nome}" /></td>
					<td class="colunas"><c:out value="${dog.raca}" /></td>
					<td class="colunas"><c:out value="${dog.sexo}" /></td>
					<td class="colunas"><c:out value="${dog.idade}" /></td>
					<td class="colunas"><Button class="btn btn-editar">Editar</Button></td>
					<td class="colunas"><input type="button"
						class="btn btn-remover"
						onClick="window.location.href='CachorroController.do'"
						value="Remover" /></td>
				</tr>
			</c:forEach>

		</table>
		<Button class="btn btn-inserir">Adiciona Dog</Button>
	</div>
	<div id="editardog" class="formsedicao">
		<form
			action="CachorroController.do?action=editar&idUsuario=<c:out value="${usuarioLogado.idUsuario}"/>"
			method="post">
			<div>
				<label for="nome">Nome</label> <input type="text" name="nome"
					placeholder="Peludinho" />
			</div>
			<div>
				<label for="raca">Raca</label> <input type="text" name="raca"
					placeholder="Yorkshine" />
			</div>
			<div>
				<label for="sexo">Sexo</label> <label> <input type="radio"
					name="optradio" value="M">M
				</label> <label> <input type="radio" name="optradio" value="F">F
				</label>

			</div>

			<div>
				<label for="idade">Idade</label> <input type="number" name="idade"
					placeholder="Idade em anos" />
			</div>
			<div>
				<button type="submit" class="btn btn-editar" value="Login"
					role="button">Atualizar</button>

			</div>
		</form>
	</div>

	<div id="cadastroDog" class="formscadastro">
		<form
			action="CachorroController.do?action=cadastrar&idUsuario=<c:out value="${usuarioLogado.idUsuario}"/>"
			method="post">

			<div>
				<label for="nome">Nome</label> <input type="text" name="nome"
					placeholder="Peludinho" class="form-control col-md-2" />
			</div>
			<div>
				<label for="raca">Raca</label> <input type="text" name="raca"
					placeholder="Yorkshine" class="form-control col-md-2" />
			</div>
			<div>
				<label for="sexo">Sexo</label> <label> <input type="radio"
					name="optradio" value="M">M
				</label> <label> <input type="radio" name="optradio" value="F">F
				</label>

			</div>
			<div>
				<label for="idade">Idade</label> <input type="number" name="idade"
					placeholder="Idade em anos" class="form-control col-md-2" />
			</div>
			<div>
				<button type="submit" class="btn btn-inserir" value="Login"
					role="button">Adicionar</button>

			</div>
		</form>
	</div>

</body>
</html>