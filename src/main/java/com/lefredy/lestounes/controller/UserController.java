package com.lefredy.lestounes.controller;

import com.lefredy.lestounes.dtos.user.UserDto;
import com.lefredy.lestounes.dtos.user.UserResponseDto;
import com.lefredy.lestounes.exceptions.DocumentNotFoundException;
import com.lefredy.lestounes.service.UserService;
import com.lefredy.lestounes.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v{version}/users",version = "1")
class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    JwtUtil jwtUtil;
@GetMapping()
    public ResponseEntity<List<UserResponseDto>> getAll(){
    return ResponseEntity.ok(userService.getUsers().stream().map(UserResponseDto::new).toList());
}
@GetMapping("{id}")
        public ResponseEntity<UserResponseDto> getOne(@PathVariable UUID id)throws DocumentNotFoundException {
            return ResponseEntity.ok(new UserResponseDto(userService.getUserById(id)));
}
@GetMapping("auth/refresh-login")
public ResponseEntity<String> refreshLogin(@AuthenticationPrincipal String username){
    return ResponseEntity.ok(jwtUtil.generateToken(username));
}


@PostMapping("auth/public/sign-up")
    public ResponseEntity<UserResponseDto> signUp(@RequestBody UserDto userDto){
    return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponseDto(userService.signUp(userDto)));
}
@PostMapping("auth/public/login")
    public ResponseEntity<String> login(@RequestBody UserDto userDto){
    return ResponseEntity.ok(userService.login(userDto));
 }

 @PatchMapping("auth/grant/{id}")
 public ResponseEntity<UserResponseDto> grantOne(@PathVariable UUID id) throws DocumentNotFoundException {
     return ResponseEntity.ok(new UserResponseDto(userService.grantAdmin(id)));
 }
}
