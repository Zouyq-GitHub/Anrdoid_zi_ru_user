var mysql = require('mysql');

//重连次数
let n = 0;

var connection = {
    host: 'localhost',
    user: 'root',
    password: '123',
    port: '3306',
    database: 'zi_ru_demo'
};

let connect = mysql.createConnection(connection);

connect.connect(function (err) {
    if (err) {
        console.log("连接失败,正在重新连接");
        //设定定时器2秒重新连接
        setTimeout(function () {
            autoConnect(connect);
        }, 2000)
    } else {
        console.log("连接成功");
        sqlQuery(connect);
    }
});

//查询 
function sqlQuery(connect) {
    let sqlQuery = "select * from user"
    connect.query(sqlQuery, function (err, res) {
        if (err) {
            console.log("error");
        } else {
            console.log(res);
            closeMysql(connect)
        }
    });
}

//操作成功后关闭连接
function closeMysql(connect) {
    connect.end((err) => {
        if (err) {
            console.log("关闭失败");
        } else {
            console.log("exit");
        }
    });
}

//重新连接
function autoConnect(connect) {

    if (n < 10) {
        n++;
        connect.connect(function (err) {
            if (err) {
                console.log('mysql自动连接:' + n);
                setTimeout(function () {
                    autoConnect(connect)
                }, 2000);
            } else {
                console.log("连接成功");
                sqlQuery(connect);
            }
        })
    } else {
        console.log("重连失败，检查服务是否开通");
    }
}