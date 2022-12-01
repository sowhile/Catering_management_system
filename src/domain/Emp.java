package domain;

/**
 * @author sowhile
 * <p>
 * 2022/12/1 10:04
 */
public class Emp {
    private Integer id;
    private String user;
    private String passwd;
    private String name;
    private String job;

    public Emp() {
    }

    public Emp(Integer id, String user, String passwd, String name, String job) {
        this.id = id;
        this.user = user;
        this.passwd = passwd;
        this.name = name;
        this.job = job;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", passwd='" + passwd + '\'' +
                ", name='" + name + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
