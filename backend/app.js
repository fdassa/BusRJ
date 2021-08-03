const BusUtils = require("./utils/busutils");
const UpdateBody = require("./model/updatebody");
const Network = require("./network/network");
const waitTimeAfterUpdate = 2*60*1000; // atualiza o banco de dados a cada 2 minutos

function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

async function execute() {
	while(true) {
		Network.obtemDadosOnibus().then( dadosOnibus => {
			console.log("Recupera dados dos ônibus com sucesso.");
			const listaEntidades = BusUtils.obtemListaDeEntidades(dadosOnibus);
			Network.atualizaBancoDeDados(new UpdateBody("append", listaEntidades)).then(res =>
				console.log("Atualiza banco de dados com sucesso.")
			);
		});
		await sleep(waitTimeAfterUpdate);
	}
}

execute();