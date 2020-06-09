#!/usr/bin/env node

const express = require('express'); 
const app = express(); 
const path = require('path');
const fs = require('fs');

app.use(express.json());
var amqp = require('amqplib/callback_api');
 
app.get('/', (req, res) => {
	res.send('This is an Ingestion Platform!');
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