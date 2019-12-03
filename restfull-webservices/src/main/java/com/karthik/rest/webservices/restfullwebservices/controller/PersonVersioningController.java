package com.karthik.rest.webservices.restfullwebservices.controller;

import com.karthik.rest.webservices.restfullwebservices.model.Name;
import com.karthik.rest.webservices.restfullwebservices.model.PersonV1;
import com.karthik.rest.webservices.restfullwebservices.model.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

    @GetMapping("v1/person")
    public PersonV1 getPersonV1(){
        return new PersonV1("Karthik N");
    }

    @GetMapping("v2/person")
    public PersonV2 getPersonV2(){
        return new PersonV2(new Name("Karthik ","N"));
    }

    // using params
    @GetMapping(value = "/person", params = "version=1")
    public PersonV1 getPersonV1Param(){
        return new PersonV1("Karthik N");
    }

    @GetMapping(value = "/person", params = "version=2")
    public PersonV2 getPersonV2Param(){
        return new PersonV2(new Name("Karthik ","N"));
    }

    // Using headers
    @GetMapping(value = "/person", headers = "X-API-VERSION=1")
    public PersonV1 getPersonV1Headers(){
        return new PersonV1("Karthik N");
    }

    @GetMapping(value = "/person", headers = "X-API-VERSION=2")
    public PersonV2 getPersonV2Headers(){
        return new PersonV2(new Name("Karthik ","N"));
    }

    // Using produces
    @GetMapping(value = "/person", produces = "application/kk-person-v1+json")
    public PersonV1 getPersonV1Produces(){
        return new PersonV1("Karthik N");
    }

    @GetMapping(value = "/person", produces = "application/kk-person-v2+json")
    public PersonV2 getPersonV2Produces(){
        return new PersonV2(new Name("Karthik ","N"));
    }
}
