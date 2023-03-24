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
import org.springframework.web.bind.annotation.RestController;
import bitcamp.bootapp.dao.TeacherDao;
import bitcamp.bootapp.vo.Teacher;

@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500", "http://192.168.0.32:5500"})
@RestController
public class TeacherController {

  TeacherDao teacherDao = new TeacherDao();


  @PostMapping("/teachers")
  public Object addTeacher(
      String name,
      int tel,
      String email,
      int highGrade,
      String univ,
      String major,
      int pay) {

    Teacher t = new Teacher();
    t.setName(name);
    t.setTel(tel);
    t.setEmail(email);
    t.setHighGrade(highGrade);
    t.setUniv(univ);
    t.setMajor(major);
    t.setPay(pay);
    t.setCreatedDate(new Date(System.currentTimeMillis()).toString());

    this.teacherDao.insert(t);

    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status","success");

    return contentMap;
  }


  @GetMapping("/teachers")
  public Object getTeachers() {

    Teacher[] teachers = this.teacherDao.findAll();

    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status","success");
    contentMap.put("data", teachers);

    return contentMap;
  }

  @GetMapping("/teachers/{teacherNo}")
  public Object getTeacher(@PathVariable int teacherNo) {

    Teacher t = this.teacherDao.findByNo(teacherNo);

    Map<String,Object> contentMap = new HashMap<>();

    if (t == null ) {
      contentMap.put("status","failure");
      contentMap.put("data", "해당번호의 강사가 없습니다.");
    } else {
      contentMap.put("status","success");
      contentMap.put("data", t);
    }
    return contentMap;
  }
  @PutMapping("/teachers/{No}")
  // @PathVariable no로
  public Object updateTeacher(
      Teacher teacher) {

    Map<String,Object> contentMap = new HashMap<>();

    Teacher old = this.teacherDao.findByNo(teacher.getNo());
    if (old == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "강사정보가 없습니다");
      return contentMap;
    }

    teacher.setCreatedDate(new Date(System.currentTimeMillis()).toString());

    this.teacherDao.update(teacher);

    contentMap.put("status","success");

    return contentMap;
  }

  @DeleteMapping("/teachers/{teacherNo}")
  // @PathVariable 낱개로 받을때는 어노테이션 생략 x
  public Object deleteTeacher(
      @PathVariable int teacherNo) {

    Teacher t = this.teacherDao.findByNo(teacherNo);

    Map<String,Object> contentMap = new HashMap<>();

    if (t == null) {
      contentMap.put("status","failure");
      contentMap.put("data", "정보가 없습니다.");
    } else {
      this.teacherDao.delete(t);
      contentMap.put("status","success");
    }
    return contentMap;
  }

}


