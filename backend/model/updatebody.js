'use strict';

class UpdateBody {
	constructor(actionType, entities) {
		this.actionType = actionType;
		this.entities = entities;
    }
}

module.exports = UpdateBody;