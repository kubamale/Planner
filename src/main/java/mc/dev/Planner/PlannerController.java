package mc.dev.Planner;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/planner")
public class PlannerController {

    @Autowired
    AssignmentService assignmentService;

    @PostMapping
    public void addAssignment(@RequestBody AssignmentRequest assignmentRequest){
        assignmentService.addAssignment(assignmentRequest);
    }

    @GetMapping
    public List<Assignment> getAssignments(){
        return assignmentService.getAssignments();
    }

    @PutMapping(path = "{id}")
    public void updateAssignment(@PathVariable("id") Long id, @RequestBody AssignmentRequest updateRequest){

        assignmentService.updateAssignment(id, updateRequest);
    }

    @DeleteMapping(path = "{id}")
    public void deleteAssignment(@PathVariable("id") Long id){
        assignmentService.deleteAssignment(id);
    }

}
