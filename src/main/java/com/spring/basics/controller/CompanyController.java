package com.spring.basics.controller;

import com.spring.basics.entry.ERole;
import com.spring.basics.entry.Login;
import com.spring.basics.entry.Register;
import com.spring.basics.model.Department;
import com.spring.basics.model.Employee;
import com.spring.basics.model.RefreshToken;
import com.spring.basics.model.Role;
import com.spring.basics.response.Message;
import com.spring.basics.security.JwtUtils;
import com.spring.basics.service.PaginationService;
import com.spring.basics.service.RefreshTokenService;
import com.spring.basics.security.UserDetailsImpl;
import com.spring.basics.security.UserInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.spring.basics.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/company")
public class CompanyController
{
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    RefreshTokenService refreshTokenService;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PaginationService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Register register)
    {
        if (employeeRepository.existsByName(register.getName())) {
            return ResponseEntity.badRequest().body(new Message("Error: Username is already taken!"));
        }

        if (employeeRepository.existsByMail(register.getMail())) {
            return ResponseEntity.badRequest().body(new Message("Error: Email is already in use!"));
        }

        // Create new user's account
        Employee employee = new Employee(register.getName(), register.getMail(),
                encoder.encode(register.getPassword()));

        List<String> strRoles = register.getRoles();
        List<Role> roles = new ArrayList<>();

        if (strRoles == null) {
            System.out.println("null role so user");
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    case "user":
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        employee.setRoles(roles);
        employeeRepository.save(employee);

        return ResponseEntity.ok(new Message("User registered successfully!"));


    }

    @PostMapping("/login")
    public ResponseEntity<Object> authenticate(@RequestBody Login login)
    {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getName(), login.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken((int) userDetails.getEmpId());

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(userDetails.getEmpId(), refreshToken.getToken(), userDetails.getUsername(),
                        userDetails.getMail(), roles));
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser()
    {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new Message("You've been signed out!"));
    }

    @GetMapping("/pageGet")
    public ResponseEntity<List<Employee>> getAllPages(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "empId") String sortBy
            )
    {
        List<Employee> list = service.getAllPages(pageNo, pageSize, sortBy);
        return new ResponseEntity<List<Employee>>(list, new HttpHeaders(), HttpStatus.OK);
    }

//    @GetMapping("/filterFindByDepartmentName")
//    public Optional<Department> findByDepartmentName(@RequestParam(defaultValue = "") String deptName)
//    {
//        return service.findByDeptNames(deptName);
//    }
    @GetMapping("/pageFilterFindByDepartmentName")
    public Page<Department> findByDepartmentName(@RequestParam(defaultValue = "0") Integer pageNo,
                                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                                 @RequestParam(defaultValue = "deptId") String sortBy,
                                                 @RequestParam(defaultValue = "") String deptName)
    {
        return service.findByDeptName(pageNo,pageSize,sortBy,deptName);
    }

}
