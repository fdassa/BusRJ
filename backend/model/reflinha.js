'use strict';

class RefLinha {
	constructor(linha) {
		this.type = "Relationship";
		this.value = "urn:ngsi-ld:Linha:".concat(linha.toString());
    }
}

module.exports = RefLinha;