package bitcamp.util;

// 객체 목록을 다루는 기능을 규정

public interface List {

  // interface는 기본 default 가 public이고 abstract 라서 생략해도 된다
  /*public abstract*/ void add(Object value);
  public abstract Object[] toArray();
  public abstract Object get(int index);
  public abstract Object set(int index, Object value);
  public abstract boolean remove(Object value);
  public abstract int indexOf(Object value);
  int size();

}
