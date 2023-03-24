package bitcamp.myapp;

public class App {

  public static void main(String[] args) {
    goMainMenu();
    System.out.println("안녕히 가세요!");
    Prompt.close();
  }

  private static void goMainMenu() {
    BoardHandler boardHandler = new BoardHandler("게시판 관리");

    while (true) {
      System.out.println("1. 게시판");
      System.out.println("0. 종료");
      int menuNo = Prompt.inputInt("메뉴> ");

      if (menuNo == 1) {
        boardHandler.service();
      } else if (menuNo == 0) {
        break;
      } else {
        System.out.println("잘못된 메뉴 번호 입니다.");
      }
    }
  }
}