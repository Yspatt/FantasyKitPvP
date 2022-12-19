package fantasynetwork.fantasykitpvp.service;

import java.util.List;
import java.util.Optional;

public interface Service<T, K> {

    List<T> all();
    Optional<T> get(K key);
    void remove(K key);

    void init();
    void stop();
    void run();

}