package uz.pdp.restfullspringcompanyapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.restfullspringcompanyapp.payload.WorkerDTO;
import uz.pdp.restfullspringcompanyapp.service.WorkerService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {

    private final WorkerService service;

    @Autowired
    public WorkerController(WorkerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                    @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return service.getAllWorkers(page, size);
    }

    @GetMapping("/id={workerId}")
    public ResponseEntity<?> getOne(@PathVariable("workerId") Integer id) {
        return service.getOneWorker(id);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody WorkerDTO workerDTO) {
        return service.addNewWorker(workerDTO);
    }

    @PutMapping("/id={workerId}")
    public ResponseEntity<?> update(@PathVariable("workerId") Integer id, @Valid @RequestBody WorkerDTO workerDTO) {
        return service.editWorker(id, workerDTO);
    }

    @DeleteMapping("/id={workerId}")
    public ResponseEntity<?> delete(@PathVariable("workerId") Integer id) {
        return service.deleteWorker(id);
    }
}
