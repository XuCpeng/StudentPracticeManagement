package cn.medemede.j2ee.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RolePermRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void Test1(){
        System.out.println(roleRepository.findOne(1));
    }

}