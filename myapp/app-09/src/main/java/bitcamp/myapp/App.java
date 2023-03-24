package bitcamp.myapp;
public class App {

  public static void main(String[] args) {
    goMainMenu();
    System.out.println("안녕히가세요!");

    Prompt.close();
  }

  private static void goMainMenu() {
    while(true) {
      System.out.println("1. 일반학생관리");
      System.out.println("2. 국비지원학생관리");
      System.out.println("3. 위탁교육생관리");
      System.out.println("9. 종료");
      int menuNo = Prompt.inputInt("메뉴> ");
      if(menuNo == 1){
        MemberHandler.title ="일반학생";
        MemberHandler.service();
      }else if(menuNo==2) {
        Member2Handler.title ="국비지원학생";
        Member2Handler.service();
      }else if(menuNo==3) {
        Member3Handler.title ="위탁교육생관리";
        Member3Handler.service();
      } else if(menuNo ==9 ) {
        break;
      }else
        System.out.println("잘못된 메뉴입니다.");
    } // main()

  }
} // class App









