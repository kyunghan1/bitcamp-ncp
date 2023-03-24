package bitcamp.bootapp.vo;


public class Teacher {

  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getTel() {
    return tel;
  }
  public void setTel(int tel) {
    this.tel = tel;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public int getHighGrade() {
    return highGrade;
  }
  public void setHighGrade(int highGrade) {
    this.highGrade = highGrade;
  }
  public String getUniv() {
    return univ;
  }
  public void setUniv(String univ) {
    this.univ = univ;
  }
  public String getMajor() {
    return major;
  }
  public void setMajor(String major) {
    this.major = major;
  }
  public int getPay() {
    return pay;
  }
  public void setPay(int pay) {
    this.pay = pay;
  }
  public String getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
  }
  private int no;
  private String name; //강사 이름
  private int tel;   // 전화
  private String email;  // 이메일
  private int highGrade; // 최종학력
  private String univ;  // 대학교
  private String major;  //전공
  private int pay;  // 강사료
  private String createdDate;
}
