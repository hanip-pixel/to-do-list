public class Task {

    private int taskId;
    private String description;
    private String priority;
    private boolean isCompleted;


    private static int nextId = 1;


    public Task(String description, String priority) {

        this.taskId = nextId++;
        this.description = description;
        this.priority = priority;
        this.isCompleted = false;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void markCompleted() {
        this.isCompleted = true;
    }

    @Override
    public String toString() {
        String status = isCompleted ? " Selesai" : " Belum Selesai";
        return String.format("[%d] %s (Prio: %s) - %s", 
                             taskId, description, priority, status);
    }
}