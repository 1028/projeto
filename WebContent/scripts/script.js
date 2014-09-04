$(document).ready(function() {
	$('#celular').mask("(99) 99999-9999");
	$('#nascimento').mask("99/99/9999");
	
	//Verifica��o dos campos
	/*$('form').submit(function(e) {
		if(valida($('#nome').val()) || valida($('#nascimento').val()) ) {
			return false;
		}
		else {
			e.preventDefault();
			//criar objeto JSON
			var obj = {
					"nome" : $('#nome').val(),
					"sobrenome" : $('#sobrenome').val(),
					"celular" : $('#celular').val(),
					"nascimento" : $('#nascimento').val(),
					"email" : $('#email').val(),
					"tratamento" : $('#tratamento option:selected').val(),
					"perfil" : $('#perfil option:selected').val()
			};
			//criar requisi��o ajax
			$.ajax({
				url: '/ProjetoIntegradoWEB/cadastroPassageiroIncluir.jsp',
				dataType: 'text',
				data: obj,
				type: 'POST',
				sucess: function(data, textStatus) {
					$('#msg').append('<p>' + data + '</p>');
					$('#msg').append('<p>' + textStatus + '</p>');
				},
				error: function(xhr,er) {
					$('#msg').append('<p>Erro </p>' + '<p>' + xhr.statusText + '</p>' + '<p>' + er + '</p>');
				}
			});
			return false;
			
		}
	});*/
	
	function valida(texto) {
		var i = 0;
		while(i < texto.length && texto.length > 0) {
			if(!(texto.substring(i,i+1) == " ")) {
				return false;
			}
			i++;
		}
		return true;
	};

	
	
});