package com.zfl19.org.mapper;

import com.zfl19.PetHomeApp;
import com.zfl19.org.domain.Department;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PetHomeApp.class)
public class DepartmentMapperTest {

    @Autowired
    private DepartmentMapper mapper;

    @Test
    public void test() {
        List<Department> departments = mapper.selectAll();
        departments.stream().forEach(System.out::println);
    }

}