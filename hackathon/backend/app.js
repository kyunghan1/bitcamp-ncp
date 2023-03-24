const express = require('express');
const app = express();
const port = 3000;

const request = require('request');

const fs = require('fs');
const path = require('path');
const { getHeapSpaceStatistics } = require('v8');
const csvFilePath = path.join(__dirname, 'stationInfo.csv');
const csvFile = fs.readFileSync(csvFilePath, 'utf-8');
let stationData = {}

app.listen(port, () => {
    console.log(`${port}포트 서버 실행`);

    for (let station of csvFile.split('\r\n')) {
        let splitedData = station.split(',');
        stationData[splitedData[1]] = [splitedData[0], splitedData[2]]; //이 데이터 좀 바꿔야함
    }
});

app.get("/metro", (req, res) => {
    res.set('Access-Control-Allow-Origin', '*');
    res.set('Content-Type', 'application/json; charset=UTF-8');
    console.log(req.query.search);

    let requestUrl = "http://swopenAPI.seoul.go.kr/api/subway/4a4c59534966736838357a47736d6d/json/realtimeStationArrival/0/10/" + encodeURIComponent(req.query.search); //요청을 역명(한국어로함)
    request.get({ uri: requestUrl }, (error, response, body) => {   
        console.log(response);
        res.send(JSON.stringify(primaryProcessing(JSON.parse(body), req.query.line)));
    });
});

//body얘는 이상한 스트링 덩어리임
// 기본은
// 0번 1번은 상행
// 2번 3번은 하행
//필요한정보 역, 몇분후도착
// 이전역 다음역 알아야함

function primaryProcessing(responseObj, lineNumber) {
    let outheader = {};
    let outbody = {};
    let right = 0;
    let left = 0;
    let key = "";

    if (responseObj.status == 500) {
        return {};
    }

    responseObj.realtimeArrivalList.forEach((e) => {
        if(true) { //e.subwayId == lineNumber
            outheader = {
                before: stationData[e.statnFid][1], //전역정보
                next: stationData[e.statnTid][1], //다음역정보                
            };

            if (e.subwayHeading == "오른쪽") {
                right++;
                key = `right${right}`;
            } else {
                left++;
                key = `left${left}`;
            }
            outbody[key] = {
                ready: e.arvlMsg3, //지금어딘지
                nowstate: e.arvlCd, //상태
                //여기서 시간도 받아올수 있으면 ㄱㄱ
            }
        }    
    });
    return {header: outheader, body: outbody}
};

// var obj= {
// beginRow: null,
// endRow: null,
// curPage: null,
// pageRow: null,
// totalCount: 4,
// rowNum: 1,
// selectedCount: 4,
// subwayId: '1001',
// subwayNm: null,
// updnLine: '상행',
// trainLineNm: '병점행 - 가산디지털단지방면',
// subwayHeading: '오른쪽',
// statnFid: '1001080144',
// statnTid: '1001080142',
// statnId: '1001080143',
// statnNm: '독산',
// trainCo: null,
// ordkey: '01016병점0',
// subwayList: '1001',
// statnList: '1001080143',
// btrainSttus: null,
// barvlDt: '0',
// btrainNo: '0766',
// bstatnId: '21',
// bstatnNm: '병점',
// recptnDt: '2022-12-22 00:06:23',
// arvlMsg2: '[16]번째 전역 (오산대)',
// arvlMsg3: '오산대',
// arvlCd: '99'
// }
