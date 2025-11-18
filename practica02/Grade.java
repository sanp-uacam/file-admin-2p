import java.io.Serializable;

public class Grade implements Serializable {

    private int id;
    private String studentName;
    private double score;

    private String subject;
    private Status status;
    private long timestamp;

    public Grade(int id, String studentName, double score, String subject, Status status) {
        this.id = id;
        this.studentName = studentName;
        this.score = score;
        this.subject = subject;
        this.status = status;
        this.timestamp = System.currentTimeMillis();
    }

    public int getId() { return id; }

   @Override
public String toString() {
    return "Grade[" +
            "id=" + id +
            ", studentName='" + studentName + "'" +
            ", score=" + score +
            ", subject='" + subject + "'" +
            ", status=" + status +
            ", timestamp=" + timestamp +
            "]";
}


    public String toJson() {
        return "{\n" +
                "  \"id\": " + id + ",\n" +
                "  \"studentName\": \"" + studentName + "\",\n" +
                "  \"score\": " + score + ",\n" +
                "  \"subject\": \"" + subject + "\",\n" +
                "  \"status\": \"" + status + "\",\n" +
                "  \"timestamp\": " + timestamp + "\n" +
                "}";
    }
}
