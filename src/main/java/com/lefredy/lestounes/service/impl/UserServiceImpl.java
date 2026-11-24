package com.lefredy.lestounes.service.impl;

import com.lefredy.lestounes.constants.eRoles;
import com.lefredy.lestounes.dtos.user.UserDto;
import com.lefredy.lestounes.dtos.user.UserUpdateDto;
import com.lefredy.lestounes.exceptions.AuthentificationUnSuccessfulException;
import com.lefredy.lestounes.exceptions.DocumentNotFoundException;
import com.lefredy.lestounes.repository.MongoUserRepository;
import com.lefredy.lestounes.schemas.User;
import com.lefredy.lestounes.service.UserService;
import com.lefredy.lestounes.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MongoUserRepository personRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    public List<User> getUsers(){
        return personRepository.findAll();
    }
    public User getUserById(UUID id) throws DocumentNotFoundException {
        return personRepository.findById(id).orElseThrow(DocumentNotFoundException::new);
    }

    @Override
    public User getByUsername(String username) throws DocumentNotFoundException {
        return personRepository.findByUsername(username).orElseThrow(DocumentNotFoundException::new);
    }

    public User signUp(UserDto userDto){
    return personRepository.save(new User(userDto,passwordEncoder));
    }


    @Override
    public String login(UserDto userDto) {
        User authentificatedUser = getByUsername(userDto.getUsername());
        if (!passwordEncoder.matches(userDto.getPassword(),authentificatedUser.getPassword())){
            throw new AuthentificationUnSuccessfulException();
        }
        return jwtUtil.generateToken(authentificatedUser.getUsername());
    }

    @Override
    public User updateUser(UUID id,UserUpdateDto userUpdateDto) throws DocumentNotFoundException {
        User updatedUser = getUserById(id);
        updatedUser.updateUser(userUpdateDto,passwordEncoder);
        return personRepository.save(updatedUser);
    }

    @Override
    public User grantAdmin(UUID id) throws DocumentNotFoundException{
        User updatedUser = getUserById(id);
        updatedUser.addRole(eRoles.ADMIN);
        return personRepository.save(updatedUser);
    }
}
