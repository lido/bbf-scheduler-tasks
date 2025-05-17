    package com.lido.bffschedulertasks.controller;

    import com.lido.bffschedulertasks.business.UserService;
    import com.lido.bffschedulertasks.business.dto.out.UserDTOResponse;
    import com.lido.bffschedulertasks.infrastructure.security.SecurityConfig;
    import io.swagger.v3.oas.annotations.Operation;
    import io.swagger.v3.oas.annotations.responses.ApiResponse;
    import io.swagger.v3.oas.annotations.security.SecurityRequirement;
    import io.swagger.v3.oas.annotations.tags.Tag;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
@Tag(name = "User", description = "Register and Login User")
public class UserController {

    private final UserService userService;


   @PostMapping
   @Operation(summary = "Save User", description = "Create a new user")
   @ApiResponse(responseCode = "200", description = "User saved successfully")
   @ApiResponse(responseCode = "400", description = "User already registered")
   @ApiResponse(responseCode = "500", description = "Sever error")
    public ResponseEntity<UserDTOResponse> saveUser(@RequestBody UserDTOResponse userDTO){
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "User Login", description = "User Login")
    @ApiResponse(responseCode = "200", description = "User loged successfully")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @ApiResponse(responseCode = "500", description = "Sever error")
    public String login(@RequestBody UserDTOResponse userDTO){
        return userService.login(userDTO);
    }

    @GetMapping
    @Operation(summary = "Search User data by email",
            description = "Search User data")
    @ApiResponse(responseCode = "200", description = "User found")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Sever error")
    public ResponseEntity<UserDTOResponse> findUserByEmail(@RequestHeader(name = "Authorization", required=false) String token,
                                                           @RequestParam("email") String email){
        return ResponseEntity.ok(userService.findUserByEmail(token, email));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Delete User by email",
            description = "Delete User")
    @ApiResponse(responseCode = "200", description = "User deleted successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Sever error")
    public ResponseEntity<Void> deleteUserByEmail(@RequestHeader(name = "Authorization", required=false) String token,
                                                  @PathVariable("email") String email){
        userService.deleteUserByEmail(token, email);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Update User data",
            description = "Update  User data")
    @ApiResponse(responseCode = "200", description = "User updated")
    @ApiResponse(responseCode = "404", description = "User not regitered")
    @ApiResponse(responseCode = "500", description = "Sever error")
    public ResponseEntity<UserDTOResponse> updateUserData(@RequestHeader("Authorization") String token,
                                                          @RequestBody UserDTOResponse userDTO){
       return ResponseEntity.ok(userService.updateUserData(token, userDTO));
    }
}
