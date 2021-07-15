'use strict';
const axios = require("axios");
const urlPrefeitura = "https://dadosabertos.rio.rj.gov.br/apiTransporte/apresentacao/rest/index.cfm/obterTodasPosicoes";
const urlUpdateFiware = "http://localhost:1026/v2/op/update";
	
class Network {
	static async atualizaBancoDeDados(updateBody) {
		try {
			const response = await axios.post(urlUpdateFiware, updateBody);
			return response;
		} catch (error) {
			console.error(error.status);
		}
	}

	static async obtemDadosOnibus() {
		try {
			const response = await axios.get(urlPrefeitura);
			return response.data;
		} catch (error) {
			console.error(error.status);
		}
	}
}

module.exports = Network;