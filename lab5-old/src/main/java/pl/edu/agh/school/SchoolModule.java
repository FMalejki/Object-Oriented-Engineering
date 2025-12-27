package pl.edu.agh.school;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.multibindings.ProvidesIntoSet;
import com.google.inject.name.Named;

import pl.edu.agh.logger.ConsoleMessageSerializer;
import pl.edu.agh.logger.FileMessageSerializer;
import pl.edu.agh.logger.IMessageSerializer;
import pl.edu.agh.school.persistence.SerializablePersistenceManager;

public class SchoolModule extends AbstractModule {

    @Provides
    public IPersistenceManager providePersistenceManager(SerializablePersistenceManager persistenceManager) {
        return persistenceManager;
    }

    @Provides
    @Named("teacherStorage")
    public String provideTeachersStorageName() {
        return "teachers2.dat";
    }
    
    @Provides
    @Named("classesStorage")
    public String provideClassesStorageName() {
        return "classes2.dat";
    }

    @Provides
    @Named("logFilename")
    public String provideLogFilename() { return "persistence.log"; }

    @ProvidesIntoSet
    public IMessageSerializer provideFileMessageSerializer(FileMessageSerializer serializer) {
        return serializer;
    }

    @ProvidesIntoSet
    public IMessageSerializer provideConsoleMessageSerializer(ConsoleMessageSerializer consoleMessageSerializer) {
        return consoleMessageSerializer;
    }
}
