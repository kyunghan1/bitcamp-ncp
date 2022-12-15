(function() {
	
	var state = 1;
	var puzzle = document.getElementById('puzzle');

	solve();
	
	puzzle.addEventListener('click', function(e) {   //만든 퍼즐에 이벤트리스너를 추가하고 
		if(state == 1) {
			puzzle.className = 'animate';
			shiftCell(e.target);                            //shiftcell 함수를 호출합니다
		}
	});
	

	document.getElementById('scramble').addEventListener('click', scramble);


	function solve() {
		
		if(state == 0) {
			return;
		}
		
		puzzle.innerHTML = '';
		

		// [[00][01][02][03]]
		// [[10][11][12][13]]
		// [[20][21][22][23]]
		// [[30][31][32][33]]
		var n = 1;
		for(var i = 0; i <= 3; i++) {
			for(var j = 0; j <= 3; j++) {
				var cell = document.createElement('span');
				cell.id = 'cell-'+i+'-'+j;  // +i+ 행을 나타냄
				cell.style.left = (j*80+1*j+1)+'px';
				cell.style.top = (i*80+1*i+1)+'px';
				
				if(n <= 15) {
					// cell.classList.add("number");
					cell.classList.add(`number${n}`);
					cell.classList.add(n);
					cell.innerHTML = (n++).toString();
					// n++;
				} else {
					cell.className = 'empty';
				}
				
				puzzle.appendChild(cell);
			}
		}
		
	}

	function shiftCell(cell) {                           
		
		
		if(cell.clasName != 'empty') {                             //shiftcell의 조건문을 추가하고 만약 선택한 cell id가 empty가 아니라면
			
			var emptyCell = getEmptyAdjacentCell(cell);                //빈 인접셀을 가져온다
			
			if(emptyCell) {                                           // 인접셀의 스타일과 아이디 값을 바꾼다
	
				var tmp = {style: cell.style.cssText, id: cell.id};   
				

				cell.style.cssText = emptyCell.style.cssText;
				cell.id = emptyCell.id;
				emptyCell.style.cssText = tmp.style;
				emptyCell.id = tmp.id;
				
				if(state == 1) {                                        

					setTimeout(checkOrder, 150);                            // checkOrder를 실행한다
				}
			}
		}
		
	}


	function getCell(row, col) {                 // getCell의 row와 col 값을 cell id로 리턴받습니다
	
		return document.getElementById('cell-'+row+'-'+col);
		
	}


	function getEmptyCell() {                      // getEmptyCell은 puzzle내부에 class이름을 empty로 설정된 값을 받습니다
	
		return puzzle.querySelector('.empty');
			
	}
	

	function getEmptyAdjacentCell(cell) {
		

		var adjacent = getAdjacentCells(cell);
		

		for(var i = 0; i < adjacent.length; i++) {
			if(adjacent[i].className == 'empty') {
				return adjacent[i];
			}
		}
		

		return false;
		
	}


	function getAdjacentCells(cell) {                        
		
		var id = cell.id.split('-');                         //id를 구분해서 가져옵니다
		

		var row = parseInt(id[1]);                        // parseInt로 cell아이디값을 숫자로 변환하고
		var col = parseInt(id[2]);
		
		var adjacent = [];
		

		if(row < 3){adjacent.push(getCell(row+1, col));}			//모든 배열에 데이터를 넣습니다
		if(row > 0){adjacent.push(getCell(row-1, col));}
		if(col < 3){adjacent.push(getCell(row, col+1));}
		if(col > 0){adjacent.push(getCell(row, col-1));}
		
		return adjacent;
		
	}
	

	function checkOrder() {
		

		if(getCell(3, 3).className != 'empty') {             // 배열의 3,3의 값이 empty가 아니라면 checkOrder를 다시 실행합니다
			return;
		}

		var n = 1;

		for(var i = 0; i <= 3; i++) {
			for(var j = 0; j <= 3; j++){
				if(n <= 15 && getCell(i, j).innerHTML != n.toString()) {

					return;
				}
				n++;
			}
		}
		

		if(confirm('Congrats, You did it! \nScramble the puzzle?')) {
			scramble();
		}
	
	}


	function scramble() {
	
		if(state == 0) {
			return;
		}
		
		puzzle.removeAttribute('class');
		state = 0;
		
		var previousCell;
		var i = 1;
		var interval = setInterval(function() {
			if(i <= 100){
				var adjacent = getAdjacentCells(getEmptyCell());
				if(previousCell){
					for(var j = adjacent.length-1; j >= 0; j--) {
						if(adjacent[j].innerHTML == previousCell.innerHTML) {
							adjacent.splice(j, 1);
						}
					}
				}

				previousCell = adjacent[rand(0, adjacent.length-1)];
				shiftCell(previousCell);
				i++;
			} else {
				clearInterval(interval);
				state = 1;
			}
		}, 5);

	}
	

	function rand(from, to) {

		return Math.floor(Math.random() * (to - from + 1)) + from;

	}

}());
