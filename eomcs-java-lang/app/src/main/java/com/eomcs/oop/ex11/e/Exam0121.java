// anonymous class : 클래스를 상속 받은 익명 클래스 정의
package com.eomcs.oop.ex11.e;

public class Exam0121 {

  // 클래스는 static과 non-static이 구분된다.
  static abstract class A {
    abstract void print();
  }

  public static void main(final String[] args) {
    // 2) 익명 클래스로 서브 클래스 만들기
    // => 인스턴스를 한 번 만 생성할 것이라면,
    //    로컬 클래스로 정의하는 것 보다 익명 클래스로 정의하는 것이 더 낫다.
    // => 특히 객체를 사용하려는 곳에 바로 익명 클래스를 정의하면
    //    읽기 쉽기 때문에 소스 코드를 유지보수에 더 좋다.
    //
    // 익명 클래스로 인터페이스 구현하기
    // 문법:
    // => 수퍼클래스 레퍼런스 = new 수퍼클래스() {};
    //    - 파라미터 없이 호출하면 익명 클래스의 기본 생성자를 호출한다
    //    - 파라미터를 가지고 생성자를 호출하면 수퍼 클래스에서 생성자를 찾아 호출한다
    //
    A obj2 = new A() {
      @Override
      void print() {
        System.out.println("Hello!");
      }
    };
    obj2.print();
  }
}
