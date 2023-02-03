package mc.dev.Planner;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Assignment {
    @Id
    @SequenceGenerator(
            name = "planner_sequence",
            sequenceName = "planner_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "planner_sequence"
    )
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String finishDate;
    @Column(nullable = false)
    private boolean isFinished;

    public Assignment( String name, String description, String finishDate) {
        this.name = name;
        this.description = description;
        this.finishDate = finishDate;
        this.isFinished = false;
    }

    public Assignment(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public boolean isFinished() {
        return isFinished;
    }
}
