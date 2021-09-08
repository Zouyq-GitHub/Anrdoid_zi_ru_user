var express = require('express')
var path = require('path')
var app = express()

app.get('/', (req, res)=>{
    res.send('Hello world');
});

app.get('/img', function (req, res, next) {
    res.sendFile(path.join(__dirname, 'public/2.png'));
})

app.get('/report', function (req, res, next) {
    res.sendFile(path.join(__dirname, 'public/images/2.png'));
})


app.get('/air', function (req, res, next) {
    res.sendFile(path.join(__dirname, 'video/lenveo.mp4'));
})
app.listen(8080, ()=>{
    console.log('Server is running at http://localhost:8080')
})

app.get('/public/images/*', function (req, res) {
    res.sendFile( __dirname + "/" + req.url );
    console.log("Request for " + req.url + " received.");
})