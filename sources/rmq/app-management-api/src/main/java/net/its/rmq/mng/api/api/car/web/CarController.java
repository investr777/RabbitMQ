package net.its.rmq.mng.api.api.car.web;

import lombok.RequiredArgsConstructor;
import net.its.rmq.mng.api.api.car.service.CarApiService;
import net.its.rmq.mng.api.api.car.web.resources.CarInResource;
import net.its.rmq.mng.api.api.car.web.resources.CarOutResource;
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
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {

    private final CarApiService carApiService;

    @GetMapping()
    @ResponseBody
    List<CarOutResource> getAll() {

        return carApiService.findAll();
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    CarOutResource create(@Valid @RequestBody CarInResource inResource) {

        return carApiService.create(inResource);
    }

    @GetMapping("/{vin}")
    @ResponseBody
    CarOutResource get(@PathVariable String vin) {

        return carApiService.findByVin(vin);
    }

    @DeleteMapping("/{vin}")
    void delete(@PathVariable String vin) {

        carApiService.deleteByVin(vin);
    }
}
