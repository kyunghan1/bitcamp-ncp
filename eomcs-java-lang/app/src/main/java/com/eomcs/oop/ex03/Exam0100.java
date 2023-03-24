// # 인스턴스 변수
//
package com.eomcs.oop.ex03;

public class Exam0100 {

  //static 필드 = 클래스필드(변수)
  //클래스를 로딩할때 Method area 영역에 생성된다.
  //클래스는 단 한번만 로딩된다.
  //따라서 스태틱 변수도 한번만 생성된다.
  //jvm을 종료할때 메모리에서 한꺼번에 제거된다.
  static int a;

  //non-static 필드 = 인스턴스 필드
  //new 연산자를 실행할 때 heap 영역에 생성된다.
  //new 연산자를 실행할 때마다 생성된다.
  //가비지 콜렉터에 의해 인스턴스가 해제 될때 제거 된다.
  int b;

  public static void main(String[] args) { //파라미터 = 로컬변수(args)

    //로컬변수
    //메소드가 호출될때 jvm stack 영역에 생성된다.
    //메소드 호출이 끝나면 제거된다.
    int c;
    c=100;

    //현재 시점
    //method area : a 변수 존재
    //jvm 스택 : args, c 변수 존재
    //heap : 아직생선된 객체없음
    Exam0100 obj; // obj는 main()을 호출할때 시작 시점에 jvm stack에 생성된 상태

    obj = new Exam0100();

    //현재 실행 시점
    //Method area : a변수 존재
    // jvm stack : args,c,obj 존재
    //heap : b변수 존재

    System.out.println(c);
  }
}
