package com.finki.students.auctionapi.web;

import com.finki.students.auctionapi.domain.User;
import com.finki.students.auctionapi.domain.UserRequest;
import com.finki.students.auctionapi.payload.JWTLoginSuccessResponse;
import com.finki.students.auctionapi.payload.LoginRequest;
import com.finki.students.auctionapi.security.JwtTokenProvider;
import com.finki.students.auctionapi.services.MapValidationErrorService;
import com.finki.students.auctionapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.finki.students.auctionapi.security.SecurityConstants.TOKEN_PREFIX;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) {
            return errorMap;
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX + tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequest userRequest, BindingResult result) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) {
            return errorMap;
        }

        User user = userService.saveOrUpdateUser(userRequest);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<?> getUser(@RequestParam(name = "username") String username) {

        User user = userService.getUser(username);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

}
