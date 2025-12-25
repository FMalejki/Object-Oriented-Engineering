package pl.edu.agh.school.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import pl.edu.agh.school.persistence.IPersistenceManager;
import pl.edu.agh.school.persistence.SerializablePersistenceManager;

public class SchoolModule extends AbstractModule {
    @Provides
    public IPersistenceManager providePersistenceManager(SerializablePersistenceManager serializablePersistenceManager) {
        return serializablePersistenceManager;
    }

    @Provides
    @Named("teachersStorage")
    public String provideTeachersStorageFileName() {
        return "guice-teachers.dat";
    }

    @Provides
    @Named("classStorage")
    public String provideClassStorageFileName() {
        return "guice-classes.dat";
    }
}
