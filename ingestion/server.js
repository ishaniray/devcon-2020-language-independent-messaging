const express = require('express'); 
const app = express(); 
app.use(express.json());
 
app.get('/', (req, res) => {
	res.send('This is an Ingestion Platform!');
});

app.post('/ingest', (req, res)=> {
	console.log(req);
});
 
const port = 8080;
app.listen(port, () => console.log(`Listening on port ${port}..`));