function calcularValorTotalItem() {
	  var n1 = parseInt(document.getElementById('quantidadeItem').value);
	  var n2 = parseInt(document.getElementById('valorUniformeItem').value);
	  document.getElementById('resultado').value = "R$ "+ n1 * n2;
	}