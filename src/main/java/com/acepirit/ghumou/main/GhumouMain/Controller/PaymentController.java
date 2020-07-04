package com.acepirit.ghumou.main.GhumouMain.Controller;

import com.acepirit.ghumou.main.GhumouMain.Entity.*;
import com.acepirit.ghumou.main.GhumouMain.Response.Khaltiresponse.KhalitResponses;
import com.acepirit.ghumou.main.GhumouMain.Service.GlobalResponseService;
import com.acepirit.ghumou.main.GhumouMain.Service.OrderService;
import com.acepirit.ghumou.main.GhumouMain.Service.PaymentService;
import com.acepirit.ghumou.main.GhumouMain.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/api/v2/")
public class PaymentController {
    @Autowired
    WebClient.Builder webClientBuilder;
   //initilizing webclient library for postrequest to the server
//    @PostConstruct
//    public void init(){
//        webClient = WebClient.builder().baseUrl("https://khalti.com/api/v2/payment")
//                .defaultHeader(HttpHeaders.AUTHORIZATION,"Key test_secret_key_e12a2cb299fd49d9bbdb097888cf638a")
//                .build();
//    }
    @Autowired
    private GlobalResponseService globalResponseService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;


    //from sending through thymeleaf frontend
    @PostMapping("/payment/khalti")
    public void processKhaltiPayment(@RequestBody KhalitGETData khaltidata){
        System.out.println(khaltidata.getAmount()+khaltidata.getToken());

        KhalitResponses res = webClientBuilder.defaultHeader(HttpHeaders.AUTHORIZATION,"Key test_secret_key_e12a2cb299fd49d9bbdb097888cf638a")
                .build()
                .post()
                .uri("https://khalti.com/api/v2/payment/verify/")
                .syncBody(khaltidata)
                .retrieve()
                .bodyToMono(KhalitResponses.class)
                .block();


                System.out.println(res.getState().getName());
                if(khaltidata.getAmount()==res.getAmount() && res.getState().getName().matches("Completed")){

                   // return globalResponseService.globalResponse("Success",HttpStatus.OK.value());
                }

    }

    //for processing payment from frontend
    @PostMapping("/payment")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> doPayment(@RequestBody PaymentRequest paymentRequest) {
        KhalitGETData khalitdata = new KhalitGETData();
        khalitdata.setAmount(paymentRequest.getPaymentAmount());
        khalitdata.setToken(paymentRequest.getToken());

        KhalitResponses res = webClientBuilder.defaultHeader(HttpHeaders.AUTHORIZATION, "Key test_secret_key_e12a2cb299fd49d9bbdb097888cf638a")
                .build()
                .post()
                .uri("https://khalti.com/api/v2/payment/verify/")
                .syncBody(khalitdata)
                .retrieve()
                .bodyToMono(KhalitResponses.class)
                .block();



        System.out.println(res.getState().getName());
        if (khalitdata.getAmount() == res.getAmount() && res.getState().getName().matches("Completed")) {
            //setting order status to processing
            Orderpackage orders = orderService.findById(paymentRequest.getOrder_id());
            orders.setOrderStatus("PROCESSING");
            orderService.saveDirect(orders);
            //saving payment to database

            paymentService.save(res.getIdx(),paymentRequest);
            return globalResponseService.responseClient(paymentRequest);
        }else{
            return globalResponseService.globalResponse("error",HttpStatus.BAD_REQUEST.value());
        }


    }
    //for viewing single transcation  of khalti
    @GetMapping("/transcation-details/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_SELLAR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> transcationDetails(@PathVariable int id){
        String transcationidx = paymentService.findById(id).getTranscationIdx();
        String res = webClientBuilder.defaultHeader(HttpHeaders.AUTHORIZATION, "Key test_secret_key_e12a2cb299fd49d9bbdb097888cf638a")
                .build()
                .get()
                .uri("https://khalti.com/api/v2/payment/merchant-transaction/"+transcationidx+"/")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return globalResponseService.globalResponse(res,HttpStatus.OK.value());

    }

    @GetMapping("/payment/paymentdata/{userid}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_SELLAR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getPaymentBasedOnUserId(@PathVariable int userid){
        List<Payment> payment = paymentService.findByUserid(userid);
        if(payment.size()>0){
            return globalResponseService.listPayment(payment);
        }else{
            return  globalResponseService.globalResponse("No Payment with client id "+userid,HttpStatus.OK.value());
        }
    }



    @GetMapping("/payment/payment-sellar-data/{sellarid}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_SELLAR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getPaymentBasedONSellar(@PathVariable int sellarid){
        User user = userService.findById(sellarid);

        List<Payment> payment = paymentService.findBySellar(user.getUser_profile().getOrganizationName());
        if(payment.size()>0){
            return globalResponseService.listPayment(payment);
        }else{
            return  globalResponseService.globalResponse("No Payment with client id ",HttpStatus.OK.value());
        }
    }









}






