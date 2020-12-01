package model.repositories;

import controller.exceptions.RepositoryException;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.entities.Person;
import fillers.DataFiller;
import fillers.StaticPersonFiller;

import javax.annotation.PostConstruct;
import java.util.List;

@Data
@NoArgsConstructor
public class PersonRepository implements Repository<Person> {

    private List<Person> people;

    public PersonRepository(List<Person> people) {
        this.people = people;
    }

    @Override
    public void add(Person element) throws RepositoryException {
        for (Person person : people) {
            if (person.equals(element))
                throw new RepositoryException("This client already exists");
        }
        this.people.add(element);
    }

    @Override
    public void remove(Person element) throws RepositoryException {
        for (Person person : people) {
            if (person.equals(element)) {
                this.people.remove(element);
            }
        }
        throw new RepositoryException("This client doesn't exist");
    }

    @Override
    public List<Person> getAll() {
        return people;
    }


     @PostConstruct
     private void initPeople() {
         DataFiller dataFiller = new StaticPersonFiller();
         this.people = dataFiller.Fill();
     }

}
