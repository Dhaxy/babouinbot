/**
 * Function calling
 */
const Discord = require('discord.js');

/**
 * config.json file
 */
const config = require('./config');

/**
 * Discord client for the bot
 * @type {module:discord.js.Client}
 */

const client = new Discord.Client();

client.on('ready', () => {
	console.log('Babouinbot ' + config.version + ' ready.');
});

client.on('message', message => {
	MessageParsing(message);
});

function MessageParsing(message) {
	if (!message.author.bot) {
		switch (message.content) {
			case '?bot' :
				commandTest(message);
				break;
			case '?time' :
				commandTime(message);
				break;
			case '?iamretarded' :
				commandHelp(message);
				break;
			default :
				commandUnknown(message);
				break;
		}
	}
}

function commandTest(message) {
	message.channel.send('Je suis un bot.');
}

function commandTime(message) {
	const time = new Date();

	const date = time.getDate() + "/"
			+ (time.getMonth()+1)  + "/"
			+ time.getFullYear();

	const hours = time.getHours() + ":"
			+ time.getMinutes() + ":"
			+ time.getSeconds();

	message.channel.send('Nous sommes le : ' + date + ' et il est : ' + hours);
}

function commandUnknown(message) {
	message.channel.send('Commande inconnue, pour obtenir de l\'aide tapez ?iamretarded');
}

function commandHelp(message) {
	message.channel.send('Commandes disponibles :' +
			' - ' );
}

client.login(config.token);