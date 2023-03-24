package bitcamp.myapp.dao;

import java.sql.Date;
import bitcamp.myapp.vo.Board;
import bitcamp.util.List;

public class BoardDao {

  // 특정 클래스를 지정하기 보다는 인터페이스를통해 관계를 느슨하게 만든다
  List list;
  int lastNo;
  public BoardDao(List list) {
    this.list = list;
  }


  public void insert(Board board) {
    // 객체를 배열에 담기 전에 그 객체의 번호를 설정한다.
    board.setNo(++lastNo);

    // 인스턴스를 생성할 때의 날짜와 시각을 설정한다.
    board.setCreatedDate(new Date(System.currentTimeMillis()).toString());

    // 그런 후에 수퍼 클래스에서 상속 받은 insert()를 사용하여 객체를 배열에 보관한다.
    list.add(board);

    // super.insert() ?
    // => 현재 클래스에서 insert()를 찾지 말고, 수퍼 클래스에서 찾아 올라 가라!
  }

  public Board[] findAll( ) {
    Board[] boards = new Board[list.size()];
    Object[] arr = list.toArray();
    for (int i = 0; i < boards.length; i++) {
      boards[i] = (Board) arr[i];
    }
    return boards;
  }

  // Board 객체를 게시글 번호를 찾는 메서드
  public Board findByNo(int no) {
    Board b = new Board();
    b.setNo(no);

    int index = list.indexOf(b);
    if (index == -1) {
      return null;
    }
    return (Board) list.get(index);
  }

  public void update(Board b) {
    int index = list.indexOf(b);
    list.set(index, b);
  }
  public boolean delete(Board b) {
    return list.remove(b);

  }


  // 수퍼 클래스의 insert()는 객체를 등록할 때 번호를 자동증가시키는 기능이 없다.
  // 그러나 BoardDao는 그런 기능이 필요하다.
  // => 수퍼 클래스의 메서드를 서브 클래스의 역할이나 목적에 맞게 재정의 한다.
  // => 이것을 '오버라이딩(overriding)"이라 부른다.

}























