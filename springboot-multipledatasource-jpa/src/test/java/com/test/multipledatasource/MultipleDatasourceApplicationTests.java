package com.test.multipledatasource;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.multipledatasource.beana.BeanA;
import com.test.multipledatasource.beana.BeanAJPARepository;
import com.test.multipledatasource.beanb.BeanB;
import com.test.multipledatasource.beanb.BeanBJPARepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultipleDatasourceApplicationTests {

	@Autowired
	BeanAJPARepository srva ;
	
	@Autowired
	BeanBJPARepository srvb ;
	
	@Test
	public void contextLoads() {
	}
	
	@Transactional
	@Test
	public void testInsert() throws Exception{
		BeanA a = new BeanA();
		a.setId(9L);
		a.setName("beanA 1111");
		srva.save(a);
		
		int i = 2/0;
		
		a = new BeanA();
		a.setId(10L);
		a.setName("beanA 1111");
		srva.save(a);
		
		BeanB b = new BeanB();
		b.setId(1L);
		b.setName("Beanb");
		srvb.save(b);
	}

}
