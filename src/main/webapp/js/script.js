function calcularValorTotalItem() {
	var quantidadeItens = parseFloat(document.getElementById('quantidadeItem').value);
	var valorItens = parseFloat(document.getElementById('valorUniformeItem').value);
	document.getElementById('resultado').value = "R$ "+ quantidadeItens * valorItens;
}

mostrarAlerta = function(){
	alert('Você não possui nenhum orçamento.');
}

function getValorPerfil(perfilSelectCount){
	var select = document.getElementById('perfilSelect' + perfilSelectCount);
	var perfilSelecionado = select.options[select.selectedIndex].value;
	document.getElementById('valorPerfil' + perfilSelectCount).value = perfilSelecionado;
}

function getStatusOrcamento(orcamentoSelectCount){
	var select = document.getElementById('orcamentoSelect' + orcamentoSelectCount);
	var statusSelecionado = select.options[select.selectedIndex].value;
	document.getElementById('statusOrcamento' + orcamentoSelectCount).value = statusSelecionado;
}