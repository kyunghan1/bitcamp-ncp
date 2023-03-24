

// express 라이브러리 로딩하기
const express = require('express');

// HTTP 요청을 다루는 라이브러리 로딩하기
const request = require('request');

// post 요청으로 보낸 payload 를 분석하는 라이브러리 분석하기
// const bodyParser = require('body-parser');  <= body parser 가 4.18에선 포함되었기 때문에 굳이 이렇게 안써도댐

// post 요청으로 보낸 payload 데이터를 분석할 객체를 지정하기
// => content-type: application/x-www-form-urlencoded 형식으로 된 payload 처리
//   예) name=hong&age=20
app.use(express.urlencoded({extended: true})); // qs 라이브러리가 더 많은 기능을 포함하기 떄문에 true 써야댐


const port = 3000;   // 웹 서버 포트 번호

// express() 를 호출하여 웹서버를 준비한다
const app = express();

// 클라이언트 요청에 대해 호출될 메서드를 등록
app.get(                                                    // GET 요청이 들어 왔을때 호출될 메서드 지정
    '/exam01-1',                                           // 요청 url
    (req, res) => {                                       // 요청 핸들러 :  요청이 들어 왔을 때 호출되는 메서드
      res.set("Access-Control-Allow-Origin", "*");       // CORS 문제 해결
      res.set("Content-Type", "text/plain;charset=UTF-8");
      res.send("안녕하세요 박경한 입니다!");
    }  
);  


// 웹서버 실행하기
app.listen(
  3000,                                         //포트 번호 지정
  () => {                                       // 서버가 시작되었을때 호출될 함수 = 리스너 = 이벤트 핸들러
    console.log(`${port}번 포트에서 서버 시작했음!!`);  //   `  <--- backtic

  }     
);












