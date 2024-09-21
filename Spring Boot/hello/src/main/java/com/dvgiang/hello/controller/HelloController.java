package com.dvgiang.hello.controller;

import com.dvgiang.hello.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController //Ket hop giua: @Controller và @ResponseBody
// @Controller: Đánh dấu class là String MVC controller
// @ResponseBody: Thông báo cho Spring rằng dữ liệu trả về nên được chuyển trực tiếp thành JSON/XML

/* @RequestMapping
 ** Dùng để ánh xạ các yêu cầu HTTP tới các phương thức xử lý của controller
 **  Trên class: định nghĩa URL cơ sở cho tất cả phương thức bên trong
 **  Trên phương thức: định nghĩa URL cụ thể và kiểu yêu cầu HTTP (GET, POST, DELETE/...cho các phương thức
 */
@RequestMapping("/")
public class HelloController {
    @GetMapping //HTTP GET == @RequestMapping(method = RequestMethod.GET)
    public String hello() {
        return "Hello, Java Spring Boot!";
    }

    @PostMapping //HTTP POST == @RequestMapping(method = RequestMethod.POST)

    //@RequestBody: chuyển đổi JSON từ body của request thành kiểu đối tượng (data binding)
    public String hello(@RequestBody User user) {
        return "Hello, My name is " + user.getName() + ".\nMy phone number is " + user.getPhone();
    }
}
