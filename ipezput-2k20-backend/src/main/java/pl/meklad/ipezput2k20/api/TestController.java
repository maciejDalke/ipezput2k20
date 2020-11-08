package pl.meklad.ipezput2k20.api;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.meklad.ipezput2k20.dto.TestDTO;
import pl.meklad.ipezput2k20.dto.TestDoneDTO;
import pl.meklad.ipezput2k20.dto.TestQuestionDTO;
import pl.meklad.ipezput2k20.service.TestDoneService;
import pl.meklad.ipezput2k20.service.TestQuestionService;
import pl.meklad.ipezput2k20.service.TestService;
import pl.meklad.ipezput2k20.util.ApiPaths;

/**
 * Create by dev on 06.11.2020
 */
@RestController
@RequestMapping(ApiPaths.TestCtrl.CTRL)
@CrossOrigin
public class TestController {

    private final TestService testService;
    private final TestDoneService testDoneService;
    private final TestQuestionService testQuestionService;

    @Autowired
    public TestController(TestService testService,
                          TestDoneService testDoneService,
                          TestQuestionService testQuestionService) {
        this.testService = testService;
        this.testDoneService = testDoneService;
        this.testQuestionService = testQuestionService;
    }

    //======================================================================================================================
    @GetMapping(value = "all")
    public ResponseEntity<Iterable<TestDTO>> showAllTests() {
        return ResponseEntity.ok(testService.findAllTest());
    } //ok

    @GetMapping(path = "{testId}")
    public ResponseEntity<TestDTO> showByTestId(@PathVariable("testId") Long testId) {
        return ResponseEntity.ok(testService.findByTestId(testId).orElseThrow());
    } //ok

    @PostMapping(value = "createTest")
    public ResponseEntity<TestDTO> createTest(@RequestBody TestDTO testDTO) {
        return ResponseEntity.ok(testService.createTest(testDTO));
    }

    @PutMapping(path = "{testId}")
    public ResponseEntity<TestDTO> updateTest(@PathVariable("testId") Long testId,
                                              @RequestBody TestDTO testDTO) throws NotFoundException {
        return ResponseEntity.ok(testService.updateTest(testDTO, testId));
    }

    @DeleteMapping(path = "{testId}")
    public ResponseEntity<HttpStatus> deleteTest(@PathVariable("testId") Long testId) {
        boolean isRemoved = testService.deleteTestByTestId(testId);
        if (!isRemoved)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //======================================================================================================================
    @GetMapping(path = "done/all")
    public ResponseEntity<Iterable<TestDoneDTO>> showAllTestsDone() {
        return ResponseEntity.ok(testDoneService.findAllTestDone());
    }

    @GetMapping(path = "done/{id}")
    public ResponseEntity<TestDoneDTO> showByUserId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(testDoneService.findByTestId(id).orElseThrow());
    }

    @PostMapping(value = "done/createTestDone")
    public ResponseEntity<TestDoneDTO> createTestDone(@RequestBody TestDoneDTO testDoneDTO) {
        return ResponseEntity.ok(testDoneService.createTestDone(testDoneDTO));
    }

    @PutMapping(path = "done/{id}")
    public ResponseEntity<TestDoneDTO> updateUser(@PathVariable("id") Long id,
                                                  @RequestBody TestDoneDTO testDoneDTO) {
        return ResponseEntity.ok(testDoneService.updateTest(testDoneDTO, id));
    }

    @DeleteMapping(path = "done/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        boolean isRemoved = testService.deleteTestByTestId(id);
        if (!isRemoved)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //======================================================================================================================
    @GetMapping(path = "question/all")
    public ResponseEntity<Iterable<TestQuestionDTO>> showAllTestQuestions() {
        return ResponseEntity.ok(testQuestionService.findAllTestQuestion());
    } //ok

    @GetMapping(path = "question/{id}")
    public ResponseEntity<TestQuestionDTO> showByTestQuestionId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(testQuestionService.findByTestQuestionId(id).orElseThrow());
    } //ok

    @PostMapping(value = "question/createTestQuestion")
    public ResponseEntity<TestQuestionDTO> createTestQuestion(@RequestBody TestQuestionDTO testQuestionDTO) {
        return ResponseEntity.ok(testQuestionService.createTestQuestion(testQuestionDTO));
    }

    @PutMapping(path = "question/{id}")
    public ResponseEntity<TestQuestionDTO> updateTestQuestion(@PathVariable("id") Long id,
                                                               @RequestBody TestQuestionDTO testQuestionDTO) {
        return ResponseEntity.ok(testQuestionService.updateTestQuestion(testQuestionDTO, id));
    }

    @DeleteMapping(path = "question/{id}")
    public ResponseEntity<HttpStatus> deleteTestQuestionByTestQuestionId(@PathVariable("id") Long id) {
        boolean isRemoved = testQuestionService.deleteTestQuestionByTestQuestionId(id);
        if (!isRemoved)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
