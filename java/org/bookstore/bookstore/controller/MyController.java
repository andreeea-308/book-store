package org.bookstore.bookstore.controller;

import org.bookstore.bookstore.dto.*;
import org.bookstore.bookstore.entity.UserEntity;
import org.bookstore.bookstore.enums.UserRole;
import org.bookstore.bookstore.service.*;
import org.bookstore.bookstore.validator.BookDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class MyController {

    @Autowired
    private BookService bookService;
    @Autowired
    private BookDtoValidator bookDtoValidator;
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/home")
    public String getHomePage(Model model) {
        List<BookDto> bookDtoList = bookService.getAllBookDtoList();
        model.addAttribute("bookDtoList", bookDtoList);
        System.out.println(bookDtoList);
        return "home";
    }
    @GetMapping("/add-book")
    public String getAddBookSite (Model model){
        BookDto bookDto = new BookDto();
        model.addAttribute("bookDto", bookDto);
        return "addBook";
    }

    @PostMapping("/add-book")
    public String postAddBook(@ModelAttribute BookDto bookDto, BindingResult bindingResult,
                                 @RequestParam("imageFile") MultipartFile imageFile,
                              @AuthenticationPrincipal UserEntity userEntity) throws IOException {
        //if (userEntity.getRole() == UserRole.ROLE_ADMIN) {
        System.out.println("S-a apelat metoda postAddBook " + bookDto);
        System.out.println(imageFile.getBytes());
        bookDtoValidator.validate(bookDto, bindingResult);
        if(bindingResult.hasErrors()){
            return "addBook";
        }
        bookService.addBook(bookDto, imageFile);
        return "redirect:/add-book";
    }

    @GetMapping("/book/{bookId}")
    public String getViewBook (@PathVariable(value = "bookId") String bookId, Model model) {
        System.out.println("Am dat click pe produsul cu Id-ul " + bookId);
        Optional<BookDto> optionalBookDto = bookService.getBookDtoByBookId(bookId);
        if (optionalBookDto.isEmpty()){
            return "error";
        }
        BookDto bookDto = optionalBookDto.get();
        model.addAttribute("bookDto", bookDto);
        SelectionDto selectionDto = new SelectionDto();
        model.addAttribute("selectionDto", selectionDto);
        return "viewBook";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute UserDto userDto){
        System.out.println(userDto);
        userService.register(userDto);
        return "redirect:/register";
    }

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }

    @PostMapping("/book/{bookId}")
    public String postAddToCart(@PathVariable(value = "bookId") String bookId, @ModelAttribute SelectionDto selectionDto,
                                Authentication authentication){
        cartService.addToCart(authentication.getName(), selectionDto, bookId);
        System.out.println("Vreau sa adaug " + selectionDto.getQuantity() + " pentru cartea cu id-ul: " + bookId);
        System.out.println("Userul logat: " + authentication.getName());
        return "redirect:/home";
    }

    @GetMapping("/viewCart")
    public String getViewCart(Authentication authentication, Model model){
        CartDto cartDto = cartService.getCartDtoFor(authentication.getName());
        System.out.println(cartDto);
        model.addAttribute("cartDto", cartDto);
        OrderDto orderDto = new OrderDto();
        model.addAttribute("orderDto", orderDto);
        return "viewCart";
    }

    @PostMapping("/finalize-order")
    public String postFinalizeOrder(@ModelAttribute OrderDto orderDto, Authentication authentication){
        System.out.println(orderDto);
        orderService.finalizeOrder(orderDto, authentication.getName());
        return "orderFinalized";
    }


}


