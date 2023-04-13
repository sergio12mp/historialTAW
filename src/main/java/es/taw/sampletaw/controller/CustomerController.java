package es.taw.sampletaw.controller;

import es.taw.sampletaw.dao.CustomerRepository;
import es.taw.sampletaw.dao.DiscountCodeRepository;
import es.taw.sampletaw.dao.MicroMarketRepository;
import es.taw.sampletaw.entity.Customer;
import es.taw.sampletaw.entity.DiscountCode;
import es.taw.sampletaw.entity.MicroMarket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    protected CustomerRepository customerRepository;

    @Autowired
    protected DiscountCodeRepository discountCodeRepository;

    @Autowired
    protected MicroMarketRepository microMarketRepository;

    @GetMapping("/")
    public String doListar (Model model) {
        List<Customer> lista = this.customerRepository.findAll();
        model.addAttribute("clientes", lista);
        return "customers";
    }

    @GetMapping("/editar")
    public String doEditar (@RequestParam("id") Integer idcustomer, Model model) {
        Customer cliente = this.customerRepository.findById(idcustomer).orElse(null);
        model.addAttribute("cliente", cliente);

        List<DiscountCode> listadescuentos = this.discountCodeRepository.findAll();
        model.addAttribute("descuentos", listadescuentos);

        List<MicroMarket> listasupers = this.microMarketRepository.findAll();
        model.addAttribute("supermercados", listasupers);

        return "customer";
    }

    @PostMapping("/guardar")
    public String doGuardar (@ModelAttribute("cliente") Customer cliente) {
        this.customerRepository.save(cliente);
        return "redirect:/";
    }

    @GetMapping("/borrar")
    public String doBorrar (@RequestParam("id") Integer idcustomer) {
        this.customerRepository.deleteById(idcustomer);
        return "redirect:/";
    }

}
