#!/usr/bin/env node

const express = require('express'); 
const app = express(); 
const path = require('path');
const fs = require('fs');

app.use(express.json());
var amqp = require('amqplib/callback_api');
app.use(express.static(__dirname + '/resources'));
const directoryPath = path.join(__dirname, '/resources/images/reports');

app.get('/', (req, res) => {
	res.send('This is an Ingestion Platform!');
});

app.get('/reports', (req, res) => {
	var images = [];
	var imageNames = [];
	fs.readdir(directoryPath, function (err, files) {
		if (err) {
			return console.log('Unable to scan directory: ' + err);
		}

		files.forEach(function (file) {
			images.push(`/images/reports/${file}`);
			imageNames.push(file.substring(0, file.lastIndexOf(".")));
		});

		
		res.render('index.ejs', { 
			images: images,
			imageNames: imageNames
		});
	});
	
});


app.post('/ingest', (req, res)=> {
	console.log(new Date() + ": ");
	console.log(req.body);
	res.sendStatus(200);



amqp.connect('amqp://localhost', function(error0, connection) {
    if (error0) {
        throw error0;
    }
    connection.createChannel(function(error1, channel) {
        if (error1) {
            throw error1;
        }
		
		var consumerQueue = 'consumer';
		channel.assertQueue(consumerQueue, {
                durable: false
            });
			
			const jsonStr = JSON.stringify(req.body);

            channel.sendToQueue(consumerQueue, Buffer.from(jsonStr));
            console.log(jsonStr);        
    });
});
});
const port = 8080;
app.listen(port, () => console.log(`Listening on port ${port}..`));