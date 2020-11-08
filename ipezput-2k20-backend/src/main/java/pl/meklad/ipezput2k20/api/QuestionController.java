package pl.meklad.ipezput2k20.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.meklad.ipezput2k20.dto.QuestionDTO;
import pl.meklad.ipezput2k20.service.QuestionService;
import pl.meklad.ipezput2k20.util.ApiPaths;

/**
 * Create by dev on 07.11.2020
 */
@RestController
@RequestMapping(ApiPaths.QuestionCtrl.CTRL)
@CrossOrigin
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<QuestionDTO>> showAllQuestions() {
        return ResponseEntity.ok(questionService.findAllQuestions());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<QuestionDTO> showQuestionsById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(questionService.findQuestionByQuestionId(id).orElseThrow());
    }

    @PostMapping(value = "createQuestion")
    public ResponseEntity<QuestionDTO> createQuestion(@RequestBody QuestionDTO questionDTO) {
        return ResponseEntity.ok(questionService.createQuestion(questionDTO));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<QuestionDTO> updateQuestion(@PathVariable("id") Long id,
                                                @RequestBody QuestionDTO questionDTO) {
        return ResponseEntity.ok(questionService.updateQuestion(questionDTO, id));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<HttpStatus> deleteQuestion(@PathVariable("id") Long id) {
        boolean isRemoved = questionService.deleteQuestionByQuestionId(id);
        if (!isRemoved)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
