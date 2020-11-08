package pl.meklad.ipezput2k20.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.meklad.ipezput2k20.dto.GradeDTO;
import pl.meklad.ipezput2k20.service.GradeService;
import pl.meklad.ipezput2k20.util.ApiPaths;

/**
 * Create by dev on 07.11.2020
 */
@RestController
@RequestMapping(ApiPaths.GradeCtrl.CTRL)
@CrossOrigin
public class GradeController {
    private final GradeService gradeService;

    @Autowired
    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<GradeDTO>> showAllGrades() {
        return ResponseEntity.ok(gradeService.findAllGrades());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<GradeDTO> showGradeById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(gradeService.findByGradeId(id).orElseThrow());
    }

    @GetMapping(path = "userId/{id}")
    public ResponseEntity<GradeDTO> showAllGradeByUserId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(gradeService.findByUserId(id).orElseThrow());
    }

    @PostMapping(value = "createGrade")
    public ResponseEntity<GradeDTO> createGrade(@RequestBody GradeDTO gradeDTO) {
        return ResponseEntity.ok(gradeService.createGrade(gradeDTO));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<GradeDTO> updateGrade(@PathVariable("id") Long id,
                                                @RequestBody GradeDTO gradeDTO) {
        return ResponseEntity.ok(gradeService.updateGrade(gradeDTO, id));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<HttpStatus> deleteGrade(@PathVariable("id") Long id) {
        boolean isRemoved = gradeService.deleteTestByTestId(id);
        if (!isRemoved)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
