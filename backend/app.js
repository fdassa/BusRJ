const BusUtils = require("./utils/busutils");
const UpdateBody = require("./model/updatebody");
const Network = require("./network/network");
const waitTimeAfterUpdate = 15*60*1000; // atualiza o banco de dados a cada 15 minutos

function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

async function execute() {
	while(true) {
		Network.obtemListaOnibus().then( listaOnibus => {
			const listaOnibusFormatada = BusUtils.formataListaOnibus(listaOnibus);
			const updateBody = new UpdateBody("append", listaOnibusFormatada);
			Network.atualizaBancoDeDados(updateBody).then(res => console.log("Successfully updated fiware database"));
		});
		await sleep(waitTimeAfterUpdate);
	}
}

execute();