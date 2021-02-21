package ma.enset.productsapp.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.productsapp.repositories.ProductRepository;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.client.KeycloakClientRequestFactory;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.hateoas.PagedModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Controller
public class ProductController{
    @Autowired
    private KeycloakRestTemplate keycloakRestTemplate;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/products")
    public String products(Model model){
        model.addAttribute("products",productRepository.findAll());
        return "products";
    }

    @GetMapping("/suppliers")
    public String suppliers(Model model){
       PagedModel<Supplier> pagedSuppliers=keycloakRestTemplate.getForObject("http://localhost:8083/suppliers",PagedModel.class);
       model.addAttribute("suppliers",pagedSuppliers);
        return "suppliers";
    }



    @GetMapping("/jwt")
    @ResponseBody
    public Map<String,String> map(HttpServletRequest request){
        KeycloakAuthenticationToken token=(KeycloakAuthenticationToken) request.getUserPrincipal();
        KeycloakPrincipal principal=(KeycloakPrincipal) token.getPrincipal();
        KeycloakSecurityContext keycloakSecurityContext=principal.getKeycloakSecurityContext();
        Map<String,String> map=new HashMap<>();
        map.put("access_token",keycloakSecurityContext.getIdTokenString());
        return map;

    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Model model){
        model.addAttribute("errorMessage","Not Authorized");
        return "errors";
    }

}


@Data
class Supplier {

    private Long id;
    private String name;
    private String email;
}
