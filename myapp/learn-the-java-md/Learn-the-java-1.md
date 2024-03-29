# java compile 
---
## 다른 패키지 클래스에 접근하기

date : *23.12.29*

```
~$ javac -classpath /bin/main A.java

-classpath = -cp
bin/main <-- 루트 패키지가 들어있는 폴더 이어야 한다
bin/main/p1 <-- 패키지 경로를 직접 지정할 수 없다
```
`-classpath` 사용하는 클래스의 소스 파일이 있다면 -classpath로 경로를 지정할 필요가 없다.
루트 패키지와 클래스가 들어있는 디렉토리 경로
자바 컴파일러와 JVM은 클래스를 찾을 이 옵션의 경로를 따라간다.
```
~$ javac -d bin/main -sourcepath src/main/java src/main/java/A.java
```
`-sourcepath` A.java를 컴파일 할때 A클래스가 사용하는 다른 클래스의 소스 파일 경로를 알려준다.
A.java를 컴파일 할 때 A 가 사용하는 클래스의 소스 파일도 함께 컴파일 된다.

---

## main() 메서드
```
import p1.B;
// import p2.C;
import p2.px.C;
import p2.px.aaaaa.bbbbb.ccccc.ddddd.D;


class A {
  B obj;
  p2.C obj2;
  C obj3;
  D obj4;

  public static void main(String[] args) {
    System.out.println("Hello!-A");
    
  }
}
```
```
~$ java -cp bin/main A  
Hello!-A
```
java =

- bytecode 인터프리터
- JVM  

A =

- A 클래스 코드를 메모리에 적재 (loading)
- bytecode 검증
- main() 호출
- public static void main(String[ ] args) { } <-- 엔트리 포인트


`public static void main(String[ ] args) { }`
- main() 의 형식을 반드시 지켜야 한다 없으면 실행 오류
- method signature

---

## encoding 소스 파일의 인코딩 지정
```
~$ javac -encoding UTF-8 -d bin/main -sourcepath src/main/java src/main/java/A.java
```
- .java 파일에 한글이 포함 되어있을 경우 컴파일 할때 `-encoding UTF-8` 이라고 지정 해야 한다

## javadoc 주석을 html로 추출
```
~$ javadoc -encoding UTF-8 -charset UTF-8 -d javadoc -sourcepath src/main/java com.eomcs.lang.ex02
```
- `-encoding UTF-8 -charset UTF-8` 컴파일할때 추가

---

## 데이터 타입과 리터럴

### 정수
```
System.out.println(78); 10진수 (일반적)
System.out.println(+78);
System.out.println(-53_278); (알아보기 쉽게하기 위해 밑줄 _ 사용)
System.out.println(0116);  8진수 (잘 사용하지 않음)
System.out.println(0x4e);  16진수 (2진수의 간결한 표현)
System.out.println(0b01001110);  2진수 (직설적 표현법)
```
4byte (int) 
- 접미사를 붙이지 않고 숫자를 표현하면 4바이트로 저장된다 `System.out.println(2147483647);` 4바이트 +최대값   `System.out.println(-2147483648);` - 최소값


8byte (long)
- 4바이트 메모리를 벗어나는 정수 값을 표현할때 사용  
`System.out.println(2147483648L);` 숫자뒤에 **L** 사용

ex
- 서로다른 표기법  
`System.out.println(100);`  
`System.out.println(100L);`


### 부동소수점
```
System.out.println(3.14);
System.out.println(31.4e-1); 31.4 * 10의-1승 = 3.14
System.out.println(0.0314e2); 0.0314 * 10의2승 = 3.14
```
4byte (float - 단정도)
- 숫자 맨 뒤에 f 또는 F를 붙인다  
`System.out.println(3.14f); `
- 유효자릿수는 **7자리**까지 저장되고 넘어가면 잘린다

8byte (double - 배정도)
- 숫자 맨 뒤에 d 또는 D를 붙인다 **생략 가능**  
`System.out.println(3.14);`  
`System.out.println(3.14d);`
- 유효자릿수는 **16**자리 까지 저장되고 넘어가면 잘린다

### 논리
```
System.out.println(true); true = 1 (소문자로만 작성)
System.out.println(false); false = 0
```
4byte 리터럴
- 4byte int 메모리 저장
- 배열일 경우 1byte


### 문자
```
System.out.println('가');
```
2byte 리터럴
- 작은 따옴표를 사용하여 표현할 수 있고 2byte 코드로 저장한다
- 직접 문자를 적지 않고 **Character code** 를 적어서 출력 할 수도 있다  
`System.out.println('\u0041');` \u 소문자 필수로 적고 뒤에 16진수값   
`System.out.println('\101');` \ 8진수도 가능 단 0~377 범위에서만  

- 아래와 같이 키보드에 없는 걸 직접 적을땐 유니코드를 직접 적는다

```
System.out.println('\u4eba');  사람 '인(人)' 코드
System.out.println('\u00a9');  copyright © 문자 코드
System.out.println('\u03c0');  파이 코드 'π'
System.out.println('\u03a3');  시그마 기호 'Σ'
```
- 문자코드 표기법 `System.out.println((char)0x41);`  
문자의 코드 값임을 알려주기 위해 (char)를 써야한다

`System.out.println('각' + 1);`  
이라고 적으면 정수를 출력한다  
`System.out.println((char)('각' + 1));`  
이라고 적어야 `각` 다음의 문자가 출력될수있다


### 문자열
```
System.out.println("ABC가나다"); 
```
문자열 리터럴
- '문자' 와는 다르게 큰 따옴표를 써야한다

```
\n - Line Feed(LF), 0x0a        줄바꿈
\r - Carrage Return(CR), 0x0d   커서를 처음으로
\f - Form Feed, 0x0c            ?
\t - Tab, 0x09                  탭 공백 추가
\b - Backspace, 0x08            커서를 뒤로 한칸 이동
\' - Single Quote, 0x27         '
\" - Double Quote, 0x22         "
\\ - Backslash, 0x5c            문자 출력
```
