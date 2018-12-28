package sec.project.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Event extends AbstractPersistable<Long> {

    private String name;

    @OneToMany
    private List<Signup> signups = new ArrayList<>();
    
    @Column(columnDefinition = "MEDIUMTEXT")
    private String comments = "";

    public Event() {
        super();
    }

    public Event(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Signup> getSignups() {
        return signups;
    }

    public void setSignups(List<Signup> signups) {
        this.signups = signups;
    }

    public String getComments() {
        return comments;
    }

    public void addComment(String comment) {
        this.comments += comment + "<br>" + "--------" + "<br>";
    }

}
