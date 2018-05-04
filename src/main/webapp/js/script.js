function calcularValorTotalItem() {
	var quantidadeItens = parseFloat(document.getElementById('quantidadeItem').value);
	var valorItens = parseFloat(document.getElementById('valorUniformeItem').value);
	document.getElementById('resultado').value = "R$ "+ quantidadeItens * valorItens;
}

function mostrarAlerta(){
	alert('Você não possui nenhum orçamento.');
}

function getValorPerfil(){
	var perfilSelect = document.getElementById("perfilSelect");
	var perfilSelecionado = perfilSelect.options[perfilSelect.selectedIndex].value;
	document.getElementById('valorPerfil').value = ""+ perfilSelecionado;
}