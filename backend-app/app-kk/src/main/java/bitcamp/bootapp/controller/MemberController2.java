package bitcamp.bootapp.controller;


import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import bitcamp.bootapp.dao.MemberDao;
import bitcamp.bootapp.vo.Member;

@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500", "http://192.168.0.32:5500"})
@RestController
public class MemberController2 {

  MemberDao memberDao = new MemberDao();


  @PostMapping("/members1")
  public Object addMember(
      @RequestParam (name = "name", required = false)String name,
      @RequestParam (required = false)String tel,
      @RequestParam (required = false)String postno,
      @RequestParam (required = false)String basicaddress,
      @RequestParam (required = false)String detailaddress,

      @RequestParam (required = false)boolean working,
      // &..working=xxx&.. 파라미터가 있으면 true 없으면 false
      // "on"=true/"off"=false, "1"=true/"0"=false 그밖에 문자열은 변환 오류

      @RequestParam (required = false)char gender,
      // &..gender=xxx&.. 'M' charAt(0)
      // 문자 1개의 문자열 변환 그밖에 문자열은 변환 오류 발생

      @RequestParam (required = false)byte level){
    // &..level=xxx&.. byte.parseByte("1")
    // Byte 메모리 허용값 128을 넘어가면 오류 발생

    System.out.printf("%s,%s,%s,%s,%s,%b,%c,%d\n",
        name, tel, postno, basicaddress, detailaddress, working, gender, level);

    Member m = new Member();
    m.setName(name);
    m.setTel(tel);
    m.setPostNo(postno);
    m.setBasicAddress(basicaddress);
    m.setDetailAddress(detailaddress);
    m.setWorking(working);
    m.setGender(gender);
    m.setLevel(level);
    m.setCreatedDate(new Date(System.currentTimeMillis()).toString());

    this.memberDao.insert(m);

    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status","success");

    return contentMap;
  }


  @GetMapping("/members1")
  public Object getMembers() {

    Member[] members = this.memberDao.findAll();

    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status","success");
    contentMap.put("data", members);

    return contentMap;
  }

  @GetMapping("/members1/{memberNo}")
  public Object getMember(@PathVariable int memberNo) {

    Member m = this.memberDao.findByNo(memberNo);

    Map<String,Object> contentMap = new HashMap<>();

    if (m== null ) {
      contentMap.put("status","failure");
      contentMap.put("data", "해당번호의 회원이 없습니다.");
    } else {
      contentMap.put("status","success");
      contentMap.put("data",m);
    }
    return contentMap;
  }
  @PutMapping("/members1/{No}")
  // @PathVariable no로
  public Object updateMember(
      Member member) {

    Map<String,Object> contentMap = new HashMap<>();

    Member old = this.memberDao.findByNo(member.getNo());
    if (old == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "회원이 없습니다");
      return contentMap;
    }

    member.setCreatedDate(new Date(System.currentTimeMillis()).toString());

    this.memberDao.update(member);

    contentMap.put("status","success");

    return contentMap;
  }

  @DeleteMapping("/members1/{memberNo}")
  // @PathVariable 낱개로 받을때는 어노테이션 생략 x
  public Object deleteMember(
      @PathVariable int memberNo) {

    Member m = this.memberDao.findByNo(memberNo);

    Map<String,Object> contentMap = new HashMap<>();

    if (m == null) {
      contentMap.put("status","failure");
      contentMap.put("data", "게시글이 없거나 암호가 맞지 않습니다.");
    } else {
      this.memberDao.delete(m);
      contentMap.put("status","success");
    }
    return contentMap;
  }

}


