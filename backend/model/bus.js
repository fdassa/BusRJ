'use strict';
const Value = require("./value");
const RefLinha = require("./reflinha");

class Bus {
	constructor(datahora, ordem, linha, latitude, longitude, velocidade) {
		this.id = "urn:ngsi-ld:Onibus:".concat(ordem.toString());
		this.type = "Onibus";
		this.datahora = new Value(datahora);
		this.refLinha = new RefLinha(linha.toString());
		this.latitude = new Value(latitude);
		this.longitude = new Value(longitude);
		this.velocidade = new Value(velocidade);
    }
}

module.exports = Bus;