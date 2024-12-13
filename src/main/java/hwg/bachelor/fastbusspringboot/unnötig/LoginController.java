//package hwg.bachelor.fastbusspringboot.login;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/auth")
//public class LoginController {
//
////    @Autowired
////    private AuthenticationManager authenticationManager;
////
////    @GetMapping("/home")
////    public String home(){
////        return "home";
////    }
////
////    @GetMapping("/loginView")
////    public String login(){
////        return "home";
////    }
////
////    @PostMapping("/login")
////    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
////        System.out.println(loginRequest.getEmail()+" "+loginRequest.getPassword());
////        // Authentifizieren des Benutzers
////        Authentication authentication = authenticationManager.authenticate(
////                new UsernamePasswordAuthenticationToken(
////                        loginRequest.getEmail(),
////                        loginRequest.getPassword()
////                )
////        );
////        // Das SecurityContextHolder setzt den authentifizierten Benutzer in den Kontext
////        SecurityContextHolder.getContext().setAuthentication(authentication);
////        // Rückgabe einer Erfolgsmeldung oder Benutzerinformationen
////        return ResponseEntity.ok("Login successful");
////    }
//}
