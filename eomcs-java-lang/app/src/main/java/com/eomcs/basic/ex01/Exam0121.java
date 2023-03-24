// Object 클래스 - toString() 을 오버라이딩 할 때
package com.eomcs.basic.ex01;



public class Exam0121 {

  static class My {
    String name;
    int age;
    int date;
    int kk;
    int ll;
    boolean oo;
    char pp;
    byte rr;
    @Override
    public String toString() {
      return "My [name=" + name + ", age=" + age + ", date=" + date + ", kk=" + kk + ", ll=" + ll
          + ", oo=" + oo + ", pp=" + pp + ", rr=" + rr + "]";
    }


    // 개발을 하다 보면 인스턴스의 현재 값을 간단히 확인하고 싶을 경우가 있다.
    // 그럴 경우 toString()을 오버라이딩 하라!


  }

  public static void main(String[] args) {

    My obj1 = new My();

    obj1.name = "홍길동";
    obj1.age = 20;
    obj1.date = 12;
    obj1.kk = 1;
    obj1.ll = 2;
    obj1.oo = true;
    obj1.pp = 'a';
    obj1.rr = 127;

    System.out.println(obj1.toString());

    // println()의 파라미터 값으로 문자열을 넘겨주지 않으면,
    // println() 내부에서 파라미터로 넘어온 객체에 대해 toString() 호출한 후
    // 그 리턴 값을 출력한다.
    // 따라서 그냥 객체(주소)를 넘겨줘도 된다.
    System.out.println(obj1);
  }

}







