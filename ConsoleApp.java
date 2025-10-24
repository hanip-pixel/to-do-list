import java.util.List;
import java.util.Scanner;

public class ConsoleApp {
    
    private ToDoList taskList;
    private Scanner scanner;

    public ConsoleApp() {
        this.taskList = new ToDoList();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println(" Selamat datang di Aplikasi To-Do List Konsol (Java) ");
        String command;

        do {
            displayMenu();
            System.out.print("\nMasukkan perintah: ");
            command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "tambah":
                    handleAddTask();
                    break;
                case "lihat":
                    handleViewTasks(taskList.getAllTasks(), "SEMUA TUGAS");
                    break;
                case "selesai":
                    handleMarkComplete();
                    break;
                case "hapus":
                    handleDeleteTask();
                    break;
                case "filter":
                    handleFilterTasks();
                    break;
                case "prioritas":
                    handleViewTasks(taskList.getTasksSortedByPriority(), "TUGAS DIURUTKAN BERDASARKAN PRIORITAS");
                    break;
                case "keluar":
                    System.out.println(" Terima kasih telah menggunakan aplikasi. Sampai jumpa!");
                    break;
                default:
                    System.out.println(" Perintah tidak dikenal. Ketik 'menu' untuk melihat daftar perintah.");
            }
        } while (!command.equals("keluar"));
        
        scanner.close();
    }

    private void displayMenu() {
        System.out.println("\n-------------------------------------------");
        System.out.println("Pilih Perintah:");
        System.out.println("tambah     : Tambahkan tugas baru");
        System.out.println("lihat      : Lihat semua tugas");
        System.out.println("selesai    : Tandai tugas sebagai selesai (berdasarkan ID)");
        System.out.println("hapus      : Hapus tugas (berdasarkan ID)");
        System.out.println("filter     : Filter tugas berdasarkan status (selesai/belum)");
        System.out.println("prioritas  : Lihat tugas diurutkan berdasarkan prioritas");
        System.out.println("keluar     : Keluar dari aplikasi dan simpan data");
        System.out.println("-------------------------------------------");
    }

    private void handleAddTask() {
        System.out.print("Masukkan deskripsi tugas: ");
        String desc = scanner.nextLine();
        
        String priority;
        do {
            System.out.print("Masukkan prioritas (Tinggi/Sedang/Rendah): ");
            priority = scanner.nextLine().trim();
            priority = priority.substring(0, 1).toUpperCase() + priority.substring(1).toLowerCase();
        } while (!priority.matches("Tinggi|Sedang|Rendah"));

        taskList.addTask(desc, priority);
    }

    private void handleViewTasks(List<Task> tasks, String title) {
        System.out.println("\n=== " + title + " ===");
        if (tasks.isEmpty()) {
            System.out.println(" Daftar tugas kosong.");
        } else {
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }

    private void handleMarkComplete() {
        System.out.print("Masukkan ID tugas yang sudah selesai: ");
        if (scanner.hasNextInt()) {
            int id = scanner.nextInt();
            scanner.nextLine();
            
            if (taskList.markTaskCompleted(id)) {
                System.out.println(" Tugas ID " + id + " berhasil ditandai Selesai.");
            } else {
                System.out.println(" Tugas ID " + id + " tidak ditemukan atau sudah selesai.");
            }
        } else {
            System.out.println(" Input ID tidak valid.");
            scanner.nextLine();
        }
    }

    private void handleDeleteTask() {
        System.out.print("Masukkan ID tugas yang akan dihapus: ");
        if (scanner.hasNextInt()) {
            int id = scanner.nextInt();
            scanner.nextLine();
            
            if (taskList.deleteTask(id)) {
                System.out.println(" Tugas ID " + id + " berhasil dihapus.");
            } else {
                System.out.println(" Tugas ID " + id + " tidak ditemukan.");
            }
        } else {
            System.out.println(" Input ID tidak valid.");
            scanner.nextLine();
        }
    }

    private void handleFilterTasks() {
        System.out.print("Filter berdasarkan status ('selesai' atau 'belum'): ");
        String status = scanner.nextLine().trim().toLowerCase();
        
        List<Task> filteredTasks;
        String title;
        
        if (status.equals("selesai")) {
            filteredTasks = taskList.getFilteredTasks(true);
            title = "TUGAS YANG SUDAH SELESAI";
        } else if (status.equals("belum")) {
            filteredTasks = taskList.getFilteredTasks(false);
            title = "TUGAS YANG BELUM SELESAI";
        } else {
            System.out.println(" Status filter tidak valid.");
            return;
        }

        handleViewTasks(filteredTasks, title);
    }

    public static void main(String[] args) {
        ConsoleApp app = new ConsoleApp();
        app.run();
    }
}