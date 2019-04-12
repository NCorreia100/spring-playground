package com.example.demo;

import org.apache.coyote.Response;

import org.omg.CORBA.portable.ResponseHandler;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class WebController {

    @RequestMapping("/")
    public  String getDefault(@RequestParam("q") String query){
        return "The parameter passed in was "+query;
    }

    @GetMapping("/app/json")
        public String getJSONDefault(@RequestParam("q") String query){
        return "{q:"+query+"}";
    }

    @GetMapping("/math/pi")
    public String getPi(){
        return String.valueOf(Math.PI);
    }

    @GetMapping("/app/vehicles")
    public String getVehicles(@RequestParam Map<String, String> query, @CookieValue(name="math") String cookie){

        return "{vehicles:{year:"+query.get("year")+",doors:"+query.get("doors")+"}}";
    }
    @PostMapping("/app/math/calculate")
    public Object doMath(@RequestParam Map<String, String> query){
        int x = Integer.parseInt(query.get("x"));
        int y = Integer.parseInt(query.get("y"));
        String operation = query.get("operation");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie","math="+operation);

        if(operation==null)  return ResponseEntity.status(HttpStatus.OK).headers(headers).body(Math.round(x)+" + "+Math.round(y)+" = "+Math.round(x+y));
        if(operation.equals("add")) return Math.round(x)+" + "+Math.round(y)+" = "+Math.round(x+y);
        if(operation.equals("subtract")) return Math.round(x)+" - "+Math.round(y)+" = "+Math.round(x-y);
        if(operation.equals("multiply")) return Math.round(x)+" * "+Math.round(y)+" = "+Math.round(x*y);
        if(operation.equals("divide")) return Math.round(x)+" / "+Math.round(y)+" = "+(double)x/ (double)(y);




        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parameters cannot be accepted");
    }


}
