package model.repositories;

import controller.exceptions.RepositoryException;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.entities.User;
import fillers.DataFiller;
import fillers.StaticUserFiller;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Data
@NoArgsConstructor
public class UserRepository implements Repository<User, UUID> { // todo write methods getBy...

    private List<User> people;

    @Override
    public void update(UUID id, User element) {
        synchronized (this.people) {
            for (int i = 0; i < people.size(); i++) {
                if (id.equals(people.get(i).getUuid())) {
                    this.people.set(i, element);
                }
            }
        }
    }

    @Override
    public void add(User element) throws RepositoryException {
        synchronized (this.people) {
            for (User user : people) {
                if (user.equals(element))
                    throw new RepositoryException("This client already exists");
            }
            this.people.add(element);
        }
    }

    @Override
    public void remove(User element) throws RepositoryException {
        synchronized (this.people) {
            for (User user : people) {
                if (user.equals(element)) {
                    this.people.remove(element);
                }
            }
            throw new RepositoryException("This client doesn't exist");
        }
    }

    @Override
    public List<User> getAll() {
        synchronized (this.people) {
            return new CopyOnWriteArrayList<>(people);
        }
    }


    @PostConstruct
    public void initPeople() {
        DataFiller dataFiller = new StaticUserFiller();
        this.people = dataFiller.Fill();
    }

}
