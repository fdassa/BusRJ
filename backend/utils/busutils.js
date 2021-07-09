'use strict';
const Bus = require("../model/bus");
const Linha = require("../model/linha");
const Moment = require("moment");

class BusUtils {
	
	static #foiAtualizadoRecentemente(datahora) {
		const ultimaAtualizacao = Moment(datahora, "MM-DD-YYYY hh:mm:ss");
		const agora = Moment();
		const duration = agora.diff(ultimaAtualizacao, 'minutes');
		return duration < 75;
	}
	
	static #filtraListaDeOnibus(listaOnibus) {
		return listaOnibus.filter((onibus, index) => this.#foiAtualizadoRecentemente(onibus[0]));
	}
	
	static #ordenaListaDeOnibusPelaData(listaOnibus) {
		return listaOnibus.sort((a, b) => Moment(b[0], "MM-DD-YYYY hh:mm:ss").diff(Moment(a[0], "MM-DD-YYYY hh:mm:ss")));
	}
	
	static #formataListaOnibus(listaOnibus) {
		var listaOnibusFormatada = [];
		// columns: ['DATAHORA', 'ORDEM', 'LINHA', 'LATITUDE', 'LONGITUDE', 'VELOCIDADE']
		listaOnibus.forEach( (d) => {
			var bus = new Bus(d[0], d[1], d[2], d[3], d[4], d[5]);
			listaOnibusFormatada.push(bus);
		}, this);
		return listaOnibusFormatada;
	}
	
	static #obtemListaLinhas(listaOnibus) {
		var listaLinhas = [];
		// columns: ['DATAHORA', 'ORDEM', 'LINHA', 'LATITUDE', 'LONGITUDE', 'VELOCIDADE']
		listaOnibus.forEach( (d) => {
			var linha = new Linha(d[2]);
			listaLinhas.push(linha);
		}, this);
		return listaLinhas;
	}
	
	static #removeLinhasDuplicadas(listaLinhas) {
		return listaLinhas.filter((v,i,a)=>a.findIndex(t=>(t.id === v.id))===i);
	}
	
	static parseDadosOnibus(dadosOnibus) {
		var listaOnibusFormatada = [];

		if (!dadosOnibus.DATA) {
			console.error('No buses');
			return listaOnibusFormatada;
		}
		if(dadosOnibus.COLUMNS.length<=1){
			console.error('Bad json');
			return listaOnibusFormatada;
		}
		var listaOnibus = dadosOnibus.DATA;
		
		const listaOnibusFiltrada = this.#filtraListaDeOnibus(listaOnibus);
		const listaOnibusOrdenada = this.#ordenaListaDeOnibusPelaData(listaOnibusFiltrada);
		const listaLinhas = this.#obtemListaLinhas(listaOnibusOrdenada);
		return {
			listaOnibus: this.#formataListaOnibus(listaOnibusOrdenada),
			listaLinhas: this.#removeLinhasDuplicadas(listaLinhas)
		};
	}
}

module.exports = BusUtils;