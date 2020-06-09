const express = require('express'); 
const app = express(); 
const path = require('path');
const fs = require('fs');

app.use(express.json());
app.use(express.static(__dirname + '/resources'));
const directoryPath = path.join(__dirname, '/resources/images/reports');
 
app.get('/', (req, res) => {
	res.send('This is an Ingestion Platform!');
});

app.get('/reports', (req, res) => {
	var images = [];
	fs.readdir(directoryPath, function (err, files) {
		if (err) {
			return console.log('Unable to scan directory: ' + err);
		}

		files.forEach(function (file) {
			images.push(`/images/reports/${file}`);
		});

		const uniqueimages = new Set(images);
		res.render('index.ejs', { images: uniqueimages});
	});
	
});

app.post('/ingest', (req, res)=> {
	console.log(new Date() + ": ");
	console.log(req.body);
	res.sendStatus(200);
});
 
const port = 8080;
app.listen(port, () => console.log(`Listening on port ${port}..`));