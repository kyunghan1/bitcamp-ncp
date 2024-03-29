package bitcamp.myapp.handler;

import java.sql.Date;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;

public class MemberHandler {

  private MemberDao memberDao = new MemberDao();
  private String title;

  // 인스턴스를 만들 때 프롬프트 제목을 반드시 입력하도록 강제한다.
  public MemberHandler(String title) {
    this.title = title;
  }

  private void inputMember() {
    Member m = new Member();
    m.setNo (Prompt.inputInt("번호? "));
    m.setName (Prompt.inputString("이름? "));
    m.setTel(Prompt.inputString("전화? "));
    m.setPostNo ( Prompt.inputString("우편번호? "));
    m.setBasicAddress (Prompt.inputString("주소1? "));
    m.setDetailAddress (Prompt.inputString("주소2? "));
    m.setWorking (Prompt.inputInt("0. 미취업\n1. 재직중\n재직자? ") == 1);
    m.setGender (Prompt.inputInt("0. 남자\n1. 여자\n성별? ") == 0 ? 'M' : 'W');
    m.setLevel ((byte) Prompt.inputInt("0. 비전공자\n1. 준전공자\n2. 전공자\n전공? "));
    m.setCreatedDate (new Date(System.currentTimeMillis()).toString());

    this.memberDao.insert(m);
  }

  private void printMembers() {
    System.out.println("번호\t이름\t전화\t재직\t전공");

    Member[] members = memberDao.findAll();

    for (Member m : members) {
      System.out.printf("%d\t%s\t%s\t%s\t%s\n",
          m.getNo(), m.getName(), m.getTel(),
          m.isWorking() ? "예" : "아니오",
              getLevelText(m.getLevel()));
    }
  }

  private void printMember() {
    int memberNo = Prompt.inputInt("회원번호? ");

    Member m = this.memberDao.findByNo(memberNo);

    if (m == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    System.out.printf("    이름: %s\n", m.getName());
    System.out.printf("    전화: %s\n", m.getTel());
    System.out.printf("우편번호: %s\n", m.getPostNo());
    System.out.printf("기본주소: %s\n", m.getBasicAddress());
    System.out.printf("상세주소: %s\n", m.getDetailAddress());
    System.out.printf("재직여부: %s\n", m.isWorking() ? "예" : "아니오");
    System.out.printf("    성별: %s\n", m.getGender() == 'M' ? "남자" : "여자");
    System.out.printf("    전공: %s\n", getLevelText(m.getLevel()));
    System.out.printf("  등록일: %s\n", m.getCreatedDate());
  }

  // 인스턴스 멤버(필드나 메서드)를 사용하지 않기 때문에
  // 그냥 스태틱 메서드로 두어라!
  private static String getLevelText(int level) {
    switch (level) {
      case 0: return "비전공자";
      case 1: return "준전공자";
      default: return "전공자";
    }
  }

  private void modifyMember() {
    int memberNo = Prompt.inputInt("회원번호? ");

    Member old = this.memberDao.findByNo(memberNo);

    if (old == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    // 변경할 데이터를 저장할 인스턴스 준비
    Member m = new Member();
    m.setNo (old.getNo());
    m.setCreatedDate(old.getCreatedDate());
    m.setName(Prompt.inputString(String.format("이름(%s)? ", old.getName())));
    m.setTel(Prompt.inputString(String.format("전화(%s)? ", old.getTel())));
    m.setPostNo (Prompt.inputString(String.format("우편번호(%s)? ", old.getPostNo())));
    m.setBasicAddress (Prompt.inputString(String.format("기본주소(%s)? ", old.getBasicAddress())));
    m.setDetailAddress (Prompt.inputString(String.format("상세주소(%s)? ", old.getDetailAddress())));
    m.setWorking ( Prompt.inputInt(String.format(
        "0. 미취업\n1. 재직중\n재직여부(%s)? ",
        old.isWorking() ? "재직중" : "미취업")) == 1);
    m.setGender(Prompt.inputInt(String.format(
        "0. 남자\n1. 여자\n성별(%s)? ",
        old.getGender() == 'M' ? "남자" : "여자")) == 0 ? 'M' : 'W');
    m.setLevel ((byte) Prompt.inputInt(String.format(
        "0. 비전공자\n1. 준전공자\n2. 전공자\n전공(%s)? ",
        getLevelText(old.getLevel()))));

    String str = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (str.equalsIgnoreCase("Y")) {
      this.memberDao.update(m);
      System.out.println("변경했습니다.");
    } else {
      System.out.println("변경 취소했습니다.");
    }

  }

  private void deleteMember() {
    int memberNo = Prompt.inputInt("회원번호? ");

    Member m = this.memberDao.findByNo(memberNo);

    if (m == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    String str = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (!str.equalsIgnoreCase("Y")) {
      System.out.println("삭제 취소했습니다.");
      return;
    }

    this.memberDao.delet(m);

    System.out.println("삭제했습니다.");

  }

  private void searchMember() {
    Object[] members = this.memberDao.findAll();
    String name = Prompt.inputString("이름? ");
    System.out.println("번호\t이름\t전화\t재직\t전공");
    for (Object obj : members) {
      Member m = (Member) obj;
      if (m.getName().equalsIgnoreCase(name)) {
        System.out.printf("%d\t%s\t%s\t%s\t%s\n",
            m.getNo(), m.getName(), m.getTel(),
            m.isWorking() ? "예" : "아니오",
                getLevelText(m.getLevel()));
      }
    }
  }

  public void service() {
    while (true) {
      System.out.printf("[%s]\n", this.title);
      System.out.println("1. 등록");
      System.out.println("2. 목록");
      System.out.println("3. 조회");
      System.out.println("4. 변경");
      System.out.println("5. 삭제");
      System.out.println("6. 검색");
      System.out.println("0. 이전");
      int menuNo = Prompt.inputInt(String.format("%s> ", this.title));

      switch (menuNo) {
        case 0: return;
        case 1: this.inputMember(); break;
        case 2: this.printMembers(); break;
        case 3: this.printMember(); break;
        case 4: this.modifyMember(); break;
        case 5: this.deleteMember(); break;
        case 6: this.searchMember(); break;
        default:
          System.out.println("잘못된 메뉴 번호 입니다.");
      }
    }
  }
}