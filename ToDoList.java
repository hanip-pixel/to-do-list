import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ToDoList {

    private List<Task> tasks;

    public ToDoList() {
        
        this.tasks = new ArrayList<>();
    }


    public void addTask(String description, String priority) {
        
        Task newTask = new Task(description, priority);
        tasks.add(newTask);
        System.out.println(" Tugas berhasil ditambahkan dengan ID: " + newTask.getTaskId());
    }

    public List<Task> getAllTasks() {
        
        return new ArrayList<>(tasks);
    }

    public Task findTaskById(int taskId) {

        for (Task task : tasks) {
            if (task.getTaskId() == taskId) {
                return task;
            }
        }
        return null;
    }

    public boolean markTaskCompleted(int taskId) {
        Task task = findTaskById(taskId);
        if (task != null) {
            if (!task.isCompleted()) {
                task.markCompleted();
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean deleteTask(int taskId) {
        Task task = findTaskById(taskId);
        if (task != null) {
            tasks.remove(task);
            return true;
        }
        return false;
    }

    public List<Task> getFilteredTasks(boolean isCompleted) {
        
        return tasks.stream()
                     .filter(task -> task.isCompleted() == isCompleted)
                     .collect(Collectors.toList());
    }

    public List<Task> getTasksSortedByPriority() {
        List<Task> sortedTasks = new ArrayList<>(tasks);
        
        Comparator<Task> priorityComparator = (t1, t2) -> {
            int p1 = getPriorityValue(t1.getPriority());
            int p2 = getPriorityValue(t2.getPriority());
            return Integer.compare(p2, p1);
        };

        sortedTasks.sort(priorityComparator.thenComparing(Task::getTaskId));

        return sortedTasks;
    }
    
    private int getPriorityValue(String priority) {
        return switch (priority.toLowerCase()) {
            case "tinggi" -> 3;
            case "sedang" -> 2;
            case "rendah" -> 1;
            default -> 0;
        };
    }
}