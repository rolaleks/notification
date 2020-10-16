package ru.geekbrains.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    String taskId;
    String city;
    String county;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return city.equals(task.city) &&
                county.equals(task.county);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, county);
    }

}
