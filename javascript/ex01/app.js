const { fail } = require('assert')
const express = require('express');
const { resolve } = require('path');
const app = express()
const port = 3000

// function execute(resolve, reject) {
//   console.log("작업수행!");
//   reject();
// }

// function success() {
//   console.log("success!");
// }

// // function fail() {
// //   console.log("fail!");
// // }

// function final() {
//   console.log("compleate!");
// }

// app.get('/hello', async (req, res) => {

//   await new Promise(resolve => setTimeout(resolve, 5000));

//     console.log("Hello!");
//     res.send('Hello!');
// });
  

    
  

  // p1.then(() => {
  //   console.log("success!");
  // });
  // // p1.catch(fail);
  // p1.finally(() => {
  //   console.log("completed!");
  // });

  



// const delay = async () => {
 
//       console.log("====> 잠들다!");
//       await sleep(5000);
//       console.log("====> 깨어나다!");
  
// }
// const sleep = (ms) => {
//    return new Promise(resolve=>{
//        setTimeout(resolve,ms)
//    })
// }

app.get('/exam05_1', async (req, res) => {
  await new Promise(resolve => setTimeout(resolve, 10000));
  //res.header("Access-Control-Allow-Origin", "*");
  // delay();
  // for (var i = 0; i < 20000000; i++) {
  //   var r = Math.random() * Math.random() * Math.random() * Math.random() * Math.random() * Math.random();
  // }
  res.send('console.log("exam05_1 ok!");')
})

app.get('/exam05_2', (req, res) => {
  //res.header("Access-Control-Allow-Origin", "*");
  // delay();
  // for (var i = 0; i < 20000000; i++) {
  //   var r = Math.random() * Math.random() * Math.random() * Math.random() * Math.random() * Math.random();
  // }
  res.send('console.log("exam05_2 ok!");')
})

app.get('/exam05_x', async (req, res) => {
  await new Promise(resolve => setTimeout(resolve, 10000));
  //res.header("Access-Control-Allow-Origin", "*");
  // for (var i = 0; i < 20000000; i++) {
  //   var r = Math.random() * Math.random() * Math.random() * Math.random() * Math.random() * Math.random();
  // }
  res.send('var rate = 30000')
})

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
})

