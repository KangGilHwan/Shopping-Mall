package riverway.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import riverway.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String show(Model model) {
        model.addAttribute("products", productService.findAll());
        return "/product/shop";
    }

    @GetMapping("/form")
    public String createForm() {
        return "/product/form";
    }

    @GetMapping("/{id}")
    public String showDetail(@PathVariable Long id, Model model){
        log.debug("Here !");
        model.addAttribute("product", productService.findItem(id).toProductDto());
        return "/product/detail";
    }
}
