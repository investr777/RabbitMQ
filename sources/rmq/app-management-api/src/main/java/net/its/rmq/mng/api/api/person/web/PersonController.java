package net.its.rmq.mng.api.api.person.web;

import lombok.RequiredArgsConstructor;
import net.its.rmq.mng.api.api.person.service.PersonApiService;
import net.its.rmq.mng.api.api.person.web.resources.PersonInResource;
import net.its.rmq.mng.api.api.person.web.resources.PersonOutResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonApiService personApiService;

    @GetMapping()
    List<PersonOutResource> getAll() {

        return personApiService.findAll();
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    PersonOutResource create(@Valid @RequestBody PersonInResource inResource) {

        return personApiService.create(inResource);
    }

    @GetMapping("/{identifier}")
    @ResponseBody
    PersonOutResource get(@PathVariable String identifier) {

        return personApiService.findByIdentifier(identifier);
    }

    @DeleteMapping("/{identifier}")
    void delete(@PathVariable String identifier) {

        personApiService.deleteByIdentifier(identifier);
    }
}
