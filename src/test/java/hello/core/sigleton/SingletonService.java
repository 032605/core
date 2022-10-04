package hello.core.sigleton;

public class SingletonService {

    //자기자신을 내부 static으로 선언하면 Class 레벨에 올라가 1개만 생성하게 됨
    private static final SingletonService instance = new SingletonService();

    /**
     * 값을 꺼낼 땐 무조건 getInstance() 메소드 사용
     * @return 초기화 객체 instance 값
     */
    public static SingletonService getInstance() {
        return instance;
    }

    /**
     * 외부에서 생성할 수 없음
     */
    private SingletonService(){
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }


}
