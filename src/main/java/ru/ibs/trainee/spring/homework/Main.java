package ru.ibs.trainee.spring.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import ru.ibs.trainee.spring.homework.entity.Car;
import ru.ibs.trainee.spring.homework.entity.Engine;
import ru.ibs.trainee.spring.homework.entity.Gear;
import ru.ibs.trainee.spring.homework.entity.SteeringWheel;
import ru.ibs.trainee.spring.homework.repositories.CarRepository;
import ru.ibs.trainee.spring.homework.repositories.EngineRepository;
import ru.ibs.trainee.spring.homework.repositories.GearsRepository;
import ru.ibs.trainee.spring.homework.repositories.SteeringWheelRepository;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories
public class Main {


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("password");
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/trainee");
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

        List<Gear> gears = new ArrayList<>();
        Gear gear1 = new Gear(null, 5);
        Gear gear2 = new Gear(null, 6);
        Gear gear3 = new Gear(null, 7);
        gears.add(gear1);
        gears.add(gear2);
        gears.add(gear3);

        Car car8 = new Car(null, "Lada", "Largus", new SteeringWheel(null, "eco")
        , new Engine(null, "petrol"), gears);
        carRepository.save(car1);
        carRepository.save(car2);
        carRepository.save(car3);
        carRepository.save(car4);
        carRepository.save(car5);
        carRepository.save(car6);
        carRepository.save(car7);
        carRepository.save(car8);






    }

}
