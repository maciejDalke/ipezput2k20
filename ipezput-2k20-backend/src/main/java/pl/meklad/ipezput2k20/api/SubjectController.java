package pl.meklad.ipezput2k20.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.meklad.ipezput2k20.dto.SubjectDTO;
import pl.meklad.ipezput2k20.service.SubjectService;
import pl.meklad.ipezput2k20.util.ApiPaths;

/**
 * Create by dev on 06.11.2020
 */
@RestController
@RequestMapping(ApiPaths.SubjectCtrl.CTRL)
@CrossOrigin
public class SubjectController {
    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<SubjectDTO>> showAllUsers() {
        return ResponseEntity.ok(subjectService.findAllTest());
    }

    @GetMapping(path = "{subjectId}")
    public ResponseEntity<SubjectDTO> showBySubjectId(@PathVariable("subjectId") Long subjectId) {
        return ResponseEntity.ok(subjectService.findSubjectBySubjectId(subjectId).orElseThrow());
    }

    @GetMapping
    public ResponseEntity<SubjectDTO> showByName(@RequestParam String name) {
        return ResponseEntity.ok(subjectService.findSubjectByName(name).orElseThrow());
    }

    @PostMapping(value = "createSubject")
    public ResponseEntity<SubjectDTO> createSubject(@RequestBody SubjectDTO subjectDTO) {
        return ResponseEntity.ok(subjectService.createSubject(subjectDTO));
    }

    @PutMapping(path = "{subjectId}")
    public ResponseEntity<SubjectDTO> updateSubject(@PathVariable("subjectId") Long subjectId,
                                                    @RequestBody SubjectDTO subjectDTO) {
        return ResponseEntity.ok(subjectService.updateSubject(subjectDTO, subjectId));
    }


    @DeleteMapping(path = "{subjectId}")
    public ResponseEntity<HttpStatus> deleteSubject(@PathVariable("subjectId") Long subjectId) {
        boolean isRemoved = subjectService.deleteSubjectBySubjectId(subjectId);
        if (!isRemoved)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
