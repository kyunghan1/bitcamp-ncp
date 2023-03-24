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


//다음 클래스가 클라이언트 요청을 처리하는 일을 한다는 것을 SpringBoot 에게 알리는 표시이다
// SpringBoot 는 다음 클래스의 인스턴스를 생성해서 보관해 둔다
// "/hello" 라는 URL로 클라이언트 요청이 들어오면 해당 메서드를 호출한다
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500", "http://192.168.0.32:5500"})
@RestController
public class MemberController {
  MemberDao memberDao = new MemberDao();

  @PostMapping("/members")
  public Object addMember(
      @RequestParam (required = false)String name,
      @RequestParam (required = false)String tel,
      @RequestParam (required = false)String postno,
      @RequestParam (required = false)String basicaddress,
      @RequestParam (required = false)String detailaddress,
      @RequestParam (required = false)boolean working,
      @RequestParam (required = false)char gender,
      @RequestParam (required = false)byte level) { // 클라이언트에서 보낸 데이터를 받음 값이 안넘어오면 null이 넘어옴

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

    //응답 결과를 담을 맵 객체 준비
    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status","success");

    return contentMap;  //클라이언트로 데이터를 json 형식으로 던짐
  }
  @GetMapping("/members")
  public Object getMembers() {

    Member[] members = this.memberDao.findAll();

    //응답 결과를 담을 맵 객체 준비
    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status","success");
    contentMap.put("data", members);

    return contentMap;  //클라이언트로 데이터를 json 형식으로 던짐
  }
  @GetMapping("/members/{memberNo}")
  public Object getMember(@PathVariable int memberNo) {

    Member m = this.memberDao.findByNo(memberNo);
    //응답 결과를 담을 맵 객체 준비
    Map<String,Object> contentMap = new HashMap<>();

    if (m == null ) {
      contentMap.put("status","failure");
      contentMap.put("data", "해당번호의 게시글이 없습니다.");
    } else {
      contentMap.put("status","success");
      contentMap.put("data", m);
    }
    return contentMap;
  }
  @PutMapping("/members/{memberNo}")
  public Object updateMember(
      @PathVariable int memberNo,
      @RequestParam (required = false)String name,
      @RequestParam (required = false)String tel,
      @RequestParam (required = false)String postno,
      @RequestParam (required = false)String basicaddress,
      @RequestParam (required = false)String detailaddress,
      @RequestParam (required = false)boolean working,
      @RequestParam (required = false)char gender,
      @RequestParam (required = false)byte level) { // 클라이언트에서 보낸 데이터를 받음 값이 안넘어오면 null이 넘어옴

    Map<String,Object> contentMap = new HashMap<>();

    Member old = this.memberDao.findByNo(memberNo);
    if (old == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "게시글이 없거나 암호가 맞지 않습니다");
      return contentMap;
    }

    Member m = new Member();
    m.setNo(memberNo);
    m.setName(name);
    m.setTel(tel);
    m.setPostNo(postno);
    m.setBasicAddress(basicaddress);
    m.setDetailAddress(detailaddress);
    m.setWorking(working);
    m.setGender(gender);
    m.setLevel(level);
    m.setCreatedDate(old.getCreatedDate());

    this.memberDao.update(m);

    //응답 결과를 담을 맵 객체 준비
    contentMap.put("status","success");

    return contentMap;  //클라이언트로 데이터를 json 형식으로 던짐
  }
  @DeleteMapping("/members/{memberNo}")
  public Object deleteMember(
      @PathVariable int memberNo) {

    Member m = this.memberDao.findByNo(memberNo);
    //응답 결과를 담을 맵 객체 준비
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
