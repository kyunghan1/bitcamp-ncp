
const express = require('express');
const request = require('request');
const port = 3000;   
const app = express();
// const bodyParser = require('body-parser');
app.use(express.urlencoded({ extended: true }));


app.get(                                                    
  '/exam01-1',                                           
  (req, res) => {                                       
    res.set("Access-Control-Allow-Origin", "*");       
    res.set("Content-Type", "text/plain;charset=UTF-8");
    res.send("hello!!");
  }  
);  


app.get('/exam02-1', (req, res) => {                                       
  res.set("Access-Control-Allow-Origin", "*");       
  res.set("Content-Type", "text/plain;charset=UTF-8");
  res.send(
    `이름: ${req.query.name}\n나이: ${req.query.age}`
    );
});  


app.post('/exam02-2', (req, res) => {                                       
  res.set("Access-Control-Allow-Origin", "*");       
  res.set("Content-Type", "text/plain;charset=UTF-8");
  res.send(
    `이름: ${req.body.name}\n나이: ${req.body.age}`
    );
});  


app.get('/exam03-1', (req, res) => {                                       
  res.set("Access-Control-Allow-Origin", "*");       
  res.set("Content-Type", "text/plain;charset=UTF-8");
  
  setTimeout(()=>{res.send("hello!");}, 15000);
});  

app.get('/exam03-4', (req, res) => {                                       
  res.set("Access-Control-Allow-Origin", "*");       
  res.set("Content-Type", "text/plain;charset=UTF-8");
  
  let a = parseInt(req.query.a);
  let b = parseInt(req.query.b);
  res.send(`${a+b}`);
});  


app.get('/header', (req, res) => {                                       
  res.set("Access-Control-Allow-Origin", "*");       
  res.set("Content-Type", "text/html;charset=UTF-8");
  res.send("<h1>비트캠프 네이버 클라우드 AIaas 개발자 양성과정</h1>");
});
app.get('/footer', (req, res) => {                                       
  res.set("Access-Control-Allow-Origin", "*");       
  res.set("Content-Type", "text/html;charset=UTF-8");
  res.send("<address>서울시 서초구 비트캠프@2022</address>");
});


app.get('/exam04-3', (req, res) => {                                       
  res.set("Access-Control-Allow-Origin", "*");       
  res.set("Content-Type", "text/html;charset=UTF-8");
  
  let arr = [
    {no:1, title:"제목1111111", writer:"홍길동", viewCnt:19},
    {no:2, title:"제목2222222", writer:"임꺽정", viewCnt:312},
    {no:3, title:"제목3333333", writer:"유관순", viewCnt:31},
    {no:4, title:"제목4444444", writer:"안중근", viewCnt:100},
    {no:5, title:"제목5555555", writer:"윤봉길", viewCnt:200}
  ];
  // 배열 객체를 JSON 문자열로 변환하여 클라이언트에게 보낸다
  // => serialization 직렬화
  res.send(JSON.stringify(arr));

});


// 정책 우회
app.get('/proxy', (req, res) => {  

  res.set("Access-Control-Allow-Origin", "*");       
  res.set("Content-Type", "text/plain;charset=UTF-8");

  request.get({ uri: req.query.url }, (error, response, body) => {
  res.send(body);
  });

});  




app.get('/proxy2', (req, res) => {  

  res.set("Access-Control-Allow-Origin", "*");       
  res.set("Content-Type", "application/json; charset=UTF-8");

  let openApiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?" +
  "serviceKey=bNoAx4qhBqmEpdG5iF1CSyQCZbO4qKpsOqeCgTI4%2FKoZwOSXCGsZcv4cXxoDLemZ%2BsErmTp75dFsPXY5oA72eQ%3D%3D" +
  "&pageNo=1" +
  "&numOfRows=1000" +
  "&dataType=JSON" +
  "&base_date=" + req.query.base_date +
  "&base_time=0600" +
  "&nx=" + req.query.nx +
  "&ny=" + req.query.ny;
  console.log(openApiUrl);

  request.get({ uri: openApiUrl }, (error, response, body) => {
      res.send(body);
  });

});  


app.post('/login', (req, res) => {                                       
  res.set("Access-Control-Allow-Origin", "*");       
  res.set("Content-Type", "text/plain;charset=UTF-8");
  
  var payload = `이메일: ${req.body.email}\n`;
  payload += `암호: ${req.body.password}\n`;

  res.send(payload);
}); 


app.listen( 3000,                                         
  () => { 
    console.log(`${port}번 포트에서 서버 시작했음!!`);
  }     
);
