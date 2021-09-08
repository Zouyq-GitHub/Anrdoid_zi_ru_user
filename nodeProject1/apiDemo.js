var express = require('express')
var path = require('path')
var app = express()
// cors
const cors = require('cors');
//设置跨域访问  
app.all('*', function (req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "X-Requested-With");
    res.header("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
    res.header("X-Powered-By", ' 3.2.1')
    next();
});

//body-parser
const bodyParser = require('body-parser');
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
    extended: false
}));

//mysql
const mysql = require('mysql');
const conn = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '123',
    database: 'zi_ru_demo',
    multipleStatements: true
})
conn.connect();

//接口监听
app.listen(8080, () => {
    console.log("------start-------");
})

app.get('/', (req, res) => {
    res.send(res.send('<p>服务器启动</p>'));
})

//get无参方法
app.get('/api/test', (req, res) => {
    const sqlStr = "select *  from user"
    conn.query(sqlStr, (error, results) => {
        if (error) return res.json({
            code: 10001,
            message: error
        })
        res.json({
            code: 10000,
            message: results
        })
    })
})

//post无参方法
app.post('/api/testPost', (req, res) => {
    const sqlStr = "select *  from user"
    conn.query(sqlStr, (error, results) => {
        if (error) return res.json({
            code: 10001,
            message: error
        })
        res.json({
            code: 10000,
            message: results
        })
    })
})

//查询所有房屋信息遍历
app.post('/api/getData', (req, res) => {
    const sqlStr = "select *  from  house"
    conn.query(sqlStr, (error, results) => {
        if (error) return res.json({
            code: 10001,
            message: error
        })
         res.json(results);
    })
})


//get  post req.body传参 
app.post('/api/testParamter', (req, res) => {
    //query get参数
    // console.log(req.body);

    //get -  post 参数  body
    // console.log(req.body.testParamter);

    const sqlStr = "select *  from user where u_id = " + req.body.testParamter
    conn.query(sqlStr, (error, results) => {
        if (error) return res.json({
            code: 10001,
            message: error
        })
       res.json(results[0]);
    })
})

//登录方法 传入用户手机号 获取用户信息
app.post('/api/loginState', (req, res) => {

    // post 参数  body
    console.log(req.body);
    const sqlStr = "select *  from user where u_phone = " + req.body.phoneNumber
    conn.query(sqlStr, (error, results) => {
        if (error) return res.json({
            code: 10001,
            message: error
        })
        res.json(results[0]);
    })
})



app.get('/public/*', function (req, res) {
    res.sendFile( __dirname + "/" + req.url );
    console.log("Request for " + req.url + " received.");
})




// app.listen(8080, ()=>{
//     console.log('Server is running at http://localhost:8080')
// })