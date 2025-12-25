package pl.edu.agh.school.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoSet;
import pl.edu.agh.logger.ConsoleMessageSerializer;
import pl.edu.agh.logger.FileMessageSerializer;
import pl.edu.agh.logger.IMessageSerializer;
import pl.edu.agh.logger.Logger;

public class LoggerModule extends AbstractModule {
    protected void configure() {
        bind(Logger.class).toProvider(LoggerProvider.class).in(Singleton.class);
    }

    @ProvidesIntoSet
    protected IMessageSerializer provideConsoleMessageSerializer() {
        return new ConsoleMessageSerializer();
    }

    @ProvidesIntoSet
    protected IMessageSerializer provideFileMessageSerializer() {
        return new FileMessageSerializer("persistence.log");
    }
}
