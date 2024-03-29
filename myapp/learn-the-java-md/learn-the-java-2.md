---
title: "Java 기본 개념2"
excerpt: "java 변수 사용 (variables)"

categories:
  - Java
tags:
  - [tag1, tag2]

permalink: /java/learn-the-java2

toc: trues
toc_sticky: true

date: 2022-12-29
last_modified_at: 2022-12-29
---
## 변수 선언 방식
```
int i1;  한개씩 선언 가능
int i2;
int i3;
또는
int j1, j2, j3;
```
```
public static void main(String[] args) {
  int a1, a2 = 200, a3, a4 = 400, a5;
  System.out.println(a2);
}
int a1, a3, a5 는 선언만 해 놓은 상태이다
```
```
public static void main(String[] args) {
count = 50;
int count;
}
변수 선언 보다 변수 사용이 먼저 올 수 없다
```

## 원시 데이터 타입 크기
```
primitive data type (원시 데이터 타입)
- 정수
   - byte   : 1byte 메모리 (-128 ~ 127)
   - short  : 2byte 메모리 (-32768 ~ 32767)
   - int    : 4byte 메모리 (약 -21억 ~ 21억)
   - long   : 8byte 메모리 (약 -922경 ~ 922경)
- 부동소수점
   - float  : 4byte 메모리 (유효자릿수 7자리)
   - double : 8byte 메모리 (유효자릿수 15자리)
- 문자
   - char   : 2byte 메모리 (0 ~ 65535). UCS-2 코드 값 저장.
- 논리값
   - boolean : JVM에서 4 바이트 int 메모리를 사용한다.
               배열일 경우 1 바이트 byte 메모리를 사용한다
```

## char 변수

**char c;** 
- 문자코드 (UCS2 = UTF16 = unicode2) 를 저장하는 2 byte 메모리
- 0 ~ 65535 범위의 값만 허용
```
char c = 65;
System.out.println(c);  
```
- 현재 프로그램에서 사용하는 폰트 파일을 찾는다
- 폰트 파일에서 65에 해당하는 문자를 찾는다
- 폰트 파일에서 읽은 A 문자 그림을 출력한다

**short s;**
- 2byte
- -32768 ~ 32767 범위의 값만 허용

``` 
public static void main(String[] args) {
  long l = 100;
  int i = 100;
  short s = 100;
  byte b = 100;
  char c = 100;

  변수의 값을 다른 변수에 저장할 때,
  값의 크기에 상관없이 같거나 큰 크기의 메모리이어야 한다.
  ******(left-value 가 더 커야됨)

  long l2;
  int i2;
  short s2;
  byte b2;
  char c2;

  // long ===> long 이상
  l2 = l;
  //i2 = l; // 컴파일 오류
  //s2 = l; // 컴파일 오류
  //b2 = l; // 컴파일 오류!
  //c2 = l; // 컴파일 오류!

  // int ===> int 이상
  l2 = i;
  i2 = i;
  //s2 = i; // 컴파일 오류!
  //b2 = i; // 컴파일 오류!
  //c2 = i;  // 컴파일 오류!
  
  // short ===> short 이상
  l2 = s;
  i2 = s;
  s2 = s;
  //b2 = s; // 컴파일 오류!
  //c2 = s; // 컴파일 오류! char(0 ~ 65535) | short(-32768 ~ 32767)
  
  // byte ===> byte 이상
  l2 = b;
  i2 = b;
  s2 = b;
  b2 = b;
  //c2 = b; // 컴파일 오류! char(0 ~ 65535) | byte(-128 ~ 127)
}
```