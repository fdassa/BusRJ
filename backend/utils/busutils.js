'use strict';
const Bus = require("../model/bus");
const Moment = require("moment");

class BusUtils {
	
	static #foiAtualizadoRecentemente(datahora) {
		const ultimaAtualizacao = Moment(datahora, "MM-DD-YYYY hh:mm:ss");
		const agora = Moment();
		const duration = agora.diff(ultimaAtualizacao, 'minutes');
		return duration < 75;
	}
	
	static #ordenaListaPelaData(listaOnibus) {
		return listaOnibus.sort((a, b) => Moment(b.datahora.value, "MM-DD-YYYY hh:mm:ss").diff(Moment(a.datahora.value, "MM-DD-YYYY hh:mm:ss")));
	}
	
	static formataListaOnibus(listaOnibus) {
		var listaOnibusFormatada = [];

		if (!listaOnibus.DATA) {
			console.error('No buses');
			return listaOnibusFormatada;
		}
		if(listaOnibus.COLUMNS.length<=1){
			console.error('Bad json');
			return listaOnibusFormatada;
		}
		var data = listaOnibus.DATA;

		// columns: ['DATAHORA', 'ORDEM', 'LINHA', 'LATITUDE', 'LONGITUDE', 'VELOCIDADE']
		data.forEach( (d) => {
			var bus = new Bus(d[0], d[1], d[2], d[3], d[4], d[5]);
			if(this.#foiAtualizadoRecentemente(d[0])) {
				listaOnibusFormatada.push(bus);
			}
		}, this);
		return this.#ordenaListaPelaData(listaOnibusFormatada);
	}
}

module.exports = BusUtils;