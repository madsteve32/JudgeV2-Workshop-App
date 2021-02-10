package JudgeV2.demo.model.binding;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

public class AddExerciseBindingModel {

    private String name;
    private LocalDateTime startedOn;
    private LocalDateTime dueDate;

    public AddExerciseBindingModel() {
    }

    @Length(min = 2, message = "Exercise name length must be more than 2 characters")
    public String getName() {
        return name;
    }

    public AddExerciseBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @PastOrPresent(message = "The date cannot be in the future")
    public LocalDateTime getStartedOn() {
        return startedOn;
    }

    public AddExerciseBindingModel setStartedOn(LocalDateTime startedOn) {
        this.startedOn = startedOn;
        return this;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "The date cannot be in the past")
    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public AddExerciseBindingModel setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
        return this;
    }
}
