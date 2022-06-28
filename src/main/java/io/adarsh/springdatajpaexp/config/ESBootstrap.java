package io.adarsh.springdatajpaexp.config;

import io.adarsh.springdatajpaexp.model.KYATest;
import io.adarsh.springdatajpaexp.model.Test;
import io.adarsh.springdatajpaexp.modelES.College;
import io.adarsh.springdatajpaexp.modelES.Person;
import io.adarsh.springdatajpaexp.reposES.CollegeRepository;
import io.adarsh.springdatajpaexp.reposES.KYATestRepository;
import io.adarsh.springdatajpaexp.reposES.PersonRepository;
import io.adarsh.springdatajpaexp.reposES.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ESBootstrap implements ApplicationRunner {

    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private KYATestRepository kyaTestRepository;

    @Autowired
    private TestRepository testRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Person person1 = new Person();
        person1.setId("1");
        person1.setName("Adarsh");
        person1.setConcatenatedYearMonth("202005");
//        person1.setYear("2020");
//        person1.setMonth("05");

        Person person2 = new Person();
        person2.setId("2");
        person2.setName("Chicky");
        person2.setConcatenatedYearMonth("202005");
//        person2.setYear("2020");
//        person2.setMonth("05");

        Person person6 = new Person();
        person6.setId("6");
        person6.setName("Chicky");
        person6.setConcatenatedYearMonth("202004");
//        person6.setYear("2020");
//        person6.setMonth("04");

        Person person7 = new Person();
        person7.setId("7");
        person7.setName("Chicky");
        person7.setConcatenatedYearMonth("201904");
//        person7.setYear("2019");
//        person7.setMonth("04");

        Person person8 = new Person();
        person8.setId("8");
        person8.setName("Harsh");
        person8.setConcatenatedYearMonth("202008");
//        person8.setYear("2020");
//        person8.setMonth("08");



        Person person3 = new Person();
        person3.setName("Shubham");
        person3.setConcatenatedYearMonth("201904");
//        person3.setYear("2019");
//        person3.setMonth("04");

        Person person4 = new Person();
        person4.setName("Ankit");
        person4.setConcatenatedYearMonth("201904");
//        person4.setYear("2019");
//        person4.setMonth("04");

        Person person5 = new Person();
        person5.setName("Tushar");
        person5.setConcatenatedYearMonth("202205");
//        person5.setYear("2022");
//        person5.setMonth("05");

//        KYATest kyaTest1 = new KYATest();
//        kyaTest1.setId(1);
//        kyaTest1.setName("Test1");


        College college = new College();
        college.setId("3");
        college.setName("TIPS");
        college.setCountry("India");

        college.addPerson(person1);
        college.addPerson(person2);

        Test test = new Test();
        test.setId("1");
        test.addCollege(college);
        test.addPerson(person1);
        test.addPerson(person2);

//        college.addKyaTest(kyaTest1);

        personRepository.saveAll(List.of(person1, person2, person3, person4, person5, person6, person7, person8));
//        kyaTestRepository.save(kyaTest1);
        collegeRepository.save(college);

        testRepository.save(test);

//        personRepository.deleteAll();

    }
}
