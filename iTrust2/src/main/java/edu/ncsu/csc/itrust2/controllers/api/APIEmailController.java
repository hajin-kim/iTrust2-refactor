package edu.ncsu.csc.itrust2.controllers.api;

import edu.ncsu.csc.itrust2.models.Email;
import edu.ncsu.csc.itrust2.services.EmailService;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class APIEmailController extends APIController {

    private final EmailService service;

    @GetMapping("emails")
    public List<Email> getEmails() {
        return (List<Email>) service.findAll();
    }
}
