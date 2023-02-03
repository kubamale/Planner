package mc.dev.Planner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    public void addAssignment(AssignmentRequest assignmentRequest) {

        isDateValid(assignmentRequest.getFinishDate());


        Assignment newAssignment = new Assignment(assignmentRequest.getName(), assignmentRequest.getDescription(), assignmentRequest.getFinishDate());

        List<Assignment> assignments = assignmentRepository.findAll();
        for (Assignment assignment : assignments){
            if (assignment.getName().equals(newAssignment.getName()) && assignment.getDescription().equals(newAssignment.getDescription()) && assignment.getFinishDate().equals(newAssignment.getFinishDate())){
                throw new IllegalStateException("You already have have this assignment:\n " + newAssignment.toString());
            }
        }

        assignmentRepository.save(newAssignment);
    }

    public List<Assignment> getAssignments() {
        return assignmentRepository.findAll();
    }

    public void updateAssignment(Long id, AssignmentRequest updateRequest) {
        List<Assignment> assignmentList = assignmentRepository.findAll();
        Optional<Assignment> newAssignment = assignmentRepository.findById(id);


        if (newAssignment.isEmpty()){
            throw new IllegalStateException("No assignment with id: " + id);
        }

        if (updateRequest.getName() != null)
            newAssignment.get().setName(updateRequest.getName());




        if (updateRequest.getDescription() != null)
            newAssignment.get().setDescription(updateRequest.getDescription());


        if (updateRequest.getFinishDate() != null)
            newAssignment.get().setFinishDate(updateRequest.getFinishDate());





        for (Assignment assignment : assignmentList ){
            if (assignment.getName().equals(newAssignment.get().getName()) && assignment.getDescription().equals(newAssignment.get().getDescription()) && assignment.getFinishDate().equals(newAssignment.get().getFinishDate()) && assignment.getId() != newAssignment.get().getId()){
                throw new IllegalStateException("You already have have this assignment:\n " + newAssignment.toString());
            }
        }

        assignmentRepository.save(newAssignment.get());

    }

    public void deleteAssignment(Long id) {

        Optional<Assignment> assignment = assignmentRepository.findById(id);

        if (assignment.isEmpty()){
            throw new IllegalStateException("No assignment with id: " + id);
        }

        assignmentRepository.delete(assignment.get());

    }

    private boolean isDateValid(String date){

        //TODO: sprawdzenie czy podana data jest ok
        String[] split = date.split(" ");
        LocalDateTime dateTime = LocalDateTime.parse(split[0] + "T" + split[1]+":00");
        LocalDateTime currentDateTime = LocalDateTime.now();




        return true;
    }
}
