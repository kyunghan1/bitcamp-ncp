package bitcamp.myapp.dao;

import java.sql.Date;
import bitcamp.myapp.vo.Student;

public class StudentDao extends ObjectDao{

  //가장 최근 게시글의 글 번호를 저장하는필드
  //가장 최근 게시글이 삭제 되더라도 그 값은 그대로 유지할 것이다.
  int lastNo;
  @Override
  public Student findByNo(int no) {
    Student b = new Student();
    b.setNo(no);
    //    int index = this.indexOf(b);
    //
    //    if (index <0) {
    //      return null;
    //    }else {
    //      return (Student) this.get(index);
    //    }
    return (Student) this.get(this.indexOf(b));
  }


  @Override  //컴파일러에게 오버라이딩을 제대로 했는지 검사해 달라고 표시
  protected int indexOf(Object obj) {
    for (int i = 0; i < this.size(); i++) {
      if (((Student)this.objects[i]).getNo() == ((Student)obj).getNo()) {
        return i;
      }
    }
    return -1;
  }


  @Override
  public void insert(Object object) {
    Student s = (Student) object;

    s.setNo(++lastNo);

    s.setCreatedDate(new Date(System.currentTimeMillis()).toString());

    super.insert(object);


  }
}
