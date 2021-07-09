'use strict';
const Value = require("./value");

class Linha {
	constructor(linha) {
		this.id = "urn:ngsi-ld:Linha:".concat(linha.toString());
		this.type = "Linha";
		this.name = new Value(linha.toString());
    }
}

module.exports = Linha;