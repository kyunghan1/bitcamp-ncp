  const { body } = document;                                      //객체안의 속성명 과 변수 이름이 같을때 사용가능 (구조분해할당 destructuring)  
  const table = document.createElement('table');             //1 먼저 테이블을 만든다
  const result = document.createElement('div');
  const data = [];
  let turn = 'O';
  
  const checkWinner = (target) => {
  const rowIndex = target.parentNode.rowIndex;
  const cellIndex = target.cellIndex;
  // 세칸 다 채워졌나?
  let hasWinner = false;
  // 가로줄
  if (
    data[rowIndex][0].textContent === turn &&
    data[rowIndex][1].textContent === turn &&
    data[rowIndex][2].textContent === turn
  ) {
    hasWinner = true;
  }
  // 세로줄
  if (
    data[0][cellIndex].textContent === turn &&
    data[1][cellIndex].textContent === turn &&
    data[2][cellIndex].textContent === turn
  ) {
    hasWinner = true;
  }
  // 대각선
  if (
    data[0][0].textContent === turn &&
    data[1][1].textContent === turn &&
    data[2][2].textContent === turn
  ) {
    hasWinner = true;
  }
  if (
    data[0][2].textContent === turn &&
    data[1][1].textContent === turn &&
    data[2][0].textContent === turn
  ) {
    hasWinner = true;
  }
  return hasWinner;
};


  const callback = (event) => {
  if (event.target.textContent !== '') {
    console.log('빈칸이 아닙니다');
    return;
  }
  console.log('빈칸입니다');
  event.target.textContent = turn;
  
  const hasWinner = checkWinner(event.target);
  if (hasWinner) {
    result.textContent = `${turn}님이 승리!`;
    table.removeEventListener('click', callback);
    return;
  }
  
  const draw = data.flat().every((cell) => cell.textContent);
  if (draw) {
    result.textContent = `무승부`;
    return;
  }
  if (turn === 'O') {
    turn = 'X';
  } else if (turn === 'X') {
    turn = 'O';
  }    
};

  
  for (let i = 0; i < 3; i++) {                                                                  // let i start (j접근 불가능)
    const tr = document.createElement('tr');
    const cell = []; 
    for (let j = 0; j < 3; j++){                             //한번쓴 변수는 다른 이름으로       //let j start (i j 접근가능)
      const td = document.createElement('td');                    //태그를 만들어 놓으면 변수로 빼놓는게 더 좋다 ex eventListener
      cell.push(td);
      tr.append(td);                                         //3 tr 안에 td를 넣는다
    }                                                                                            //jjjjjjjjjjjjjj  j end
    table.append(tr);                                        //2 테이블 안에 tr을 넣고
    data.push(cell); 
    table.addEventListener('click', callback);
};                                                                                               //iiiiiiiiii      i end
  body.append(table);                                        //다 만든 테이블을 body안에 넣는다
  body.append(result);