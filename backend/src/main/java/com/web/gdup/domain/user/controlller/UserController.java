package com.web.gdup.domain.user.controlller;

import com.web.gdup.domain.follow.service.FollowService;
import com.web.gdup.domain.model.BasicResponse;
import com.web.gdup.domain.user.Entity.UserEntity;
import com.web.gdup.domain.user.dto.SignupRequest;
import com.web.gdup.domain.user.dto.UserDto;
import com.web.gdup.domain.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FollowService followService;

    @PostMapping("/login")
    @ApiOperation(value = "로그인", notes = "로그인을 위해 이메일과 패스워드를 입력받는다.")
    public Object login(@RequestBody UserDto userDto){
        Optional<UserEntity> user = null;

        ResponseEntity response = null;
        final BasicResponse result = new BasicResponse();

        try {
            user = userService.login(userDto.getEmail(), userDto.getPassword());
            result.status = true;
            result.message = "success";
            result.data = user;
            response = new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.status = true;
            result.message = "로그인 에러 입니다. 다시 시도해 주세요.";
            result.data = null;
            response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @PostMapping("/signup")
    @ApiOperation(value = "가입하기")
    public Object signup(@Valid @RequestBody SignupRequest request){

        final  BasicResponse result = new BasicResponse();
        ResponseEntity response = null;

        try {
            if(userService.finUserByEmail(request.getEmail())){
                result.status = false;
                result.message = "email 중복";
                result.data = null;
                response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
                return response;
            }
            UserEntity user = userService.signup(request);
            if(user == null){
                result.status = false;
                result.message = "username 중복";
                result.data = null;
                response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND); // 이미있는경우, NOT FOUND인가?
            } else {
                result.status = true;
                result.message = "success";
                result.data = user;
                response = new ResponseEntity<>(result, HttpStatus.OK);
            }
        } catch (Exception e) {

        }
        return response;
    }

    @GetMapping("/info/{targetName}")
    @ApiOperation(value = "타 사용자 상세보기", notes = "타 사용자의 정보(introduction)를 반환한다." +
            "파라미터로 타켓 유저의 name이 필요하다.")
    public Object getUserInfo(@PathVariable String targetName){

        ResponseEntity response = null;
        final BasicResponse result = new BasicResponse();

        if(userService.getUserInfo(targetName)){
            result.status = true;
            result.message = "해당 유저 존재";
            result.data = true;
            response = new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            result.status = true;
            result.message = "해당 유저 없음";
            result.data = false;
            response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return response;
    }



    @GetMapping("/find/follow/{userName}")
    @ApiOperation(value = "팔로우가 가능한 유저 목록", notes = "로그인 한 유저가 구독할 수 있는 유저들의 목록을 반환한다." +
            "파라미터로 현재 로그인된 유저의 name이 필요하다.")
    public Object findFollow(@PathVariable (required = true) final String userName){
        List<String> userLists = followService.findFollow(userName);
        ResponseEntity response = null;

        if(userLists.size() >= 0 ){
            for(String name : userLists){
                System.out.println(name);
            }
            final BasicResponse result = new BasicResponse();
            result.status = true;
            result.message = "success";
            result.data = userLists;
            response = new ResponseEntity<>(result, HttpStatus.OK);
        }
        else {
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @GetMapping("/find/following/{userName}")
    @ApiOperation(value = "팔로잉 유저 목록", notes = "로그인 한 유저가 구독하고 있는 유저들의 목록을 반환한다." +
            "파라미터로 현재 로그인된 유저의 name이 필요하다.")
    public Object findFollowing(@PathVariable (required = true) final String userName){
        List<String> userLists = followService.findFollowing(userName);
        ResponseEntity response = null;

        if(userLists.size() >= 0 ){
            for(String name : userLists){
                System.out.println(name);
            }
            final BasicResponse result = new BasicResponse();
            result.status = true;
            result.message = "success";
            result.data = userLists;
            response = new ResponseEntity<>(result, HttpStatus.OK);
        }
        else {
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @GetMapping("/find/follower/{userName}")
    @ApiOperation(value = "팔로워 유저 목록", notes = "로그인 한 유저를 구독하고 있는 유저들의 목록을 반환한다." +
            "파라미터로 현재 로그인된 유저의 name이 필요하다.")
    public Object findFollower(@PathVariable (required = true) final String userName){
        List<String> userLists = followService.findFollower(userName);
        ResponseEntity response = null;

        if(userLists.size() >= 0 ){
            for(String name : userLists){
                System.out.println(name);
            }
            final BasicResponse result = new BasicResponse();
            result.status = true;
            result.message = "success";
            result.data = userLists;
            response = new ResponseEntity<>(result, HttpStatus.OK);
        }
        else {
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @GetMapping("/follow")
    @ApiOperation(value = "팔로우 하기", notes = "현재 로그인 한 유저가 타 유저를 팔로우 하는 기능" +
            "파라미터로 현재 로그인된 유저의 name 과 팔로우 하고자 하는 유저의 name이 필요하다.")
    public Object follow(@RequestParam (required = true) final String userName, @RequestParam(required = true) final String following){

        ResponseEntity response = null;

        if(followService.follow(userName,following)){
            final BasicResponse result = new BasicResponse();
            result.status = true;
            result.message = "success";

            response = new ResponseEntity<>(result, HttpStatus.OK);
        }
        else {
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @GetMapping("/unfollow")
    @ApiOperation(value = "언팔로우 하기", notes = "현재 로그인 한 유저가 타 유저를 언팔로우 하는 기능" +
            "파라미터로 현재 로그인된 유저의 name 과 팔로우 하고자 하는 유저의 name이 필요하다.")
    public Object unfollow(@RequestParam (required = true) final String userName, @RequestParam(required = true) final String following){
        ResponseEntity response = null;

        if(followService.unfollow(userName,following)){
            final BasicResponse result = new BasicResponse();
            result.status = true;
            result.message = "success";
            response = new ResponseEntity<>(result, HttpStatus.OK);
        }
        else {
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return response;
    }
}
