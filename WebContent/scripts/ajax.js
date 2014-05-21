	//criar ajax
	function criaAjax() {
		var objAjax = false;
		if(window.XMLHttpRequest) {
			//Verifica o objeto ajax nos navegadores Firefox, Chrome e derivados
			objAjax = new XMLHttpRequest();
		}
		else if(window.ActiveXObject) {
			//Validação para IE
			objAjax = new ActiveXObject("Msxml2.XMLHTTP");
			if(!objAjax) {
				objAjax = new ActiveXObject("Microsoft.XMLHTTP");
			}
		}
		else {
			alert("Seu navegador não suporte AJAX!");
		}
		
		return objAjax;
	};
	
	function requisitar(arquivo, controle) {
		var requisicao = criaAjax();
		
		if(requisicao) {
			requisicao.onreadystatechange = function() {
				mostraResposta(requisicao,controle);
			};
			requisicao.open("POST",arquivo, true);
			requisicao.send(null);
		}
	}
	
	function mostraResposta(controle, requisicao) {
		if(requisicao.readyState == 4) {
			if(requisicao.status == 200 || requisicao.status == 304) {
				controle.html(requisicao.responseText);
			}
			else {
				alert(requisicao.responseText);
			}
		}
	}