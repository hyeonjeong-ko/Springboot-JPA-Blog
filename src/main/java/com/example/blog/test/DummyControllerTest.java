package com.example.blog.test;

import com.example.blog.model.RoleType;
import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;
import net.bytebuddy.pool.TypePool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Supplier;

// html file x,[data] return controller
@RestController
public class DummyControllerTest {

    //DummyControllerTest가메모리에뜰때 repo도 같이 메모리에 뜬다.
    //userRepository로 스프링이 관리하는 객체를 쏙 넣어준다!
    @Autowired //의존성 주입(DI)
    private UserRepository userRepository;

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id){
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
        }
        return "삭제되었습니다.id : " +id;
    }

    // email, password만 수정
    @Transactional
    @PutMapping("/dummy/user/{id}") // 1.json data 보냄 -> 2. spring이 java object로 바꿔서 보내줌
    public User updateUser(@PathVariable int id, @RequestBody User requestUser){ // @RequestBody ; Json data받을 때
        System.out.println("id :" + id);
        System.out.println("password :" + requestUser.getPassword());
        System.out.println("email :" + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("해당 user가 없습니다.수정에 실패하였습니다.");
        });

        // 기존 실제 데이터(user)에 수정요청받기
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());
        requestUser.setId(id);
        userRepository.save(requestUser); //save() insert할때...

        return null;
    }

    // 전체 유저 페이지
    @GetMapping("dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

    // 한 페이지당 2건 데이터 리턴
    @GetMapping("/dummy/user/page") //  http://localhost:8000/blog/dummy/user/page?page=0
    public List<User> pageList(@PageableDefault(size=2,sort="id", direction = Sort.Direction.DESC)Pageable pageable){
        Page<User> pagingUser = userRepository.findAll(pageable);

        if(pagingUser.isLast()){
        }

        List<User> users = pagingUser.getContent();
        return users;
    }


    // {id} 주소로 파라미터를 전달 받을 수 있음(변수명그대로적어야!)
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id){
        //람다식(return type supplier 몰라도됌ㅎ개꿀)
        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("해당 유저는 없습니다. id :" + id);
        });

        //user객체 = 자바object(브라우저는이해x)
        // 스프링부트 = MessageCoverter가 자동작동.(jackson호출해서 user object->json)
        return user;

    }


        /*
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>(){
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 없습니다. id :" + id);
                // return new User;
            }
        });*/

    // http://localhost:8000/blog/dummy/join
    // http의 body에 u,p,e데이터를 가지고 요청
    @PostMapping("dummy/join")
    public String join(User user){
        System.out.println("username: "+user.getUsername());
        System.out.println("password: "+user.getPassword());
        System.out.println("email: "+user.getEmail()); //복사단축키: ctrl+dd

        user.setRole(RoleType.USER);
        userRepository.save(user); // save는 insert할때 씀
        return "회원가입이 완료되었습니다.";

    }
}
