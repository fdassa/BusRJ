const BusUtils = require("./utils/busutils");
const UpdateBody = require("./model/updatebody");
const Network = require("./network/network");
const waitTimeAfterUpdate = 15*60*1000; // atualiza o banco de dados a cada 15 minutos

function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

async function execute() {
	while(true) {
		Network.obtemDadosOnibus().then( dadosOnibus => {
			const dadosParseados = BusUtils.parseDadosOnibus(dadosOnibus);
			Network.atualizaBancoDeDados(new UpdateBody("append", dadosParseados.listaLinhas)).then(res => 
				Network.atualizaBancoDeDados(new UpdateBody("append", dadosParseados.listaOnibus)).then(res => 
					console.log("Successfully updated fiware database")
				)
			);
		});
		await sleep(waitTimeAfterUpdate);
	}
}

execute();