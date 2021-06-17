package controller;

import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.CustomerService;
import service.CustomerServiceORM;
import service.ICustomerService;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
//    @Autowired
//    private ICustomerService customerService;


    private final ICustomerService customerService = new CustomerServiceORM();
    
    @GetMapping("")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/index");
        List<Customer> customerList = customerService.findAll();
        modelAndView.addObject("customers", customerList);
      return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "/edit";
    }


    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("/delete");
        Customer customer = this.customerService.findById(id);
        modelAndView.addObject("customer",customer);
        return modelAndView;
    }

    @GetMapping("/{id}/view")
    public ModelAndView viewDetail(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("/view");
        Customer customer = this.customerService.findById(id);
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }



    @PostMapping("/create")
    public ModelAndView create(Customer customer) {
        customer.setId((int) (Math.random() * 10000));
        this.customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("redirect:/customer");
        modelAndView.addObject("success", "Thêm mới thành công");
        return modelAndView;
    }



    @PostMapping("/edit")
    public ModelAndView edit(Customer customer){
        this.customerService.update(customer.getId(), customer);
        ModelAndView modelAndView = new ModelAndView("redirect:/customer");
        modelAndView.addObject("success", "Sửa thành công");
        return modelAndView;

    }

    @PostMapping("/delete")
    public ModelAndView delete(Customer customer){
        int id = customer.getId();
        this.customerService.remove(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/customer");
        modelAndView.addObject("success", "xoa thanh cong");
        return modelAndView;
    }

}

