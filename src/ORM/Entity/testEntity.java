package ORM.Entity;

import javax.persistence.*;

@Entity
@Table(name = "test", schema = "testschema")
public class testEntity {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

}
