package ru.ibs.trainee.spring.homework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import ru.ibs.trainee.spring.homework.entity.*;
import ru.ibs.trainee.spring.homework.repositories.CarRepository;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories
public class Main {
    @Autowired
    private Environment env;


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        driverManagerDataSource.setUsername(env.getProperty("spring.datasource.username"));
        driverManagerDataSource.setPassword(env.getProperty("spring.datasource.password"));
        driverManagerDataSource.setUrl(env.getProperty("spring.datasource.url"));
        return driverManagerDataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("ru/ibs/trainee/spring/homework/entity");
        factory.setDataSource(dataSource());
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);
        CarRepository carRepository = context.getBean(CarRepository.class);

        List<Gear> gears = new ArrayList<>();
        List<Car> cars = new ArrayList<>();

        Manual manual1 = new Manual("electric");
        Manual manual2 = new Manual("engine");
        Manual manual3 = new Manual("body");

        Car car1 = new Car(null, "Ford", "Fiesta");
        Car car2 = new Car(null, "Lada", "Priora");
        Car car3 = new Car(null, "Ford", "Transit");
        Car car4 = new Car(null, "Audi", "A6", new SteeringWheel(null, "leather"));
        Car car5 = new Car(null, "Audi", "A5",
                new SteeringWheel(null, "leather"), new Engine(null, "petrol"));
        Car car6 = new Car(null, "Mercedes", "s600",
                new SteeringWheel(null, "leather"), new Engine(null, "petrol"));
        Car car7 = new Car(null, "Opel", "Astra",
                new SteeringWheel(null, "eco"), new Engine(null, "diesel"));
        Car car8 = new Car(null, "Lada", "Largus", new SteeringWheel(null, "eco")
                , new Engine(null, "petrol"), gears);
        Car car9 = new Car(null, "Lada", "Largus", new SteeringWheel(null, "eco")
                , new Engine(null, "petrol"), gears);
        Car car10 = new Car(null, "Lada", "Largus", new SteeringWheel(null, "eco")
                , new Engine(null, "petrol"), gears);

        Gear gear1 = new Gear(null, 5);
        Gear gear2 = new Gear(null, 6);
        Gear gear3 = new Gear(null, 7);
        gears.add(gear1);
        gears.add(gear2);
        gears.add(gear3);


        car8.addManual(manual1);
        car9.addManual(manual2);
        car10.addManual(manual3);
        cars.add(car8);
        cars.add(car9);
        cars.add(car10);

        carRepository.save(car1);
        carRepository.save(car2);
        carRepository.save(car3);
        carRepository.save(car4);
        carRepository.save(car5);
        carRepository.save(car6);
        carRepository.save(car7);
        carRepository.saveAll(cars);






    }

}
