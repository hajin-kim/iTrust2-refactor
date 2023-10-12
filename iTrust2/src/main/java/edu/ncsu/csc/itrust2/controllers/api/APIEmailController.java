package edu.ncsu.csc.itrust2.controllers.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ncsu.csc.itrust2.models.Email;
import edu.ncsu.csc.itrust2.services.EmailService;

@RestController
public class APIEmailController extends APIController {

    private final EmailService service;

    public APIEmailController(EmailService service) {
        this.service = service;
    }

    @GetMapping ( "emails" )
    public List<Email> getEmails () {
        return (List<Email>) service.findAll();
    }

}
