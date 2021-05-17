package com.example.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersionController {

    /* Factors -
        - URI Pollution
        - Misuse of HTTP Headers
        - Caching
        - Can we execute the request on the browser
        - API Documentation
     */

    /* Basic Versioning (URI Versioning) */
    @GetMapping("v1/person") //
    public PersonV1 personV1() {
        return new PersonV1("Pokala Arun Kumar");
    }

    @GetMapping("v2/person")
    public PersonV2 personV2() {
        return new PersonV2(new Name("Arun Kumar", "Pokala"));
    }

    /* Using Params (Request Parameter Versioning)*/
    @GetMapping(value = "person/param", params = "version=1")
    public PersonV1 paramV1() {
        return new PersonV1("Pokala Arun Kumar");
    }

    @GetMapping(value = "person/param", params = "version=2")
    public PersonV2 paramV2() {
        return new PersonV2(new Name("Arun Kumar", "Pokala"));
    }

    /* Using Headers (Header Versioning)*/
    @GetMapping(value = "person/header", headers = "X-API-VERSION=1")
    public PersonV1 headerV1() {
        return new PersonV1("Pokala Arun Kumar");
    }

    @GetMapping(value = "person/header", headers = "X-API-VERSION=2")
    public PersonV2 headerV2() {
        return new PersonV2(new Name("Arun Kumar", "Pokala"));
    }

    /* Using producers (Content Negotiation or Accept Header or MIME Type Versioning)*/
    @GetMapping(value = "person/producer", produces = "application/vnd.company.app-v1+json")
    public PersonV1 producesV1() {
        return new PersonV1("Pokala Arun Kumar");
    }

    @GetMapping(value = "person/producer", produces = "application/vnd.company.app-v2+json")
    public PersonV2 producesV2() {
        return new PersonV2(new Name("Arun Kumar", "Pokala"));
    }
}
