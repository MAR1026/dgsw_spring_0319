package kr.hs.dgsw.web_01_319;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserServiceImp1 implements UserService{

    List<User> userList;

    public UserServiceImp1() {
        userList = new ArrayList<>();
        userList.add(new User("id1", "user1", "user111@dgsw.hs.kr"));
        userList.add(new User("id2", "user2", "user222@dgsw.hs.kr"));
        userList.add(new User("id3", "user3", "user333@dgsw.hs.kr"));
    }

    @Override
    public List<User> list(){
        return this.userList;
    }

    @Override
    public User view(String id){
        return this.userList.stream()
                .filter(user -> user.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    /* public User find1(String name)
    {
        Iterator<User> userIterator = this.userList.iterator();
        while(userIterator.hasNext()){
            User user = userIterator.next();
            if(user.getName().equals(name))
                return user;
        }

        return null;
    }

    public User find2(String name) {
        for(User user : this.userList){
            if(user.getName().equals(name))
                return user;
        }

        return null;
    }

    public User find3(String name) {
        User found = this.userList.stream()
                .filter(user -> user.getName().equals(name))
                .findAny()
                .orElse(null);
        return found;
    }*/

    @Override
    public boolean add(User user) {
        User found = this.view(user.getId());

        if(found == null)
        return this.userList.add(user);

        return false;
    }

    @Override
    public User update(User user) {
        User found = this.view(user.getId());
        if(found != null) {
            found.setName(user.getName());
            found.setEmail(user.getEmail());
        }

        return found;
    }

    @Override
    public boolean delete(String id){
        User found = this.view(id);

        return this.userList.remove(found);

    }

}