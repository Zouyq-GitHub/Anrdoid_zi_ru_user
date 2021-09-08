var http = require('http');
http.createServer(function(request,response){
	//发送HTTP头部
	//200状态码 OK
	//内容类型  text/plain
	response.writeHead(200,{'Content-Type':'text/plain'});
	
	//发送数据 nodeJs
	response.end('nodeJs');
	
}).listen(80);

//终端打印
console.log("success");