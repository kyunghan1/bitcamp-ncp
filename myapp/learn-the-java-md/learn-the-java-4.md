---
title: "Java 문법 해석"
excerpt: "java 문법의 이해"

categories:
  - Java
tags:
  - [tag1, tag2]

permalink: /java/learn-the-java4

toc: trues
toc_sticky: true

date: 2022-12-29
last_modified_at: 2022-12-29
---
## 키보드 입력 받기

`System.out.ptintln(no)`
- System = 도구함 calss
- out = 변수 field
- println = 작업자(연산자) method = funtion
- (no) = 파라미터

```
%d	정수형 출력  
%s  문자형 출력  
%f	실수형 출력  
%c	문자열 출력  
%b	boolean 출력  

\n  Line Feed(LF)       줄바꿈  
\r  Carrage Return(CR)  커서를 처음으로  
\f  Form Feed           ?  
\t  Tab                 탭 공백 추가  
\b  Backspace           커서를 뒤로 한칸 이동  
\'  Single Quote         '  
\"  Double Quote         "  
\\  Backslash           문자 출력
```

`Scanner keyScanner = new Scanner(System.in);`
- (System.in) = 키보드 정보가 들어있는 변수
-  new Scanner = 파라미터로 주어진 입력장치에서 데이터를 읽는 일을 하는 객체, 키보드에서 읽은 바이트를 가공한 문자정보
- keyScanner.nextLine() = 한줄의 문자열을 읽는다
```
Scanner keyScanner = new Scanner(System.in);    
int no = Integer.parseInt(keyScanner.nextLine());
```
- keyScanner = 문자열을 읽을 도구 정보 (값)
- Integer = 도구함 (class) 대문자작성

**객체 = 데이터가 저장된 메모리**

## 객체와 메서드, 파라미터


`객체.메서드(파라미터, 파라미터)`  
객체가 메서드의 값을 읽고 작업결과를 제작한다  
메서드는 작업을 수행하고  
파라미터는 메서드의 값을 사용한다  
메서드는 파라미터에 들어 있는 값을 사용만 한다 저장x
