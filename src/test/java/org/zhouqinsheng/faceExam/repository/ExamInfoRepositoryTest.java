package org.zhouqinsheng.faceExam.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExamInfoRepositoryTest {

	@Autowired
	private IExamInfoRepository examInfoRepository;

	@Test
	public void test(){
		int num =1;
		for (int i = 0; i < 100; i++) {

			num=num+num++;
		}
		System.out.println(num);
	}
}
