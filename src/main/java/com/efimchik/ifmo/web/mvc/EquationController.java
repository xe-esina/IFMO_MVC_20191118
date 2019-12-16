package com.efimchik.ifmo.web.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;

@Controller
public class EquationController {

    @PutMapping("/calc/equation")
    public ResponseEntity<String> putEquation(HttpSession session, @RequestBody String equation) {

        if (equation.indexOf('*') == -1 && equation.indexOf('/') == -1
                && equation.indexOf('+') == -1 && equation.indexOf('-') == -1)
            return new ResponseEntity<>("Wrong equation!", HttpStatus.valueOf(400));

        if (session.getAttribute("equation") == null) {
            session.setAttribute("equation", equation);
            return new ResponseEntity<>("Equation created!", HttpStatus.valueOf(201));
        }
        else {
            session.setAttribute("equation", equation);
            return new ResponseEntity<>("Equation replaced!", HttpStatus.valueOf(200));
        }
    }

    @DeleteMapping("/calc/equation")
    public ResponseEntity deleteEquation(HttpSession session){
        session.removeAttribute("equation");
        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }
}