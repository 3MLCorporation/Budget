function calcularValorTotalItem() {
	var quantidadeItens = parseFloat(document.getElementById('quantidadeItem').value);
	var valorItens = parseFloat(document.getElementById('valorUniformeItem').value);
	document.getElementById('resultado').value = "R$ "+ quantidadeItens * valorItens;
}

function mostrarAlerta(){
	alert('Você não possui nenhum orçamento.');
}