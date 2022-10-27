package com.example.csw_client.Controller;

import com.example.csw_client.Constant.API_URL_All;
import com.example.csw_client.Model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

import static com.example.csw_client.Util.API_Helper.createJerseyRestClient;

@Controller
public class ProductController {

    @GetMapping(value = "/getProducts")
    public String index(Model model) {
        Client client = createJerseyRestClient();
        WebTarget target = client.target(API_URL_All.REST_API_LIST);
        List<Product> ls = target.request(MediaType.APPLICATION_JSON_TYPE).get(List.class);
        model.addAttribute("lsProduct", ls);
        return "index";
    };

    //Create
    @GetMapping(value = "/createProduct")
    public String createProduct() {
        return "create";
    };

    @PostMapping("/createProduct")
    public String createProduct(@RequestParam String name,
                             @RequestParam Integer price,
                             @RequestParam Integer quantity) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);

        String jsonP = convertToJson(product);

        Client client = createJerseyRestClient();
        WebTarget target = client.target(API_URL_All.REST_API_CREATE);
        Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(jsonP, MediaType.APPLICATION_JSON));
        return "redirect:/getProducts";
    }

    //Update
    @GetMapping(value = "/updateProduct")
    public String updateProduct(Model model) {
        Client client = createJerseyRestClient();
        WebTarget target = client.target(API_URL_All.REST_API_LIST);
        List<Product> ls = target.request(MediaType.APPLICATION_JSON_TYPE).get(List.class);
        model.addAttribute("lsProduct", ls);
        return "update";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(@RequestParam String id,
                             @RequestParam String quantity) {
        Client client = createJerseyRestClient();
        WebTarget target = client.target(API_URL_All.REST_API_UPDATE+"/"+ id +"/"+ quantity);
        Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
                .get();
        return "redirect:/getProducts";
    }





    //Convert to Json
    private static String convertToJson(Product product) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(product);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
