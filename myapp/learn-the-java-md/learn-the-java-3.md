---
title: "Java 기본 개념3"
excerpt: "java 배열"

categories:
  - Java
tags:
  - [tag1, tag2]

permalink: /java/learn-the-java3

toc: trues
toc_sticky: true

date: 2022-12-29
last_modified_at: 2022-12-29
---

## 배열

**배열 문법** 
- 같은 종류의 메모리를 여러개 생성할때 사용하는 문법
- 메모리로 연속된 바이트들의 1차원 배열이고
- 이해를 돕기 위해 보통 2차원으로 표현한다.

**배열 선언**
- 데이터타입[ ] 변수명 = new 메모리종류[ 개수 ]
- `int [] a = new int [4]`
- `int a[ ] = new int[4]` <---- C언어 style

**배열 초기화 명령**
```
int[] arr2 = new int[]{100, 90, 80, 70, 60}; 배열 선언 + 초기화 명령
int[] arr3 = {100, 90, 80, 70, 60}; 배열선언 + 초기화명령

int[] arr5;
arr5 = new int[]{0, 0, 0, 0, 0};  선언따로 초기화명령 따로
```

## JVM heap memory
- `new` 연산자로 생성된 객체가 저장되는 곳 이다
- JVM은 heap 영역에 연속된 int 타입의 메모리를 준비한다
- 리턴값은 준비한 메모리의 시작 주소이다
- `new` 명령을 통해 준비한 메모리를 **instance** 라고 한다 예) int 배열의 인스턴스
